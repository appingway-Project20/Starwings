package com.example.admin.starwingsapp;

/**
 * Created by AKASH on 21-06-2017.
 */

public class NotificationData {
    String title;
    String details;

    public NotificationData(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }
}
