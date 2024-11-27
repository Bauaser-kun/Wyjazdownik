package com.example.wyjazdownik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> items;

    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.itemName.setText(item.getName());
        holder.itemCount.setText(String.valueOf(item.getCount()));

        holder.increaseButton.setOnClickListener(v -> {
            item.setCount(item.getCount() + 1);
            holder.itemCount.setText(String.valueOf(item.getCount()));
        });

        holder.decreaseButton.setOnClickListener(v ->{
            if (item.getCount() > 0) {
                item.setCount(item.getCount() - 1);
                holder.itemCount.setText(String.valueOf(item.getCount()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemCount;
        Button increaseButton, decreaseButton;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemCount = itemView.findViewById(R.id.count);
            increaseButton = itemView.findViewById(R.id.plus_button);
            decreaseButton = itemView.findViewById(R.id.minus_button);
        }
    }
}
