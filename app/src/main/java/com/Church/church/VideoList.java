package com.Church.church;

public class VideoList {
    private String date;
    private String title;
    private String preacher;
    private String thumbnail;
    private String url;

    public VideoList() {
    }

    public String getPreacher() { return preacher; }

    public void setPreacher(String preacher) { this.preacher = preacher; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}