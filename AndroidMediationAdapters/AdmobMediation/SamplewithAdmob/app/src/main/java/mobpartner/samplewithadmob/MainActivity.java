package mobpartner.samplewithadmob;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends ActionBarActivity {

    private LinearLayout bannerLayout;
    private Button bannerButton;
    private Button interstitialButtonLoad, interstitialButtonShow;
    private AdView adView;
    private InterstitialAd mInterstitial;

    final String BannerAdUnitId = "ca-app-pub-4360536759341314/6044612382";
    final String InterstitalAdUnitId = "ca-app-pub-4360536759341314/6146429984";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerLayout = (LinearLayout) findViewById(R.id.banner_admob);
        bannerButton = (Button) findViewById(R.id.button_banner);
        interstitialButtonLoad = (Button) findViewById(R.id.button_interstitial_load);
        interstitialButtonShow = (Button) findViewById(R.id.button_interstitial_show);
        interstitialButtonShow.setEnabled(false);

        mInterstitial = new InterstitialAd(this);
        mInterstitial.setAdUnitId(InterstitalAdUnitId);
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialButtonShow.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                interstitialButtonShow.setEnabled(false);
            }
        });

        bannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAdMobBannerAd();
            }
        });


        interstitialButtonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitial.loadAd(adRequest);
            }
        });

        interstitialButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterstitial.show();

            }
        });
    }

    private void loadAdMobBannerAd(){
        bannerLayout.removeAllViews();

        adView = new AdView(this);
        adView.setAdUnitId(BannerAdUnitId);
        adView.setAdSize(AdSize.BANNER);
        bannerLayout.addView(adView);

        AdRequest request = new AdRequest.Builder().build();

        adView.loadAd(request);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        adView.destroy();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
