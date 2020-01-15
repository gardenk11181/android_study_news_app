package com.example.newsapp;

import java.io.Serializable;

public class NewsData implements Serializable { //복잡한 데이터 구조를 잘 전달하기 위함.
    private String title;
    private String urlToImage;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
