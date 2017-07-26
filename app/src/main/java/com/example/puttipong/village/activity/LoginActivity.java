package com.example.puttipong.village.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puttipong.village.R;
import com.example.puttipong.village.fragment.Tab1Fragment;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    private Profile profile;
    private TextView tvAppName;
    private LoginButton btnFacebookLogin;
    private ProfileTracker profileTracker;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
    }

    private void initialize() {

        tvAppName = (TextView) findViewById(R.id.tvAppName);

        Typeface roboto = Typeface.createFromAsset(this.getAssets(),
                "fonts/Roboto-Thin.ttf"); //use this.getAssets if you are calling from an Activity
        tvAppName.setTypeface(roboto);

        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                setFacebookUserInfo(currentProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        btnFacebookLogin = (LoginButton) findViewById(R.id.btnFacebookLogin);
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                profileTracker.startTracking();
                Profile profile = Profile.getCurrentProfile();

                setFacebookUserInfo(profile);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login attempt canceled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), error.getCause().toString(), Toast.LENGTH_SHORT).show();
            }
        };
        btnFacebookLogin.setReadPermissions("user_friends");
        btnFacebookLogin.registerCallback(callbackManager, callback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        //Facebook login
        profile = Profile.getCurrentProfile();
        setFacebookUserInfo(profile);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setFacebookUserInfo(Profile profile) {
        if (profile != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("FACEBOOK_ID", profile.getId());
            intent.putExtra("NAME", profile.getName());
            intent.putExtra("LINK", profile.getLinkUri().toString());
            intent.putExtra("IMG_URI", profile.getProfilePictureUri(200, 200).toString());

            finish();
            startActivity(intent);
        }
    }
}
