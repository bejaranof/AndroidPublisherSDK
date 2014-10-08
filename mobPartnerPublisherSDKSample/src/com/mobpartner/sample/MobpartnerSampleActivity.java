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
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class MobpartnerSampleActivity extends Activity {
	
	private MobPartnerAdBanner mBanner;
	private MobPartnerMobStream mMobstream1, mMobstream2, mMobstream3, mMobstream4, mMobstream5;
	private LinearLayout mMobstream1layout, mMobstream2layout, mMobWidgetLayout, mMobstreamLayout;
	private MobPartnerAdInterstitial mInterstitial;
	private MobPartnerMobWall mMobwall;
	private MobPartnerMobWidget mMobWidget;
	private String mPoolID;
	private EditText poolID;
	private LinearLayout mobstreamLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mobpartner_example);
		
		//Get MobPartnerAdBanner View Instance			
		mBanner = (MobPartnerAdBanner)findViewById(R.id.banner);
		
		mMobstreamLayout = (LinearLayout) findViewById(R.id.mobstream_layout);
		mMobstream1layout = (LinearLayout)findViewById(R.id.mobstream1layout);
		mMobstream2layout = (LinearLayout)findViewById(R.id.mobstream2layout);
		mMobstream3 = (MobPartnerMobStream)findViewById(R.id.mobstream3);
		mMobstream4 = (MobPartnerMobStream)findViewById(R.id.mobstream4);
		mMobstream5 = (MobPartnerMobStream)findViewById(R.id.mobstream5);
		
		mMobWidgetLayout = (LinearLayout)findViewById(R.id.mobwidgetlayout);		
		mobstreamLayout = (LinearLayout) findViewById(R.id.mobstream_layout);
		
		poolID = (EditText)findViewById(R.id.editText1);
		mPoolID = poolID.getText().toString();
		
		
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
		//Instantiate Interstitial instance
		mInterstitial = new MobPartnerAdInterstitial(this, mPoolID);
		
		//Set listener to know any ad status notification
		mInterstitial.setMobPartnerAdListener(new MobPartnerAdListener() {
			
			@Override
			public void onStartDownloadAds() {
				
			}
			
			@Override
			public void onReady() {
				//Display Interstitial
				mInterstitial.show();
			}
			
			@Override
			public void onLoadAdSucceeded() {
				
			}
			
			@Override
			public void onLoadAdFailed(String arg0) {
				
			}
			
			@Override
			public void onAdDisappeared() {
				
			}
		});		
		
	}
	
	
	public void showBanner (View view){				
		mMobWidgetLayout.removeAllViews();
		mBanner.setVisibility(View.VISIBLE);		
		
		//Set listener to know any ad status notification
		mBanner.setMobPartnerAdListener(mBannerListener);
		
		//Configure banner parameter
		mBanner.setPoolId(mPoolID);
		
		//Displays banner
		mBanner.show();
		
	}
	
	public void showMobStream (View view){	
		mMobstream1layout.removeAllViews();
		mMobstream2layout.removeAllViews();
		
		mobstreamLayout.setVisibility(View.VISIBLE);
		
		//Example 1: Instantiate MobStream with context, poolID and Dimension
		mMobstream1 = new MobPartnerMobStream(this, mPoolID, "4x1");
		
		//Use this listener to show MobStream right away
		mMobstream1.setMobPartnerAdListener(new MobPartnerAdListener() {
			
			@Override
			public void onStartDownloadAds() {
				
			}
			
			@Override
			public void onReady() {
				mMobstream1.show();
				mMobstream1layout.addView(mMobstream1);
			}
			
			@Override
			public void onLoadAdSucceeded() {
				
			}
			
			@Override
			public void onLoadAdFailed(String arg0) {
				
			}
			
			@Override
			public void onAdDisappeared() {
				
			}
		});
		
		
		//Example2: Instantiate MobStream only with context
		mMobstream2 = new MobPartnerMobStream(this);
		
		//Configure banner parameter
		mMobstream2.setPoolId(mPoolID);
		mMobstream2.setDimension("4x2");
		
		//Use this listener to show MobStream right away
		mMobstream2.setMobPartnerAdListener(new MobPartnerAdListener() {
			
			@Override
			public void onStartDownloadAds() {
				
			}
			
			@Override
			public void onReady() {
				mMobstream2.show();
				mMobstream2layout.addView(mMobstream2);
			}
			
			@Override
			public void onLoadAdSucceeded() {
				
			}
			
			@Override
			public void onLoadAdFailed(String arg0) {
				
			}
			
			@Override
			public void onAdDisappeared() {
				
			}
		});

		//Example3: configure MobStream parameter instantiated directly from the layout
		mMobstream3.setPoolId(mPoolID);
		mMobstream3.show();		
		
		mMobstream4.setPoolId(mPoolID);
		mMobstream4.setDimension("2x2");
		mMobstream4.show();
		
		mMobstream5.setPoolId(mPoolID);
		mMobstream5.setDimension("2x3");
		mMobstream5.show();
		
		
	}

	public void mobwallShow (View view){
		//Instantiate MobPartnerMobwall
		mMobwall = new MobPartnerMobWall(this, mPoolID);
		
		//Use this listener to show MobStream right away
		mMobwall.setMobPartnerAdListener(new MobPartnerAdListener() {
			
			@Override
			public void onStartDownloadAds() {
				
			}
			
			@Override
			public void onReady() {
				//Call and show Mobwall
				mMobwall.show();
			}
			
			@Override
			public void onLoadAdSucceeded() {
				
			}
			
			@Override
			public void onLoadAdFailed(String arg0) {
				
			}
			
			@Override
			public void onAdDisappeared() {
				
			}
		});
				

	}
	
	public void showMobWidget (View view){
		mMobWidgetLayout.removeAllViews();
		mMobWidgetLayout.setVisibility(View.VISIBLE);		
		mBanner.setVisibility(View.GONE);
		
		//Instantiate MobWidget
		mMobWidget = new MobPartnerMobWidget(this);
		
		mMobWidgetLayout.addView(mMobWidget);	
		
		//Set MobWidget poolID parameter
		mMobWidget.setPoolId(mPoolID);
					
		//Use this listener to show MobStream right away
		mMobWidget.setMobPartnerAdListener(new MobPartnerAdListener() {
			
			@Override
			public void onStartDownloadAds() {
				
			}
			
			@Override
			public void onReady() {
				//Call and show Mobwall
				mMobWidget.show();
				
			}
			
			@Override
			public void onLoadAdSucceeded() {
				
			}
			
			@Override
			public void onLoadAdFailed(String arg0) {
				
			}
			
			@Override
			public void onAdDisappeared() {
				
			}
		});		
		
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
			
		}

		@Override
		public void onReady() {
			Toast.makeText(getApplicationContext(), "Banner ready to be displayed", 10000).show();
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
