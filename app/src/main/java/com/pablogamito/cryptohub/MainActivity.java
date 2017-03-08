package com.pablogamito.cryptohub;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.content.Intent;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SET UP TOP ACTION BAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Add balance value to top action bar
        LinearLayout toolbarConatainer = (LinearLayout) toolbar.findViewById(R.id.toolbar_container);
        TextView balance = new TextView(this);
        balance.setGravity(Gravity.CENTER);
        balance.setText(Float.toString(com.pablogamito.cryptohub.CryptoHub.randomNum(0, 10000)));
        toolbarConatainer.addView(balance);
        // Add tabs to action bar


        // SET UP BOTTOM NAV BAR
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
            findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_user:
                            UserFragment userFragment = new UserFragment();
                            loadMainContentFragment(userFragment, "userFragment");
                            return true;
                        case R.id.action_trade:
                            TradeFragment tradeFragment = new TradeFragment();
                            loadMainContentFragment(tradeFragment, "userFragment");
                            return true;
                        case R.id.action_notifications:
                            return true;
                    }
                    return false;
                }
            });

        // Initialize default view
        UserFragment userFragment = new UserFragment();
        loadMainContentFragment(userFragment, "userFragment");
    }

    public void loadMainContentFragment(Fragment frag, String tag) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = getFragmentManager().findFragmentById(R.id.content_main);
        if(fragment == null) {
            ft.add(R.id.content_main, frag, tag);
        } else {
            ft.replace(R.id.content_main, frag, tag);
        }
        ft.addToBackStack(null);

        ft.commit();
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
