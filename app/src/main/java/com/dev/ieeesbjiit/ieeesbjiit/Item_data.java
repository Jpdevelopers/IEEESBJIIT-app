package com.dev.ieeesbjiit.ieeesbjiit;

/**
 * Created by yugank on 21/9/17.
 */

public class Item_data {
    private int pos;
    private String title;
    private int url;
    private String subtitle;


    public Item_data(int pos, String title, int url, String subtitle) {
        this.pos = pos;
        this.title = title;
        this.url = url;
        this.subtitle = subtitle;
    }
    public int getPos() {
        return pos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
