package com.example.android.udacitytest.DataModels;

import android.graphics.Bitmap;

import com.example.android.udacitytest.Utility.DateUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;


public class Person {

    private class TokenNames{
        private static final String LAST_NAME = "lastName";
        private static final String FIRST_NAME = "firstName";
        private static final String EMAIL = "email";
        private static final String COMPANY_NAME = "company";
        private static final String START_DATE = "startDate";
        private static final String AVATAR = "avatar";
        private static final String BIO = "bio";
    }

    private String lastName;
    private String firstName;
    private String eMail;
    private String companyName;
    private Date startDate;
    private String bio;
    private String avatarURL;
    private Bitmap avatarBMP;


    public Person(JSONObject jsonObject) {
        //Parse from JSONObject
        try {
            this.lastName = jsonObject.getString(TokenNames.LAST_NAME);
            this.firstName = jsonObject.getString(TokenNames.FIRST_NAME);
            this.eMail = jsonObject.getString(TokenNames.EMAIL);
            this.companyName = jsonObject.getString(TokenNames.COMPANY_NAME);
            this.startDate = DateUtils.parseDateFromISO8601(
                    jsonObject.getString(TokenNames.START_DATE));
            this.bio = jsonObject.getString(TokenNames.BIO);
            this.avatarURL = jsonObject.getString(TokenNames.AVATAR);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Person(String lastName, String firstName, String eMail, String companyName,
                  Date startDate, String bio, String avatarURL) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.eMail = eMail;
        this.companyName = companyName;
        this.startDate = startDate;
        this.bio = bio;
        this.avatarURL = avatarURL;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public Bitmap getAvatarBMP() {
        return avatarBMP;
    }

    public void setAvatarBMP(Bitmap avatarBMP) {
        this.avatarBMP = avatarBMP;
    }
}
