package com.wealthdoctor.bill_reminder.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.wealthdoctor.MainActivity;
import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.adapter.BillReminderAdapterList;
import com.wealthdoctor.bill_reminder.model.BillReminderData;
import com.wealthdoctor.bill_reminder.model.BillReminderDetailData;

import java.util.ArrayList;
import java.util.List;


public class BillReminderDetailListActivity extends AppCompatActivity {

    BillReminderAdapterList adapterList;
    List<BillReminderDetailData> broadbandList;
    SearchView searchView;
    RecyclerView recyclerView;
    BillReminderData providerItemName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_reminder_detail_list);

        //Todo To get the category KEY to identify the List of items.
        Intent intent = getIntent();
        // BillReminderData dataItem = intent.getExtras();
        providerItemName = getIntent().getParcelableExtra("Item");


        // Dummy data to check the output.
        broadbandList = new ArrayList<>();
        broadbandList = ChildProviderList.getBroadbandLandlineList();


        adapterList = new BillReminderAdapterList(BillReminderDetailListActivity.this, broadbandList);
        recyclerView = (RecyclerView) findViewById(R.id.bill_reminder_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterList);
        adapterList.notifyDataSetChanged();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle(R.string.toolbar_title);

        setTitle("Type Provider");
        // white background notification bar
        // whiteNotificationBar(recyclerView);

        setTitle("Select Provider");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);


        //searchView.setSubmitButtonEnabled(true);
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapterList.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapterList.getFilter().filter(query);
                return false;
            }
        });

        return true;
    }

    /*private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, BillReminderActivity.class );
        startActivity(intent);
    }

}
