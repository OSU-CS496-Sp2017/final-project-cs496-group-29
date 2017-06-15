package com.example.android.beer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.beer.utils.BeerUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by hessro on 5/10/17.
 */

public class BrewAdapter extends RecyclerView.Adapter<BrewAdapter.BrewItemViewHolder> {

    private ArrayList<BeerUtils.BrewItem> mBrewItems;
    private OnBrewItemClickListener mBrewItemClickListener;

    public interface OnBrewItemClickListener {
        void onBrewItemClick(BeerUtils.BrewItem brewItem);
    }

    public BrewAdapter(OnBrewItemClickListener clickListener) {
        mBrewItemClickListener = clickListener;
    }

    public void updateBrewItems(ArrayList<BeerUtils.BrewItem> brewItems) {
		mBrewItems = brewItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mBrewItems != null) {
            return mBrewItems.size();
        } else {
            return 0;
        }
    }

    @Override
    public BrewItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.beer_item, parent, false);
        return new BrewItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BrewItemViewHolder holder, int position) {
        holder.bind(mBrewItems.get(position));
    }

    class BrewItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       // private TextView mBrewDateTV;
        private TextView mBrewDescriptionTV;
        //private final SimpleDateFormat dateFormatter;

        public BrewItemViewHolder(View itemView) {
            super(itemView);
            //dateFormatter = new SimpleDateFormat(WeatherPreferences.getDefaultDateFormat());
            //mBrewDateTV = (TextView)itemView.findViewById(R.id.tv_brew_date);
            mBrewDescriptionTV = (TextView)itemView.findViewById(R.id.tv_beer_name);
            itemView.setOnClickListener(this);
        }

        public void bind(BeerUtils.BrewItem brewItem) {
			
            //String dateString = dateFormatter.format(brewItem.dateTime);
            String detailString = brewItem.name;
            //mBrewDateTV.setText(dateString);
            mBrewDescriptionTV.setText(detailString);
        }

        @Override
        public void onClick(View v) {
            BeerUtils.BrewItem brewItem = mBrewItems.get(getAdapterPosition());
            mBrewItemClickListener.onBrewItemClick(brewItem);
        }
    }
}