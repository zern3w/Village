package com.example.puttipong.village.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.puttipong.village.R;
import com.example.puttipong.village.adapter.VillageAdapter;
import com.example.puttipong.village.dao.VillageDao;
import com.example.puttipong.village.manager.APIService;
import com.example.puttipong.village.manager.ApiUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab2Fragment extends Fragment {

    private static final String TAG = "Tab2Fragment";
    private RecyclerView recyclerView;
    private APIService mAPIService;
    Gson gson;
    private List<VillageDao> villageDaos;

    public Tab2Fragment() {
        super();
    }

    public static Tab2Fragment newInstance() {
        Tab2Fragment fragment = new Tab2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAPIService = ApiUtils.getAPIService();

        Call<VillageDao> call = mAPIService.getVillage();

        call.enqueue(new Callback<VillageDao>() {
            @Override
            public void onResponse(Call<VillageDao> call, Response<VillageDao> response) {
                Log.e(TAG, "onResponse: " + response.code());
                String server_JSONResponse = String.valueOf(response.body());
                Log.e(TAG, "onResponse: " + server_JSONResponse);
                Log.w(TAG, new GsonBuilder().setPrettyPrinting().create().toJson(response));
                Log.e("Success", new Gson().toJson(response.body()));

                VillageAdapter adapter = new VillageAdapter(response.body(), getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<VillageDao> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });

//                new Callback<VillageDao>() {
//                         @Override
//                         public void onResponse(Call<VillageDao> call, Response<VillageDao> response) {
//                             if (response.isSuccessful()) {
//
//                                 if(response.body() != null){
//                                     Log.i(TAG, "onResponse: " + response.body());
//                                     VillageDao json = response.body();
//
////use GSON to parse
//                                     if (json != null) {
//                                         Gson gson = new Gson();
//                                        l
//                                         objResponseList = Arrays.asList(objResponse);
//                                     }
//
//                                     Log.e(TAG, "onResponse: " + objResponseList.get(0).getVillageId());
//                                 }
//
//                             }else {
//                                 try {
//                                     Log.e(TAG, "onResponse: " + response.errorBody().string() );
//                                 } catch (IOException e) {
//                                     e.printStackTrace();
//                                 }
//                             }
//                         }
//
//                         @Override
//                         public void onFailure(Call<VillageDao> call, Throwable t) {
//                             Log.e(TAG, "onFailure: " + t.toString() );
//                         }
//    });

        return rootView;
    }

//    private void initInstances(View rootView) {
//        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//    }

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
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
