package com.example.mynotes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NotesEntity.class},version = 1)
public abstract class DatabaseNotes extends RoomDatabase {
    public abstract NotesDao getNotesDao();

    //Singleton Database
    private static DatabaseNotes dbInstance;

    public static synchronized DatabaseNotes getInstance(Context context) {
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    DatabaseNotes.class,
                    "Note_db").fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }

}
