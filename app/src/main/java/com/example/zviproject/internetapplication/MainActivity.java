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

        BlurLayout sampleLayout = (BlurLayout)findViewById(R.id.blur_layout);
        View hover = LayoutInflater.from(this).inflate(R.layout.hover_sample3, null);
        sampleLayout.setHoverView(hover);
        sampleLayout.addChildAppearAnimator(hover, R.id.eye, Techniques.FlipInX);
        sampleLayout.addChildDisappearAnimator(hover, R.id.eye, Techniques.FlipOutX);

        mSampleLayout3 = (BlurLayout)findViewById(R.id.blur_layout3);
        View hover3 = LayoutInflater.from(this).inflate(R.layout.hover_sample3, null);
        mSampleLayout3.setHoverView(hover3);
        mSampleLayout3.addChildAppearAnimator(hover3, R.id.eye, Techniques.Landing);
        mSampleLayout3.addChildDisappearAnimator(hover3, R.id.eye, Techniques.TakingOff);
        mSampleLayout3.enableZoomBackground(true);
        mSampleLayout3.setBlurDuration(1200);
    }


    public void onClickInformation(View view){
        Intent intent = new Intent(MainActivity.this, Information.class);
        startActivity(intent);
    }

    public void onClickShare(View view){
        Intent intent = new Intent(MainActivity.this, Developer.class);
        startActivity(intent);
    }

    public void onClickTariff(View view){
        Intent intent = new Intent(MainActivity.this, Tariff.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
