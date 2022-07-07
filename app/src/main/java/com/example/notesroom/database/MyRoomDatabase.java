package com.example.notesroom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesroom.utils.model.Note;
import com.example.notesroom.dao.NoteDao;

@Database(entities = {Note.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
    private static MyRoomDatabase instance;

    public static synchronized MyRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , MyRoomDatabase.class, "DB_NAME")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
