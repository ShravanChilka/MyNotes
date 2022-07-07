package com.example.notesroom.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.notesroom.utils.adapter.NoteAdapter;
import com.example.notesroom.utils.model.Note;
import com.example.notesroom.R;
import com.example.notesroom.viewmodel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity {




    private RecyclerView recyclerView;
    private FloatingActionButton addBtn;
    private NotesViewModel notesViewModel;
    private NoteAdapter recyclerAdapter;
    private ImageView delete;

    private static final int ADD_NOTE_REQUEST_CODE = 1;
    private static final int EDIT_NOTE_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_recycler_view);
        addBtn = findViewById(R.id.add_btn);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter = new NoteAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        notesViewModel = new NotesViewModel(getApplication());
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);


        notesViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                recyclerAdapter.submitList(notes);
            }
        });

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Delete All Notes");
                builder.setMessage("Are you sure you want to delete all notes?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        notesViewModel.deleteAll();
                        Toast.makeText(MainActivity.this, "All Notes Deleted", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if (direction == ItemTouchHelper.LEFT) {
                    Intent intent = new Intent(MainActivity.this , AddNoteActivity.class);
                    intent.putExtra("id" , recyclerAdapter.getNote(viewHolder.getAdapterPosition()).getId());
                    intent.putExtra("title" , recyclerAdapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("content" , recyclerAdapter.getNote(viewHolder.getAdapterPosition()).getContent());
                    intent.putExtra("type","edit");
                    startActivityForResult(intent , EDIT_NOTE_REQUEST_CODE );

                } else {
                    notesViewModel.deleteNote(recyclerAdapter.getNote(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeRightBackgroundColor(Color.RED)
                        .addSwipeRightActionIcon(R.drawable.ic_baseline_delete_24)
                        .addSwipeLeftBackgroundColor(Color.GREEN)
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_edit_24)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        });
        touchHelper.attachToRecyclerView(recyclerView);

//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                if (direction == ItemTouchHelper.LEFT) {
//                    Intent intent = new Intent(MainActivity.this , AddNoteActivity.class);
//                    intent.putExtra("id" , recyclerAdapter.getNote(viewHolder.getAdapterPosition()).getId());
//                    intent.putExtra("title" , recyclerAdapter.getNote(viewHolder.getAdapterPosition()).getTitle());
//                    intent.putExtra("content" , recyclerAdapter.getNote(viewHolder.getAdapterPosition()).getContent());
//                    intent.putExtra("type","edit");
//                    startActivityForResult(intent , EDIT_NOTE_REQUEST_CODE );
//
//                } else {
//                    notesViewModel.deleteNote(recyclerAdapter.getNote(viewHolder.getAdapterPosition()));
//                    Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).attachToRecyclerView(recyclerView);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                intent.putExtra("type","add");
                startActivityForResult(intent, ADD_NOTE_REQUEST_CODE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            Long time = System.currentTimeMillis();
            String formatTime = DateFormat.getDateTimeInstance().format(time);

            Note note = new Note(title, content,formatTime);
            notesViewModel.insertNote(note);
            Toast.makeText(this, "Note inserted", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == EDIT_NOTE_REQUEST_CODE ){
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            int id = data.getIntExtra("id",0);
            Long time = System.currentTimeMillis();
            String formatTime = DateFormat.getDateTimeInstance().format(time);
            Note note = new Note(title,content,formatTime);
            note.setId(id);
            notesViewModel.updateNote(note);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_all_notes) {
            notesViewModel.deleteAll();
            Toast.makeText(this, "All Notes Deleted", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}