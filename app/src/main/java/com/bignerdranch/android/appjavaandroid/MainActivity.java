package com.bignerdranch.android.appjavaandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.bignerdranch.android.appjavaandroid.adapter.TabsFragmentAdapter;
import com.bignerdranch.android.appjavaandroid.dto.DTO;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;

    private TabsFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabs();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        mToolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                                                                    mDrawerLayout,
                                                                    mToolbar,
                                                                    R.string.view_navigation_open,
                                                                    R.string.view_navigation_close
                                                            );
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.actionNotificationItem:
                        showNotificationTab();
                }
                return true;
            }
        });
    }

    private void initTabs() {
        mViewPager=(ViewPager)findViewById(R.id.viewPager);
        adapter =new TabsFragmentAdapter(getApplicationContext(),getSupportFragmentManager(),new ArrayList<DTO>());

        new MeTask().execute();

        TableLayout mTableLayout=(TableLayout)findViewById(R.id.tabLayout);
        mTableLayout.setupWithPager(mViewPager);
    }

    private void showNotificationTab() {
        mViewPager.setCurrentItem(Constants.TAB_TWO);
    }

    private class MeTask extends AsyncTask<Void,Void,DTO>{

        @Override
        protected DTO doInBackground(Void... params){
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            return template.getForObject(Constants.URL.GET_REMIND, DTO.class);
        }

        @Override
        protected void onPostExecute(DTO dto) {
            List<DTO> data = new ArrayList<>();
            data.add(dto);

            adapter.setData(data);
        }
    }
}