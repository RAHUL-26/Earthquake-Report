package com.rahulkumaryadav.earthquake;

public class Earthquake {
    private String magnitude,location,date,url;

    public Earthquake(String magnitude, String location, String date,String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
        this.url = url;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
