package com.bignerdranch.android.appjavaandroid.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.appjavaandroid.R;
import com.bignerdranch.android.appjavaandroid.dto.DTO;

import java.util.List;

public class appListAdapter extends RecyclerView.Adapter<appListAdapter.appViewHolder> {

    private List<DTO> data;

    public appListAdapter(List<DTO> data) {
        this.data = data;
    }

    @Override
    public appViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);

        return new appViewHolder(view);
    }

    @Override
    public void onBindViewHolder(appViewHolder holder, int position) {
        DTO item = data.get(position);
        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<DTO> data) {
        this.data = data;
    }

    public static class appViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;

        public appViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
