package com.example.puttipong.village.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.puttipong.village.R;
import com.example.puttipong.village.adapter.VillageAdapter;
import com.example.puttipong.village.dao.VillageDao;
import com.example.puttipong.village.manager.APIService;
import com.example.puttipong.village.manager.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab2Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "Tab2Fragment";
    private RecyclerView recyclerView;
    private APIService mAPIService;
    private VillageAdapter adapter;
    private List<VillageDao> villageDaoList;
    private SwipeRefreshLayout swipeRefreshLayout;

    public Tab2Fragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
        initInstances(rootView);
        getData();
        return rootView;
    }

    private void getData() {
        mAPIService = ApiUtils.getAPIService();
        Call<List<VillageDao>> call = mAPIService.getVillageList();

        call.enqueue(new Callback<List<VillageDao>>() {
            @Override
            public void onResponse(Call<List<VillageDao>> call, Response<List<VillageDao>> response) {
                Log.e(TAG, "onResponse: " + response.code());
                villageDaoList = response.body();

                adapter = new VillageAdapter(villageDaoList, getActivity());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<VillageDao>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void initInstances(View rootView) {
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
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
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
        refreshContent();
    }

    private void refreshContent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 5000);
    }
}
