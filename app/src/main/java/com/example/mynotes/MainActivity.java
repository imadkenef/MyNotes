package com.example.mynotes;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity{

    //DataSource
    private ArrayList<NotesEntity> notesList = new ArrayList<>();

    // Adapter
    private NotesAdapter adapter;

    //Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler clickHandler;

    //View
    private RecyclerView recyclerView;

    //ViewModel
    MyViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         mainBinding = DataBindingUtil.setContentView(
                 this,
                 R.layout.activity_main
         );
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.undo_slide_in_right);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        clickHandler = new MainActivityClickHandler(this,viewModel);
        mainBinding.setClickhandler(clickHandler);

        recyclerView = mainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        adapter = new NotesAdapter(notesList,viewModel);

        viewModel.getAllNotes().observe(this,
                new Observer<List<NotesEntity>>() {
                    @Override
                    public void onChanged(List<NotesEntity> notesEntities) {

                        notesList.clear();
                        for (NotesEntity notes : notesEntities){
                            notesList.add(notes);

                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        recyclerView.setAdapter(adapter);


        ItemsOnSwipe itemsOnSwipe = new ItemsOnSwipe(adapter,notesList);
        new ItemTouchHelper(itemsOnSwipe)
                .attachToRecyclerView(recyclerView);

    }



}