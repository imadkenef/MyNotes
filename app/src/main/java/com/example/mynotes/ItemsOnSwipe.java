package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsOnSwipe extends ItemTouchHelper.SimpleCallback {


    NotesAdapter adapter;
    ArrayList<NotesEntity> notesEntities ;

    public ItemsOnSwipe(NotesAdapter adapter,ArrayList<NotesEntity> list) {
        super(0,ItemTouchHelper.LEFT);
        this.notesEntities = list;
        this.adapter = adapter;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        NotesEntity notes = notesEntities.get(viewHolder.getAdapterPosition());
        adapter.deleteView(notes);


    }
}
