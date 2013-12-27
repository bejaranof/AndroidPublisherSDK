package com.mobpartner.android.publisher.adapter;

import java.util.Map;

import android.content.Context;

import com.mobpartner.android.publisher.views.MobPartnerAdBanner;
import com.mopub.mobileads.CustomEventBanner;

public class MobpartnerCustomEventsMoPub extends CustomEventBanner{

	private MobPartnerAdBanner mBanner;


	@Override
	protected void loadBanner(Context context, CustomEventBannerListener listener,
			Map<String, Object>  parameter1, Map<String, String> parameter2) {
		// TODO Auto-generated method stub
		
		String poolID = (String) parameter2.get("pool");		
		mBanner = new MobPartnerAdBanner(context, poolID);		
	
		//Displays banner
		mBanner.getMobPartnerAd();		
		listener.onBannerLoaded(mBanner);
		
	}

	@Override
	protected void onInvalidate() {
		// TODO Auto-generated method stub
		
	}

	

	

}
