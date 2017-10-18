package com.example.finalhwnine.Model;

import java.io.Serializable;

/**
 * Created by User on 4/27/2017.
 */

public class Album implements Serializable {

    private String name;
    private String image_one;
    private String image_two;

    public String getImage_two() {
        return image_two;
    }

    public void setImage_two(String image_two) {
        this.image_two = image_two;
    }

    public String getImage_one() {
        return image_one;
    }

    public void setImage_one(String image_one) {
        this.image_one = image_one;
    }

    public Album(String name, String image_one,String image_two) {
        this.name = name;
        this.image_one=image_one;
        this.image_two = image_two;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


}
