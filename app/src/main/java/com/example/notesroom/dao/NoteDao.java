package com.example.notesroom.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.notesroom.utils.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insertNote(Note note);

    @Query("SELECT * FROM notes_table")
    LiveData<List<Note>> getAll();

    @Delete
    void delete(Note note);

    @Query("DELETE FROM notes_table")
    void deleteAll();

    @Query("UPDATE notes_table SET notes_title = :title, notes_content = :content WHERE id = :sid ")
    void updateNote(int sid, String title,String content);

}
