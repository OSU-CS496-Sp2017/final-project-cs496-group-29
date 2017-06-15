package com.example.android.beer.utils;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BeerUtils {
	
	private final static String BREW_BASE_URL = "http://api.brewerydb.com/v2/beers";
	private final static String BREW_SEARCH_URL = "http://api.brewerydb.com/v2/search?type=beer";
	private final static String BREW_KEY_PARAM = "key";
	private final static String BREW_KEY = "bfd18adb9a71ebdd51a70d52815434d7";
	private final static String BREW_AVAILABLE_PARAM = "availableId";
	private final static String BREW_AVAILABLE = "5";
	private final static String BREW_ORDER_PARAM = "order";
	private final static String BREW_SORT_PARAM = "sort";
	private final static String BREW_SEARCH_PARAM = "q";
	
	public static class BrewItem implements Serializable {
        public static final String EXTRA_BEER_ITEM = "com.example.android.beer.utils.BrewItem.SearchResult";
        public String name;
		public Date createDate;
        public String descriptions;
        public String categoryName;
    }
	
	public static String buildBrewURL(String order, String sort) {
        return Uri.parse(BREW_BASE_URL).buildUpon()
                .appendQueryParameter(BREW_AVAILABLE_PARAM, BREW_AVAILABLE)
				.appendQueryParameter(BREW_ORDER_PARAM, order)
				.appendQueryParameter(BREW_SORT_PARAM, sort)
                .appendQueryParameter(BREW_KEY_PARAM, BREW_KEY)
                .build()
                .toString();
    }
	
	public static String newbuildBrewURL(String search) {
        return Uri.parse(BREW_SEARCH_URL).buildUpon()
                .appendQueryParameter(BREW_SEARCH_PARAM, search)
                .appendQueryParameter(BREW_KEY_PARAM, BREW_KEY)
                .build()
                .toString();
    }
	
	public static ArrayList<BrewItem> parseBrewJSON(String brewJSON) {
        try {
            JSONObject brewObj = new JSONObject(brewJSON);
            JSONArray brewList = brewObj.getJSONArray("data");

            ArrayList<BrewItem> brewItemsList = new ArrayList<BrewItem>();
            for (int i = 0; i < brewList.length(); i++) {
                BrewItem brewItem = new BrewItem();
                JSONObject brewListElem = brewList.getJSONObject(i);

				brewItem.name = brewListElem.getString("name");
				//brewItem.descriptions = brewListElem.getString("description");
				
				//String dateStr = brewListElem.getString("createDate");
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//Date cDate = sdf.parse(dateStr);
				//brewItem.createDate = cDate;

                //brewItem.categoryName = brewListElem.getJSONArray("style").getString("shortName");

                brewItemsList.add(brewItem);
            }
            return brewItemsList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}