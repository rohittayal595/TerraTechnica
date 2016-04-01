package xyz.iiemyewrs.www.technica.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.adapters.NotificationListAdapter;
import xyz.iiemyewrs.www.technica.adapters.OrganisersAdapter;

public class OrganisersActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // get the listview
        ListView lv=(ListView)findViewById(R.id.organisersList);

        View emptyView=findViewById(R.id.organiser_empty);
        lv.setEmptyView(emptyView);
        String[] names={"Dr. Anmol R. Saxena","LR Sharma","Jasleen Kaur","Hemant Vyas","Bhupesh Kumar",
                "M. Praveen","Ajay Poddar","Rishav Agrahari","Akarsh Mohan","Mohit Yadav","Amit Kumar","Anku Kumar","Tesslin varghese","Pulkit Goyal","Rohit Tayal"};
        String[] designation={"Chairman TechClub","Convener","Co-Convener","Co-Convener","Executive Secretary",
                "Print and Publicity Head","Website Designing Head","Sponsorship Committee\n Head","Media Coordination\nand Publicity Head","Infrastructure and\nLogistics Head",
                "Hospitality team Head","Discipline and\nSecurity Head","Discipline and\nSecurity Head","Designing Head","Android Developer"};
        String[] imageUrls={"http://iiemyewrs.insigniathefest.com/technica/pictures/anmol.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/lala.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/jasleen.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/hemant.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/bhupesh.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/praveen.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/ajay.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/rishav.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/akarsh.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/mohit.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/amit.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/anku.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/tesslin.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/pulkit.png",
                "http://iiemyewrs.insigniathefest.com/technica/pictures/rohit.png"
        };
        OrganisersAdapter cl = new OrganisersAdapter(this,names,designation,imageUrls);
        lv.setAdapter(cl);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] number = {"9873177085", "9718257966", "7838980689", "9013486751", "9716848553",
                        "8882934854", "8447555078", "9716583636", "8743842715", "8802370556", "8882991595", "9971248858", "8800931324", "7838109317", "8750450180"};

                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + number[position]));
                startActivity(call);
            }
        });

    }
}
