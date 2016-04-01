package xyz.iiemyewrs.www.technica.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.appConfig.Constants;
import xyz.iiemyewrs.www.technica.library.GifView;

public class MetalHunt3 extends AppCompatActivity {

    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metal_hunt_finish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_r);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Metal Hunt");


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MetalHunt3.this,CircleActivity.class));
        overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_zoom_out);
        finish();
    }
}
