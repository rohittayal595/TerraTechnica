package xyz.iiemyewrs.www.technica.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.adapters.EventsListAdapter;
import xyz.iiemyewrs.www.technica.adapters.NotificationListAdapter;
import xyz.iiemyewrs.www.technica.appConfig.Constants;
import xyz.iiemyewrs.www.technica.fragments.DialougeBox;
import xyz.iiemyewrs.www.technica.helper.ParseJson;
import xyz.iiemyewrs.www.technica.helper.SQLiteHandler;

public class Events extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sendRequest();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendRequest() {
        StringRequest stringRequest = new StringRequest(Constants.URL_EVENTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("JSON RESPONSE:", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray("result");
                            String[] ids = new String[result.length()];
                            String[] eventName = new String[result.length()];
                            String[] eventDay = new String[result.length()];
                            String[] eventVenue = new String[result.length()];
                            String[] eventTime = new String[result.length()];
                            String[] eventDisc = new String[result.length()];
                            for (int i = 0; i < result.length(); i++) {
                                JSONObject jo = result.getJSONObject(i);
                                ids[i] = jo.getString("_ID");
                                eventName[i] = jo.getString("event_name");
                                eventDay[i] = jo.getString("day");
                                eventVenue[i] = jo.getString("venue");
                                eventTime[i] = jo.getString("time");
                                eventDisc[i] = jo.getString("disc");
                            }
                            db = new SQLiteHandler(getApplicationContext());
                            db.addEventsToDatabase(ids, eventName, eventDay, eventVenue, eventTime, eventDisc);
                            mViewPager.setAdapter(mSectionsPagerAdapter);
                            Toast.makeText(Events.this,"List Updated",Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY ERROR:", error.toString());
                        Toast.makeText(Events.this, "Server not reachable", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onDetach() {
            super.onDetach();
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.cancelAll(new RequestQueue.RequestFilter(){

                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
            try {
                Field chileFragmentManager= Fragment.class.getDeclaredField("mChildFragmentManager");
                chileFragmentManager.setAccessible(true);
                chileFragmentManager.set(this,null);
            }catch (NoSuchFieldException e){
                throw new RuntimeException(e);
            }catch (IllegalAccessException e){
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_events, container, false);
            SQLiteHandler db;
            ListView mListView = (ListView) rootView.findViewById(R.id.events_list_view);
            View emptyView=rootView.findViewById(R.id.listview_events_empty);
            mListView.setEmptyView(emptyView);
            db = new SQLiteHandler(getActivity().getApplicationContext());
            Object[] obj = db.getEventsDataFromDatabase();
            final Object[] obj1=(Object[])obj[0];
            final Object[] obj2=(Object[])obj[1];
            final Object[] obj3=(Object[])obj[2];
            final Object[] obj4=(Object[])obj[3];
            final Object[] obj5=(Object[])obj[4];
            final Object[] obj6=(Object[])obj[5];
            EventsListAdapter cl;
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1) {
                cl = new EventsListAdapter(getActivity(), (String[])obj1[0],(String[])obj1[1],(String[])obj1[2]);
                mListView.setAdapter(cl);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DialogFragment newFragment = new DialougeBox();
                        Bundle args = new Bundle();
                        args.putString("name", ((String[]) obj1[0])[position]);
                        args.putString("disc", ((String[]) obj1[3])[position]);
                        newFragment.setArguments(args);
                        newFragment.show(getActivity().getSupportFragmentManager(), "EVENT_DISC");
                    }
                });
            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                cl = new EventsListAdapter(getActivity(), (String[])obj2[0],(String[])obj2[1],(String[])obj2[2]);
                mListView.setAdapter(cl);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DialogFragment newFragment = new DialougeBox();
                        Bundle args = new Bundle();
                        args.putString("name", ((String[]) obj2[0])[position]);
                        args.putString("disc", ((String[]) obj2[3])[position]);
                        newFragment.setArguments(args);
                        newFragment.show(getActivity().getSupportFragmentManager(), "EVENT_DISC");
                    }
                });
            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==3){
                cl = new EventsListAdapter(getActivity(), (String[])obj3[0],(String[])obj3[1],(String[])obj3[2]);
                mListView.setAdapter(cl);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DialogFragment newFragment = new DialougeBox();
                        Bundle args = new Bundle();
                        args.putString("name", ((String[]) obj3[0])[position]);
                        args.putString("disc", ((String[]) obj3[3])[position]);
                        newFragment.setArguments(args);
                        newFragment.show(getActivity().getSupportFragmentManager(), "EVENT_DISC");
                    }
                });
            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==4){
                cl = new EventsListAdapter(getActivity(), (String[])obj4[0],(String[])obj4[1],(String[])obj4[2]);
                mListView.setAdapter(cl);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DialogFragment newFragment = new DialougeBox();
                        Bundle args = new Bundle();
                        args.putString("name", ((String[]) obj4[0])[position]);
                        args.putString("disc", ((String[]) obj4[3])[position]);
                        newFragment.setArguments(args);
                        newFragment.show(getActivity().getSupportFragmentManager(), "EVENT_DISC");
                    }
                });
            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==5){
                cl = new EventsListAdapter(getActivity(), (String[])obj5[0],(String[])obj5[1],(String[])obj5[2]);
                mListView.setAdapter(cl);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DialogFragment newFragment = new DialougeBox();
                        Bundle args = new Bundle();
                        args.putString("name", ((String[]) obj5[0])[position]);
                        args.putString("disc", ((String[]) obj5[3])[position]);
                        newFragment.setArguments(args);
                        newFragment.show(getActivity().getSupportFragmentManager(), "EVENT_DISC");
                    }
                });
            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==6){
                cl = new EventsListAdapter(getActivity(), (String[])obj6[0],(String[])obj6[1],(String[])obj6[2]);
                mListView.setAdapter(cl);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DialogFragment newFragment = new DialougeBox();
                        Bundle args = new Bundle();
                        args.putString("name", ((String[]) obj6[0])[position]);
                        args.putString("disc", ((String[]) obj6[3])[position]);
                        newFragment.setArguments(args);
                        newFragment.show(getActivity().getSupportFragmentManager(), "EVENT_DISC");
                    }
                });
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Codeconnect";
                case 1:
                    return "INnoVITE";
                case 2:
                    return "Botmania";
                case 3:
                    return "Paradox";
                case 4:
                    return "Workshops";
                case 5:
                    return "Misc";
            }
            return null;
        }
    }
}
