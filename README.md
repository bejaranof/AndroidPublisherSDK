## Introduction
This document describes the integration steps to include MobPartner Publisher library for your Android application.
This library allows you to display advertising banners, interstitials and mobwall.

## Prerequisites
Before integrating the library you should be sure MobPartner provided you the **Pool ID**.

## Installations

- Add the library ‘MobPartnerAndroidPublisherSDK.jar’ to your project.

- In `Build Path`, make sure its listed in `Libraries` and selected in `Order and Export`.


## Setup your project for ad display


###Add the required Permission
Add the following to your **AndroidManifest.xml** file

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
```

###Add the required Activity
Add the following to your **AndroidManifest.xml** file (required only for **Mobwall**)
```xml
<activity
	android:name="com.mobpartner.android.publisher.views.MobPartnerMobwallActivity"
	android:theme="@android:style/Theme.NoTitleBar">
</activity>
```

###Add required Layouts
####By xml:

Add the following to your xml layout (required only for **banner** display)

- Add xlmns attribute to your layout 

```
xmlns:mobpartner= http://schemas.android.com/apk/lib/com.mobpartner.android.publisher 
```

- Add the banner to your XML layout 

```xml
<com.mobpartner.android.publisher.views.MobPartnerAdBanner
android:id="@+id/banner"
android:layout_width="fill_parent"
android:layout_height="wrap_content"
mobpartner:poolID=POOL_ID />
```

####Programatically:
Programmatically add your banner to your layout and initialize it. Code below is only an example. You can adapt it to your needs.

```java
LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
banner = new MobPartnerAdBanner(this, POOL_ID);
banner.setLayoutParams(layoutParams); 
```

###Set your pool ID
Declare a global variable with your pool ID

```java
private String Pool_ID;
```

###MobPartner Banner

1. Initialize your Banner: `banner = new MobPartnerAdBanner(this, POOL_ID);`



2. Retrieve the view in your activity: `banner = (MobPartnerAdBanner)findViewById(R.id.banner); `

3. Fetch content to fill your view: `banner.getMobPartnerAd();`


###MobPartner Interstitial

1. Initialize your Interstitial: `interstitial = new MobPartnerAdInterstitial(this, POOL_ID);`
2. Fetch content for your interstitial: `interstitial.fetchAds ();`
3. Display it: `interstitial.show ();`


###MobPartner MobWall
1. Initialize your Mobwall: `mobwall = new MobPartnerMobwall(this, POOL_ID);`
2. Show mobwall view: `mobwall.show();`


## Callbacks

Our SDK provides several callbacks you might find useful according to your integration.
You just need to implement the MobPartnerAdListener interface and register it with the banner or interstitial. 

```java
banner.setMobPartnerAdListener(listener);
```


- To personalize and treat each ad 

```java
banner.setMobPartnerAdListener(new MobPartnerAdListener() {
		//your code
		}) ;
```


###Callbacks

- For banner only. This callback is triggered when the displayed ad changes.
```java
onAdChanged(MobPartnerAdView adview, MobPartnerAdObject ad);
```

 
- This callback is triggered when the fetch of an ad starts.  
```java
onStartDownloadAds(MobPartnerAdView adView);
```

- This callback is triggered when the SDK successfully fetched the ads.
```java
onLoadAdSucceeded(MobPartnerAdView adView, MobPartnerAdCampaign ads); 
``` 

- This callback is triggered when the fetch of ads failed or when there is no ad served.
```java
onLoadFailed(MobPartnerAdView adView, String errorMessage); 
```
 
- This callback is triggered when the user clicks an Ad.
```java
onAdClicked(MobPartnerAdView adView, MobPartnerAdObject ad);
```

- For interstitial only. This callback is triggered when the interstitial disappears (automatically or because user dismissed it).
```java
onAdDisappeared(MobPartnerAdView adView); 
```




##Additional Parameters for Banners and Interstitials
- setKeyword - This parameter is forwarded to the advertiser for specific campaign.
```java
interstitial.setKeyword("key");
```

- setSubId - Any ID you need to have in your stats (for example sub affiliate value)
```java
interstitial.setSubId("subid");
```

- setOptional - 6 optional parameters can be sent to the adserver.
```java
interstitial.setOptional1("bla1");
```


## Demo Project
An Android demo project is available to test the different implementations and check the configurations. 

## Code Documentation
The framework's headers are documented and self explanatory.

