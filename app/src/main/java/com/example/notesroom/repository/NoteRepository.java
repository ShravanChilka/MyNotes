package com.example.notesroom.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesroom.utils.model.Note;
import com.example.notesroom.dao.NoteDao;
import com.example.notesroom.database.MyRoomDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotesLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();

    public NoteRepository(Application application){
        MyRoomDatabase myRoomDatabase = MyRoomDatabase.getInstance(application);
        noteDao = myRoomDatabase.noteDao();
        allNotesLiveData = noteDao.getAll();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotesLiveData;
    }

    public void insertNote(Note note){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insertNote(note);
            }
        });
    }

    public void updateNote(Note note){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.updateNote(note.id, note.getTitle(), note.getContent());
            }
        });
    }
    public void deleteNote(Note note){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        });
    }
    public void deleteAllNotes(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteAll();
            }
        });
    }

}
