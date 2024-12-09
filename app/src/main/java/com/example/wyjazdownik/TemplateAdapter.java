package com.example.wyjazdownik;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    void addNewTripList(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("New Template");

        final EditText input = new EditText(context);
        input.setHint("Nazwa listy");
        builder.setView(input);

        builder.setPositiveButton("Dodaj", (dialog, which) -> {
            String templateName = input.getText().toString().trim();
            if (!templateName.isEmpty()) {
                TripList newTripList = new TripList(templateName, new ArrayList<>());
                triplists.add(newTripList);
                saveTripLists();
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Anuluj", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void saveTripLists() {
    }

    ArrayList<TripList> loadTripLists() {

        return new ArrayList<>();
    }
}
