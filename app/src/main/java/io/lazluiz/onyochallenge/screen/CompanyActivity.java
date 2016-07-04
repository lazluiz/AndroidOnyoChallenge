package io.lazluiz.onyochallenge.screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import io.lazluiz.onyochallenge.R;

/**
 * Created by Luiz F. Lazzarin on 13/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class CompanyActivity extends AppCompatActivity {

    private static final String LOG = CompanyActivity.class.getSimpleName();

    private static final int TAB_HOME = 0;
    private static final int TAB_CATEGORIES = 1;
    private static final int TAB_REDEEM = 2;


    private MenuItem mMenuSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        setupViewPagerAndTabs(viewPager, tabLayout);
    }

    //region Tab & ViewPager

    private void setupViewPagerAndTabs(final ViewPager viewPager, TabLayout tabLayout) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new CompanyHomeFragment(), getString(R.string.tab_company_home_title));
        adapter.addFrag(new CompanyCategoriesFragment(), getString(R.string.tab_company_categories_title));
        adapter.addFrag(new Fragment(), getString(R.string.tab_company_redeem_title));

        viewPager.setAdapter(adapter);

        if (tabLayout != null) {

            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int position = tab.getPosition();
                    viewPager.setCurrentItem(position);

                    if(mMenuSearch != null){
                        mMenuSearch.setEnabled(position == TAB_CATEGORIES);
                        mMenuSearch.setVisible(position == TAB_CATEGORIES);
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    //endregion

    //region Toolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.company, menu);
        mMenuSearch = menu.findItem(R.id.action_search);

        return true;
    }

    //endregion
}
