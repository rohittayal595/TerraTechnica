package xyz.iiemyewrs.www.technica.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import xyz.iiemyewrs.www.technica.helper.SQLiteHandler;
import xyz.iiemyewrs.www.technica.library.GifView;

public class Sponsors extends AppCompatActivity {

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Loading

        LayoutInflater layoutInflater = getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.dialog_loading, null);
        GifView gifView1 = (GifView) v.findViewById(R.id.gif);

        gifView1.setVisibility(View.VISIBLE);

        dialog = new AlertDialog.Builder(Sponsors.this).setView(v).create();
        dialog.show();
        db =  new SQLiteHandler(getApplicationContext());

        //Loading over
        sendRequest();
        db =  new SQLiteHandler(getApplicationContext());
        Object[] obj = db.getSponsorDataFromDatabase();
        ListView lv=(ListView)findViewById(R.id.organisersList);
        final String[] imageUrls=(String[])obj[2];
        View emptyView=findViewById(R.id.organiser_empty);
        lv.setEmptyView(emptyView);
        SponsorsAdapter cl = new SponsorsAdapter(this,(String[])obj[0],(String[])obj[1]);
        lv.setAdapter(cl);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(imageUrls[position])));
            }
        });

    }
    SQLiteHandler db;

    public void sendRequest() {

        StringRequest stringRequest = new StringRequest(Constants.URL_SPONSOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("JSON RESPONSE:", response);

                        String[] ids,name=null,desig=null,url=null;

                        JSONObject jsonObject=null;
                        JSONArray result = null;
                        try {
                            jsonObject = new JSONObject(response);
                            result = jsonObject.getJSONArray("result");

                            ids = new String[result.length()];
                            name = new String[result.length()];
                            desig = new String[result.length()];
                            url = new String[result.length()];

                            for(int i=0;i<result.length();i++){
                                JSONObject jo = result.getJSONObject(i);
                                ids[i] = jo.getString("_ID");
                                name[i] = jo.getString("name");
                                desig[i] = jo.getString("desig");
                                url[i] = jo.getString("url");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        db.addSponsorsToDatabase(name, desig, url);
                        Log.d("Sponsors", "Values were added successfully to database");
                        Object[] obj = db.getSponsorDataFromDatabase();
                        ListView lv=(ListView)findViewById(R.id.organisersList);
                        final String[] imageUrls=(String[])obj[2];
                        View emptyView=findViewById(R.id.organiser_empty);
                        lv.setEmptyView(emptyView);
                        SponsorsAdapter cl = new SponsorsAdapter(Sponsors.this,(String[])obj[0],(String[])obj[1]);
                        lv.setAdapter(cl);
                        dialog.dismiss();
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(imageUrls[position])));
                            }
                        });

                        dialog.dismiss();
                        Toast.makeText(Sponsors.this, "List Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("JSON ERROR:", error.toString());
                        Toast.makeText(Sponsors.this, "Server not reachable", Toast.LENGTH_LONG).show();
                        dialog.dismiss();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
