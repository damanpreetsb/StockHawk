package com.sam_chordas.android.stockhawk.ui;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.fragment.FirstFragment;
import com.sam_chordas.android.stockhawk.fragment.SecondFragment;
import com.sam_chordas.android.stockhawk.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class StockDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Bundle symbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        String s = getIntent().getStringExtra(getResources().getString(R.string.symbol_label));

        symbol = new Bundle();
        symbol.putString(getString(R.string.symbol_bundle),s);

        getSupportActionBar().setTitle(s.toUpperCase());
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(), "DETAILS");
        adapter.addFragment(new SecondFragment(), "CHART");
        adapter.addFragment(new ThirdFragment(), "NEWS");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    FirstFragment f1 = new FirstFragment();
                    f1.setArguments(symbol);
                    return f1;
                case 1:
                    SecondFragment f2 = new SecondFragment();
                    f2.setArguments(symbol);
                    return f2;
                case 2:
                    ThirdFragment f3 = new ThirdFragment();
                    f3.setArguments(symbol);
                    return f3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}