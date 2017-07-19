package com.example.puttipong.village;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private Button btnFacebookMore;
    private TextView tvInfo;
    private LoginButton btnFacebookLogin;
    private CallbackManager callbackManager;
    private ProfilePictureView profilePictureView;
    String userId, firstName, lastName, gender, id, link, email;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        btnFacebookLogin = (LoginButton)findViewById(R.id.btnFacebookLogin);
        btnFacebookMore = (Button) findViewById(R.id.btnFacebookMore);
        tvInfo = (TextView) findViewById(R.id.tvInfo);

        btnFacebookMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/"+userId));
                    startActivity(intent);
                } catch(Exception e) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/")));
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("FACEBOOK_ID","1245680797");

                    startActivity(intent);
                    finish();
                }
            }
        });

        btnFacebookLogin.setReadPermissions(Arrays.asList(
                "email", "user_birthday", "public_profile"));

        profilePictureView = (ProfilePictureView) findViewById(R.id.friendProfilePicture);


        btnFacebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();

//                Bundle bundle = new Bundle();
//                bundle.putParcelable("USER_INFO", profile);

                Tab1Fragment fragment = new Tab1Fragment();
                fragment.newInstance(profile);

                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {
                tvInfo.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                tvInfo.setText(error.getCause().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
