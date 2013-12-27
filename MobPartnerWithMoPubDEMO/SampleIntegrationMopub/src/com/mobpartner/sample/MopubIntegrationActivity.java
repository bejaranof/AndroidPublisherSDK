package com.mobpartner.sample;

import com.mobpartner.SampleIntegrationMopub.R;
import com.mopub.mobileads.MoPubView;

import android.os.Bundle;
import android.app.Activity;

public class MopubIntegrationActivity extends Activity {
	
	// Declare an instance variable for your MoPubView.
	private MoPubView moPubView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mopubintegration_activity);
	
	    moPubView = (MoPubView) findViewById(R.id.adview);
	    moPubView.setAdUnitId("YOUR_MOPUB_AD_UNIT_ID"); // Enter your Ad Unit ID from www.mopub.com
	    moPubView.loadAd();
	    
	}
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		moPubView.destroy();
	}
}
