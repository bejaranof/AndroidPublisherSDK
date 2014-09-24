package com.mobpartner.sample;

import com.mobpartner.android.publisher.views.MobPartnerAdBanner;
import com.mobpartner.android.publisher.views.MobPartnerAdInterstitial;
import com.mobpartner.android.publisher.views.MobPartnerAdListener;
import com.mobpartner.android.publisher.views.MobPartnerMobStream;
import com.mobpartner.android.publisher.views.MobPartnerMobWall;
import com.mobpartner.android.publisher.views.MobPartnerMobWidget;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class MobpartnerSampleActivity extends Activity {
	
	private MobPartnerAdBanner mBanner;
	private MobPartnerMobStream mMobstream1, mMobstream2, mMobstream3, mMobstream4, mMobstream5;
	private MobPartnerAdInterstitial mInterstitial;
	private MobPartnerMobWall mMobwall;
	private MobPartnerMobWidget mMobWidget;
	private static String LOG_TAG="MobpartnerSampleApp";
	private String mPoolID;
	private EditText poolID;
	private LinearLayout mobstreamLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mobpartner_example);
		
		//Get MobPartnerAdBanner View Instance
		mBanner = (MobPartnerAdBanner)findViewById(R.id.banner);
		mMobstream1 = (MobPartnerMobStream)findViewById(R.id.mobstream1);
		mMobstream2 = (MobPartnerMobStream)findViewById(R.id.mobstream2);
		mMobstream3 = (MobPartnerMobStream)findViewById(R.id.mobstream3);
		mMobstream4 = (MobPartnerMobStream)findViewById(R.id.mobstream4);
		mMobstream5 = (MobPartnerMobStream)findViewById(R.id.mobstream5);
		
		mMobWidget = (MobPartnerMobWidget)findViewById(R.id.mobwidget);
		
		mobstreamLayout = (LinearLayout) findViewById(R.id.mobstream_layout);
		poolID = (EditText)findViewById(R.id.editText1);
		mPoolID = poolID.getText().toString();
		
		//Initialize Mobwall instance
		mMobwall = new MobPartnerMobWall(this, mPoolID);
		
		//Initialize Interstitial instance
		mInterstitial = new MobPartnerAdInterstitial(this, mPoolID);
		
		poolID.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				mPoolID = poolID.getText().toString();
			}
		});
				
	}
	
	public void interstitialShow (View view){	
		//Set listener to know any ad status notification
		mInterstitial.setMobPartnerAdListener(mInsterstitialListener);
		
		//Display Interstitial
		mInterstitial.show();
		
	}
	
	
	public void showBanner (View view){				
		mMobWidget.setVisibility(View.GONE);		
		mBanner.setVisibility(View.VISIBLE);
		
		//Set listener to know any ad status notification
		mBanner.setMobPartnerAdListener(mBannerListener);
		
		//Configure banner parameter
		mBanner.setPoolId(mPoolID);
		
		//Displays banner
		mBanner.show();
		
	}
	
	public void showMobStream (View view){	
		mobstreamLayout.setVisibility(View.VISIBLE);
		
		//Configure banner parameter
		mMobstream1.setPoolId(mPoolID);
		mMobstream2.setPoolId(mPoolID);
		mMobstream3.setPoolId(mPoolID);
		mMobstream4.setPoolId(mPoolID);
		mMobstream5.setPoolId(mPoolID);
		
		mMobstream1.setDimension("4x1");
		mMobstream2.setDimension("4x2");
		mMobstream3.setDimension("4x3");
		mMobstream4.setDimension("2x2");
		mMobstream5.setDimension("2x3");
		
		//Displays banner
		mMobstream1.show();
		mMobstream2.show();
		mMobstream3.show();
		mMobstream4.show();
		mMobstream5.show();
		
	}

	public void mobwallShow (View view){
		//Call and show Mobwall
		mMobwall.show();
	}
	
	public void showMobWidget (View view){
		mMobWidget.setVisibility(View.VISIBLE);		
		mBanner.setVisibility(View.GONE);
		
		//Initialize Mobwall instance
		mMobWidget.setPoolId(mPoolID);
		//Call and show Mobwall
		mMobWidget.show();
	}
	
	public void mobsearchShow (View view){
		Intent intent = new Intent(getApplicationContext(), MobSearchActivity.class);
		intent.putExtra("poolID", mPoolID);  
		startActivity(intent);		
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
