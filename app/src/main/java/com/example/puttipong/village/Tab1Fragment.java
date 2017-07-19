package com.example.puttipong.village;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

public class Tab1Fragment extends Fragment {

    private TextView tvName, tvFullName;
    private ImageView imgProfile;
    private Profile profile = null;
    private Button btnLogout;
    private static final String TAG = "Tab1Fragment";

    public Tab1Fragment() {
        super();
    }

    public static Tab1Fragment newInstance(Profile profile) {
        Tab1Fragment fragment = new Tab1Fragment();
        Bundle args = new Bundle();
        args.putParcelable("USER_INFO", profile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab1, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvFullName = (TextView) rootView.findViewById(R.id.tvFullName);
        imgProfile = (ImageView) rootView.findViewById(R.id.imgProfile);
        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        tvName.setText("NEw Puttipong");

        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: "+ savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            profile = (Profile) bundle.getParcelable("USER_INFO");
        } else {
            profile = Profile.getCurrentProfile();
        }

//        Picasso.with(getActivity())
//                .load(profile.getProfilePictureUri(400, 400).toString())
//                .into(imgProfile);
        Log.i(TAG, "onActivityCreated: " + profile.getName());
        Log.i(TAG, "onActivityCreated: " + profile.getFirstName() + " " + profile.getLastName());


        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    private void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().finish();
        startActivity(intent);
    }
}
