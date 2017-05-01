package com.example.zviproject.internetapplication.dao;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.text.BoringLayout;
import android.widget.TextView;


import com.example.zviproject.internetapplication.R;
import com.mysql.jdbc.DocsConnectionPropsHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

/**
 * Created by zviproject on 30.04.17.
 */


public class UserDao extends AppCompatActivity{
    private static final String URL = "jdbc:mysql://sb-db01.softbistro.online/my_net?useUnicode=yes&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "rotrotrot";
    private static final String SQL_GET_CLIENT_FOR_CHECK = "SELECT count(id) FROM user WHERE `password` = ? and contract_number = ?";

    private String password;
    private Integer contractNumber;
    public Boolean access = false;
    public UserDao(String password, Integer contractNumber){
        this.password = password;
        this.contractNumber = contractNumber;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public class MyRequestAutorization extends AsyncTask<Boolean, Void, Boolean> {
        public boolean lAccess = false;
        @Override
        protected Boolean doInBackground(Boolean... params) {

            try {
            Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(URL,USER,PASSWORD);

                PreparedStatement ps = con.prepareStatement(SQL_GET_CLIENT_FOR_CHECK);
                ps.setString(1, password);
                ps.setInt(2, contractNumber);

                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                if(resultSet.getInt(1) == 1){
                    lAccess = true;
                }



            } catch (Exception e){
                e.printStackTrace();
            }
            return lAccess;
        }
    }

    public Boolean getAutorizationStatus(){
        try {
        MyRequestAutorization myRequestAutorization=new MyRequestAutorization();
        myRequestAutorization.execute(access);

            return myRequestAutorization.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }


}
