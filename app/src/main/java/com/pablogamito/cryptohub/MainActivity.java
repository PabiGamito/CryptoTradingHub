package com.pablogamito.cryptohub;

import android.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.content.Intent;
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
                        case R.id.action_portfolio:
                            return true;
                        case R.id.action_trade:
                            return true;
                        case R.id.action_account:
                            return true;
                    }
                    return false;
                }
            });

        // Initialize gridView
        CryptoHub app = (CryptoHub)this.getApplication();
        final ArrayList<TradingPair> tradingPairs = CryptoHub.getPairs();
        final ListView pairsListView = (ListView) findViewById(R.id.pairsListView);
        final PairsArrayAdapter pairsArrayAdapter = new PairsArrayAdapter(this, tradingPairs);
        pairsListView.setAdapter(pairsArrayAdapter);

        pairsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Create a new activity with an intent: show full screen pair view
                Intent intent = new Intent(MainActivity.this, PairView.class);
                // Pass data to the new intent
                intent.putExtra("EXTRA_PAIR_ID", Integer.toString(position) );
                startActivity(intent);

                // TODO: On click make row grow to full size filling up with chart and buy and sell button with current text going to top of page
            }
        });
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
