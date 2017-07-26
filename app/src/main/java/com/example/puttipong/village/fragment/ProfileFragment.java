package com.example.puttipong.village.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puttipong.village.R;
import com.example.puttipong.village.dao.VillageDao;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    private ImageView imgVillage;
    private TextView tvVillageName, tvAddress,
            tvDetail, tvTel, tvEmail, tvWeb;
    private VillageDao villageDao;

    public ProfileFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        Bundle mBundle = getArguments();
        villageDao = (VillageDao) mBundle.getSerializable("VILLAGE");
        imgVillage = (ImageView) rootView.findViewById(R.id.imgVillage);
        tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);
        tvVillageName = (TextView) rootView.findViewById(R.id.tvVillageName);
        tvDetail = (TextView) rootView.findViewById(R.id.tvDetail);
        tvTel = (TextView) rootView.findViewById(R.id.tvTel);
        tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);
        tvWeb = (TextView) rootView.findViewById(R.id.tvWeb);

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

        if (villageDao != null) {
            Picasso.with(getActivity())
                    .load(villageDao.getImageUrl())
                    .into(imgVillage);
            tvVillageName.setText(villageDao.getVillageName());
            tvAddress.setText(villageDao.getAddress());
            tvDetail.setText(villageDao.getDetail());
            tvTel.setText(villageDao.getPhoneNumber());
            tvEmail.setText(villageDao.getEmail());
            tvWeb.setText((CharSequence) villageDao.getWebsite());

        }

        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
