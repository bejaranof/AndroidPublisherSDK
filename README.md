## Introduction
MobPartnerSDK includes a software development kit (SDK) that enables you to promote other applications inside your app. This framework runs from Android 2.2 onwards and is MRAID 2.0 compliant.

Distinct components are available in this SDK:

A **banner** that is presented as part of an app. It can appear once the app has been launched.

An **interstitial** that present an app. It can be launched when the app start or at any point during the app experience.

A **MobWall** a component that will present applications grouped by categories with a detail mode. This can be used as a modal view or include it in a tabbar.

A **MobStream** is a native ad unit that displays users a primary app offer that suits the content they are viewing. Fully customizable and easily implemented via our SDK helps you increase your conversion rate by giving the users a non-intrusive experience.

A **MobbWidget** is a native ad unit, HTML-based allowing publishers to offer contextually relevant app suggestions to users, accelerating mobile revenue, and can be activated with a few lines of code, without the need for an SDK.

A **MobSearch** is a new native mobile advertising unit helping developers monetize their apps by delivering contextually relevant app suggestions - displayed below an app’s search field in real time, as the user inputs their query. It uses a predictive text algorithm to serve an ad based on a combination of the text entered into the search field and the user’s characteristics.

## Prerequisites
Before integrating the library you should make sure MobPartner provided you with a [**Pool ID**](http://www.mobpartner.com/).

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

###Display MobPartner Ad-units.
There are two ways of displaying our ad-units. Directly through XML or programatically.

####Through XML:

This only works for **banner**, **MobWidget** and **MobStream**


#####Add xlmns attribute to your layout file:
```xml
xmlns:mobpartner= http://schemas.android.com/apk/lib/com.mobpartner.android.publisher
```

#####Add an ad-unit view to your layout:

**Banners:**
```xml
<com.mobpartner.android.publisher.views.MobPartnerAdBanner
    android:id="@+id/banner"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    mobpartner:poolId="POOL_ID" />
```

**MobWidget:**
```xml
<com.mobpartner.android.publisher.views.MobPartnerMobWidget 
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	mobpartner:poolId="POOL_ID"/> 
```

**MobStream:**
```xml
<com.mobpartner.android.publisher.views.MobPartnerMobStream
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    mobpartner:poolId="POOL_ID" 
	mobpartner:dimension="DIMENSION"/>
```
**Note**: MobStream comes in five different dimensions (4x1, 4x2, 4x3, 2x2, 2x3). Please choose the dimension of your preference by assigning **"4x1"**, **"4x2"**, **"4x3"**, **"2x2"** or **"2x3"** to the dimension field.


####Programmatically:
You have the option of creating the MobPartner's advertisement view programmatically. After it is created you can add it to your layout.


#####MobPartner Banner

1. Instantiate and initialize your banner: `MobPartnerAdBanner banner = new MobPartnerAdBanner(CONTEXT, POOL_ID);`
2. Add the view to a layout: `bannerLayout.addView(banner);`
3. Display content: `banner.show();`  


#####MobPartner Interstitial

1. Instantiate and initialize your interstitial: `MobPartnerAdInterstitial interstitial = new MobPartnerAdInterstitial(CONTEXT, POOL_ID);`
2. Display it: `interstitial.show();`


#####MobPartner MobWall
1. Instantiate and initialize your Mobwall: `MobPartnerMobwall mobwall = new MobPartnerMobwall(CONTEXT, POOL_ID);`
2. Show mobwall view: `mobwall.show();`
3. To closs mobwall programatically: `mobwall.dismiss();`

#####MobPartner MobWidget
1. Instantiate and initialize your MobWidget: `MobPartnerMobWidget mobwidget = new MobPartnerMobWidget(CONTEXT, POOL_ID);`
2. Add the view to a layout: `mobwidgetLayout.addView(mobwidget);`
3. Show mobwall view: `mobwidget.show();`

#####MobPartner MobStream
1. Instantiate and initialize your MobStream: `MobPartnerMobStream mobstream = new MobPartnerMobStream(CONTEXT, POOL_ID, DIMENSION);`
2. Add the view to a layout: `mobstreamLayout.addView(mobstream);`
3. Show mobstream view: `mobstream.show();`

**Note**: MobStream comes in five different dimensions (4x1, 4x2, 4x3, 2x2, 2x3). Please choose the dimension of your preference by assigning **"4x1"**, **"4x2"**, **"4x3"**, **"2x2"** or **"2x3"** to the dimension field.


However, you also have the option to set the different parameters separately.

**Example:**

- **For DIMENSION "4x1":**
```java
MobPartnerMobStream mobstream = new MobPartnerMobStream(CONTEXT);
mobstream.setPoolId(POOL_ID);
mobstream.setDimension("4x1");
mobstream.show();
```

- **For DIMENSION "4x2":**
```java
MobPartnerMobStream mobstream = new MobPartnerMobStream(CONTEXT);
mobstream.setPoolId(POOL_ID);
mobstream.setDimension("4x2");
mobstream.show();
```
- **For DIMENSION "4x3":**
```java
MobPartnerMobStream mobstream = new MobPartnerMobStream(CONTEXT);
mobstream.setPoolId(POOL_ID);
mobstream.setDimension("4x3");
mobstream.show();
```

- **For DIMENSION "2x2";**
```java
MobPartnerMobStream mobstream = new MobPartnerMobStream(CONTEXT);
mobstream.setPoolId(POOL_ID);
mobstream.setDimension("2x2");
mobstream.show();
```

- **For DIMENSION "2x3";**
```java
MobPartnerMobStream mobstream = new MobPartnerMobStream(CONTEXT);
mobstream.setPoolId(POOL_ID);
mobstream.setDimension("2x3");
mobstream.show();
```

#####MobPartner MobSearch
1. Initialize your MobSearch: `MobPartnerMobSearch search = new MobPartnerMobSearch(this, POOL_ID);`

2. Insert the MobSearch banner inside a layout container.
- Create a layout container to contain the MobSearch:
`LinearLayout mobsearch = new LinearLayout(this);`
- Add the MobSearch inside the layout container.  
`layoutParam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);`       
`mobsearch.addView(search, layoutParam);`


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
onReady();
```

**Note**: The callbacks will only work if you create you view programatically.


## Demo Project
An Android demo project is available to test the different implementations and check the configurations. 
