package xyz.iiemyewrs.www.technica.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.etiennelawlor.imagegallery.library.activities.ImageGalleryActivity;
import com.etiennelawlor.imagegallery.library.enums.PaletteColorType;
import com.jaredrummler.android.device.DeviceName;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xyz.iiemyewrs.www.technica.BuildConfig;
import xyz.iiemyewrs.www.technica.R;
import xyz.iiemyewrs.www.technica.appConfig.Constants;
import xyz.iiemyewrs.www.technica.library.CircleMenuLayout;
import xyz.iiemyewrs.www.technica.library.GifView;
import xyz.iiemyewrs.www.technica.library.GuillotineAnimation;

public class CircleActivity extends AppCompatActivity {
	private static final long RIPPLE_DURATION = 250;


	@InjectView(R.id.toolbarMain)
	Toolbar toolbar;
	@InjectView(R.id.root)
	FrameLayout root;
	@InjectView(R.id.content_hamburger)
	View contentHamburger;

	private CircleMenuLayout mCircleMenuLayout;

	private String[] mItemTexts = new String[] { "InstaFeed", "Sponsors", "Maps",
			"Contact", "Gallery","Events" };
	private int[] mItemImgs = new int[] { R.drawable.ic_insta,
			R.drawable.sponsor,
			R.drawable.maps,
			R.drawable.phone_circle,
			R.drawable.gallery,
			R.drawable.calendar
	};

	AlertDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main02);
		ButterKnife.inject(this);

		if (toolbar != null) {
			setSupportActionBar(toolbar);
			getSupportActionBar().setTitle(null);
		}

		View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
		root.addView(guillotineMenu);

		new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
				.setStartDelay(RIPPLE_DURATION)
				.setActionBarViewForAnimation(toolbar)
				.setClosedOnStart(true)
				.build();

		showPopup(guillotineMenu);

		mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
		mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
		
		

		mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener()
		{
			
			@Override
			public void itemClick(View view, int pos)
			{

				if(pos==0){
					startActivity(new Intent(CircleActivity.this, InstaFeedActivity.class));
				}else if(pos==1){
					startActivity(new Intent(CircleActivity.this,Sponsors.class));
				}else if(pos==2){
					startActivity(new Intent(CircleActivity.this,MapsActivity.class));
				}else if(pos==3){
					startActivity(new Intent(CircleActivity.this,OrganisersActivity.class));
				}else if(pos==4){
					sendRequest();
				}else if(pos==5){
					startActivity(new Intent(CircleActivity.this,Events.class));
				}
				overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_zoom_out);

			}
			
			@Override
			public void itemCenterClick(View view)
			{
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nitdelhi.ac.in"));
				startActivity(browserIntent);
			}
		});
		
	}
	public void showPopup(View v){
		LinearLayout about = (LinearLayout)v.findViewById(R.id.about_group);
		about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CircleActivity.this, AboutUs.class));
				overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_zoom_out);
			}
		});

		LinearLayout notification=(LinearLayout)v.findViewById(R.id.notifcation_group);
		notification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CircleActivity.this, Notification.class));
				overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_zoom_out);
			}
		});

		LinearLayout faq=(LinearLayout)v.findViewById(R.id.faq_group);
		faq.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CircleActivity.this, FAQ.class));
				overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_zoom_out);
			}
		});

		LinearLayout share =(LinearLayout)v.findViewById(R.id.share_group);
		share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent i = new Intent(Intent.ACTION_SEND);
					i.setType("text/plain");
					i.putExtra(Intent.EXTRA_SUBJECT, "TerraTechnica'16");
					String sAux = "https://play.google.com/store/apps/details?id=xyz.iiemyewrs.www.technica \n";
					i.putExtra(Intent.EXTRA_TEXT, sAux);
					startActivity(Intent.createChooser(i, "choose one"));
				} catch (Exception e) {
				}
			}
		});

		LinearLayout feedback = (LinearLayout) findViewById(R.id.feedback_group);
		feedback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String message =
						"1. What would you rate this app? (On scale of 1-5)\n\n" +
						"2. How likely is it that you would recommend this app to a friend or colleague? (On scale of 1-5)\n\n" +
						"3. What's one thing we could do to create a better experience for you?\n";
				message += "\n\nVersion: " + BuildConfig.VERSION_NAME + " (" + BuildConfig.VERSION_CODE + ")";
				message += "\nDevice Name: " + DeviceName.getDeviceName();
				message += "\nSystem Version: " + Build.VERSION.RELEASE;
				String[] to = {"rohit.tayal@nitdelhi.ac.in"};
				Intent sendIntent = new Intent(Intent.ACTION_VIEW);
				sendIntent.setType("plain/text");
				sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
				sendIntent.putExtra(Intent.EXTRA_EMAIL, to);
				sendIntent.putExtra(Intent.EXTRA_SUBJECT, "TerraTechnica App Feedback");
				sendIntent.putExtra(Intent.EXTRA_TEXT, message);
				startActivity(sendIntent);
			}
		});

		LinearLayout metal=(LinearLayout)v.findViewById(R.id.metal_hunt_group);
		metal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CircleActivity.this, MetalHunt.class));
				overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_zoom_out);
			}
		});
	}
	void gallery(int i){
		Intent intent = new Intent(CircleActivity.this, ImageGalleryActivity.class);

		ArrayList<String> images = new ArrayList<>();
		for(int j=1;j<=i;j++){
			images.add("http://iiemyewrs.insigniathefest.com/technica/pics/"+j+".jpg");
		}
		intent.putStringArrayListExtra("images", images);
		intent.putExtra("palette_color_type", PaletteColorType.LIGHT_MUTED);

		startActivity(intent);
	}

	public void sendRequest() {
		//Loading

		LayoutInflater layoutInflater = getLayoutInflater();
		View v = layoutInflater.inflate(R.layout.dialog_loading, null);
		GifView gifView1 = (GifView) v.findViewById(R.id.gif);

		gifView1.setVisibility(View.VISIBLE);

		dialog = new AlertDialog.Builder(CircleActivity.this).setView(v).create();
		dialog.show();

		//Loading over

		StringRequest stringRequest = new StringRequest(Constants.URL_NUMBER,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.e("JSON RESPONSE:", response);
						gallery(Integer.parseInt(response));
						dialog.dismiss();
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("JSON ERROR:", error.toString());
						Toast.makeText(CircleActivity.this, "Server not reachable", Toast.LENGTH_LONG).show();

						dialog.dismiss();
					}
				});
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);
	}

	private long mBackPressed;
	@Override
	public void onBackPressed() {

		if (mBackPressed +2000>System.currentTimeMillis()) {
			finish();
			System.exit(0);
			return;
		}
		else{
			Toast.makeText(CircleActivity.this,"Press BACK again to exit",Toast.LENGTH_SHORT).show();
		}
		mBackPressed  =System.currentTimeMillis();
	}
}
