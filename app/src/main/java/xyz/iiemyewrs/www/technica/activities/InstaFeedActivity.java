package xyz.iiemyewrs.www.technica.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.adapters.InstaFeedListAdapter;
import xyz.iiemyewrs.www.technica.appConfig.APIClient;
import xyz.iiemyewrs.www.technica.instagram.InstaFeed;
import xyz.iiemyewrs.www.technica.library.GifView;


public class InstaFeedActivity extends AppCompatActivity {

    private static final String TAG = InstaFeedActivity.class.getSimpleName();
    private ListView mInstaFeedListView;
    private SwipeRefreshLayout mSwipeDownRefreshLayout;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadInstaFeed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void loadInstaFeed() {
        setContentView(R.layout.activity_insta_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_r);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mInstaFeedListView = (ListView) findViewById(R.id.insta_feed_list_view);
        View emptyView=findViewById(R.id.listview_insta_empty);
        mInstaFeedListView.setEmptyView(emptyView);
        mSwipeDownRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.insta_feed_swipe_refresh);

        mSwipeDownRefreshLayout.setColorSchemeResources(R.color.primary);

        //Loading

        LayoutInflater layoutInflater = getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.dialog_loading, null);
        GifView gifView1 = (GifView) v.findViewById(R.id.gif);

        gifView1.setVisibility(View.VISIBLE);

        dialog = new AlertDialog.Builder(InstaFeedActivity.this).setView(v).create();
        dialog.show();

        //Loading over

        APIClient.getInstagram().getFeed(new Callback<InstaFeed>() {
            @Override
            public void success(InstaFeed instaFeed, Response response) {
                dialog.dismiss();

                mInstaFeedListView.setAdapter(new InstaFeedListAdapter(getApplicationContext(), instaFeed));
                mInstaFeedListView.setVisibility(View.VISIBLE);

            }

            @Override
            public void failure(RetrofitError error) {
//                setContentView(R.layout.no_connection_layout);
//                Button retryButton = (Button) findViewById(R.id.retry_button);
//                retryButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        loadInstaFeed();
//                    }
//                });
                dialog.dismiss();
                Toast.makeText(InstaFeedActivity.this,"Failed to load.",Toast.LENGTH_LONG).show();
            }
        });

        mSwipeDownRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeDownRefreshLayout.setRefreshing(true);
                APIClient.getInstagram().getFeed(new Callback<InstaFeed>() {
                    @Override
                    public void success(InstaFeed instaFeed, Response response) {
                        InstaFeedListAdapter adapter = new InstaFeedListAdapter(getApplicationContext(), instaFeed);
                        adapter.notifyDataSetChanged();
                        mInstaFeedListView.setAdapter(adapter);
                        mInstaFeedListView.setVisibility(View.VISIBLE);
                        mSwipeDownRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(RetrofitError error) {
//                        mSwipeDownRefreshLayout.setRefreshing(false);
//                        setContentView(R.layout.no_connection_layout);
//                        Button retryButton = (Button) findViewById(R.id.retry_button);
//                        retryButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                loadInstaFeed();
//                            }
//                        });
                        Toast.makeText(InstaFeedActivity.this,"No Internet Connectivity",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        mInstaFeedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0 && visibleItemCount > 0 && mInstaFeedListView.getChildAt(0).getTop() >= 0)
                    mSwipeDownRefreshLayout.setEnabled(true);
                else mSwipeDownRefreshLayout.setEnabled(false);
            }
        });
    }

}
