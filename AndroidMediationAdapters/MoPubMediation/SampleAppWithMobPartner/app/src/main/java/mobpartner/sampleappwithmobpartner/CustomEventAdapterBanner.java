package mobpartner.sampleappwithmobpartner;

import android.content.Context;

import com.mobpartner.android.publisher.views.MobPartnerAdBanner;
import com.mobpartner.android.publisher.views.MobPartnerAdListener;
import com.mopub.mobileads.CustomEventBanner;

import java.util.Map;

public class CustomEventAdapterBanner extends CustomEventBanner {

    private MobPartnerAdBanner mBanner;
    private CustomEventBannerListener mListener;

    @Override
    protected void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {

        mListener = customEventBannerListener;
        String poolID = (String) serverExtras.get("pool");
        mBanner = new MobPartnerAdBanner(context, poolID);

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
