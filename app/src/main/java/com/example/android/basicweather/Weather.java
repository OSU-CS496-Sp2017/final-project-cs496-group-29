package com.example.android.basicweather;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Vinod on 4/21/2017.
 */
public class Weather {

    private Random rand;
    private int random_number,random_temp;
    private String weather,detailed_weather;
    private int day_of_week, day_of_month;

    private String[] day = {
            "Sun", "Mon","Tues","Wed","Thur","Fri","Sat"
    };
    private String[] forecast = {
            "Sunny and Warm", "Rain","Cold and Sunny","Snow","Thunderstorm","Hail","Blizzard"
    };

    private String[] details = {
            "No clouds and extremely hot", "Rainy so wear a jacket","Sunny but still should wear jacket","Driving may not be safe","Avoid getting shocked","Hail so stay inside","Extremely dangerous to go out"
    };


    /*Holds weathers*/


    public Weather(int d,int n){

        rand = new Random();
        day_of_week = d;
        day_of_month = n;
        set_weather();
        set_detailed_weather();


    }

    public String get_weather(){
        return weather;
    }
    public String get_detailed_weather(){
        return detailed_weather;
    }

    private void set_weather(){
        random_number = rand.nextInt(6)+ 0;
        random_temp = rand.nextInt(100)+ 30;
        weather = day[day_of_week] + " May " + day_of_month + " - " +  forecast[random_number] + " - " + random_temp;

    }

    private void set_detailed_weather(){
        detailed_weather = "Details:" + details[random_number];
    }


}
