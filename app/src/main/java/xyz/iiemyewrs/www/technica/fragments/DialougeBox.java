package xyz.iiemyewrs.www.technica.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.appConfig.Constants;
import xyz.iiemyewrs.www.technica.library.CanaroTextView;

/**
 * Created by iiemyewrs on 25/1/16.
 */
public class DialougeBox extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v=inflater.inflate(R.layout.dialouge, null);

        TextView message = (TextView) v.findViewById(R.id.dialog_box);
        message.setText(getArguments().getString("disc")+"\n");

        View title = inflater.inflate(R.layout.dialog_title,null);
        CanaroTextView canaroTextView = (CanaroTextView)title.findViewById(R.id.event_name);
        canaroTextView.setText(getArguments().getString("name"));

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v)
                .setCustomTitle(title)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        final ImageView imageView =(ImageView) v.findViewById(R.id.eventsImage);
        String name =getArguments().getString("name");
        name = name.substring(0,name.indexOf(' ')==-1?name.length():name.indexOf(' '));
        name = name.toLowerCase();
        Picasso.with(v.getContext()).load(Constants.URL_EVENTS_IMAGE+name+".jpeg").into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                imageView.setImageResource(R.drawable.no_image);
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();

    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(R.drawable.back);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
