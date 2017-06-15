package com.example.android.basicweather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class BeerDetailed extends AppCompatActivity {
    private String d1,d2,d3,d4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detailed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView mForecastTextView = (TextView)findViewById(R.id.sv);
        TextView mForecastTextView2 = (TextView)findViewById(R.id.two);
        TextView mForecastTextView3 = (TextView)findViewById(R.id.three);

        d1 = getIntent().getStringExtra("1");
        d2 = getIntent().getStringExtra("2");
        d3 = getIntent().getStringExtra("3");
        d4 = getIntent().getStringExtra("4");


        mForecastTextView.setText(d1);
        mForecastTextView2.setText(d2);
        mForecastTextView3.setText(d3);
            }

    }


