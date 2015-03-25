package mobpartner.samplewithadmob;

import android.app.Activity;
import android.util.Log;

import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.customevent.CustomEventInterstitial;
import com.google.ads.mediation.customevent.CustomEventInterstitialListener;
import com.mobpartner.android.publisher.views.MobPartnerAdInterstitial;
import com.mobpartner.android.publisher.views.MobPartnerAdListener;

public class CustomEventAdapterInterstitial implements CustomEventInterstitial {

    private MobPartnerAdInterstitial mInterstitial;
    private CustomEventInterstitialListener mListener;
    private String TAG = "MobPartner";

    @Override
    public void requestInterstitialAd(CustomEventInterstitialListener customEventInterstitialListener, Activity activity, String label, String serverParameter, MediationAdRequest mediationAdRequest, Object o) {
        mListener = customEventInterstitialListener;

        String poolID = serverParameter;
        Log.d(TAG, "------->> Interstitial POOL: " + poolID);
        mInterstitial = new MobPartnerAdInterstitial(activity, poolID);

        mInterstitial.setMobPartnerAdListener(new MobPartnerAdListener() {
            @Override
            public void onReady() {
                mListener.onReceivedAd();

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
    public void showInterstitial() {
        mInterstitial.show();
    }


    @Override
    public void destroy() {

    }
}
