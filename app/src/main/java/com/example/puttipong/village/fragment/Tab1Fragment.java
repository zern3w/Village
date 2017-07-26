package com.example.puttipong.village.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puttipong.village.activity.LoginActivity;
import com.example.puttipong.village.R;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

public class Tab1Fragment extends Fragment implements View.OnClickListener {

    private TextView tvName, tvFullName, tvUserId;
    private ImageView imgProfile;
    private Button btnLogout;
    private static final String TAG = "Tab1Fragment";
    private String name, link, facebookId, imgUri;

    public Tab1Fragment() {
        super();
    }

    public static Tab1Fragment newInstance(Bundle bundle) {
        Tab1Fragment fragment = new Tab1Fragment();
        fragment.setArguments(bundle);
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
        tvUserId = (TextView) rootView.findViewById(R.id.tvUserId);
        imgProfile = (ImageView) rootView.findViewById(R.id.imgProfile);
        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);

        imgProfile.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

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
        Bundle bundle = getArguments();

        if (bundle != null) {
            name = bundle.getString("NAME");
            link = bundle.getString("LINK");
            facebookId = bundle.getString("FACEBOOK_ID");
            imgUri = bundle.getString("IMG_URI");

            tvName.setText(name);
            tvFullName.setText(name);
            tvUserId.setText(facebookId);
            Picasso.with(getActivity())
                    .load(imgUri)
                    .into(imgProfile);
        }

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

    @Override
    public void onClick(View v) {
        if (v == imgProfile) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(link));
            startActivity(intent);
        }

        if (v == btnLogout) {
            logout();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
