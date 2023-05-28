package com.example.spotifly;

public class CurrentMusic {
    String rawID,title,author,duration;
    Integer id;

    public CurrentMusic(Integer id, String rawID, String title, String author, String duration){
        this.id = id;
        this.rawID = rawID;
        this.title = title;
        this.author = author;
        this.duration = duration;
    }
}
