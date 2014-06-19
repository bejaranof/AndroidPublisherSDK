## Introduction
This document describes the integration steps to include MobPartner Publisher library to your Android application.
This library allows you to display advertising banners, interstitials, Mobwall, MobWidget and MobStream.

## Prerequisites
Before integrating the library you should make sure MobPartner provided you with a **Pool ID**.

## Installations

- Add the library [MobPartnerAndroidPublisherSDK.jar](https://github.com/MobPartner/AndroidPublisherSDK/raw/master/MobPartnerAndroidPublisherSDK3_0.jar) to your project.

- In `Build Path`, make sure its listed in `Libraries` and selected in `Order and Export`.


## Setup your Project for Ad Display


###Add the Required Permission
Add the following to your **AndroidManifest.xml** file

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
```

###Add Required Layouts
####By XML:

Add xlmns attribute to your layout (required only for **banner** and **MobStream** display)
```xml
xmlns:mobpartner= http://schemas.android.com/apk/lib/com.mobpartner.android.publisher
```

Add the following to your xml layout (required only for **banner** and **MobStream** display)

#####For Banners:
```xml
<com.mobpartner.android.publisher.views.MobPartnerAdBanner
    android:id="@+id/banner"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    mobpartner:poolID="POOL_ID" />
```

#####For MobStream:
```xml
<com.mobpartner.android.publisher.views.MobPartnerMobStream
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    mobpartner:poolID="POOL_ID" />
```

####Programmatically:
Programmatically add your banner to your layout and initialize. The code below is only an example and you can adapt it to your needs.

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

1. Initialize your banner: `banner = new MobPartnerAdBanner(this, POOL_ID);`
2. Retrieve the view in your activity: `banner = (MobPartnerAdBanner)findViewById(R.id.banner); `
3. Fetch content to fill your view: `banner.show`  


###MobPartner Interstitial

1. Initialize your interstitial: `interstitial = new MobPartnerAdInterstitial(this, POOL_ID);`
2. Display it: `interstitial.show ();`


###MobPartner MobWall
1. Initialize your Mobwall: `mobwall = new MobPartnerMobwall(this, POOL_ID);`
2. Show mobwall view: `mobwall.show();`

###MobPartner MobWidget
1. Initialize your MobWidget: `mobwidget = new MobPartnerMobWidget(this, POOL_ID);`
2. Show mobwall view: `mobwidget.show();`

###MobPartner MobStream
1. Initialize your MobStream: `mobstream = new MobPartnerMobStream(this, POOL_ID);`
2. Retrieve the view in your activity: `mobstream = (MobPartnerMobStream)findViewById(R.id.mobstream); `
2. Show mobwall view: `mobstream.show();`


## Callbacks

Our SDK provides several callbacks you might find useful according to your integration.
You just need to implement the MobPartnerAdListener interface and register it with the desired Ad Unit. 

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
 
- This callback is triggered when the fetch of an ad starts.  
```java
onStartDownloadAds();
```

- This callback is triggered when the SDK successfully fetched the ads.
```java
onLoadAdSucceeded(); 
``` 

- This callback is triggered when the fetch of ads failed or when there is no ad served.
```java
onLoadFailed(String errorMessage); 
```

- This callback is triggered when the Interstitial and MobWall disappear (automatically or because user dismissed it)
```java
onAdDisappeared(); 
```

## Demo Project
An Android demo project is available to test the different implementations and check the configurations. 

## Code Documentation
The framework's headers are documented and self explanatory.

