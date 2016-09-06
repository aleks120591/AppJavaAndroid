package com.bignerdranch.android.appjavaandroid.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bignerdranch.android.appjavaandroid.dto.DTO;
import com.bignerdranch.android.appjavaandroid.fragment.AbstractTabFragment;
import com.bignerdranch.android.appjavaandroid.fragment.BirthdaysFragment;
import com.bignerdranch.android.appjavaandroid.fragment.HistoryFragment;
import com.bignerdranch.android.appjavaandroid.fragment.IdeasFragment;
import com.bignerdranch.android.appjavaandroid.fragment.TodoFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    private List<DTO> data;

    private HistoryFragment historyFragment;

    public TabsFragmentAdapter(Context context, FragmentManager fm, List<DTO> data) {
        super(fm);
        this.data = data;
        this.context = context;
        initTabsMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();
        historyFragment = HistoryFragment.getInstance(context, data);
        tabs.put(0, historyFragment);
        tabs.put(1, IdeasFragment.getInstance(context));
        tabs.put(2, TodoFragment.getInstance(context));
        tabs.put(3, BirthdaysFragment.getInstance(context));
    }

    public void setData(List<DTO> data) {
        this.data = data;
        historyFragment.refreshList(data);
    }
}
