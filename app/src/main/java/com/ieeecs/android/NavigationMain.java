package com.ieeecs.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



/**
 * Created by Krishna Kalubandi on 25-02-2015.
 */
public class NavigationMain extends ActionBarActivity{
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    ListView mListView;
    String mTitle;
    String[] menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTitle = "IEEE-CS VIT";
        mListView = (ListView) findViewById(R.id.drawer_list);
        menu = getResources().getStringArray(R.array.menu);
        if(savedInstanceState == null){
            selectItem(0);
        }
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(this,R.layout.drawer_list_item,menu);
        mListView.setAdapter(menuAdapter);
        mListView.setOnItemClickListener(new MyListListener());

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
        //to sync the toggler onresuming activity
    }

    private class MyListListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        switch (item.getItemId()) {
            case R.id.action_settings:
            {

            }break;
            case R.id.menu_login:{
                startActivity(new Intent(NavigationMain.this,LoginActivityMaster.class));
            }break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void selectItem(int position) {
        switch (position) {
            case 0:
                 getFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
                 mTitle = menu[position];
                getSupportActionBar().setTitle(mTitle);
                mDrawerLayout.closeDrawer(mListView);
                break;
            case 1:
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new AnnouncementFragment()).commit();
                mTitle  = menu[position];
                getSupportActionBar().setTitle(mTitle);
                mDrawerLayout.closeDrawer(mListView);
            break;
            case 5:
                logout();
                break;


        }
    }
    public void logout() {
        SharedPreferences settings = getSharedPreferences("USER_LOGIN",0);
        settings.getBoolean("hasLoggedIn",false);
        settings.edit().putBoolean("hasLoggedIn",true).commit();
        settings.edit().clear().commit();
        Toast.makeText(this, "Logged Out! Please restart the app", Toast.LENGTH_SHORT).show();

    }

}
