package com.example.mynotes;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Note_table")
public class NotesEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String content;
    private String date;

    private boolean favorite;


    public NotesEntity() {
    }

    public NotesEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NotesEntity(String title, String content, String date, boolean favorite) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
