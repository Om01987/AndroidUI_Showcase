package com.example.androiduishowcase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ElementViewHolder> {

    // 1. Define interface for click callbacks
    public interface OnItemClickListener {
        void onItemClick(UiElement element);
    }

    private Context context;
    private List<UiElement> elementList;
    private OnItemClickListener listener;

    // 2. Updated constructor to accept listener
    public ElementAdapter(Context context, List<UiElement> elementList, OnItemClickListener listener) {
        this.context = context;
        this.elementList = elementList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_element, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementViewHolder holder, int position) {
        UiElement element = elementList.get(position);
        holder.elementName.setText(element.getName());

        // 3. Set click listener on itemView that calls listener.onItemClick
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(element);
            }
        });
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }

    static class ElementViewHolder extends RecyclerView.ViewHolder {

        TextView elementName;

        public ElementViewHolder(@NonNull View itemView) {
            super(itemView);
            elementName = itemView.findViewById(R.id.elementName);
        }
    }
}
