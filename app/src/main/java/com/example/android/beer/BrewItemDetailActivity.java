package com.example.android.beer;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.beer.utils.BeerUtils;

import java.text.SimpleDateFormat;

public class BrewItemDetailActivity extends AppCompatActivity {

    private static final String BREW_HASHTAG = "#CS496FinalProject";

    private TextView mNameTV;
    private BeerUtils.BrewItem mBrewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_item_detail);

        mNameTV = (TextView)findViewById(R.id.tv_beer_name);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(BeerUtils.BrewItem.EXTRA_BEER_ITEM)) {
            mBrewItem = (BeerUtils.BrewItem)intent.getSerializableExtra(
                    BeerUtils.BrewItem.EXTRA_BEER_ITEM
            );
            fillInLayoutText(mBrewItem);
        }
    }
	
	private void fillInLayoutText(BeerUtils.BrewItem brewItem) {

        String name = brewItem.name;

        mNameTV.setText(name);
    }
}