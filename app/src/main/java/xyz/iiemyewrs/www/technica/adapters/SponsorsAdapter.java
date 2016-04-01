package xyz.iiemyewrs.www.technica.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.appConfig.Constants;

/**
 * Created by iiemyewrs on 10/2/16.
 */
public class SponsorsAdapter extends ArrayAdapter<String> {
    private String[] names;
    private String[] designation;
    private Activity context;
    LayoutInflater inflater;

    public SponsorsAdapter(Activity context, String[] names, String[] designation) {
        super(context, R.layout.organisers1, names);
        this.context = context;
        this.names = names;
        this.designation = designation;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (position % 2 != 0)
            convertView = inflater.inflate(R.layout.organisers1, null);
        else
            convertView = inflater.inflate(R.layout.organisers2, null);

        TextView textViewName = (TextView) convertView.findViewById(R.id.organiserName);
        TextView textViewDesignation = (TextView) convertView.findViewById(R.id.organiserDesignation);
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.organiserImage);
        textViewName.setText(names[position]);
        textViewDesignation.setText("(" + designation[position] + ")");

        String name = names[position];
        name = name.substring(0, name.indexOf(' ') == -1 ? name.length() : name.indexOf(' '));
        name = name.toLowerCase();

        Picasso.with(context).load(Constants.URL_EVENTS_IMAGE + name + ".jpeg").into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                imageView.setImageResource(R.drawable.no_image);
            }
        });
        return convertView;
    }
}