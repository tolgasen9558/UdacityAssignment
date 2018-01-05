package com.example.android.udacitytest.Utility;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private final static String DEBUG_TAG = "NetworkUtils";
    public final static String CARD_DATA_URL = "https://s3-us-west-2.amazonaws.com/" +
            "udacity-mobile-interview/CardData.json";

    public static String downloadCardData(String myURL) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Start the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.i(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            StringBuilder fullString = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                fullString.append(line).append('\n');
            }

            return fullString.toString();
        }
        catch(Exception e) {
            Log.i(DEBUG_TAG, e.toString());
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    public static Bitmap downloadCardAvatar(String iconURL) throws IOException {
        Bitmap bitmap = null;
        InputStream is = null;

        try {
            // Download Image from URL
            is = new URL(iconURL).openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.i(DEBUG_TAG, e.toString());
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
        return bitmap;
    }

    public static boolean checkNetworkConnection(Activity context){
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
