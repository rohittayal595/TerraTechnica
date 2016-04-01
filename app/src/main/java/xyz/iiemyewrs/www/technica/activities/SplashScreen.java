package xyz.iiemyewrs.www.technica.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.listener.OnStateChangeListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.helper.QuickstartPreferences;
import xyz.iiemyewrs.www.technica.helper.RegistrationIntentService;

public class SplashScreen extends AppCompatActivity {
    private String path1 = "M 253.00,32.67\n" +
            "           C 268.80,34.00 283.82,36.26 299.00,40.98\n" +
            "             367.10,62.14 417.08,114.65 436.71,183.00\n" +
            "             440.59,196.51 444.83,219.08 445.00,233.00\n" +
            "             445.33,261.68 443.16,283.48 434.26,311.00\n" +
            "             413.31,375.71 362.78,424.92 298.00,444.98\n" +
            "             274.15,452.36 254.61,453.28 230.00,453.00\n" +
            "             214.06,452.81 192.17,447.72 177.00,442.67\n" +
            "             158.47,436.49 140.99,427.50 125.00,416.28\n" +
            "             42.84,358.60 15.48,248.04 54.43,157.00\n" +
            "             77.46,103.18 122.35,60.91 178.00,42.35\n" +
            "             191.92,37.71 206.46,35.01 221.00,33.28\n" +
            "             221.00,33.28 233.00,32.67 233.00,32.67\n" +
            "             241.12,31.43 245.28,32.38 253.00,32.67 Z\n" +
            "           M 306.00,129.00\n" +
            "           C 318.09,129.02 312.98,131.57 322.02,139.89\n" +
            "             325.93,143.50 330.69,145.51 336.00,145.91\n" +
            "             353.01,147.19 363.35,132.83 362.99,117.00\n" +
            "             362.66,102.68 351.31,88.70 336.00,90.18\n" +
            "             331.41,90.62 326.62,92.52 323.01,95.39\n" +
            "             320.75,97.18 318.28,100.41 315.83,101.40\n" +
            "             314.05,102.13 310.96,102.00 309.00,102.00\n" +
            "             309.00,102.00 117.00,102.00 117.00,102.00\n" +
            "             117.00,102.00 117.00,176.00 117.00,176.00\n" +
            "             117.00,176.00 185.00,176.00 185.00,176.00\n" +
            "             185.00,176.00 185.00,326.00 185.00,326.00\n" +
            "             185.00,326.00 257.00,326.00 257.00,326.00\n" +
            "             257.00,326.00 258.00,311.00 258.00,311.00\n" +
            "             258.00,311.00 258.00,179.00 258.00,179.00\n" +
            "             258.00,179.00 279.00,179.00 279.00,179.00\n" +
            "             279.00,179.00 279.00,326.00 279.00,326.00\n" +
            "             279.00,326.00 349.00,326.00 349.00,326.00\n" +
            "             349.00,326.00 349.00,352.00 349.00,352.00\n" +
            "             349.00,352.00 306.00,352.00 306.00,352.00\n" +
            "             306.00,352.00 289.00,351.00 289.00,351.00\n" +
            "             289.00,351.00 185.00,351.00 185.00,351.00\n" +
            "             183.01,351.00 179.90,351.12 178.09,350.40\n" +
            "             175.26,349.28 172.36,345.29 169.99,343.10\n" +
            "             166.01,339.44 162.38,337.75 157.00,337.17\n" +
            "             141.59,335.52 129.38,347.94 129.01,363.00\n" +
            "             128.65,378.11 138.20,393.70 155.00,392.90\n" +
            "             159.89,392.66 165.17,390.65 169.00,387.61\n" +
            "             177.79,380.64 173.14,378.02 185.00,378.00\n" +
            "             185.00,378.00 374.00,378.00 374.00,378.00\n" +
            "             374.00,378.00 374.00,300.00 374.00,300.00\n" +
            "             374.00,300.00 304.00,300.00 304.00,300.00\n" +
            "             304.00,300.00 304.00,152.00 304.00,152.00\n" +
            "             304.00,152.00 233.00,152.00 233.00,152.00\n" +
            "             233.00,152.00 233.00,239.00 233.00,239.00\n" +
            "             233.00,239.00 232.00,254.00 232.00,254.00\n" +
            "             232.00,254.00 232.00,300.00 232.00,300.00\n" +
            "             232.00,300.00 210.00,300.00 210.00,300.00\n" +
            "             210.00,300.00 210.00,149.00 210.00,149.00\n" +
            "             210.00,149.00 142.00,149.00 142.00,149.00\n" +
            "             142.00,149.00 142.00,129.00 142.00,129.00\n" +
            "             142.00,129.00 306.00,129.00 306.00,129.00 Z\n" +
            "           M 341.00,129.10\n" +
            "           C 334.54,131.42 329.02,126.25 328.19,120.00\n" +
            "             327.33,113.52 329.29,109.79 335.00,106.74\n" +
            "             349.50,103.60 353.38,124.63 341.00,129.10 Z\n" +
            "           M 158.00,375.56\n" +
            "           C 151.66,378.37 145.53,373.17 144.35,367.00\n" +
            "             143.20,361.04 145.90,356.55 151.02,353.74\n" +
            "             166.16,350.20 169.47,370.45 158.00,375.56 Z";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "SplashScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean sentToken = sharedPreferences
                .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
        if (!sentToken) {
            if (checkPlayServices()) {
                // Start IntentService to register this application with GCM.
                Intent intent2 = new Intent(this, RegistrationIntentService.class);
                startService(intent2);
            }
        }


        final FillableLoader fillableLoader = (FillableLoader) findViewById(R.id.fillableLoader);
        fillableLoader.setSvgPath(path1);
        fillableLoader.start();

        fillableLoader.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(int i) {
                if (i == State.FINISHED) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SplashScreen.this, CircleActivity.class));
                            overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_zoom_out);
                            finish();
                        }
                    }, 1000);

                }
            }
        });
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
