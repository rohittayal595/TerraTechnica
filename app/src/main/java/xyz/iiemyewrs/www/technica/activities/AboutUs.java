package xyz.iiemyewrs.www.technica.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.iiemyewrs.www.technica.R;

public class AboutUs extends AppCompatActivity {
    String text2="<strong>TECH CLUB</strong></br></br>Advocating the sense of technological advancements, and rendering the required ideological vision to the students of the Institute, Tech Club of NIT Delhi, has come up as a fundamental and significant club of the institution. Constituting members from all the branches and years, the club aims at nurturing the technical skills of the students by organising various competitions, workshops, guest lectures, and other events. Tech Club of NIT Delhi has also brought up the tech fest of the college, TerraTechnica. With TerraTechnica&#39;16, the club seeks to present to the students new opportunities in the engineering dimensions combined with the inculcation of brighter and broader ideas, and help them interact with the students of other Institutes as well.</br></br><strong>TERRATECHNICA&#39;16</strong></br></br>Manifesting the opportunities of technology and innovation, and prioritising the need to extend the same, National Institute of Technology Delhi inherently presents its Maiden technical festival, the TerraTechnica&#39;16. Conveying the sole motivation of continuous and cumulative importance of technology and its attributes in the developing scenario of the nation, TerraTechnica&#39;16 tends to strengthen the role of engineering applications and its utilisation. The fest renders the bright minds of the country an opportunistic potential to evolve their technical skills, combined with the notion of emerging at the pole position in the competitive environments. It enunciates itself as the learning grounds for proper conceptual vision, developmental ideas, establishing a foreground for technical and engineering applications, and their achievability. In addition, TerraTechnica&#39;16 also exposes the absolute growth of the visual modality of the participants to intensify the scope of nurturing. An inspiration amalgamating with the spark of an idea embarks the inception of the new technology &#45; TerraTechnica&#39;16 (the clique of techies).";

    final String fb_url = "https://www.facebook.com/terratechnica/";
    final String twitter_url = "https://twitter.com/TerraTechnica";
    final String insta_url = "http://instagram.com/terratechnica";
    final String youtube_url = "https://www.youtube.com/channel/UCwkrKduy5122UrOAPZIpRMQ";
    final String gplus_url = "https://www.youtube.com/channel/UCwkrKduy5122UrOAPZIpRMQ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_r);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About Us");


        /*TextView tv=(TextView)findViewById(R.id.aboutTT);
        tv.setText(text);*/
        text2="<html><body style=\"text-align:justify\"> <font size=\"4\" color=\"#ffffff\">"+text2+"</font></body></html>";

        WebView webView = (WebView)findViewById(R.id.aboutTT);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.loadData(String.format(" %s ", text2),"text/html","utf-8");

        ImageView fb, insta, twitter, gplus, youtube;
        fb = (ImageView) findViewById(R.id.fb_image);
        insta = (ImageView) findViewById(R.id.insta_image);
        twitter = (ImageView) findViewById(R.id.twitter_image);
        gplus = (ImageView) findViewById(R.id.gplus_image);
        youtube = (ImageView) findViewById(R.id.youtube_image);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent;
                try{
                    getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana",0);
                    browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("fb://page/802193373225620"));
                }catch(Exception e){
                    browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(fb_url));
                }
                startActivity(browserIntent);
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent;
                try{
                    getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana",0);
                    browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/_u/terratechnica"));
                }catch(Exception e){
                    browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(insta_url));
                }
                startActivity(browserIntent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitter_url));
                startActivity(browserIntent);
            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(gplus_url));
                startActivity(browserIntent);
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtube_url));
                startActivity(browserIntent);
            }
        });
    }


}
