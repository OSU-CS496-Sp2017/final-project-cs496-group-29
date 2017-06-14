package com.example.android.basicweather;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.LoaderManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>  {

    /*  bfd18adb9a71ebdd51a70d52815434d7  */

    public Context context = this;
    /*Recycle View*/
    public RecyclerView rv;
    private LinearLayoutManager lm;
    private RecyclerAdapter adapter;
    private ProgressBar mLoadingIndicatorPB;
    private ArrayList<String> beer = new ArrayList<String>();
    private static final String TAG = MainActivity.class.getSimpleName();

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
        adapter = new RecyclerAdapter(context,beer);
        rv.setAdapter(adapter);

       // mLoadingIndicatorPB = (ProgressBar)findViewById(R.id.pb_loading_indicator);

        Bundle argsBundle = new Bundle();
        argsBundle.putString("url", "http://api.brewerydb.com/v2/beers?srmId=5%20-%205&key=bfd18adb9a71ebdd51a70d52815434d7");
        getSupportLoaderManager().initLoader(0, argsBundle, this);
        adapter.update(beer);

    }

    private void set_dummy_list(){
        d = 0;
        for(int i = 0; i < 30; i++){
            if(d > 6){
                d = 0;
            }

           // display.add("Beer");
            d++;
        }

    }

    public void recycle_manage(){

        rv.setLayoutManager(lm);

    }


    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {

            String mSearchResultsJSON;

            @Override
            protected void onStartLoading() {

                if (args != null) {
                    if (mSearchResultsJSON != null) {
                        Log.d(TAG, "Delivered cached results");
                        deliverResult(mSearchResultsJSON);
                    } else {
//                        mLoadingIndicatorPB.setVisibility(View.VISIBLE);
                        Log.d(TAG, "Loading again from server");
                        forceLoad();
                    }
                }

            }

            @Override
            public String loadInBackground() {
                URL url;
                HttpURLConnection url_connection;



                ProgressBar progress;
                try {

                    url = new URL("http://api.brewerydb.com/v2/beers?srmId=5%20-%205&key=bfd18adb9a71ebdd51a70d52815434d7");
                    //  System.out.println(params[0]);
                    url_connection = (HttpURLConnection) url.openConnection();

                    try {

                        BufferedReader b_reader = new BufferedReader(new InputStreamReader(url_connection.getInputStream()));
                        // System.out.println("NENE!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        StringBuilder string_builder = new StringBuilder();
                        String line = null;
                        while((line = b_reader.readLine()) != null){
                            string_builder.append(line).append("\n");
                            //  System.out.println(line);

                        }
                        b_reader.close();
                        return string_builder.toString();
                    }
                    finally{
                        url_connection.disconnect();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;

                }

            }

            @Override
            public void deliverResult(String data) {
                adapter.update(beer);
                mSearchResultsJSON = data;
                super.deliverResult(data);
            }



        };


    }

    @Override
    public void onLoadFinished(Loader<String> loader, String b) {
        Log.d(TAG, "AsyncTaskLoader's onLoadFinished called");
//        mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
        if (b != null) {
            System.out.println("GOOOOOOOOOOOOOOOOOOODDDDDDDDDDDDDDDDDDDDDDDDDDDD!!!");
            parse(b);
        } else {
            System.out.println("THERE IS NOTHING HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
           // mForecastItemsRV.setVisibility(View.INVISIBLE);
           // mLoadingErrorMessageTV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    public void parse(String b){

        try {
            JSONObject j = new JSONObject(b);
            JSONArray data = j.getJSONArray("data");
            for(int i = 0; i < 45; i++){
                String d,w,name;
                double t;

                JSONObject c = data.getJSONObject(i);
              //  JSONObject name = c.getJSONObject("main");
              //  JSONArray weath= c.getJSONArray("weather");
              //  JSONObject forecast = weath.getJSONObject(0);

                name = c.getString("name");
                System.out.println(name);
                beer.add(name);
               // d = c.getString("dt_txt");
               // t = main.getDouble("temp");
               // w = forecast.getString("main");

               // Weather weather_day = new Weather(d,t,w);
               // holder.add(weather_day);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




}
