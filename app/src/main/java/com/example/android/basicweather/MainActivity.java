package com.example.android.basicweather;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public Context context = this;
    /*Recycle View*/
    public RecyclerView rv;
    private LinearLayoutManager lm;
    private RecyclerAdapter adapter;
    private ArrayList<String> display = new ArrayList<String>();

    private int d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView)findViewById(R.id.rview);
       lm = new LinearLayoutManager(context);
        recycle_manage();
      //  create_list();
        set_dummy_list();
        adapter = new RecyclerAdapter(context,display);
        rv.setAdapter(adapter);
    }

    private void set_dummy_list(){
        d = 0;
        for(int i = 0; i < 30; i++){
            if(d > 6){
                d = 0;
            }

            display.add("Beer");
            d++;
        }

    }

    public void recycle_manage(){

        rv.setLayoutManager(lm);

    }




}
