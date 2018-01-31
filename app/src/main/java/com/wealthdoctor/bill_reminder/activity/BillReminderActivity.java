package com.wealthdoctor.bill_reminder.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
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
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.wealthdoctor.MainActivity;
import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.calender.data.CalendarAdapter;
import com.wealthdoctor.bill_reminder.calender.data.Day;
import com.wealthdoctor.bill_reminder.calender.widget.FlexibleCalendar;
import com.wealthdoctor.bill_reminder.expandable_recycler_view.expand.GenreAdapter;
import com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter.ChildProvider;
import com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter.ParentProvider;
import com.wealthdoctor.bill_reminder.reminder.DateTimeSorter;
import com.wealthdoctor.bill_reminder.reminder.Reminder;
import com.wealthdoctor.bill_reminder.reminder.ReminderDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

//import static com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter.ReminderDataFactory.makeGenres;


public class BillReminderActivity extends AppCompatActivity {

    private FloatingActionButton addButton;
    private FlexibleCalendar viewCalendar;
    public static GenreAdapter adapter;
    boolean flag = true;
    RecyclerView recyclerView;

    // Initialize lists
    List<String> br_status = new ArrayList<>();
    List<String> br_parentName = new ArrayList<>();
    List<String> br_bill_id = new ArrayList<>();
    List<String> br_amount = new ArrayList<>();
    List<String> Actives = new ArrayList<>();
    List<String> DateAndTime = new ArrayList<>();
    List<Integer> IDList = new ArrayList<>();
    List<DateTimeSorter> DateTimeSortList = new ArrayList<>();
    int reminderSize;
    public static List<ParentProvider> br_reminder_list;
    ReminderDatabase db;
    List<Reminder> reminderList;
    int key = 0;
    public static LinkedHashMap<Integer, Integer> IDmap = new LinkedHashMap<>();

    @Override
    protected void onResume() {
        super.onResume();
       // viewCalendar.collapse(500);
    }

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
        //this.finish();
        overridePendingTransition(0, 0);
        getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        setTitle("Bill Reminder");
        db = new ReminderDatabase(this);
        viewCalendar = (FlexibleCalendar) findViewById(R.id.calendar);

        addButton = (FloatingActionButton) findViewById(R.id.fab_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BillReminderActivity.this, BillReminderListActivity.class);
                startActivity(intent);
            }
        });
        //Todo put in seperate thread, getting all the reminder from database
        reminderList = new ArrayList<>();

        // Calender Expand functionality
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:
        /*RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }*/
        br_reminder_list = dataList();
        adapter = new GenreAdapter(BillReminderActivity.this, br_reminder_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        float offsetPx = getResources().getDimension(R.dimen.bottom_offset_dp);
        BottomOffsetDecoration bottomOffsetDecoration = new BottomOffsetDecoration((int) offsetPx);
        recyclerView.addItemDecoration(bottomOffsetDecoration);
        //viewCalendar.collapse(500);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    viewCalendar.collapseTo(500);

                } else {
                    viewCalendar.expand(500);

                }
            }
        });


// Todo to control the recyclerview scroller on item click

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

       // runLayoutAnimation(recyclerView);
        calender();
    }
// recycler view falling down navigation
   /* private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
*/
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

        reminderList = db.getAllReminders();
        reminderSize = reminderList.size();
        br_reminder_list = new ArrayList<>();
        // Add date and time as DateTimeSorter objects
        for (int k = 0; k < reminderSize; k++) {
            // DateTimeSortList.add(new DateTimeSorter(key, DateAndTime.get(k)));
            IDList.add(reminderList.get(k).getBr_id());
            br_parentName.add(reminderList.get(k).getBr_parent_name());
            br_amount.add(reminderList.get(k).getBr_amount());
            br_bill_id.add(reminderList.get(k).getBr_bill_id());
            DateAndTime.add(reminderList.get(k).getBr_due_date() + " " + reminderList.get(k).getBr_due_date_time());
            br_status.add(reminderList.get(k).getBr_status());
        }
        int num = DateAndTime.size();

        for (int k = 0; k < reminderSize; k++) {
            DateTimeSortList.add(new DateTimeSorter(key, DateAndTime.get(k)));

            key++;
        }

        int numDate = DateTimeSortList.size();
        // Sort items according to date and time in ascending order
        Collections.sort(DateTimeSortList, new DateTimeComparator());
        int k = 0;


        //ParentProvider reminderListDB = null;
// Todo updating main list reminder data
        for (DateTimeSorter item : DateTimeSortList) {

            int i = item.getIndex();

            br_reminder_list.add(new ParentProvider(IDList.get(i),
                    br_parentName.get(i),
                    DateAndTime.get(i),
                    br_bill_id.get(i),
                    br_amount.get(i),
                    br_status.get(i),
                    makeSublistEditorial(),
                    R.mipmap.ic_launcher_round));
            //  Log.d("Database", reminderList.get(i).getBr_bill_id());

            IDmap.put(k, IDList.get(i));
            k++;

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

    // Class to compare date and time so that items are sorted in ascending order
    public class DateTimeComparator implements Comparator {
        DateFormat f = new SimpleDateFormat("dd-mm-yyyy hh:mm");

        public int compare(Object a, Object b) {
            String o1 = ((DateTimeSorter) a).getDateTime();
            String o2 = ((DateTimeSorter) b).getDateTime();

            try {
                return f.parse(o1).compareTo(f.parse(o2));
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    // Deleting reminder
    // Get the reminder id associated with the recycler view item
    /*public void deleteReminder(int adapterPosition) {
//        if(br_reminder_list.isEmpty())return;
        br_reminder_list.clear();
        br_reminder_list.add((ParentProvider) dataList());
        adapter.notifyItemRemoved(adapterPosition);
        }




    public void onDeleteItem(int count) {
        dataList().clear();
        dataList().addAll(dataList());
    }

    public void removeItemSelected(int selected) {
        if (dataList().isEmpty()) return;
        dataList().remove(selected);
        adapter.notifyItemRemoved(selected);
    }
*/
    protected int getDefaultItemCount() {
        return 100;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    static class BottomOffsetDecoration extends RecyclerView.ItemDecoration {
        private int mBottomOffset;

        public BottomOffsetDecoration(int bottomOffset) {
            mBottomOffset = bottomOffset;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int dataSize = state.getItemCount();
            int position = parent.getChildAdapterPosition(view);
            if (dataSize > 0 && position == dataSize - 1) {
                outRect.set(0, 0, 0, mBottomOffset);
            } else {
                outRect.set(0, 0, 0, 0);
            }

        }
    }
}



