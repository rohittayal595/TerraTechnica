package xyz.iiemyewrs.www.technica.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MetalHunt extends AppCompatActivity {

    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_r);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Metal Hunt");


        setContentView(R.layout.activity_metal_hunt);

        final Button login=(Button)findViewById(R.id.login_metal_hunt);

        final EditText code =(EditText) findViewById(R.id.code_metal);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogIn(code.getText().toString());
            }
        });


    }
    public void checkLogIn(final String code){

        //Loading

        LayoutInflater layoutInflater = getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.dialog_loading, null);
        GifView gifView1 = (GifView) v.findViewById(R.id.gif);

        gifView1.setVisibility(View.VISIBLE);

        dialog = new AlertDialog.Builder(MetalHunt.this).setView(v).create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //Loading over


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("JSON RESPONSE:", response);
                if(!response.equals("0")){
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MetalHunt.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("team_id", Integer.parseInt(response));
                    editor.putInt("round",1);
                    editor.commit();

                    Toast.makeText(MetalHunt.this, "Your Time starts now! Good Luck!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MetalHunt.this, MetalHunt2.class));
                    overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_zoom_out);
                    finish();


                }else{
                    Toast.makeText(MetalHunt.this,"No such team exists, Contact event managers!",Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("JSON ERROR:", error.toString());
                Toast.makeText(MetalHunt.this, "Server not reachable", Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("code", code);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
