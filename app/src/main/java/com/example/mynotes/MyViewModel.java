package com.example.mynotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynotes.NotesEntity;
import com.example.mynotes.Repository;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class MyViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<NotesEntity>> getAllNote;


    Stack<NotesEntity> deletedNotesStack = new Stack<>();


    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);


    }

    public LiveData<List<NotesEntity>> getAllNotes(){
        getAllNote = repository.getAllNotes();
        return getAllNote;

    }
    public void updateNoteFavoriteStatus(int noteId,  Boolean isFavorite){
        repository.updateNoteFavoriteStatus(noteId,isFavorite);
    }
    public void AddNewNotes(NotesEntity notes){
        repository.addNotes(notes);
    }

    public void deleteNotes(NotesEntity notes){
        //deletedNotesStack.push(position);
        repository.deleteNotes(notes);
    }

    public boolean undoDeletion() {
        if (deletedNotesStack.isEmpty()) {
            return false; // No notes to undo
        }
        NotesEntity restoredNote = deletedNotesStack.pop();
        // Call the repository to restore the note
        repository.addNotes(restoredNote);
        return true;
    }

}
