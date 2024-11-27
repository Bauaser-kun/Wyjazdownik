package com.example.wyjazdownik;

import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder> {
    private List<TripList> triplists;

    public TemplateAdapter (List<TripList> templates) {
        this.triplists = templates;
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
    }

    @Override
    public int getItemCount() {
        return triplists.size();
    }

    public class TemplateViewHolder extends RecyclerView.ViewHolder {
        TextView listName;

        public TemplateViewHolder(View itemView) {
            super(itemView);
            listName = itemView.findViewById(R.id.list_Name);
        }
    }
}
