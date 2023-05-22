package com.example.spotifly;

public class CurrentMusic {
    String rawID,title,author,duration;

    public CurrentMusic(String rawID, String title, String author, String duration){
        this.rawID = rawID;
        this.title = title;
        this.author = author;
        this.duration = duration;
    }
}
