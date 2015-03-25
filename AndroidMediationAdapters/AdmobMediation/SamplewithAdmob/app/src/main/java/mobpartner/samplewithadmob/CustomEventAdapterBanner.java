package mobpartner.samplewithadmob;

import android.app.Activity;
import android.util.Log;

import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.customevent.CustomEventBanner;
import com.google.ads.mediation.customevent.CustomEventBannerListener;
import com.mobpartner.android.publisher.views.MobPartnerAdBanner;
import com.mobpartner.android.publisher.views.MobPartnerAdListener;

public class CustomEventAdapterBanner implements CustomEventBanner {

    private MobPartnerAdBanner mBanner;
    private CustomEventBannerListener mListener;
    private String TAG = "MobPartner";

    @Override
    public void requestBannerAd(CustomEventBannerListener customEventBannerListener, Activity activity, String label, String serverParameter, AdSize adSize, MediationAdRequest mediationAdRequest, Object o) {

        mListener = customEventBannerListener;
        String poolID = serverParameter;
        Log.d(TAG, "------->> Banner POOL: " + poolID);
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
    public void destroy() {

    }
}
