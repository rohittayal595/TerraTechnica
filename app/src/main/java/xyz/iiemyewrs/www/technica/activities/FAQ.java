package xyz.iiemyewrs.www.technica.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.adapters.SponsorsAdapter;
import xyz.iiemyewrs.www.technica.appConfig.Constants;
import xyz.iiemyewrs.www.technica.helper.QuickstartPreferences;
import xyz.iiemyewrs.www.technica.helper.SQLiteHandler;
import xyz.iiemyewrs.www.technica.library.GifView;

public class FAQ extends AppCompatActivity {
    String text2 = "Please Make sure that you are connected to internet!";
    String text;
    final String fb_url = "https://www.facebook.com/terratechnica/";
    final String twitter_url = "https://twitter.com/TerraTechnica";
    final String insta_url = "http://instagram.com/terratechnica";
    final String youtube_url = "https://www.youtube.com/channel/UCwkrKduy5122UrOAPZIpRMQ";
    final String gplus_url = "https://www.youtube.com/channel/UCwkrKduy5122UrOAPZIpRMQ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_r);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("FAQs");

        //Loading

        LayoutInflater layoutInflater = getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.dialog_loading, null);
        GifView gifView1 = (GifView) v.findViewById(R.id.gif);

        gifView1.setVisibility(View.VISIBLE);

        //Loading over
        sendRequest();
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        text2= sharedPreferences
                .getString(QuickstartPreferences.FAQ_DATA, "<h2>Unable to connect to internet! Please check your internet connection and try again.</h2>");
        text="<html><body style=\"text-align:justify\"> <font size=\"4\" color=\"#ffffff\">"+text2+"</font></body></html>";
        WebView webView = (WebView)findViewById(R.id.aboutTT);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.loadData(String.format(" %s ", text), "text/html", "utf-8");

        ImageView fb, insta, twitter, gplus, youtube;
        fb = (ImageView) findViewById(R.id.fb_image);
        insta = (ImageView) findViewById(R.id.insta_image);
        twitter = (ImageView) findViewById(R.id.twitter_image);
        gplus = (ImageView) findViewById(R.id.gplus_image);
        youtube = (ImageView) findViewById(R.id.youtube_image);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent;
                try{
                    getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana",0);
                    browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("fb://page/802193373225620"));
                }catch(Exception e){
                    browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(fb_url));
                }
                startActivity(browserIntent);
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent;
                try{
                    getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana",0);
                    browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/_u/terratechnica"));
                }catch(Exception e){
                    browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(insta_url));
                }
                startActivity(browserIntent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitter_url));
                startActivity(browserIntent);
            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(gplus_url));
                startActivity(browserIntent);
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtube_url));
                startActivity(browserIntent);
            }
        });
    }

    public void sendRequest() {

        StringRequest stringRequest = new StringRequest(Constants.URL_FAQ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("JSON RESPONSE:", response);

                        SharedPreferences sharedPreferences =
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(QuickstartPreferences.FAQ_DATA, response);
                        editor.commit();
                        String obj= sharedPreferences
                                .getString(QuickstartPreferences.FAQ_DATA, "<h2>Unable to connect to internet! Please check your internet connection and try again.</h2>");
                        text2= obj;
                        text="<html><body style=\"text-align:justify\"> <font size=\"4\" color=\"#ffffff\">"+text2+"</font></body></html>";
                        WebView webView = (WebView)findViewById(R.id.aboutTT);
                        webView.setBackgroundColor(Color.TRANSPARENT);
                        webView.loadData(String.format(" %s ", text), "text/html", "utf-8");
                        Toast.makeText(FAQ.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("JSON ERROR:", error.toString());
                        Toast.makeText(FAQ.this, "Server not reachable", Toast.LENGTH_LONG).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            sendRequest();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
