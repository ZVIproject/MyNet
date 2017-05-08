package com.example.zviproject.internetapplication.entity;

import android.support.annotation.IntegerRes;

/**
 * Created by zviproject on 07.05.17.
 */

public class AddTariff {
    private Integer clientId;
    private Integer tariffId;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }
}
