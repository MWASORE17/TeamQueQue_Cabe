package queque.cabe.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import queque.cabe.R;
import queque.cabe.dao.DBController;
import queque.cabe.fragments.FragmentAccount;
import queque.cabe.fragments.FragmentHome;
import queque.cabe.fragments.FragmentInbox;
import queque.cabe.fragments.FragmentMore;
import queque.cabe.fragments.FragmentOffers;
import queque.cabe.utilities.BottomNavigationViewHelper;
import queque.cabe.view.CustomViewPager;

/**
 * Created by Willy Laurents on 3/8/2017.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewPager) CustomViewPager viewPager;
    private DBController dbController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        ButterKnife.bind(this);

        dbController = new DBController(this);
//        ArrayList<HashMap<String,String>> map = dbController.getAllDataFromTable(DBController.db_user);
//        Log.d("test",">"+map.get(0).get("email"));
//        HashMap<String,String> map = dbController.getDataWhere(DBController.db_user,"email","dev@cabe.com");
//        Log.d("test",">"+map.get("password"));

//        ArrayList<String>arrayList = new ArrayList<>();
//        arrayList.add(null);
//        arrayList.add("test@gmail.com");
//        arrayList.add("asdfasdfzv");
//        arrayList.add("123");
//        dbController.insertDataTo(DBController.db_user,arrayList);
//
//        ArrayList<HashMap<String,String>> map = dbController.getAllDataFromTable(DBController.db_user);
//        ArrayList<HashMap<String,String>> map = dbController.getNearMeData("db_outlet","3.62346","98.6696");
        setupViewPager(viewPager);
        viewPager.setPagingEnabled(false);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                viewPager.setCurrentItem(0,false);
                                break;
                            case R.id.action_offers:
                                viewPager.setCurrentItem(1,false);
                                break;
                            case R.id.action_account:
                                AlertDialog dialog;
                                View alertView = View.inflate(new ContextThemeWrapper(MainActivity.this, R.style.AlertDialog_AppCompat), R.layout.frag_account,null);
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setView(alertView);
                                dialog = builder.create();
                                dialog.show();
                                break;
                            case R.id.action_inbox:
                                viewPager.setCurrentItem(3,false);
                                break;
                            case R.id.action_more:
                                viewPager.setCurrentItem(4,false);
                                break;
                        }
                        return false;
                    }
                });
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if(toolbar!=null) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(MainActivity.this.getSupportFragmentManager());
        adapter.addFragment(new FragmentHome(), "Home");
        adapter.addFragment(new FragmentOffers(), "Offers");
        adapter.addFragment(new FragmentAccount(), "Account");
        adapter.addFragment(new FragmentInbox(), "Inbox");
        adapter.addFragment(new FragmentMore(), "More");
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
            return mFragmentList.get(position);
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
