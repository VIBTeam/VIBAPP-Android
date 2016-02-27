package com.makers.vibapp.data.model;

import android.support.graphics.drawable.VectorDrawableCompat;

/**
 * Created by Eliseo on 26/02/2016.
 */
public class VibSoundType {

    int imageResource;
    String title;

    public VibSoundType(int imageResource, String title) {
        this.imageResource = imageResource;
        this.title = title;
    }

    public int getImage() {
        return imageResource;
    }

    public void setImage(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}