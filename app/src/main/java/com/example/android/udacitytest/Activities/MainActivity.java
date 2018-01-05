package com.example.android.udacitytest.Activities;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.udacitytest.Adapters.PersonListAdapter;
import com.example.android.udacitytest.DataModels.Person;
import com.example.android.udacitytest.Utility.NetworkUtils;
import com.example.android.udacitytest.R;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String DEBUG_TAG = "MainActivity";

    private List<Person> peopleList;

    private ProgressBar progressBar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleList = new ArrayList<>();
        new DownloadPersonDataTask().execute(NetworkUtils.CARD_DATA_URL);

        mRecyclerView = findViewById(R.id.mainactivity_recycler_view);
        progressBar = findViewById(R.id.mainactivity_progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PersonListAdapter(peopleList);
        mRecyclerView.setAdapter(mAdapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private class DownloadPersonDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return NetworkUtils.downloadCardData(urls[0]);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            //Check if the response is not null
            if(response == null || response.contentEquals("")){
                Log.i(DEBUG_TAG, "returned respons is null");
                Toast.makeText(getApplicationContext(), "Could not get any " +
                                "response from server.", Toast.LENGTH_SHORT).show();
                return;
            }

            //If the response is okay, parse the data and update list
            JSONArray jsonArray;

            try {
                jsonArray = new JSONArray(response);
                for(int i = 0; i < jsonArray.length(); i++) {
                    Person person = new Person(jsonArray.getJSONObject(i));
                    peopleList.add(person);
                    new DownloadAvatarTask().execute(person);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class DownloadAvatarTask extends AsyncTask<Person, Void, Bitmap>{

        Person person;

        @Override
        protected Bitmap doInBackground(Person... people) {
            try {
                this.person = people[0];
                return NetworkUtils.downloadCardAvatar(people[0].getAvatarURL());
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                person.setAvatarBMP(bitmap);

                mAdapter.notifyDataSetChanged();
                if (progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                }
            }
            else{
                Log.i(DEBUG_TAG, "returned bitmap is null");
                Toast.makeText(getApplicationContext(), "Could not download avatar from " +
                        "server", Toast.LENGTH_LONG).show();
            }
        }
    }
}
