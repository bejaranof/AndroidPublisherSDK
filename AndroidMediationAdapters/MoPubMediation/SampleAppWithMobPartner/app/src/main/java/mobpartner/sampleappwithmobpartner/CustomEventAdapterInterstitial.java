package mobpartner.sampleappwithmobpartner;

import android.content.Context;

import com.mobpartner.android.publisher.views.MobPartnerAdInterstitial;
import com.mobpartner.android.publisher.views.MobPartnerAdListener;
import com.mopub.mobileads.CustomEventInterstitial;

import java.util.Map;

public class CustomEventAdapterInterstitial extends CustomEventInterstitial {

    private MobPartnerAdInterstitial mInterstitial;
    private CustomEventInterstitialListener mListener;

    @Override
    protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {

        mListener = customEventInterstitialListener;

        String poolID = (String) serverExtras.get("pool");
        mInterstitial = new MobPartnerAdInterstitial(context, poolID);

        mInterstitial.setMobPartnerAdListener(new MobPartnerAdListener() {
            @Override
            public void onReady() {

                mListener.onInterstitialLoaded();
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
                mListener.onInterstitialDismissed();
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
