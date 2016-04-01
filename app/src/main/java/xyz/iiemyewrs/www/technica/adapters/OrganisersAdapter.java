package xyz.iiemyewrs.www.technica.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.appConfig.Constants;

/**
 * Created by iiemyewrs on 10/2/16.
 */
public class OrganisersAdapter extends ArrayAdapter<String> {
    private String[] names;
    private String[] imageUrls;
    private String[] designation;
    private Activity context;
    LayoutInflater inflater;

    public OrganisersAdapter(Activity context, String[] names,String[] designation, String[] imageUrls) {
        super(context, R.layout.organisers1,names);
        this.context = context;
        this.names = names;
        this.designation=designation;
        this.imageUrls = imageUrls;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(position%2!=0)
            convertView=inflater.inflate(R.layout.organisers1,null);
            else
            convertView=inflater.inflate(R.layout.organisers2,null);

            TextView textViewName = (TextView) convertView.findViewById(R.id.organiserName);
            TextView textViewDesignation = (TextView) convertView.findViewById(R.id.organiserDesignation);
            final ImageView imageView =(ImageView) convertView.findViewById(R.id.organiserImage);

            textViewName.setText(names[position]);
            textViewDesignation.setText("("+designation[position]+")");

        Picasso.with(context).load(imageUrls[position]).into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                imageView.setImageResource(R.drawable.pulkit);
            }
        });
        return convertView;
    }
}