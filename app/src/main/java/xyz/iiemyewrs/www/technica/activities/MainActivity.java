package xyz.iiemyewrs.www.technica.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import xyz.iiemyewrs.www.technica.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView showText;
    EditText inputPassword;

    @Override
    public void onBackPressed() {
        createAndBack();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAndBack();
    }

    void createAndBack(){
        setContentView(R.layout.activity_main);
        Button login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(this);
        Button signup = (Button) findViewById(R.id.signUpButton);
        signup.setOnClickListener(this);
        Button google = (Button)findViewById(R.id.googleSignIn);
        google.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.loginButton) {
            setContentView(R.layout.activity_login);
            passwordAndShow();
        }else if(v.getId() == R.id.signUpButton){
            setContentView(R.layout.activity_signup);
            passwordAndShow();
        }else if(v.getId() == R.id.googleSignIn){
            startActivity(new Intent(MainActivity.this,CircleActivity.class));
            overridePendingTransition(R.anim.animation_slide_up_appear, R.anim.animation_zoom_out);
            finish();

        }
    }

    void passwordAndShow(){
        showText = (TextView) findViewById(R.id.showPassword);
        showText.setText("Show");
        showText.setVisibility(View.INVISIBLE);
        showText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String state = showText.getText().toString();
                if (state.equals("Show")) {
                    showText.setText("Hide");
                    inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    showText.setText("Show");
                    inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        inputPassword = (EditText) findViewById(R.id.password);
        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() <= 0) {
                    showText.setVisibility(View.INVISIBLE);
                } else {
                    showText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    //TODO:Change forgot method.
    public void forgot(View view){

    }
}
