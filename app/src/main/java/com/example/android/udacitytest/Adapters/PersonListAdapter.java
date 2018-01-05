package com.example.android.udacitytest.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.udacitytest.DataModels.Person;
import com.example.android.udacitytest.Utility.DateUtils;
import com.example.android.udacitytest.R;
import com.example.android.udacitytest.Utility.TextUtils;

import java.util.List;
import java.util.Locale;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.ViewHolder> {

    private List<Person> mDataset;

    public PersonListAdapter(List<Person> cardsList) {
        mDataset = cardsList;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,
                parent, false);
        return new ViewHolder(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTV;
        public TextView emailTV;
        public TextView companyNameTV;
        public TextView startDateTV;
        public TextView bioTV;
        public ImageView avatarIV;

        public ViewHolder(View v) {
            super(v);

            nameTV = itemView.findViewById(R.id.cardview_name_tv);
            emailTV = itemView.findViewById(R.id.cardview_email_tv);
            companyNameTV = itemView.findViewById(R.id.cardview_companyname_tv);
            startDateTV = itemView.findViewById(R.id.cardview_startdate_tv);
            bioTV = itemView.findViewById(R.id.cardview_bio_tv);
            avatarIV = itemView.findViewById(R.id.cardview_avatar_IV);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = mDataset.get(position);
        holder.nameTV.setText(String.format(Locale.US, "%s %s",
                person.getFirstName(), person.getLastName()));
        holder.emailTV.setText(person.geteMail());
        holder.companyNameTV.setText(person.getCompanyName());
        holder.startDateTV.setText(DateUtils.getDateStringFromDate(person.getStartDate()));
        holder.bioTV.setText(TextUtils.getStyledString(person.getBio()));
        holder.avatarIV.setImageBitmap(person.getAvatarBMP());
    }
}
