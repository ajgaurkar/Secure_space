package com.saipc.ashish.instareplica;


import java.util.ArrayList;

public class ImageObject {

    private String name;
    private String url;
    private boolean isLiked;
    private String caption;
    private String hashtags;
    private ArrayList<String> comment_list;
    private ArrayList<String> post_img_urls;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public ArrayList<String> getComment_list() {
        return comment_list;
    }

    public void setComment_list(ArrayList<String> comment_list) {
        this.comment_list = comment_list;
    }

    public ArrayList<String> getPost_img_urls() {
        return post_img_urls;
    }

    public void setPost_img_urls(ArrayList<String> post_img_urls) {
        this.post_img_urls = post_img_urls;
    }
}
