package com.dev.ieeesbjiit.ieeesbjiit;

/**
 * Created by yugank on 27/9/17.
 */

public class SponsorClass {
    String photourl;
    String name;
    String category;

    public SponsorClass(String photourl, String name, String category) {
        this.photourl = photourl;
        this.name = name;
        this.category = category;
    }

    public SponsorClass() {
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
