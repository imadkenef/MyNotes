package com.example.mynotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    void insert(NotesEntity notes);

    @Delete
    void delete(NotesEntity notes);


    @Query("SELECT * FROM Note_table")
    LiveData<List<NotesEntity>> getAllNotes();

    @Query("UPDATE Note_table SET favorite = :isFavorite WHERE id = :noteId")
    void updateNoteFavoriteStatus(int noteId,  Boolean isFavorite);
}
