package com.example.zviproject.internetapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zviproject.internetapplication.dao.InformationDao;
import com.example.zviproject.internetapplication.entity.AddTariff;
import com.example.zviproject.internetapplication.entity.TariffEntity;
import com.example.zviproject.internetapplication.entity.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Tariff extends DashBoard {


    private ListView mListView;
    private TextView tariffInfoConsider;
    private TextView tariffInfoDescription;
    private TextView tariffCost;
    private String mFileName = "myData";
    private AddTariff addTariff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tariff);


        //------------------------------------------------------------------- dashboard -----------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.tariff);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //----------------------------------------------------------- Output data ------------------------------------------------

        final User user = new InformationDao(Integer.parseInt(readFile())).getUserInformation();
        final Map<String, TariffEntity> tariffEntityMap =new InformationDao(null).getAllTariffs();



        //----------------------------------- List -----------------------------------------------

        mListView = (ListView) findViewById(R.id.tariffList);

        List<String> tarrifsName = new LinkedList<>(tariffEntityMap.keySet());

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tarrifsName);

        mListView.setAdapter(adapter);

        tariffInfoConsider = (TextView) findViewById(R.id.tariffInfoCnsider);
        tariffInfoDescription= (TextView) findViewById(R.id.tariffInfoDescription);
        tariffCost= (TextView) findViewById(R.id.tariffInfoCost);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                tariffInfoConsider.setText(tariffEntityMap.get(adapter.getItem(position)).getCondition());
                tariffCost.setText(tariffEntityMap.get(adapter.getItem(position)).getCost().toString());
                tariffInfoDescription.setText(tariffEntityMap.get(adapter.getItem(position)).getDescription());

                addTariff = new AddTariff();
                addTariff.setTariffId(tariffEntityMap.get(adapter.getItem(position)).getId());
                addTariff.setClientId(user.getId());


            }
        });




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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.tariff);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        deleteFile("myData");
        Intent intent = new Intent(Tariff.this, Autorization.class);
        startActivity(intent);
    }

    public void addTariff(View view){
        try {
            InformationDao informationDao = new InformationDao(null, addTariff);
            informationDao.insertTariff();

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Додано тарифний план!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
            toast.show();
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Помилка при додаванні!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
            toast.show();
        }
    }

    public String readFile() {

        FileInputStream fin = null;
        StringBuilder sb = new StringBuilder();


        try {
            fin = openFileInput(mFileName);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);

            String text = new String (bytes);
            return text;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
