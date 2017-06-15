package com.example.android.beer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.beer.utils.NetworkUtils;
import com.example.android.beer.utils.BeerUtils;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BrewAdapter.OnBrewItemClickListener, LoaderManager.LoaderCallbacks<String> {

	private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BREW_URL_KEY = "brewUrl";
    private static final int BREW_LOADER_ID = 0;

    /*Recycle View*/
    private RecyclerView mBrewItemsRV;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mLoadingErrorMessageTV;
    private BrewAdapter mBrewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mLoadingIndicatorPB = (ProgressBar)findViewById(R.id.pb_loading_indicator);
        mLoadingErrorMessageTV = (TextView)findViewById(R.id.tv_loading_error_message);
        mBrewItemsRV = (RecyclerView)findViewById(R.id.rv_beer_items);

        mBrewAdapter = new BrewAdapter(this);
        mBrewItemsRV.setAdapter(mBrewAdapter);
        mBrewItemsRV.setLayoutManager(new LinearLayoutManager(this));
        mBrewItemsRV.setHasFixedSize(true);

		String brewUrl = BeerUtils.buildBrewURL();
        Bundle argsBundle = new Bundle();
        argsBundle.putString(BREW_URL_KEY, brewUrl);

        getSupportLoaderManager().initLoader(BREW_LOADER_ID, argsBundle, this);
        //adapter.update(beer);

    }

	@Override
    public void onBrewItemClick(BeerUtils.BrewItem brewItem) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
        Intent intent = new Intent(this, BrewItemDetailActivity.class);
        intent.putExtra(BeerUtils.BrewItem.EXTRA_BEER_ITEM, brewItem);
        startActivity(intent);
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {

            String mBrewJSON;

            @Override
            protected void onStartLoading() {
                if (mBrewJSON != null) {
                    Log.d(TAG, "AsyncTaskLoader delivering cached forecast");
					//doWeatherSearch();
                    deliverResult(mBrewJSON);
                } else {
                    mLoadingIndicatorPB.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Override
            public String loadInBackground() {
                String brewUrl = args.getString(BREW_URL_KEY);
                if (brewUrl == null || brewUrl.equals("")) {
                    return null;
                }
                Log.d(TAG, "AsyncTaskLoader loading beers from url: " + brewUrl);

                String brewJSON = null;
                try {
                    brewJSON = NetworkUtils.doHTTPGet(brewUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return brewJSON;
            }

            @Override
            public void deliverResult(String brewJSON) {
                mBrewJSON = brewJSON;
                super.deliverResult(brewJSON);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String brewJSON) {
        Log.d(TAG, "AsyncTaskLoader load finished");
        mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
        if (brewJSON != null) {
			SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

            mLoadingErrorMessageTV.setVisibility(View.INVISIBLE);
            mBrewItemsRV.setVisibility(View.VISIBLE);
            ArrayList<BeerUtils.BrewItem> brewItems = BeerUtils.parseBrewJSON(brewJSON);
            mBrewAdapter.updateBrewItems(brewItems);
        } else {
            mBrewItemsRV.setVisibility(View.INVISIBLE);
            mLoadingErrorMessageTV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        // Empty
    }
}