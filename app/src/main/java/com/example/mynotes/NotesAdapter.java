package com.example.mynotes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.databinding.NotesViewBinding;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.Collections;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private ArrayList<NotesEntity> notes;
    private MyViewModel viewModel;


    public NotesAdapter(ArrayList<NotesEntity> notes,MyViewModel viewModel) {
        this.notes = notes;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotesViewBinding notesViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.notes_view,
                parent,
                false
        );


        return new ViewHolder(notesViewBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotesEntity currentnotes = notes.get(position);

        holder.notesViewBinding.setNotes(currentnotes);
        if(currentnotes.isFavorite()){
            holder.notesViewBinding.star.setImageResource(R.drawable.star);

        }else {
            holder.notesViewBinding.star.setImageResource(R.drawable.unstar);
        }
       holder.onClick(currentnotes);



    }

    public void deleteView(NotesEntity notes){
        viewModel.deleteNotes(notes);
        notifyItemRemoved(notes.getId());
    }
    public void setContacts(ArrayList<NotesEntity> notes) {
        this.notes = notes;

        // Inform the associated RecyclerView that the underlying
        // dataset has changed, and the RecyclerView should refresh
        // its views to reflect these changes.
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private  NotesViewBinding notesViewBinding;
        private NotesEntity notes;

        public ViewHolder(@NonNull NotesViewBinding notesViewBinding) {
            super(notesViewBinding.getRoot());
            this.notesViewBinding = notesViewBinding;

        }
        void onClick(NotesEntity notes){

            notesViewBinding.star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (notes.isFavorite()){

                        notesViewBinding.star.setImageResource(R.drawable.unstar);
                        viewModel.updateNoteFavoriteStatus(notes.getId(),false);

                    }else if (!notes.isFavorite()){

                        notesViewBinding.star.setImageResource(R.drawable.star);
                        viewModel.updateNoteFavoriteStatus(notes.getId(),true);

                    }
                }
            });

        }

    }
}
