/*

REFERENCES:

https://guides.codepath.com/android/using-the-recyclerview
https://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465
https://www.youtube.com/watch?v=XxW5scw-n9Q

*/
package com.example.android.basicweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vinod on 4/21/2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context con;
    private ArrayList<String> beer_data = new ArrayList<String>();



    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView t;
        private Button b;


        /*Constructor - views initialized belonging to RecyclerView*/
        public ViewHolder(View itemView) {

            super(itemView);
            t = (TextView)itemView.findViewById(R.id.recycle_text);




        }



    }

    /*Constructor handles data RecyclerView displays*/
    public RecyclerAdapter(Context context, ArrayList<String> w){
        this.con = context;
        beer_data = w;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View beer_view = inflater.inflate(R.layout.beer_layout, parent, false); //fetch xml file

        ViewHolder v = new ViewHolder(beer_view);

        return v;
    }

    /*Contents of each item in RecyclerView*/
    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int i){
        final int extra_i = i;
        viewHolder.t.setText("Beer");



    }



    @Override
    public int getItemCount() {
        return beer_data.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}
