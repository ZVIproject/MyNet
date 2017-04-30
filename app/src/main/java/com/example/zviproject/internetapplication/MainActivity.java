package com.example.zviproject.internetapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidviewhover.BlurLayout;

public class MainActivity extends ActionBarActivity {

    private Context mContext;
    private BlurLayout mSampleLayout3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickInformation(View view){
        Intent intent = new Intent(MainActivity.this, Information.class);
        startActivity(intent);
    }

    public void onClickShare(View view){
        Intent intent = new Intent(MainActivity.this, Share.class);
        startActivity(intent);
    }

    public void onClickTariff(View view){
        Intent intent = new Intent(MainActivity.this, Tariff.class);
        startActivity(intent);
    }


}
