package com.example.notesroom.utils.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesroom.R;
import com.example.notesroom.utils.model.Note;
import com.example.notesroom.viewmodel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {

    private NotesViewModel notesViewModel;
    private List<Note> noteList;

    public NoteAdapter(){
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return  oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getContent().equals(newItem.getContent());
        }
    };

    public Note getNote(int position){
        return getItem(position);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = getItem(position);
        holder.titleTv.setText(note.getTitle());
        holder.contentTv.setText(note.getContent());
        holder.timeTv.setText(note.getTime());
        holder.cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(getRandomColor()));
    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.one);
        colorCode.add(R.color.two);
        colorCode.add(R.color.three);
        colorCode.add(R.color.four);
        colorCode.add(R.color.five);
        colorCode.add(R.color.six);
        colorCode.add(R.color.seven);
        colorCode.add(R.color.eight);
        colorCode.add(R.color.nine);
        colorCode.add(R.color.ten);
        colorCode.add(R.color.eleven);
        colorCode.add(R.color.twelve);
        colorCode.add(R.color.thirteen);
        Random random = new Random();
        int number = random.nextInt(colorCode.size());
        return colorCode.get(number);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView titleTv, contentTv, timeTv;
        CardView cardView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTv = itemView.findViewById(R.id.time_tv);
            cardView = itemView.findViewById(R.id.card_view);
            titleTv = itemView.findViewById(R.id.title_tv);
            contentTv = itemView.findViewById(R.id.content_tv);
        }
    }
}
