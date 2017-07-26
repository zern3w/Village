package com.example.puttipong.village.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.puttipong.village.R;
import com.example.puttipong.village.adapter.SectionPageAdapter;
import com.example.puttipong.village.fragment.Tab1Fragment;
import com.example.puttipong.village.fragment.Tab2Fragment;
import com.example.puttipong.village.fragment.Tab3Fragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private Tab1Fragment tab1Fragment;
    private Tab2Fragment tab2Fragment;
    private Tab3Fragment tab3Fragment;
    boolean doubleBackToExitPressedOnce = false;
    private SectionPageAdapter mSectionPageAdapter;
    private static final String TAG = "MainActivity";
    final int[] ICONS = new int[]{
            R.drawable.ic_perm_identity_white_24dp,
            R.drawable.ic_list_white_24dp,
            R.drawable.ic_place_white_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d(TAG, "onCreate: Starting.");

        initInstances();
    }

    private void initInstances() {
        tab1Fragment = new Tab1Fragment();
        tab2Fragment = new Tab2Fragment();
        tab3Fragment = new Tab3Fragment();
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        tab1Fragment.setArguments(getIntent().getExtras());
        // Set up the ViewPager with the sections adapter.
        setupViewPager(mViewPager);

        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(ICONS[1]);
        tabLayout.getTabAt(2).setIcon(ICONS[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(tab1Fragment, getString(R.string.tab_text_1));
        adapter.addFragment(tab2Fragment, getString(R.string.tab_text_2));
        adapter.addFragment(tab3Fragment, getString(R.string.tab_text_3));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, R.string.double_back_exit, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }
}