package com.example.zviproject.internetapplication;

import android.app.ListActivity;
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
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
import java.util.List;
import java.util.Map;


public class Information extends DashBoard{
    private TextView infoName;
    private TextView infoSurname;
    private TextView infoBalance;
    private String mFileName = "myData";
    private ListView mListView;

    private TextView tariffInfoConsider;
    private TextView tariffInfoDescription;
    private TextView tariffCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_information);

        //----------------------------------------------------- Бокове меню --------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.information);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final Map<String, TariffEntity> tariffEntityMap =new InformationDao(null).getAllTariffs();

        //----------------------------------- Список -----------------------------------------------
        List<String> tariffs = new InformationDao(Integer.parseInt(readFile())).getUserTariffs();
       mListView = (ListView) findViewById(R.id.infoList);


       final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tariffs);

        mListView.setAdapter(adapter);

        tariffInfoConsider = (TextView) findViewById(R.id.tariffInfoConsider);
        tariffInfoDescription= (TextView) findViewById(R.id.tariffInfoDescription);
        tariffCost= (TextView) findViewById(R.id.tariffInfoCost);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                tariffInfoConsider.setText(tariffEntityMap.get(adapter.getItem(position)).getCondition());
                tariffCost.setText(tariffEntityMap.get(adapter.getItem(position)).getCost().toString());
                tariffInfoDescription.setText(tariffEntityMap.get(adapter.getItem(position)).getDescription());



            }
        });







        infoName = (TextView) findViewById(R.id.infoName);
        infoBalance= (TextView) findViewById(R.id.infoBalance);
        infoSurname= (TextView) findViewById(R.id.infoSurname);

        try {
            User user = new InformationDao(Integer.parseInt(readFile())).getUserInformation();

            infoName.setText(user.getName());
            infoSurname.setText(user.getSurname());
            infoBalance.setText(user.getBalance().toString());
        }catch (Exception e){
            deleteFile("myData");
            Intent intent = new Intent(Information.this, Autorization.class);
            startActivity(intent);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Користувача видалено!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
            toast.show();
        }

    }


    public void onClickItem(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*public class MyListActivity extends ListActivity {

        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                    "Linux", "OS/2" };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);
        }

        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
            String item = (String) getListAdapter().getItem(position);
            Toast.makeText(this, item + " выбран", Toast.LENGTH_LONG).show();
        }
    }*/



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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.information);
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
        Toast toast = Toast.makeText(getApplicationContext(),
                "У майбутній версії програми!",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 200);
        toast.show();
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
        Intent intent = new Intent(Information.this, Developer.class);
        startActivity(intent);
    }

    @Override
    public void onClickExit(){
        deleteFile("myData");
        Intent intent = new Intent(Information.this, Autorization.class);
        startActivity(intent);
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
