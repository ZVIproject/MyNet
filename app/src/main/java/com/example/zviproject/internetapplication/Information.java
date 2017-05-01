package com.example.zviproject.internetapplication;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;


public class Information extends DashBoard{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.boardSmallLabel);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.boardInfo) {
            onClickInfo();
        } else if (id == R.id.boardDeveloper) {
            onClickDeveloper();

        } else if (id == R.id.boardTarif) {
            onClickTariff();

        } else if (id == R.id.boardShare) {
            onClickShare();

        } else if (id == R.id.boardMain) {
            onClickMain();
        }
        else if (id == R.id.boardExit) {
            onClickExit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClickInfo(){
        Intent intent = new Intent(Information.this, Information.class);
        startActivity(intent);
    }

    @Override
    public void onClickShare(){
        Intent intent = new Intent(Information.this, Share.class);
        startActivity(intent);
    }

    @Override
    public void onClickMain(){
        Intent intent = new Intent(Information.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickTariff(){
        Intent intent = new Intent(Information.this, Tariff.class);
        startActivity(intent);
    }

    @Override
    public void onClickDeveloper(){
        Intent intent = new Intent(Information.this, Autorization.class);
        startActivity(intent);
    }

    @Override
    public void onClickExit(){
        deleteFile("myData");
        Intent intent = new Intent(Information.this, Autorization.class);
        startActivity(intent);
    }


}
