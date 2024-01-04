package com.example.mynotes;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    ExecutorService executorService ;
    Handler handler;
    private final NotesDao notesDao;

    public Repository(Application application) {
        DatabaseNotes notes = DatabaseNotes.getInstance(application);
        this.notesDao = notes.getNotesDao();

        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

    }
    public void addNotes(NotesEntity notes){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.insert(notes);
            }
        });

    }

    public void deleteNotes(NotesEntity notes){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.delete(notes);
            }
        });

    }

    public void updateNoteFavoriteStatus(int noteId,  Boolean isFavorite){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.updateNoteFavoriteStatus(noteId,isFavorite);

            }
        });

    }
    public LiveData<List<NotesEntity>> getAllNotes(){
        return notesDao.getAllNotes();
    }
}
