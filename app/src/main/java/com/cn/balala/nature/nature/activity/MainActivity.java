package com.cn.balala.nature.nature.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.fragment.ConferenceFragment;
import com.cn.balala.nature.nature.fragment.MainFragment;
import com.cn.balala.nature.nature.fragment.MessageFragment;
import com.cn.balala.nature.nature.fragment.TreeHoleFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RadioGroup rgNavBar;
    RadioButton rbMain;
    RadioButton rbConference;
    RadioButton rbTreeHole;
    RadioButton rbMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.ic_news);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initView() {
        rgNavBar = (RadioGroup) findViewById(R.id.rg_nav_bar);
        rbMain = (RadioButton) findViewById(R.id.rb_main);
        rbConference = (RadioButton) findViewById(R.id.rb_conference);
        rbTreeHole = (RadioButton) findViewById(R.id.rb_tree_hole);
        rbMessage = (RadioButton) findViewById(R.id.rb_message);

        rbMain.setChecked(true);

        rgNavBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (i) {
                    case R.id.rb_main:
                        Toast.makeText(MainActivity.this, "首页", Toast.LENGTH_SHORT).show();
                        MainFragment mainFragment = new MainFragment();
                        transaction.replace(R.id.layout_fragment, mainFragment);
                        break;
                    case R.id.rb_conference:
                        Toast.makeText(MainActivity.this, "会议", Toast.LENGTH_SHORT).show();
                        ConferenceFragment conferenceFragment = new ConferenceFragment();
                        transaction.replace(R.id.layout_fragment, conferenceFragment);
                        break;
                    case R.id.rb_tree_hole:
                        Toast.makeText(MainActivity.this, "树洞", Toast.LENGTH_SHORT).show();
                        TreeHoleFragment treeHoleFragment = new TreeHoleFragment();
                        transaction.replace(R.id.layout_fragment, treeHoleFragment);
                        break;
                    case R.id.rb_message:
                        Toast.makeText(MainActivity.this, "消息", Toast.LENGTH_SHORT).show();
                        MessageFragment messageFragment = new MessageFragment();
                        transaction.replace(R.id.layout_fragment, messageFragment);
                        break;
                }
                transaction.commit();
            }
        });

    }
}
