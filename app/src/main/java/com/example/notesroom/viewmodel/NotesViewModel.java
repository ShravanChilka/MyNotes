package com.example.notesroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesroom.repository.NoteRepository;
import com.example.notesroom.utils.model.Note;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> notesList;


    public NotesViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        notesList = noteRepository.getAllNotes();

    }

    public LiveData<List<Note>> getAllNotes(){
        return notesList;
    }

    public void insertNote(Note note){
        noteRepository.insertNote(note);
    }

    public void updateNote(Note note){
        noteRepository.updateNote(note);
    }

    public void deleteNote(Note note){
        noteRepository.deleteNote(note);
    }

    public void deleteAll(){
        noteRepository.deleteAllNotes();
    }
}
