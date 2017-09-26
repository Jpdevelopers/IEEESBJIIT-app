package com.dev.ieeesbjiit.ieeesbjiit;

/**
 * Created by yugank on 26/9/17.
 */

public class MemberClass {
    private String photourl;
    private int url;
    private  String member_name;
    private  String member_post;
    private  String member_email;
    private  String member_work;

    public MemberClass(String member_name, String member_post, String member_email, String member_work) {
        this.member_name = member_name;
        this.member_post = member_post;
        this.member_email = member_email;
        this.member_work = member_work;
    }

    public MemberClass(String member_name, String member_post, String member_email, String member_work, String photourl) {
        this.member_name = member_name;
        this.member_post = member_post;
        this.member_email = member_email;
        this.photourl= photourl;
        this.member_work= member_work;
    }
    public MemberClass(String member_name, String member_post, int url) {
        this.member_name = member_name;
        this.member_post = member_post;
        this.url= url;
    }

    public MemberClass() {
    }

    public String getPhotourl() {

        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_post() {
        return member_post;
    }

    public void setMember_post(String member_post) {
        this.member_post = member_post;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public String getMember_work() {
        return member_work;
    }

    public void setMember_work(String member_work) {
        this.member_work = member_work;
    }
}
