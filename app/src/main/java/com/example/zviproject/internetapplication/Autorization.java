package com.example.zviproject.internetapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zviproject.internetapplication.dao.UserDao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Autorization extends AppCompatActivity {

    private EditText password;
    private EditText contractNumber;
    private Integer numberOfNumeric = 7;
    private String mFileName = "myData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);
        if(readFile()!=null){
            Intent intent = new Intent(Autorization.this, MainActivity.class);
            startActivity(intent);
        }else {


            contractNumber = (EditText) findViewById(R.id.autContract);
            password = (EditText) findViewById(R.id.autPassword);
        }

    }


    public void onClick(View view) {
        if(contractNumber.getText().length()==0 && password.getText().length()==0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Введіть поля!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
            toast.show();
            return;
        }

        if(contractNumber.getText().length()!=numberOfNumeric) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "№ договору містить 7 цифр!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
            toast.show();
            return;
        }

        if(password.getText().length()==0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Введіть пароль!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
            toast.show();
            return;
        }

        UserDao userDao = new UserDao(password.getText().toString() ,Integer.parseInt(contractNumber.getText().toString()));


        //--------------------------------------------- Autorization data ------------------------------------------
        if(userDao.getAutorizationStatus()==true){

            writeToFile(contractNumber.getText().toString());
            Intent intent = new Intent(Autorization.this, MainActivity.class);
            startActivity(intent);
        }else{
            password.setText("");
            contractNumber.setText(readFile());
        }
    }






    //--------------------------------------------------File system ------------------------------------


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

    public void writeToFile(String dataForInsert) {

        try {
            FileOutputStream outputStream = openFileOutput(mFileName, MODE_PRIVATE);
            outputStream.write(dataForInsert.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
