package com.example.group.ondemandhomerepair;

public class Rating {

    private float rating;
    private String comment;

    public Rating(float r, String c){
        this.rating = r;
        this.comment = c;
    }

    public float getRating(){
        return this.rating;
    }

    public String getComment(){
        return this.comment;
    }

    public void setRating(float r){
        this.rating = r;
    }

    public void setComment(String c){
        this.comment = c;
    }
}
