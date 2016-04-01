package xyz.iiemyewrs.www.technica.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import xyz.iiemyewrs.www.technica.R;

/**
 * Created by iiemyewrs on 24/1/16.
 */
public class EventsListAdapter extends ArrayAdapter<String> {
    private String[] name;
    private String[] venue;
    private String[] time;
    private Activity context;
    LayoutInflater inflater;

    public EventsListAdapter(Activity context, String[] name, String[] venue, String[] time) {
        super(context, R.layout.events_list_item,name);
        this.context = context;
        this.name = name;
        this.venue = venue;
        this.time = time;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.events_list_item,null);
        }
        TextView textViewData = (TextView) convertView.findViewById(R.id.dataInList);
        TextView textViewVenue = (TextView) convertView.findViewById(R.id.venueInList);
        TextView textViewTime = (TextView) convertView.findViewById(R.id.timeInList);

        textViewData.setText(name[position]);
        textViewVenue.setText("Venue: "+venue[position]);
        textViewTime.setText("Time: "+time[position]);

        return convertView;
    }
}