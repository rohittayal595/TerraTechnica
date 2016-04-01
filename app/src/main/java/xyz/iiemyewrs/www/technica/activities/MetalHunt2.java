package xyz.iiemyewrs.www.technica.activities;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.appConfig.Constants;
import xyz.iiemyewrs.www.technica.library.GifView;

public class MetalHunt2 extends AppCompatActivity {

    AlertDialog dialog;
    int team_id;
    int round_id;
    SharedPreferences sharedPref;
    ImageView imageView;
    TextView round;
    EditText codeInRound;
    Button verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metal_hunt2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_r);
        setSupportActionBar(toolbar);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        team_id= sharedPref.getInt("team_id", 999);
        round_id=sharedPref.getInt("round",999);

        getSupportActionBar().setTitle("Metal Hunt");

        round = (TextView)findViewById(R.id.round_mental_hunt);
        round.setText("Round " + sharedPref.getInt("round", 999));

        imageView =(ImageView)findViewById(R.id.metal_image);
        Picasso.with(MetalHunt2.this).load("http://www.iiemyewrs.xyz/technica/config/pics/"+team_id+"_"+round_id+".jpeg").into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                imageView.setImageResource(R.drawable.no_image);
            }
        });

        codeInRound =(EditText)findViewById(R.id.code_in_round_metal);

        verify = (Button)findViewById(R.id.verify_metal_hunt);
        if (round_id==4){
            verify.setText("Finish");
        }
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ro=null;
                if(round_id==1){
                    ro="one";
                }else if(round_id==2){
                    ro="two";
                }else if(round_id==3){
                    ro="three";
                }else if (round_id==4){
                    ro="four";
                }
                checkCode(codeInRound.getText().toString(),ro,team_id);

            }
        });

    }

    public void checkCode(final String code, final String round_stirng, final int team_id){

        //Loading

        LayoutInflater layoutInflater = getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.dialog_loading, null);
        GifView gifView1 = (GifView) v.findViewById(R.id.gif);

        gifView1.setVisibility(View.VISIBLE);

        dialog = new AlertDialog.Builder(MetalHunt2.this).setView(v).create();
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
                    SharedPreferences.Editor editor = sharedPref.edit();
                    round_id++;
                    editor.putInt("round",round_id);

                    if(round_id==5){
                        startActivity(new Intent(MetalHunt2.this, MetalHunt3.class));
                        overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_zoom_out);
                        finish();
                    }else {

                        round.setText("Round " + round_id);

                        codeInRound.setText("");

                        Picasso.with(MetalHunt2.this).load("http://www.iiemyewrs.xyz/technica/config/pics/" + team_id + "_" + round_id + ".jpeg").into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                imageView.setImageResource(R.drawable.no_image);
                            }
                        });

                        if (round_id == 4) {
                            verify.setText("Finish");
                        }

                        Toast.makeText(MetalHunt2.this, "Congrats! That was right one! You have entered round " + round_id, Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MetalHunt2.this,"Wrong Code, Try some other place for your clue!",Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("JSON ERROR:", error.toString());
                Toast.makeText(MetalHunt2.this, "Server not reachable", Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("code_id", code);
                params.put("team_id", String.valueOf(team_id));
                params.put("round_id", round_stirng);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(MetalHunt2.this,"You should not close this screen before ending the contest!",Toast.LENGTH_LONG).show();
    }
}
