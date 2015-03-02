# Integrate MobPartner with MoPub

MobPartner is compatible with MoPub Mediation. Two integration methods are provided : Custom Native Network (you will need to integrate the MobPartner SDK) and Custom Network (you will to use our Javascript HTML code).

## Custom Network - Javascript Integration Method
MoPub supports the integration of 3rd party Ad Network without SDK through Custom Network implementation. In order MoPub be able to support Ad Network that is not already listed, it will require the 3rd party Ad Network HTML code.

For a step by step documentation, please download the [Custom Network Javascript Integration Guide] (https://github.com/MobPartner/AndroidOther/blob/master/AndroidMediationAdapters/MoPubMediation/Custom_Network-MoPub_Mediation-MobPartner_Android_Version.pdf?raw=true).

## Custom Native Network - SDK Integration Method
MoPub supports the integration of 3rd party Ad Network SDKs like MobPartner through Custom Native Network implementation. In order MoPub be able to support Ad Network that is not already listed, it will require the development of a Custom Event class.

### Create a Custom Event on MoPub Website

- Log in to your MoPub account at  [https://app.mopub.com](https://app.mopub.com)

- Click on `Networks` Menu tab.

- Create an ad Network by clicking on `Add a Network` and select `Custom Native Network`.

- Fill in the **Title**, **Custom Event Class** and **Custom Event Class Data** then click `Save and Continue`.

	- **Title** : Ad Network label that you will assign to your customized ad network
	- **Custom Event Class** : class name that will be implemented in your code to show MobPartners’ ad. It should be a full class path from your application project.
	- **Custom Event Class Data** : data parameters that are required to be passed back to your Custom Event 	class; in this case your pool ID from MobPartner and it should be in JSON format.


### Add MoPub banner to your project

- Before starting with MoPub integration please make sure you added MobPartner's publisher SDK library to your project, you can find it at:
[https://github.com/MobPartner/AndroidPublisherSDK](https://github.com/MobPartner/AndroidPublisherSDK)

- Download and import the latest MoPub Android SDK library into your project. You can find it at [https://github.com/mopub/mopub-android-sdk/wiki/Getting-Started](https://github.com/mopub/mopub-android-sdk/wiki/Getting-Started). If you are not using any other third party ad network you only need **MoPub Android Base SDK**.

- Configure the AndroidManifest.xml with Mobpartners and MoPubs settings. You can find Mobpartner’s settings at [https://github.com/MobPartner/AndroidPublisherSDK](https://github.com/MobPartner/AndroidPublisherSDK).


```xml
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />

    <activity
        android:name="com.mopub.mobileads.MoPubActivity"
        android:configChanges="keyboardHidden|orientation|screenSize" />
    <activity
        android:name="com.mopub.mobileads.MraidActivity"
        android:configChanges="keyboardHidden|orientation|screenSize" />
    <activity
        android:name="com.mopub.common.MoPubBrowser"
        android:configChanges="keyboardHidden|orientation|screenSize" />
    <activity
        android:name="com.mopub.mobileads.MraidVideoPlayerActivity"
        android:configChanges="keyboardHidden|orientation|screenSize" />

    <meta-data android:name="com.google.android.gms.version"
        android:value="@integer/google_play_services_version" />

```
- Add **com.mopub.mobileads.MoPubView** into your XML layout to define your ad banner slot.
- Set the ad Unit ID and load MoPub ads in your Activity. Ad Unit ID and integration instructions can be found at: **Inventory** tab -> click on your app -> click on the desired ad Unit -> click on **Code Integration** tab on the top right corner.
- Create a class in your application project with the same class name defined in your Custom Network.
- Include the following code.

```java
public class MobpartnerCustomEventsMoPubBanner extends CustomEventBanner{

    private MobPartnerAdBanner mBanner;
    private CustomEventBannerListener mListener;


	@Override
	protected void loadBanner(Context context, CustomEventBannerListener listener,
			Map<String, Object>  localExtras, Map<String, String> serverExtras) {
		
		mListener = customEventBannerListener;
		String poolID = (String) serverExtras.get("pool");		
		mBanner = new MobPartnerAdBanner(context, poolID);		
	
		//Displays banner
		mBanner.setMobPartnerAdListener(new MobPartnerAdListener() {

   		       	@Override
          		public void onReady() {
          			mBanner.show();
          			mListener.onBannerLoaded(mBanner);
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
	protected void onInvalidate() {
		
	}	

}


```
### Add MoPub Interstitial to your project

- Before starting with MoPub integration please make sure you added MobPartner's publisher SDK library to your project, you can find it at:
[https://github.com/MobPartner/AndroidPublisherSDK](https://github.com/MobPartner/AndroidPublisherSDK)

- Download and import the latest MoPub Android SDK library into your project. You can find it at [https://github.com/mopub/mopub-android-sdk/wiki/Getting-Started](https://github.com/mopub/mopub-android-sdk/wiki/Getting-Started). If you are not using any other third party ad network you only need **MoPub Android Base SDK**.

- Configure the AndroidManifest.xml with Mobpartners and MoPubs settings. You can find Mobpartner’s settings at [https://github.com/MobPartner/AndroidPublisherSDK](https://github.com/MobPartner/AndroidPublisherSDK).

```xml
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />

    <activity
        android:name="com.mopub.mobileads.MoPubActivity"
        android:configChanges="keyboardHidden|orientation|screenSize" />
    <activity
        android:name="com.mopub.mobileads.MraidActivity"
        android:configChanges="keyboardHidden|orientation|screenSize" />
    <activity
        android:name="com.mopub.common.MoPubBrowser"
        android:configChanges="keyboardHidden|orientation|screenSize" />
    <activity
        android:name="com.mopub.mobileads.MraidVideoPlayerActivity"
        android:configChanges="keyboardHidden|orientation|screenSize" />

    <meta-data android:name="com.google.android.gms.version"
        android:value="@integer/google_play_services_version" />

```

- Create a class in your application project with the same class name defined in your Custom Network.
- Include the following code.

```java
public class CustomEventAdapterInterstitial extends CustomEventInterstitial{

    private MobPartnerAdInterstitial mInterstitial;
    private CustomEventInterstitialListener mListener;


	@Override
	protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> localExtras, 
		Map<String, String> serverExtras) {
		
		mListener = customEventInterstitialListener;
		String poolID = (String) serverExtras.get("pool");		
		mInterstitial= new MobPartnerAdInterstitial (context, poolID);		
	
		//Displays Interstitial
		mInterstitial.setMobPartnerAdListener(new MobPartnerAdListener() {

   		       	@Override
          		public void onReady() {
          			mListener. onInterstitialLoaded (mInterstitial);
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
				mListener. onInterstitialDismissed();

          		}

          	});	
	
	}

	@Override
    	protected void showInterstitial() {
       		mInterstitial.show();
    	}

	@Override
	protected void onInvalidate() {
		
	}	

}

```




For a step by step documentation, please download the [Custom Native Network SDK Integration Guide] (https://github.com/MobPartner/AndroidPublisherSDK/blob/master/AndroidMediationAdapters/MoPubMediation/MoPub%20Mediation%20-%20Custom%20Native%20Network.pdf?raw=true).


