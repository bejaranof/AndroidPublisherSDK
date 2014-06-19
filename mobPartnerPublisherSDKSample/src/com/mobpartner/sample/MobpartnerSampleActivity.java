package com.mobpartner.sample;

import com.mobpartner.android.publisher.views.MobPartnerAdBanner;
import com.mobpartner.android.publisher.views.MobPartnerAdInterstitial;
import com.mobpartner.android.publisher.views.MobPartnerAdListener;
import com.mobpartner.android.publisher.views.MobPartnerMobStream;
import com.mobpartner.android.publisher.views.MobPartnerMobWall;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class MobpartnerSampleActivity extends Activity {
	
	private MobPartnerAdBanner mBanner;
	private MobPartnerMobStream mMobstream;
	private MobPartnerAdInterstitial mInterstitial;
	private MobPartnerMobWall mMobwall;
	private static String LOG_TAG="MobpartnerSampleApp";
	private String mPoolID;
	private EditText poolID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mobpartner_example);
		
		//Get MobPartnerAdBanner View Instance
		mBanner = (MobPartnerAdBanner)findViewById(R.id.banner);
		mMobstream = (MobPartnerMobStream)findViewById(R.id.mobstream);
		
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
				
	}
	
	public void interstitialShow (View view){
		//Initialize an interstitial ad
		mInterstitial = new MobPartnerAdInterstitial(this, mPoolID);
	
		//Set listener to know any ad status notification
		mInterstitial.setMobPartnerAdListener(mInsterstitialListener);
		
		//Display Interstitial
		mInterstitial.show();
		
	}
	
	
	public void showBanner (View view){		
		mMobstream.setVisibility(View.GONE);
		mBanner.setVisibility(View.VISIBLE);
		
		//Set listener to know any ad status notification
		mBanner.setMobPartnerAdListener(mBannerListener);
		
		//Configure banner parameter
		mBanner.setPoolId(mPoolID);
		
		//Displays banner
		mBanner.show();
		
	}
	
	public void showMobStream (View view){	
		mBanner.setVisibility(View.GONE);
		mMobstream.setVisibility(View.VISIBLE);
		
		//Configure banner parameter
		mMobstream.setPoolId(mPoolID);
		
		//Displays banner
		mMobstream.show();
		
	}

	public void mobwallShow (View view){
		//Initialize Mobwall instance
		mMobwall = new MobPartnerMobWall(this, mPoolID);
		//Call and show Mobwall
		mMobwall.show();
	}
	
	
	private MobPartnerAdListener mBannerListener = new MobPartnerAdListener() {
		
		@Override
		public void onStartDownloadAds() {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Downloading Banner Ads", 3000).show();
		}
		
		@Override
		public void onLoadAdSucceeded() {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Banner Ad loading succeeded", 3000).show();
		}
		
		@Override
		public void onLoadAdFailed(String arg1) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "Banner Ad load failed, please make sure you inserted your poolID", 10000).show();
		}
		
		@Override
		public void onAdDisappeared() {
			// TODO Auto-generated method stub
			
		}

	};
	
	private MobPartnerAdListener mInsterstitialListener = new MobPartnerAdListener() {
		
		@Override
		public void onStartDownloadAds() {
			// TODO Auto-generated method stub
			Log.i(LOG_TAG, "Downloading Interstitial Ads");
		}
		
		@Override
		public void onLoadAdSucceeded() {
			// TODO Auto-generated method stub
			Log.i(LOG_TAG, "Interstitial Load succeeded");
		}
		
		@Override
		public void onLoadAdFailed(String arg1) {
			// TODO Auto-generated method stub
			Log.i(LOG_TAG, "Interstitial load failed, please check if there is any Interstitial in your pool");
		}
		
		@Override
		public void onAdDisappeared() {
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
	        mBanner.show();
        }
    }

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
        if(mInterstitial != null) mInterstitial.dismiss();
	}


}
