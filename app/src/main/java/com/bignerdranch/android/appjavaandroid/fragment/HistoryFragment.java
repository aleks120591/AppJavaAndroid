package com.bignerdranch.android.appjavaandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.appjavaandroid.R;
import com.bignerdranch.android.appjavaandroid.adapter.appListAdapter;
import com.bignerdranch.android.appjavaandroid.dto.DTO;

import java.util.List;

public class HistoryFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_history;

    private List<DTO> data;

    private appListAdapter adapter;

    public static HistoryFragment getInstance(Context context, List<DTO> data) {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setData(data);
        fragment.setTitle(context.getString(R.string.tab_item_history));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycleView);
        rv.setLayoutManager(new LinearLayoutManager(context));

        adapter = new appListAdapter(data);
        rv.setAdapter(adapter);

        return view;
    }

    public void refreshList(List<DTO> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<DTO> data) {
        this.data = data;
    }
}
