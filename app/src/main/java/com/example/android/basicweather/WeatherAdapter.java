/*

REFERENCES:

https://guides.codepath.com/android/using-the-recyclerview
https://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465
https://www.youtube.com/watch?v=XxW5scw-n9Q

*/
package com.example.android.basicweather;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Vinod on 4/21/2017.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    Context con;
    private ArrayList<Weather> weather = new ArrayList<Weather>();



    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView t;
        private Button b;


        /*Constructor - views initialized belonging to RecyclerView*/
        public ViewHolder(View itemView) {

            super(itemView);
            t = (TextView)itemView.findViewById(R.id.recycle_text);
            b = (Button)itemView.findViewById(R.id.detail_weather);



        }

        public void weather_clicked(View v) {
            //findViewById(R.id.recycle_text).getText().toString();
             // Toast.makeText(getApplicationContext(),"HERE", Toast.LENGTH_LONG).show();

        }

    }

    /*Constructor handles data RecyclerView displays*/
    public WeatherAdapter(Context context,ArrayList<Weather> w){
        this.con = context;
        weather = w;
    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View weather_view = inflater.inflate(R.layout.weather_layout, parent, false); //fetch xml file

        ViewHolder v = new ViewHolder(weather_view);

        return v;
    }

    /*Contents of each item in RecyclerView*/
    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder viewHolder, int i){
        final int extra_i = i;
        viewHolder.t.setText(weather.get(i).get_weather());

        viewHolder.b.setOnClickListener(new View.OnClickListener() {

            /*For detailed weather*/
            @Override
            public void onClick(View v) {
               Toast.makeText(con, weather.get(extra_i).get_detailed_weather(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public int getItemCount() {
        return weather.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}
