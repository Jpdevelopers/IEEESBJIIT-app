package com.dev.ieeesbjiit.ieeesbjiit;

/**
 * Created by yugank on 24/9/17.
 */

public class userInfo {
    String name;
    String email;
    String enroll;
    String phone;
    String accomodation;

    public userInfo(String name, String email, String phone, String accomodation) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.accomodation = accomodation;

    }

    public userInfo(String name, String email, String enroll, String phone, String accomodation) {

        this.name = name;
        this.email = email;
        this.enroll = enroll;
        this.phone = phone;
        this.accomodation = accomodation;
    }

    public userInfo() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEnroll() {
        return enroll;
    }

    public String getPhone() {
        return phone;
    }

    public String getAccomodation() {
        return accomodation;
    }


}
