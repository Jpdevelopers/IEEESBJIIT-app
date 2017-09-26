package com.dev.ieeesbjiit.ieeesbjiit;

/**
 * Created by yugank on 25/9/17.
 */

public class EventClass {
    private String event_name;
    private String date;
    private String venue;
    private String info;
    private String time;
    private int registrations=0;

    public EventClass() {
    }

    public EventClass(String event_name, String date, String venue, String info) {
        this.event_name = event_name;
        this.date = date;
        this.venue = venue;
        this.info = info;

    }

    public EventClass(String event_name, String date, String venue, String info, String time, int registrations) {
        this.event_name = event_name;
        this.date = date;
        this.venue = venue;
        this.info = info;
        this.time = time;
        this.registrations= registrations;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getinfo() {
        return info;
    }

    public void setinfo(String info) {
        this.info = info;
    }

    public int getRegistrations() {
        return registrations;
    }

    public void setRegistrations(int registrations) {
        this.registrations = registrations;
    }
}
