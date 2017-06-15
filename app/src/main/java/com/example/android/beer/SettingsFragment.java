package com.example.android.beer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
		addPreferencesFromResource(R.xml.prefs);
		EditTextPreference searchPref = (EditTextPreference)findPreference(getString(R.string.pref_search_key));
		searchPref.setSummary(searchPref.getText());
	}
	
	@Override
	public void onResume() {
		super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(getString(R.string.pref_search_key))) {
			EditTextPreference searchPref = (EditTextPreference)findPreference(key);
			searchPref.setSummary(searchPref.getText());
		}
	}
}