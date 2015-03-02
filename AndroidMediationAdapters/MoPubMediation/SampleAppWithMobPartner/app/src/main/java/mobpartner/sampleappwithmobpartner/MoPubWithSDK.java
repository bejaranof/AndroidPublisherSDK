package mobpartner.sampleappwithmobpartner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;

public class MoPubWithSDK extends Activity implements MoPubInterstitial.InterstitialAdListener{

    private MoPubView mMoPubView;
    private MoPubInterstitial mMoPubInterstitial;
    private Button bannerButton;
    private Button interstitialButtonLoad, interstitialButtonShow;

    final String BannerAdUnitId = "10c385322b464ed58ba223533383e619";
    final String InterstitalAdUnitId = "93e2a6ec69ac4dd7a49d8e199aa5fe67";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mopub_with_sdk);

        mMoPubView = (MoPubView) findViewById(R.id.banner_mopubview);
        bannerButton = (Button) findViewById(R.id.button_banner);
        interstitialButtonLoad = (Button) findViewById(R.id.button_interstitial_load);
        interstitialButtonShow = (Button) findViewById(R.id.button_interstitial_show);
        interstitialButtonShow.setEnabled(false);

        mMoPubInterstitial = new MoPubInterstitial(this, InterstitalAdUnitId);
        mMoPubInterstitial.setInterstitialAdListener(this);

        loadMoPubBannerAd();

        bannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMoPubBannerAd();
            }
        });

        interstitialButtonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoPubInterstitial.load();
            }
        });

        interstitialButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoPubInterstitial.show();

            }
        });
    }

    private void loadMoPubBannerAd(){
        mMoPubView.setAdUnitId(BannerAdUnitId);
        mMoPubView.setAutorefreshEnabled(true);
        mMoPubView.loadAd();
    }


    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        interstitialButtonShow.setEnabled(true);
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {

    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        interstitialButtonShow.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMoPubView.destroy();

        if (mMoPubInterstitial != null) {
            mMoPubInterstitial.destroy();
            mMoPubInterstitial = null;
        }
    }
}
