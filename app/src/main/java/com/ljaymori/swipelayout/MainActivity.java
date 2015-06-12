package com.ljaymori.swipelayout;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    RecyclerView recyclerView;
    MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new MyRecyclerViewAdapter(this, initData());
        mAdapter.setOnListViewRevealListener(new MyRecyclerViewAdapter.OnListViewRevealListener() {
            @Override
            public void onReveal() {
                Log.i("MainActivity", "scroll false");
//                recyclerView.setScrollContainer(false);
//                recyclerView.setClickable(false);
//                recyclerView.setFocusable(false);
            }
        });
        mAdapter.setOnListViewVanishListener(new MyRecyclerViewAdapter.OnListViewVanishListener() {
            @Override
            public void onVanish() {
                Log.i("MainActivity", "scroll true");
//                recyclerView.setScrollContainer(true);
//                recyclerView.setClickable(true);
//                recyclerView.setFocusable(true);
            }
        });

        recyclerView.setAdapter(mAdapter);
    }

    private ArrayList<String> initData() {
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0; i<10; i++) {
            list.add("TITLE_" + i);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
