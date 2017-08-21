package com.sachinstarwings.learning.destination.models;

/**
 * Created by AKASH on 07-07-2017.
 */

public class TopicData {
    private String title,author,date,link;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getLink() {
        return link;
    }

    public TopicData(String title, String author, String date, String link) {

        this.title = title;
        this.author = author;
        this.date = date;
        this.link = link;
    }
}
