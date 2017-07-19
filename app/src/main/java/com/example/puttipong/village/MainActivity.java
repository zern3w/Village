package com.example.puttipong.village;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    String facebookId;
    final int[] ICONS = new int[]{
            R.drawable.ic_perm_identity_white_24dp,
            R.drawable.ic_list_white_24dp,
            R.drawable.ic_place_white_24dp
    };

    private ViewPager mViewPager;

    private SectionPageAdapter mSectionPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Starting.");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            facebookId = extras.getString("FACEBOOK_ID");
            Log.d(TAG, "onCreate: "+facebookId);
            //The key argument here must match that used in the other activity
        }

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(ICONS[1]);
        tabLayout.getTabAt(2).setIcon(ICONS[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Profile");
        adapter.addFragment(new Tab2Fragment(), "Village's List");
        adapter.addFragment(new Tab3Fragment(), "Maps");
        viewPager.setAdapter(adapter);
    }
}
