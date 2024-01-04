package com.example.mynotes;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.mynotes.databinding.ActivityAddNewNotesBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivityClickHandler extends MaterialAlertDialogBuilder {



    Context context;

    NotesEntity notes;
    MyViewModel viewModel;
    MaterialAlertDialogBuilder dialog;
    ActivityAddNewNotesBinding binding;

    public MainActivityClickHandler(Context context, MyViewModel viewModel) {
        super(context);

        this.context = context;
        this.viewModel = viewModel;


    }

    @NonNull
    @Override
    public AlertDialog create() {

        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.activity_add_new_notes,
                null,
                false
        );
        notes = new NotesEntity();

        return super.create();
    }




    public void onFabClicked(View view){

        dialog.setView(binding.getRoot());
        binding.setNotes(notes);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }

//    public void onSubmitClickHandler(View view){
//        if (notes.getTitle() == null || notes.getContent() == null){
//            Toast.makeText(context, "Empty Input", Toast.LENGTH_SHORT).show();
//        }else {
//            currentDate = Calendar.getInstance().getTime();
//            formattedDate = DateFormat.getDateInstance().format(currentDate);
//
//            Intent intent = new Intent(
//                    context,
//                    MainActivity.class
//            );
//
//            NotesEntity nList = new NotesEntity(notes.getTitle(),notes.getContent(),formattedDate,notes.isFavorite());
//            viewModel.AddNewNotes(nList);
//            context.startActivity(intent);
//        }
//    }


}
