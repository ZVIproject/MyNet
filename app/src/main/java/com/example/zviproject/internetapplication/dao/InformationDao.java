package com.example.zviproject.internetapplication.dao;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zviproject.internetapplication.Tariff;
import com.example.zviproject.internetapplication.entity.AddTariff;
import com.example.zviproject.internetapplication.entity.TariffEntity;
import com.example.zviproject.internetapplication.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by zviproject on 01.05.17.
 */

public class InformationDao extends AppCompatActivity {
    private static final String URL = "jdbc:mysql://sb-db01.softbistro.online/my_net?useUnicode=yes&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "rotrotrot";
    private static final String SQL_GET_CLIENT_INFORMATION= "SELECT * FROM user WHERE contract_number = ?";
    private static final String SQL_INSERT_TARIFFS= "INSERT INTO connect_user_tariff_plan (user_id, tariff_plan_id) values (?,?)";

    private static final String SQL_GET_ALL_TARIFFS= "select * from tariff_plan" ;

    private static final String SQL_GET_CLIENT_TARIFFS= "select t.name from tariff_plan as t " +
            "inner join connect_user_tariff_plan as cutp on t.id = cutp.tariff_plan_id " +
            "inner join user on user.id = cutp.user_id " +
            "where user.contract_number = ?";







    private Integer contractNumber;
    private AddTariff addTariff;

    public InformationDao(Integer contractNumber, AddTariff addTariff){
        this.contractNumber = contractNumber;
        this.addTariff = addTariff;

    }

    public InformationDao(Integer contractNumber){
        this.contractNumber = contractNumber;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public class GetAllTariffs extends  AsyncTask<Void, Void, Map<String, TariffEntity>> {
        @Override
        protected Map<String, TariffEntity> doInBackground(Void... params) {
            Map<String, TariffEntity> tariffs = new HashMap<>();
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

                PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_TARIFFS);

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    TariffEntity tariff = new TariffEntity();

                    tariff.setId(resultSet.getInt(1));
                    tariff.setCost(resultSet.getDouble(2));
                    tariff.setName(resultSet.getString(3));
                    tariff.setCondition(resultSet.getString(4));
                    tariff.setDescription(resultSet.getString(5));

                    tariffs.put(tariff.getName(),tariff);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return tariffs;
        }
    }

    public class InsertTariffs extends  AsyncTask<AddTariff, Void, Void> {
        @Override
        protected Void doInBackground(AddTariff... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

                PreparedStatement ps = con.prepareStatement(SQL_INSERT_TARIFFS);
                ps.setInt(1, addTariff.getClientId());
                ps.setInt(2, addTariff.getTariffId());

                ps.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class GetTariffs extends  AsyncTask<Integer, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Integer... params) {
            List<String> tariffs = new LinkedList<>();
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

                PreparedStatement ps = con.prepareStatement(SQL_GET_CLIENT_TARIFFS);
                ps.setInt(1, contractNumber);

                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    tariffs.add(resultSet.getString(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return tariffs;
        }
        }

    public class MyRequestInformation extends AsyncTask<Integer, Void, User> {
        @Override
        protected User doInBackground(Integer... params) {
            User user = new User();
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(URL,USER,PASSWORD);

                PreparedStatement ps = con.prepareStatement(SQL_GET_CLIENT_INFORMATION);
                ps.setInt(1, contractNumber);

                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                user.setId(resultSet.getInt(1));
                user.setPassword(resultSet.getString(2));
                user.setContactNumber(resultSet.getInt(5));
                user.setName(resultSet.getString(3));
                user.setSurname(resultSet.getString(4));
                user.setBalance(resultSet.getDouble(6));
                user.setConnectionStatus(resultSet.getString(7));

            } catch (Exception e){
                e.printStackTrace();
            }
            return user;
        }
    }

    public User getUserInformation(){
        try {
            MyRequestInformation myRequestInformation=new MyRequestInformation();
            myRequestInformation.execute(contractNumber);

            return myRequestInformation.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getUserTariffs(){
        try {
            GetTariffs tariffs=new GetTariffs();
            tariffs.execute(contractNumber);

            return tariffs.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, TariffEntity> getAllTariffs(){
        try {
            GetAllTariffs tariffs=new GetAllTariffs();
            tariffs.execute();

            return tariffs.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertTariff(){
            InsertTariffs tariffs=new InsertTariffs();
            tariffs.execute();

    }
}
