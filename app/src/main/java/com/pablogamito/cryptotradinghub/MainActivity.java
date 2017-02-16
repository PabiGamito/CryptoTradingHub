package com.pablogamito.cryptotradinghub;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize toolbar
//        Menu toolbar = (Menu) findViewById(R.id.toolbar);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_toolbar, toolbar);
//        setSupportActionBar(toolbar);

        // Initialize gridView
        final ArrayList<Pair> pairs = ((CryptoTradingHub) this.getApplication()).getPairs();
        final ListView pairsListView = (ListView) findViewById(R.id.pairsListView);
        final PairsArrayAdapter pairsArrayAdapter = new PairsArrayAdapter(this, pairs);
        pairsListView.setAdapter(pairsArrayAdapter);

        pairsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Create a new activity with an intent: show full screen pair view
                Intent intent = new Intent(MainActivity.this, pairView.class);
                // Pass data to the new intent
                intent.putExtra("EXTRA_PAIR_ID", Integer.toString(position) );
                startActivity(intent);


                // TODO: On click make row grow to full size filling up with chart and buy and sell button with current text going to top of page
                // On a pair click run:
//                Fragment clickedFragment = getFragmentManager().findFragmentById((int) id);
//                int duration = 500; // miliseconds
//                int offset = 0;
//                pairsListView.smoothScrollToPositionFromTop(position,offset,duration);
//                // pairsListView remove all margin, fill parent
//                TextView price = (TextView) v.findViewById(R.id.price);
//                LinearLayout pairContainerLinearLayout = (LinearLayout) v.findViewById(R.id.pairContainerLinearLayout);
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
//                pairContainerLinearLayout.setLayoutParams(params);
//
//                price.setText("YESS!!!");
//                Toast.makeText(MainActivity.this, "Pair "+ position, Toast.LENGTH_SHORT).show();


                // Refresh view with all changes made
//                pairsArrayAdapter.notifyDataSetChanged();

                // Commit state of fragment before update for back button to bring back to small view state
//                getFragmentManager().beginTransaction()
//                        .add(clickedFragment, "detail")
//                        // Add this transaction to the back stack
//                        .addToBackStack("back_detail")
//                        .commit();
//
//                // Update fragment state
//                View fragmentView = clickedFragment.getView();
//                TextView price = (TextView) fragmentView.findViewById(R.id.price);
//                price.setText("YESSS!!!");


            }
        });

        // Get data
//        DownloadTask task = new DownloadTask();
//        task.execute();

    }
}
