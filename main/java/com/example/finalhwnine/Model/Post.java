package com.example.finalhwnine.Model;

import java.io.Serializable;

/**
 * Created by User on 4/27/2017.
 */

public class Post implements Serializable {
    private String content;

    public Post(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
