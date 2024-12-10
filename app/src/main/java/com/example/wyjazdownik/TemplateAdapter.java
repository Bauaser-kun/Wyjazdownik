package com.example.wyjazdownik;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder> {
    private List<TripList> triplists;
    private OnTemplateClickListener clickListener;

    public interface OnTemplateClickListener {
        void onTemplateClick (TripList tripList);
    }

    public TemplateAdapter (List<TripList> templates, OnTemplateClickListener listener) {
        this.triplists = templates;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry_view, parent, false);
        return new TemplateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        TripList tripList = triplists.get(position);
        holder.listName.setText(tripList.getName());
        holder.itemView.setOnClickListener(v -> clickListener.onTemplateClick(tripList));
    }

    @Override
    public int getItemCount() {
        return triplists.size();
    }

    public class TemplateViewHolder extends RecyclerView.ViewHolder {
        TextView listName;

        public TemplateViewHolder(View itemView) {
            super(itemView);
            listName = itemView.findViewById(R.id.schemes);
        }
    }
    public void  updateTripLists(List<TripList> newLists) {
        this.triplists = newLists;
        notifyDataSetChanged();
    }
}
