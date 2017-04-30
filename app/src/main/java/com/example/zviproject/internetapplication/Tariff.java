package com.example.zviproject.internetapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Tariff extends DashBoard {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tariff);
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

    @Override
    public void onClickInfo(){
        Intent intent = new Intent(Tariff.this, Information.class);
        startActivity(intent);
    }

    @Override
    public void onClickShare(){
        Intent intent = new Intent(Tariff.this, Share.class);
        startActivity(intent);
    }

    @Override
    public void onClickMain(){
        Intent intent = new Intent(Tariff.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickTariff(){
        Intent intent = new Intent(Tariff.this, Tariff.class);
        startActivity(intent);
    }

    @Override
    public void onClickDeveloper(){
        Intent intent = new Intent(Tariff.this, Autorization.class);
        startActivity(intent);
    }


    @Override
    public void onClickExit(){
        Intent intent = new Intent(Tariff.this, Autorization.class);
        startActivity(intent);
    }

}
