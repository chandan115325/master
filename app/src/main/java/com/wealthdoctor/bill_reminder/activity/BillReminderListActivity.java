package com.wealthdoctor.bill_reminder.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.adapter.BillReminderAdapter;
import com.wealthdoctor.bill_reminder.model.BillReminderData;

import java.util.ArrayList;
import java.util.List;


public class BillReminderListActivity extends AppCompatActivity {


    BillReminderAdapter adapter;
    List<BillReminderData> billReminderDataList;
    RecyclerView recyclerView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_reminder_list);


        billReminderDataList = new ArrayList<>();

        billReminderDataList.add(new BillReminderData("Credit Card"));
        billReminderDataList.add(new BillReminderData("Data Card"));
        billReminderDataList.add(new BillReminderData("Electricity"));
        billReminderDataList.add(new BillReminderData("Water"));
        billReminderDataList.add(new BillReminderData("EMI"));
        billReminderDataList.add(new BillReminderData("Insurance"));
        billReminderDataList.add(new BillReminderData("Investment"));
        billReminderDataList.add(new BillReminderData("Mobile"));
        billReminderDataList.add(new BillReminderData("Credit Card"));
        billReminderDataList.add(new BillReminderData("Data Card"));
        billReminderDataList.add(new BillReminderData("Electricity"));
        billReminderDataList.add(new BillReminderData("Water"));
        billReminderDataList.add(new BillReminderData("EMI"));
        billReminderDataList.add(new BillReminderData("Insurance"));
        billReminderDataList.add(new BillReminderData("Investment"));
        billReminderDataList.add(new BillReminderData("Mobile"));

        adapter = new BillReminderAdapter(BillReminderListActivity.this, billReminderDataList);
        recyclerView = (RecyclerView) findViewById(R.id.bill_reminder_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle(R.string.toolbar_title);

        setTitle("Type Provider");
        // white background notification bar
        //whiteNotificationBar(recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        // searchView.setSubmitButtonEnabled(true);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);

                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    //To change the color of status bar/*
    /*private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }*/

}
