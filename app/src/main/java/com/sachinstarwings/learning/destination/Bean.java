package com.sachinstarwings.learning.destination;


/**
 * Created by apple on 01/04/16.
 */
public class Bean {


    private int newsimage1;
    private String imgurl;
    private int more;
    private String newsname;
    private String time;
    private String news;
    private String newssub;
    private String url;
    private String author;

    public Bean(int newsimage1, String imgurl, int more, String newsname, String time, String news, String newssub, String url,String author) {

        this.newsimage1 = newsimage1;
        this.imgurl = imgurl;
        this.more = more;
        this.newsname = newsname;
        this.time = time;
        this.news = news;
        this.newssub = newssub;
        this.url = url;
        this.author=author;
    }

    public int getNewsimage1() {
        return newsimage1;
    }

    public void setNewsimage1(int newsimage1) {
        this.newsimage1 = newsimage1;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public String getNewsname() {
        return newsname;
    }

    public void setNewsname(String newsname) {
        this.newsname = newsname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getNewssub() {
        return newssub;
    }

    public void setNewssub(String newssub) {
        this.newssub = newssub;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAuthor(String author) {
        this.author= author;
    }

    public String getAuthor() {
        return author;
    }

}