package xyz.iiemyewrs.www.technica.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.adapters.NotificationListAdapter;
import xyz.iiemyewrs.www.technica.appConfig.Constants;
import xyz.iiemyewrs.www.technica.helper.ParseJson;
import xyz.iiemyewrs.www.technica.helper.SQLiteHandler;

public class Notification extends AppCompatActivity {

    private ListView mNotificationListView;
    private SwipeRefreshLayout mSwipeDownRefreshLayout;
    SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_r);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mNotificationListView = (ListView) findViewById(R.id.notification_list_view);
        View emptyView=findViewById(R.id.listview_notification_empty);
        mNotificationListView.setEmptyView(emptyView);
        mSwipeDownRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.notification_swipe_refresh);
        mSwipeDownRefreshLayout.setColorSchemeResources(R.color.primary);

        mSwipeDownRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeDownRefreshLayout.setRefreshing(true);
            }
        });

        mSwipeDownRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeDownRefreshLayout.setRefreshing(true);
                sendRequest();
            }
        });

        db =  new SQLiteHandler(getApplicationContext());
        Object[] obj = db.getNotificationDataFromDatabase();
        NotificationListAdapter cl = new NotificationListAdapter(this,(String[])obj[0],(String[])obj[1]);
        mNotificationListView.setAdapter(cl);

        sendRequest();
    }

    public void sendRequest() {

        StringRequest stringRequest = new StringRequest(Constants.URL_NOTIFICATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("JSON RESPONSE:", response);
                      showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("JSON ERROR:", error.toString());
                        Toast.makeText(Notification.this, "Server not reachable", Toast.LENGTH_LONG).show();

                        mSwipeDownRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                mSwipeDownRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJson pj = new ParseJson(json);
        pj.parseJSON();

        db.addNotificationsToDatabase(ParseJson.data, ParseJson.date);
        Log.d("Notification", "Values were added successfully to database");
        Object[] obj = db.getNotificationDataFromDatabase();
        NotificationListAdapter cl = new NotificationListAdapter(this,(String[])obj[0],(String[])obj[1]);
        mNotificationListView.setAdapter(cl);

        mSwipeDownRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeDownRefreshLayout.setRefreshing(false);
            }
        });

        Toast.makeText(Notification.this,"Notification List Updated Successfully",Toast.LENGTH_SHORT).show();
    }
}
