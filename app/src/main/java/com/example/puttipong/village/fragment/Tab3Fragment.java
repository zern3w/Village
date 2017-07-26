package com.example.puttipong.village.fragment;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puttipong.village.R;
import com.example.puttipong.village.activity.MainActivity;
import com.example.puttipong.village.dao.VillageDao;
import com.example.puttipong.village.manager.APIService;
import com.example.puttipong.village.manager.ApiUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Tab3Fragment extends Fragment implements OnMapReadyCallback {
    private static final String TAG = "Tab3Fragment";
    private GoogleMap mMap;
    private APIService mAPIService;
    private VillageDao villageDao;
    private double lat, lng;
    private String villageName, villageAddress, imgUrl;
    private ProfileFragment mFragment;
    private Bundle mBundle;
    private SupportMapFragment mapFragment;
    private View rootView;

    public Tab3Fragment() {
        super();
    }

    public static Tab3Fragment newInstance() {
        Tab3Fragment fragment = new Tab3Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab3, container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map_fragment);
        if (mapFragment != null) {
            getData();
            mapFragment.getMapAsync(this);
        }
    }

    private void getData() {
        if (mMap == null) {
            mAPIService = ApiUtils.getAPIService();
            Call<VillageDao> call = mAPIService.getVillage();

            call.enqueue(new Callback<VillageDao>() {
                @Override
                public void onResponse(Call<VillageDao> call, Response<VillageDao> response) {
                    Log.i(TAG, "onResponse: " + response.code());

                    villageDao = response.body();
                    lat = villageDao.getLatitude();
                    lng = villageDao.getLongitude();
                    villageName = villageDao.getVillageName();
                    villageAddress = villageDao.getAddress();
                    imgUrl = villageDao.getImageUrl();
                    Log.e(TAG, "onResponse: " + villageName);

                    setMarker();
                }

                @Override
                public void onFailure(Call<VillageDao> call, Throwable t) {
                    Log.i(TAG, "onFailure: " + t.toString());
                }
            });
        }
    }

    private void setMarker() {
        LatLng villageLatLng = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(villageLatLng).title(villageName));
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.info_window_layout, null);
                LatLng latLng = marker.getPosition();

                TextView tvVillageName = (TextView) v.findViewById(R.id.tvVillageName);
                TextView tvAddress = (TextView) v.findViewById(R.id.tvAddress);
                TextView tvLocation = (TextView) v.findViewById(R.id.tvLocation);
                ImageView imgVillage = (ImageView) v.findViewById(R.id.imgVillage);

                tvVillageName.setText(villageName);
                tvAddress.setText(villageAddress);
                tvLocation.setText("lat: " + latLng.latitude
                        + "   ,lng: " + latLng.longitude);

                Picasso.with(getActivity())
                        .load(imgUrl)
                        .into(imgVillage);

                return v;
            }

        });
        mMap.moveCamera(CameraUpdateFactory.newLatLng(villageLatLng));
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                showDetail();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.e(TAG, "onMapReady: " + villageName + lat + lng);
    }

    private void showDetail() {
        mFragment = new ProfileFragment();
        mBundle = new Bundle();
        mBundle.putSerializable("VILLAGE", villageDao);
        mFragment.setArguments(mBundle);
        Log.e(TAG, "showDetail: started");

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contentContainer2, mFragment);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
