package com.example.android.beer;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.text.SimpleDateFormat;

import com.example.android.beer.utils.BeerUtils;

public class BrewItemDetailActivity extends AppCompatActivity {

    private static final String BREW_HASHTAG = "#CS496FinalProject";
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, MMM d, h:mm a");

    private TextView mNameTV;
	private TextView mDateTV;
    private BeerUtils.BrewItem mBrewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_item_detail);

        mNameTV = (TextView)findViewById(R.id.tv_beer_name);
		mDateTV = (TextView)findViewById(R.id.tv_date);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(BeerUtils.BrewItem.EXTRA_BEER_ITEM)) {
            mBrewItem = (BeerUtils.BrewItem)intent.getSerializableExtra(
                    BeerUtils.BrewItem.EXTRA_BEER_ITEM
            );
            fillInLayoutText(mBrewItem);
        }
    }
	
	private void fillInLayoutText(BeerUtils.BrewItem brewItem) {

        String name = "Brew Name: " + brewItem.name;
		String cDate = "Brew made on: " +  DATE_FORMATTER.format(mBrewItem.createDate);

        mNameTV.setText(name);
		mDateTV.setText(cDate);
    }
}