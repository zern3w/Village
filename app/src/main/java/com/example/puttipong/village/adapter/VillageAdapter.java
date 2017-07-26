package com.example.puttipong.village.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puttipong.village.R;
import com.example.puttipong.village.activity.MainActivity;
import com.example.puttipong.village.dao.VillageDao;
import com.example.puttipong.village.fragment.ProfileFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VillageAdapter extends RecyclerView.Adapter<VillageAdapter.ViewHolder> {

    private Bundle mBundle;
    private Context context;
    private ProfileFragment mFragment;
    private List<VillageDao> villageDaos;

    private static final String TAG = "VillageAdapter";

    public VillageAdapter(List<VillageDao> villageDaos, Context context) {
        this.villageDaos = villageDaos;
        this.context = context;
    }

    @Override
    public VillageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        VillageDao villageDao = villageDaos.get(position);

        viewHolder.tvName.setText(villageDao.getVillageName());
        viewHolder.tvAddress.setText(villageDao.getAddress());

        Picasso.with(this.context)
                .load(villageDao.getImageUrl())
                .into(viewHolder.imgVillage);
    }

    @Override
    public int getItemCount() {
        return villageDaos == null ? 0
                : villageDaos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgVillage;
        private TextView tvAddress, tvName;

        public ViewHolder(View view) {
            super(view);

            tvName = (TextView) view.findViewById(R.id.tvName);
            tvAddress = (TextView) view.findViewById(R.id.tvAddress);
            imgVillage = (ImageView) view.findViewById(R.id.imgVillage);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Log.i(TAG, "onClick: " + getAdapterPosition());
            fragmentJump(getAdapterPosition());
        }

        private void fragmentJump(int adapterPosition) {
            mFragment = new ProfileFragment();

            mBundle = new Bundle();
            mBundle.putSerializable("VILLAGE", villageDaos.get(adapterPosition));
            mFragment.setArguments(mBundle);

            FragmentManager fragmentManager = ((MainActivity) (context)).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.contentContainer, mFragment);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}