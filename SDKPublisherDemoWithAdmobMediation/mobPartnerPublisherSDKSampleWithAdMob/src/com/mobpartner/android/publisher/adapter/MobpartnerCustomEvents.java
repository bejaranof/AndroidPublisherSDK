package com.mobpartner.android.publisher.adapter;

import android.app.Activity;

import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.customevent.CustomEventBanner;
import com.google.ads.mediation.customevent.CustomEventBannerListener;
import com.mobpartner.android.publisher.views.MobPartnerAdBanner;

public class MobpartnerCustomEvents implements CustomEventBanner{

	private MobPartnerAdBanner mBanner;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestBannerAd(CustomEventBannerListener listener, Activity activity, String label, 
			String serverParameter, AdSize size, MediationAdRequest mediationAdRequest, 
			Object customEventExtra) {
		// TODO Auto-generated method stub
		
		String poolID = serverParameter;
		mBanner = new MobPartnerAdBanner(activity, poolID);		
	
		//Displays banner
		mBanner.getMobPartnerAd();		
		listener.onReceivedAd(mBanner);
	}

}
