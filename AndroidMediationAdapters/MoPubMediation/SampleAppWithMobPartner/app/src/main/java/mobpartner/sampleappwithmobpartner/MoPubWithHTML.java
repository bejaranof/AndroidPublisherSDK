package mobpartner.sampleappwithmobpartner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;

public class MoPubWithHTML extends Activity implements MoPubInterstitial.InterstitialAdListener{

    private MoPubView mMoPubView;
    private MoPubInterstitial mMoPubInterstitial;
    private Button bannerButton;
    private Button interstitialButtonLoad, interstitialButtonShow;
    final String BannerAdUnitId = "3bbfb17fb43b4079a723b966caa046e7";
    final String InterstitalAdUnitId = "35a59eaf31694af7920328b1595157f6";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mopub_with_html);

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

        interstitialButtonShow.setEnabled(false);
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {

    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {

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
