package com.tantao.novel.view.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.tantao.novel.R;
import com.tantao.novel.base.BaseActivity;
import com.tantao.novel.helper.BottomNavigationViewHelper;

public class MainActivity extends BaseActivity{

    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private ContactsFragment contactsFragment;
    private MeFragment meFragment;
    private Fragment[] fragments;
    private FrameLayout fy;
    private int lastShowFragment = 0;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastShowFragment != 0) {
                        switchFrament(lastShowFragment, 0);
                        lastShowFragment = 0;
                    }
                    return true;
                case R.id.navigation_find:
                    if (lastShowFragment !=1){
                        switchFrament(lastShowFragment,1);
                        lastShowFragment = 1;
                    }
                    return true;
                case R.id.navigation_contacts:
                    if (lastShowFragment !=2){
                        switchFrament(lastShowFragment,2);
                        lastShowFragment = 2;
                    }
                    return true;
                case R.id.navigation_me:
                    if (lastShowFragment !=3){
                        switchFrament(lastShowFragment,3);
                        lastShowFragment = 3;
                    }
                    return true;
            }
            return false;
        }
    };



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initBottomNavigationView();
        initFragments();
    }

    private void initBottomNavigationView(){
        fy = (FrameLayout) findViewById(R.id.fy_content);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    private void switchFrament(int lastIndex, int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fy_content, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    private void initFragments(){
        homeFragment = new HomeFragment();
        findFragment= new FindFragment();
        contactsFragment = new ContactsFragment();
        meFragment=new MeFragment();
        fragments = new Fragment[]{homeFragment,findFragment,contactsFragment,meFragment};
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fy_content, homeFragment)
                .show(homeFragment)
                .commit();
    }


}
