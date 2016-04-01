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
 * Created by iiemyewrs on 16/1/16.
 */
public class NotificationListAdapter extends ArrayAdapter<String> {
    private String[] data;
    private String[] date;
    private Activity context;
    LayoutInflater inflater;

    public NotificationListAdapter(Activity context, String[] data, String[] date) {
        super(context, R.layout.notification_list_item,date);
        this.context = context;
        this.data = data;
        this.date = date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.notification_list_item,null);
        }
        TextView textViewData = (TextView) convertView.findViewById(R.id.dataInList);
        TextView textViewDate = (TextView) convertView.findViewById(R.id.dateInList);

        textViewData.setText(data[position]);
        textViewDate.setText(date[position]);

        return convertView;
    }
}