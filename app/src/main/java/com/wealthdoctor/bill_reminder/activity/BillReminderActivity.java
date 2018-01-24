package com.wealthdoctor.bill_reminder.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.calender.data.CalendarAdapter;
import com.wealthdoctor.bill_reminder.calender.data.Day;
import com.wealthdoctor.bill_reminder.calender.widget.FlexibleCalendar;
import com.wealthdoctor.bill_reminder.expandable_recycler_view.expand.GenreAdapter;
import com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter.ChildProvider;
import com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter.ParentProvider;
import com.wealthdoctor.bill_reminder.reminder.Reminder;
import com.wealthdoctor.bill_reminder.reminder.ReminderDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

//import static com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter.ReminderDataFactory.makeGenres;


public class BillReminderActivity extends AppCompatActivity {

    private FloatingActionButton addButton;
    private FlexibleCalendar viewCalendar;
    public GenreAdapter adapter;
    boolean flag = true;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getWindow().setNavigationBarColor(Color.WHITE);
        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle(R.string.toolbar_title);

        setTitle("Bill Reminder");

        viewCalendar = (FlexibleCalendar) findViewById(R.id.calendar);
        addButton = (FloatingActionButton) findViewById(R.id.fab_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BillReminderActivity.this, BillReminderListActivity.class);
                startActivity(intent);
            }
        });

        // Calender Expand functionality
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        adapter = new GenreAdapter(dataList());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    viewCalendar.collapse(500);

                } else {
                    viewCalendar.expand(500);

                }
            }
        });
// Todo to control the recyclerview scroller on item click
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
        viewCalendar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (flag) {
                    viewCalendar.collapse(500);
                    flag = false;
                } else if (!flag) {
                    viewCalendar.expand(500);
                    flag = true;
                }
                return false;
            }


        });


        calender();
    }

    public void calender() {


        // init calendar
        Calendar cal = Calendar.getInstance();
        CalendarAdapter adapter = new CalendarAdapter(this, cal);
        viewCalendar.setAdapter(adapter);

        // bind events of calendar
        viewCalendar.setCalendarListener(new FlexibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Day day = viewCalendar.getSelectedDay();
                Log.i(getClass().getName(), "Selected Day: "
                        + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
            }

            @Override
            public void onItemClick(View v) {
                Day day = viewCalendar.getSelectedDay();
                Log.i(getClass().getName(), "The Day of Clicked View: "
                        + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
            }

            @Override
            public void onDataUpdate() {
                Log.i(getClass().getName(), "Data Updated");
            }

            @Override
            public void onMonthChange() {
                Log.i(getClass().getName(), "Month Changed"
                        + ". Current Year: " + viewCalendar.getYear()
                        + ", Current Month: " + (viewCalendar.getMonth() + 1));
            }

            @Override
            public void onWeekChange(int position) {
                Log.i(getClass().getName(), "Week Changed"
                        + ". Current Year: " + viewCalendar.getYear()
                        + ", Current Month: " + (viewCalendar.getMonth() + 1)
                        + ", Current Week position of Month: " + position);
            }
        });

        // use methods
        viewCalendar.addEventTag(2015, 8, 10);
        viewCalendar.addEventTag(2015, 8, 14);
        viewCalendar.addEventTag(2015, 8, 23);

        viewCalendar.select(new Day(2015, 4, 22));


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        // menuInflater.inflate(R.menu.bill_reminder, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // Retrieving data from ReminderDatabase
    public List<ParentProvider> dataList() {
        List<ParentProvider> br_reminder_list = new ArrayList<>();
        ReminderDatabase dbList = new ReminderDatabase(this);
        List<Reminder> reminderList = new ArrayList<>();
        reminderList = dbList.getAllReminders();


        int reminderSize = reminderList.size();
        //ParentProvider reminderListDB = null;
// Todo updating main list reminder data
        for (int i = 0; i < reminderSize; i++) {
            br_reminder_list.add(new ParentProvider(reminderList.get(i).getBr_parent_name(),
                    reminderList.get(i).getBr_created_date(), reminderList.get(i).getBr_bill_id(),
                    reminderList.get(i).getBr_amount(), reminderList.get(i).getBr_status(),
                    makeSublistEditorial(), R.mipmap.ic_launcher_round));
            Log.d("Database", reminderList.get(i).getBr_bill_id());
           /* ParentProvider(String title, String dueDate, String billInformation, String billStatus, String billAmount,
                    List<ChildProvider> items, int iconResId)*/
        /*public  List<ParentProvider> makeGenres () {
            return Arrays.asList(makeRockGenre(), makeRockGenre(), makeRockGenre(), makeRockGenre());
        }*/
        }
        //return Arrays.asList(reminderListDB);
        return br_reminder_list;
    }

    /*public  ParentProvider makeRockGenre() {
        return new ParentProvider("Airtel", makeSublistEditorial(), R.mipmap.ic_launcher_round);
    }*/


    public static List<ChildProvider> makeSublistEditorial() {
        ChildProvider airtel = new ChildProvider("Already Paid", R.id.child_delete, R.id.child_edit, true);
        return Arrays.asList(airtel);
    }
}



