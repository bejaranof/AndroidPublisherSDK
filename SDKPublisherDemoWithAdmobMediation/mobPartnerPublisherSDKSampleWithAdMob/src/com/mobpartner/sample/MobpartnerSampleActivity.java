package com.mobpartner.sample;


import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.mobpartner.android.publisher.http.MobPartnerAdCampaign;
import com.mobpartner.android.publisher.http.MobPartnerAdObject;
import com.mobpartner.android.publisher.views.MobPartnerAdBanner;
import com.mobpartner.android.publisher.views.MobPartnerAdInterstitial;
import com.mobpartner.android.publisher.views.MobPartnerAdListener;
import com.mobpartner.android.publisher.views.MobPartnerAdView;
import com.mobpartner.android.publisher.views.MobPartnerMobwall;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class MobpartnerSampleActivity extends Activity {
	
	private Button mBtnInterstitialShow;
	private Button mBtnBanner;
	private MobPartnerAdBanner mBanner;
	private MobPartnerAdInterstitial mInterstitial;
	private MobPartnerMobwall mMobwall;
	private static String LOG_TAG="MobpartnerSampleApp";
	private String mPoolID;
	private EditText poolID;
	
	//For Admob mediation
	private AdView adView;
	private String AD_UNIT_ID = "Admob_Mediation_AD_Unit_ID";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mobpartner_example);
		
		//Get MobPartnerAdBanner View Instance
		mBanner = (MobPartnerAdBanner)findViewById(R.id.bannerdisplay);
		
		mBtnBanner = (Button)findViewById(R.id.button2);	
		mBtnInterstitialShow = (Button)findViewById(R.id.button1);
		
		poolID = (EditText)findViewById(R.id.editText1);
		mPoolID = poolID.getText().toString();
		
		poolID.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				mPoolID = poolID.getText().toString();
			}
		});
		
		//------- Used only with Admob mediation ---------
		LinearLayout layout = (LinearLayout)findViewById(R.id.layoutBanner);
		adView = new AdView(this, AdSize.SMART_BANNER, AD_UNIT_ID);
		
	    // Initiate a generic request.
	    AdRequest adRequest = new AdRequest();

	    // Load the adView with the ad request.
	    adView.loadAd(adRequest);
	    layout.addView(adView);
	    
	    //------------------------------------------------
	}
	
	public void interstitialShow (View view){
		//Initialize an interstitial ad
		mInterstitial = new MobPartnerAdInterstitial(this, mPoolID);
	
		//Set listener to know any ad status notification
		mInterstitial.setMobPartnerAdListener(mInsterstitialListener);

		//Set interstitial parameters
		mInterstitial.fetchAds();
		
		//Display Interstitial
		mInterstitial.show();
		
	}
	
	
	public void refreshBanner (View view){
		
		if(mBanner != null) mBanner.stopOrPauseMobPartnerAd();
		
		//Set listener to know any ad status notification
		mBanner.setMobPartnerAdListener(mBannerListener);
		
		//Configure banner parameter
		mBanner.setPoolId(mPoolID);
		
		//Displays banner
		mBanner.getMobPartnerAd();
		
	}

	public void mobwallShow (View view){
		//Initialize Mobwall instance
		mMobwall = new MobPartnerMobwall(this, mPoolID);
		//Call and show Mobwall
		mMobwall.show();
				
	}
	
	
	private MobPartnerAdListener mBannerListener = new MobPartnerAdListener() {
		
		@Override
		public void onStartDownloadAds(MobPartnerAdView arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Downloading Banner Ads", 3000).show();
		}
		
		@Override
		public void onLoadAdSucceeded(MobPartnerAdView arg0,
				MobPartnerAdCampaign arg1) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Banner Ad loading succeeded", 3000).show();
		}
		
		@Override
		public void onLoadAdFailed(MobPartnerAdView arg0, String arg1) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Banner Ad load failed, please make sure you inserted your poolID", 10000).show();
		}
		
		@Override
		public void onAdDisappeared(MobPartnerAdView arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAdClicked(MobPartnerAdView arg0, MobPartnerAdObject arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAdChanged(MobPartnerAdView arg0, MobPartnerAdObject arg1) {
			// TODO Auto-generated method stub
			
		}

	};
	
	private MobPartnerAdListener mInsterstitialListener = new MobPartnerAdListener() {
		
		@Override
		public void onStartDownloadAds(MobPartnerAdView arg0) {
			// TODO Auto-generated method stub
			Log.i(LOG_TAG, "Downloading Interstitial Ads");
		}
		
		@Override
		public void onLoadAdSucceeded(MobPartnerAdView arg0,
				MobPartnerAdCampaign arg1) {
			// TODO Auto-generated method stub
			Log.i(LOG_TAG, "Interstitial Load succeeded");
		}
		
		@Override
		public void onLoadAdFailed(MobPartnerAdView arg0, String arg1) {
			// TODO Auto-generated method stub
			Log.i(LOG_TAG, "Interstitial load failed, please check if there is any Interstitial in your pool");
		}
		
		@Override
		public void onAdDisappeared(MobPartnerAdView arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAdClicked(MobPartnerAdView arg0, MobPartnerAdObject arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAdChanged(MobPartnerAdView arg0, MobPartnerAdObject arg1) {
			// TODO Auto-generated method stub
			
		}
	};
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mobpartner_example, menu);
		return true;
	}
	

	@Override
    protected void onPause() {
        super.onPause();	        
        if(mBanner != null) mBanner.stopOrPauseMobPartnerAd();
        if(mInterstitial != null) mInterstitial.dismiss();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(mBanner != null) {
        	mBanner.stopOrPauseMobPartnerAd();
	        mBanner.getMobPartnerAd();
        }
    }

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
        if(mBanner != null) mBanner.stopOrPauseMobPartnerAd();
        if(mInterstitial != null) mInterstitial.dismiss();
	}


    
    

}
