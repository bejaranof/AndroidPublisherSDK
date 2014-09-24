package com.mobpartner.sample;

import com.mobpartner.android.publisher.views.MobPartnerMobSearch;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.FrameLayout.LayoutParams;

@SuppressLint("NewApi")
public class MobSearchActivity extends Activity implements SearchView.OnQueryTextListener {

    private SearchView mSearchView;
    private ListView mListView;
    
    private final String[] mStrings = Countries.sCountryStrings;
    
    //MobSearch
    private MobPartnerMobSearch search;    
    private LinearLayout mobsearch;
    private LinearLayout.LayoutParams layoutParam;
    //-----

    @SuppressLint("InlinedApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.searchview_filter);

        String pooID = getIntent().getExtras().getString("poolID");
        
        //MobSearch
        search = new MobPartnerMobSearch(getBaseContext(), pooID);
        layoutParam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mobsearch = new LinearLayout(this);
        mobsearch.addView(search,layoutParam);
        //-----
        
        mSearchView = (SearchView) findViewById(R.id.search_view);        
        mListView = (ListView) findViewById(R.id.list_view);
        
        //MobSearch
        mListView.addHeaderView(mobsearch);
        //-----
        
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings));
        mListView.setTextFilterEnabled(true);        
        
        setupSearchView();
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true); 
        mSearchView.setQueryHint("Search Here");
    }


	@Override
    public boolean onQueryTextChange(String newText) {

        if (TextUtils.isEmpty(newText)) {
            mListView.clearTextFilter();
        } else {
        	
            mListView.setFilterText(newText.toString());
            search.queryTextChange(newText);
            
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    
}