# How to Integrate MobPartner with MoPub

## Create a Custom Event on MoPub Website

- Log in to your MoPub account at  [https://app.mopub.com](https://app.mopub.com)

- Click on `Networks` Menu tab.

- Create an ad Network by clicking on `Add a Network` and select `Custom Native Network`.

- Fill in the **Title**, **Custom Event Class** and **Custom Event Class Data** then click `Save and Continue`.

	- **Title** : Ad Network label that you will assign to your customized ad network
	- **Custom Event Class** : class name that will be implemented in your code to show MobPartners’ ad.
	- **Custom Event Class Data** : data parameters that are required to be passed back to your Custom Event class; 	in this case your pool ID from MobPartner and it needs to be in JSON format.


## Add MoPub banner to your project

Before starting with Admob integration please make sure you added MobPartner's publisher SDK library to your project, you can find it at:
[https://github.com/MobPartner/AndroidPublisherSDK](https://github.com/MobPartner/AndroidPublisherSDK)

You can find the MoPub documentation below:
[https://github.com/mopub/mopub-android-sdk/wiki/Banner-Integration](https://github.com/mopub/mopub-android-sdk/wiki/Banner-Integration)

- Download and import the latest MoPub Android SDK library into your project. You can find it at [https://github.com/mopub/mopub-android-sdk/wiki/Getting-Started](https://github.com/mopub/mopub-android-sdk/wiki/Getting-Started). If you are not using any other third party ad network you only need **MobPub Android Base SDK**.
- Configure the AndroidManifest.xml with Mobpartners and MoPubs settings. You can find Mobpartner’s settings at [https://github.com/MobPartner/AndroidPublisherSDK](https://github.com/MobPartner/AndroidPublisherSDK).


```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<activity android:name="com.mopub.mobileads.MoPubActivity" android:configChanges="keyboardHidden|orientation"/>
<activity android:name="com.mopub.mobileads.MraidActivity" android:configChanges="keyboardHidden|orientation"/>
<activity android:name="com.mopub.mobileads.MraidBrowser" android:configChanges="keyboardHidden|orientation"/>
<activity android:name="com.mopub.mobileads.MraidVideoPlayerActivity" android:configChanges="keyboardHidden|orientation"/>


```
- Add **com.mopub.mobileads.MoPubView** into your XML layout to define your ad banner slot.
- Load MoPub ads. [https://github.com/mopub/mopub-android-sdk/wiki/Banner-Integration](https://github.com/mopub/mopub-android-sdk/wiki/Banner-Integration) 

- Implement the custom event created on the MoPub website by defining a class with the same class name set in the configuration in your project. This could be as the following.


	- **Your MobpartnerCustomEvent class should look like this**

```java
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

```
