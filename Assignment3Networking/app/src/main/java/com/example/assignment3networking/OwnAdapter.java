package com.example.assignment3networking;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OwnAdapter extends RecyclerView.Adapter<OwnAdapter.OwnViewHolder> {

    private ArrayList<Bitmap> bmp_images;
    private OnNoteListener onNoteListener;

    public OwnAdapter(ArrayList<Bitmap> bmp_images, OnNoteListener onNoteListener) {
        this.bmp_images = bmp_images;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public OwnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewid, parent, false);
        return new OwnViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnViewHolder holder, int position) {
        holder.textView.setText("Image number " + holder.getAdapterPosition());
        holder.imageView.setImageBitmap(bmp_images.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return bmp_images.size();
    }

    public class OwnViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;
        private Button removeButton;
        private OnNoteListener onNoteListener;


        public OwnViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            removeButton = itemView.findViewById(R.id.removeButton);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeNoteAt(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    private void removeNoteAt(int position) {
        bmp_images.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, bmp_images.size());
        //notifyDataSetChanged();
        //change number in listing notes
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

    public void setOnNoteListener(OnNoteListener onNoteListeners){
        onNoteListener = onNoteListeners;
    }
}

