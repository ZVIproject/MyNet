package com.example.zviproject.internetapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.left_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    public void onClickInfo(){
        Intent intent = new Intent(DashBoard.this, Information.class);
        startActivity(intent);
    }
    public void onClickShare(){
        Intent intent = new Intent(DashBoard.this, Share.class);
        startActivity(intent);
    }
    public void onClickMain(){
        Intent intent = new Intent(DashBoard.this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickTariff(){
        Intent intent = new Intent(DashBoard.this, Tariff.class);
        startActivity(intent);
    }
    public void onClickDeveloper(){
        Intent intent = new Intent(DashBoard.this, Autorization.class);
        startActivity(intent);
    }

    public void onClickExit(){
        deleteFile("myData");
        Intent intent = new Intent(DashBoard.this, Autorization.class);
        startActivity(intent);
    }

}
