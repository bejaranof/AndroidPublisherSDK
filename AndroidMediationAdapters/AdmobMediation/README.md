# Integrate MobPartner with AdMob

MobPartner is compatible with AdMob Mediation. The integration can be done with Custom Event Adapters.


### Configure a New Ad Network on the AdMob Platform

1.	Sign in to your **AdMob** Account [https://apps.admob.com](https://apps.admob.com)
2.	Click on **Monetize** Tab 
3.	Select the app you want to update under the **All apps list**.
4.	Click on **Ad source**.
5.	Click on **New ad network**.
6.	Select **Custom event**.
7.	Fill in the required fields.
	- **Class Name** : Name of the custom event class that you will implement in your app code that will show MobPartner’s ad.
	- **Label** : Name for the custom event.
	- **Parameter** : MobPartner’s Pool ID. If you don’t have it yet please retrieve it at MobPartner website. 	[http://www.mobpartner.com](http://www.mobpartner.com)
8.	Click **Continue**.



### Add AdMob Banner and/or Interstitial to your Project

1.	Set up your project with AdMob.
	- For **Eclipse** please refer to: 
https://developers.google.com/mobile-ads-sdk/docs/admob/android/eclipse?hl=hi-IN
	- For **Android Studio** please refer to: 
https://developers.google.com/mobile-ads-sdk/docs/admob/android/quick-start?hl=hi-IN
2.	Add [AdMob Banner](https://developers.google.com/mobile-ads-sdk/docs/admob/android/banner?hl=hi-IN) to your project. 
3.	Add [AdMob Interstitial](https://developers.google.com/mobile-ads-sdk/docs/admob/android/interstitial?hl=hi-IN) to your project.



### Add MobPartner Custom Event Adapter to Your Project
1.	Download, configure and import the latest MobPartner Android SDK ([MobPartnerAndroidPublisherSDK.jar](https://github.com/MobPartner/AndroidPublisherSDK)) to your project. Remember to configure your AndroidManifest.xml.
2.	Create a class in your application project with the same class name defined in your Custom Event. 
3.	Include the following code in your Custom Event Class.
**Note**: The Class name of your Custom Event must match the one you set up previously on AdMob.

**For Banners**:

```java

public class MobpartnerCustomEventsMoPubBanner implements CustomEventBanner{

    private MobPartnerAdBanner mBanner;
    private CustomEventBannerListener mListener;

	@Override
	protected void requestBannerAd(CustomEventBannerListener customEventBannerListener, Activity activity, String label, String serverParameter, AdSize adSize, MediationAdRequest mediationAdRequest, Object o) {		

		mListener = customEventBannerListener;
		String poolID = serverParameter;	
		mBanner = new MobPartnerAdBanner(activity, poolID);		
		mBanner.setMobPartnerAdListener(new MobPartnerAdListener() {

   		       	@Override
          		public void onReady() {
          			mBanner.show();
          			mListener.onReceivedAd(mBanner);
          		}

          		@Override
          		public void onStartDownloadAds() {
          		}

          		@Override
          		public void onLoadAdSucceeded() {
          		}

          		@Override
          		public void onLoadAdFailed(String s) {
          		}

          		@Override
          		public void onAdDisappeared() {
          		}
          	});	
	}

	@Override
	protected void destroy () {
		
	}	

}



```

**For Interstitial**:

```java
public class CustomEventAdapterInterstitial implements CustomEventInterstitial {

    private MobPartnerAdInterstitial mInterstitial;
    private CustomEventInterstitialListener mListener;


	@Override
	protected void requestInterstitialAd(CustomEventInterstitialListener customEventInterstitialListener, Activity activity, String label, String serverParameter, MediationAdRequest mediationAdRequest, Object o) {
		
		mListener = customEventInterstitialListener;
		String poolID = serverParameter;		
		mInterstitial= new MobPartnerAdInterstitial (activity, poolID);		
		mInterstitial.setMobPartnerAdListener(new MobPartnerAdListener() {

   		       	@Override
          		public void onReady() {
          			mListener. onReceivedAd();
          		}

          		@Override
          		public void onStartDownloadAds() {

          		}

          		@Override
          		public void onLoadAdSucceeded() {

          		}

          		@Override
          		public void onLoadAdFailed(String s) {

          		}

          		@Override
          		public void onAdDisappeared() {
				mListener.onDismissScreen();

          		}

          	});	
	
	}

	@Override
    	protected void showInterstitial() {
       		mInterstitial.show();
    	}

	@Override
	protected void destroy() {
		
	}	
}

```

For a complete sample please refer to the sample project [SampleWithAdMob]().


