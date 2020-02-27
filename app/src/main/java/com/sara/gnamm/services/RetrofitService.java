package com.sara.gnamm.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    //private static final String BASE_URL = "http://192.168.1.148:8080"; //Casa
    private static final String BASE_URL = "http://192.168.16.119:8080";

    private static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null) {

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return instance;
    }
}

