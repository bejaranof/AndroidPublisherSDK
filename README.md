## Introduction
This document describes the integration steps to include MobPartner Publisher library to your Android application.
This library allows you to display advertising **banners**, **interstitials**, **Mobwall**, **MobWidget**, **MobStream** and **MobSearch**.

## Prerequisites
Before integrating the library you should make sure MobPartner provided you with a **Pool ID**.

## Installations

- Add the libraries: [MobPartnerAndroidPublisherSDK.jar](https://github.com/MobPartner/AndroidPublisherSDK/raw/master/MobPartnerAndroidPublisherSDK4_0_1.jar) and [google-play-services_lib](https://github.com/MobPartner/AndroidPublisherSDK/tree/master/google-play-services_lib) to your project.

- In `Build Path`, make sure that the **MobPartnerAndroidPublisherSDK.jar** is listed in `Libraries` and selected 
in `Order and Export`.

- In `Properties`, under the `Android` tab, make sure that **google-play-services_lib** is added in the Library List section.


## Setup your project for Ad Display


###Add the Required Permission
Add the following to your **AndroidManifest.xml** file

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

###Add Required Layouts
####By XML:

Add xlmns attribute to your layout (required only for **banner**, **MobWidget** and **MobStream** display)
```xml
xmlns:mobpartner= http://schemas.android.com/apk/lib/com.mobpartner.android.publisher
```

#####For Banners:
```xml
<com.mobpartner.android.publisher.views.MobPartnerAdBanner
    android:id="@+id/banner"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    mobpartner:poolId="POOL_ID" />
```

#####For MobWidget: 
```xml
<com.mobpartner.android.publisher.views.MobPartnerMobWidget 
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	mobpartner:poolId="POOL_ID"/> 
```

#####For MobStream:
```xml
<com.mobpartner.android.publisher.views.MobPartnerMobStream
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    mobpartner:poolId="POOL_ID" 
	mobpartner:dimension="DIMENSION"/>
```
**Note**: MobStream comes in five different dimensions (4x1, 4x2, 4x3, 2x2, 2x3). Please choose the dimension of your preference by assigning **"4x1"**, **"4x2"**, **"4x3"**, **"2x2"** or **"2x3"** to the dimension field.


####Programmatically:
You also have the option of adding MobPartner's advertisement layout programmatically.The code below are only examples, you can adapt it to your needs.

#####For Banners:
```java
LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
banner = new MobPartnerAdBanner(this, POOL_ID);
banner.setLayoutParams(layoutParams); 
```


##Set your pool ID
Declare a global variable with your pool ID

```java
private String Pool_ID;
```

##Display MobPartner Advertisements

After setting the layout you can proceed to display the advertisement as the following:

###MobPartner Banner

1. Initialize your banner: `MobPartnerAdBanner banner = new MobPartnerAdBanner(this, POOL_ID);`
2. Retrieve the view from your activity layout: `banner = (MobPartnerAdBanner)findViewById(R.id.banner); `
3. Fetch content to fill your view: `banner.show`  


###MobPartner Interstitial

1. Initialize your interstitial: `MobPartnerAdInterstitial interstitial = new MobPartnerAdInterstitial(this, POOL_ID);`
2. Display it: `interstitial.show ();`


###MobPartner MobWall
1. Initialize your Mobwall: `MobPartnerMobwall mobwall = new MobPartnerMobwall(this, POOL_ID);`
2. Show mobwall view: `mobwall.show();`
3. To closs mobwall programatically: `mobwall.dismiss();`

###MobPartner MobWidget
1. Initialize your MobWidget: `MobPartnerMobWidget mobwidget = new MobPartnerMobWidget(this, POOL_ID);`
2. Show mobwall view: `mobwidget.show();`

###MobPartner MobStream
1. Initialize your MobStream: `MobPartnerMobStream mobstream = new MobPartnerMobStream(this, POOL_ID);`
2. Retrieve the view from your activity layout: `mobstream = (MobPartnerMobStream)findViewById(R.id.mobstream); `
3. Set the dimension of the view: `mobstream.setDimension(DIMENSION);`


- **For DIMENSION "4x1":**
```java
mobstream.setDimension("4x1");
```

- **For DIMENSION "4x2":**
```java
mobstream.setDimension("4x2");
```
- **For DIMENSION "4x3":**
```java
mobstream.setDimension("4x3");
```

- **For DIMENSION "2x2";**
```java
mobstream.setDimension("2x2");
```

- **For DIMENSION "2x3";**
```java
mobstream.setDimension("2x3");
```

4.Show mobstream view: `mobstream.show();`

###MobPartner MobSearch
1. Initialize your MobSearch: `MobPartnerMobSearch search = new MobPartnerMobSearch(this, POOL_ID);`

2. Insert the MobSearch banner inside a layout container.
- Create a layout container to contain the MobSearch:
`LinearLayout mobsearch = new LinearLayout(this);`
- Add the MobSearch inside the layout container.  
`layoutParam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);`       
`mobsearch.addView(search,layoutParam);`


3. Insert the layout container as header inside your List View that displays your search View results.
Example:
`ListView mListView = (ListView) findViewById(R.id.list_view);`
`mListView.addHeaderView(mobsearch);`
`mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));`


4. Inside your OnQueryTextListener that is linked to your search View Call: `search.queryTextChange(newText);`


## Callbacks

Our SDK provides several callbacks you might find useful according to your integration.
You just need to implement the **MobPartnerAdListener** interface and register it with the desired Ad Unit. 

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

- This callback is triggered when the SDK successfully loaded the ads.
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

- This callback is triggered when the ad-unit is ready to start fetching content. **This callback is required if you want to show ad right away.** 
```java 
onReady()
```

## Demo Project
An Android demo project is available to test the different implementations and check the configurations. 
