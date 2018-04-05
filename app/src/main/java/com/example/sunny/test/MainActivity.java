package com.example.sunny.test;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sunny.test.Fragments.InfoFragment;
import com.example.sunny.test.Fragments.NewsFragment;
import com.example.sunny.test.Fragments.SportFragment;
import com.example.sunny.test.Fragments.TechnologyFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        manager = getSupportFragmentManager();

        manager.beginTransaction()
                .replace(R.id.container, new NewsFragment())
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void openInfoFrag(){
        manager.beginTransaction()
                .replace(R.id.container, new InfoFragment())
                .addToBackStack("")
                .commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mako) {
            manager.beginTransaction()
                    .replace(R.id.container, new NewsFragment())
                    .addToBackStack("")
                    .commit();
        } else if (id == R.id.nav_one) {
            manager.beginTransaction()
                    .replace(R.id.container, new SportFragment())
                    .addToBackStack("")
                    .commit();
        } else if (id == R.id.nav_ynet_tech) {
            manager.beginTransaction()
                    .replace(R.id.container, new TechnologyFragment())
                    .addToBackStack("")
                    .commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
