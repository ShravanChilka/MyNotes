package com.example.notesroom.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notesroom.database.MyRoomDatabase;
import com.example.notesroom.databinding.ActivityAddNoteBinding;
import com.example.notesroom.utils.model.Note;
import com.example.notesroom.R;

import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String type = getIntent().getStringExtra("type");
        if (type.equals("edit")) {

            //edit

            setTitle("edit");
            binding.titleEdit.setText(getIntent().getStringExtra("title"));
            binding.contentEdit.setText(getIntent().getStringExtra("content"));
            int id = getIntent().getIntExtra("id", 0);

            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = binding.titleEdit.getText().toString();
                    String content = binding.contentEdit.getText().toString();

                    if (title.isEmpty() && content.isEmpty()) {
                        Toast.makeText(AddNoteActivity.this, "Please Enter Something", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent dataIntent = new Intent();
                        dataIntent.putExtra("title", title);
                        dataIntent.putExtra("content", content);
                        dataIntent.putExtra("id", id);
                        setResult(RESULT_OK, dataIntent);
                        finish();
                    }
                }
            });


        } else {

            //new note

            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String title = binding.titleEdit.getText().toString();
                    String content = binding.contentEdit.getText().toString();


                    if (title.isEmpty() && content.isEmpty()) {
                        Toast.makeText(AddNoteActivity.this, "Please Enter Something", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent dataIntent = new Intent();
                        dataIntent.putExtra("title", title);
                        dataIntent.putExtra("content", content);
                        setResult(RESULT_OK, dataIntent);
                        finish();
                    }
                }
            });
        }
    }
}
