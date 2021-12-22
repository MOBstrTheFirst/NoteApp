package com.example.comeback;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Note> notes;

    public Adapter(Context context, List<Note> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title, date, time;
        title = notes.get(position).getTitle();
        date = notes.get(position).getDate();
        time = notes.get(position).getTime();
        Log.d("onBindViewHolder",String.valueOf(notes.get(position).getId()));

        holder.nTitle.setText(title);
        holder.nDate.setText(date);
        holder.nTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nTitle, nDate, nTime;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            constraintLayout = itemView.findViewById(R.id.mainLayout);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),Details.class);
                    Log.d("My clickItem is", String.valueOf(getAdapterPosition()));
                    Log.d("My ID is", String.valueOf(notes.get(getAdapterPosition()).getId()));
                    intent.putExtra("ID",notes.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
