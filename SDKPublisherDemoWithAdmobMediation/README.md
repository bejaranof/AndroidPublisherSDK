# How to Integrate MobPartner with Admob Mediation

## Create a Custom Event on Admob Mediation Website

- Log in to your Admob Mediation account at [https://mediation.admob.com](https://mediation.admob.com)

- Click on `Site & Apps` then choose `Ad Network Mediation` tab.

- Create an ad Network Mediation Placement by clicking on `Add Network Mediation Placement` and save your settings.

- Click on `Manage Settings` from the network placement you just created.

- Then add a custom event by clicking on `Add Custom Event`.

- Enter your own personal information like the following :

	- **Label** : is the Ad Network label that will be printed on your `Add network Mediation Placement`, **you won’t be able to change this label later**.

	- **Class Name** : is the class name that will be implemented in your code to show MobPartner’s add.

	- **Parameter** : is the poolID given by MobPartner.


## Add Admob Banner to Your Project

Before starting with Admob integration please make sure you added MobPartner's publisher SDK library to your project, you can find it at:
[https://github.com/MobPartner/AndroidPublisherSDK](https://github.com/MobPartner/AndroidPublisherSDK)

You can find the Admob documentation here:
[https://developers.google.com/mobile-ads-sdk/docs/admob/mediation#android](https://developers.google.com/mobile-ads-sdk/docs/admob/mediation#android)

- Import the latest Admob SDK (GoogleAdMobAdsSDk-6.4.1.jar) to your project.

- Configure the AndroidManifest.xml with the following.

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<activity 
      android:name="com.google.ads.AdActivity"   android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"/>

```
- Add com.google.ads.Adview to your UI. [https://developers.google.com/mobile-ads-sdk/docs/admob/fundamentals#android] (https://developers.google.com/mobile-ads-sdk/docs/admob/fundamentals#android)

- Implement the custom event created on the Admob Mediation website by defining a class with the same class name set in the configuration in your project. This could be as the following.
Or you can just add [mobpartnerCustomEventBannerAdapter.jar] (https://github.com/MobPartner/AndroidPublisherSDK/blob/master/SDKPublisherDemoWithAdmobMediation/mobpartnerCustomEventBannerAdapter.jar) directly to your project.


	- **Your MobpartnerCustomEvent class should look like this**

```java
public class MobpartnerCustomEvents implements CustomEventBanner{

	private MobPartnerAdBanner mBanner;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestBannerAd(CustomEventBannerListener listener, Activity activity, 
String label,String serverParameter, AdSize size, 
MediationAdRequest mediationAdRequest, 
			Object customEventExtra) {
		// TODO Auto-generated method stub
		
		String poolID = serverParameter;
		mBanner = new MobPartnerAdBanner(activity, poolID);		
	
		//Displays banner
		mBanner.getMobPartnerAd();		
		listener.onReceivedAd(mBanner);
	}

}

```

You can find a demo project here with the name of [SDKPublisherDemoWithAdmobMediation](https://github.com/MobPartner/AndroidPublisherSDK/tree/master/SDKPublisherDemoWithAdmobMediation/mobPartnerPublisherSDKSampleWithAdMob)
