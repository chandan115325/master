package com.wealthdoctor.bill_reminder.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.model.BillReminderDetailData;
import com.wealthdoctor.bill_reminder.reminder.Reminder;
import com.wealthdoctor.bill_reminder.reminder.ReminderDatabase;

import java.util.Calendar;
import java.util.List;


public class BillReminderDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText selectDateEditText;
    private TextInputEditText amountEditText;
    private TextInputEditText informationEditText;
    private TextInputEditText addNoteEditText;
    private TextInputEditText timeEditText;

    private TextView monthlyText;
    private TextView biMonthlyText;
    private TextView quarterlyText;
    private TextView halfYearlyText;
    private TextView yearlyText;

    private View coloredView;
    private View dummyView;
    private long mRepeatTime;
    private String mRepeat;
    private String mRepeatNo;

    // Constant values in milliseconds
    private static final long milMinute = 60000L;
    private static final long milHour = 3600000L;
    private static final long milDay = 86400000L;
    private static final long milWeek = 604800000L;
    private static final long milMonth = 2592000000L;

    private String monthly;
    private String biMonthly;
    private String halfYearly;
    private String quarterly;
    private String yearly;
    private String dueDate;
    private String dueAmount;
    private String information;

    private boolean billFrequency;
    private String mRepeatType;

    private String br_parent_name;
    private int br_parent_id;
    private String br_child_name;
    private int br_child_id;
    private String br_due_date;
    private String br_due_date_time;
    private String br_amount;
    private String br_bill_id;
    private String br_bill_frequency;
    private String br_note;
    private String br_already_paid;
    private String br_status;
    private String br_created_date;
    private String br_edited_date;
    private String br_last_viewed_date;
    private int br_lang_id;

    private String reminderType;
    private ScrollView billReminderScroller;

    private Calendar mCalendar;
    private int mYear, mMonth, mHour, mMinute, mDay;
    List<Reminder> data;

    TextInputLayout tilDueDate;
    TextInputLayout tilDueTime;
    TextInputLayout tilDueAmount;
    TextInputLayout tilInformation;

    BillReminderDetailData billReminderDetailData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_reminder_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Bill Account");


        selectDateEditText = (TextInputEditText) findViewById(R.id.bill_reminder_due_date_edittext);
        timeEditText = (TextInputEditText) findViewById(R.id.bill_reminder_due_date_time_edittext);
        amountEditText = (TextInputEditText) findViewById(R.id.bill_reminder_amount_edittext);
        informationEditText = (TextInputEditText) findViewById(R.id.bill_reminder_information_edittext);
        dummyView = (View) findViewById(R.id.dummyView);
        addNoteEditText = (TextInputEditText) findViewById(R.id.bill_reminder_note);

        // bill frequency textView initialization
        monthlyText = (TextView) findViewById(R.id.bill_reminder_monthly);
        halfYearlyText = (TextView) findViewById(R.id.bill_reminder_half_yearly);
        biMonthlyText = (TextView) findViewById(R.id.bill_reminder_bi_monthly);
        yearlyText = (TextView) findViewById(R.id.bill_reminder_yearly);
        quarterlyText = (TextView) findViewById(R.id.bill_reminder_quarterly);
        billReminderScroller = (ScrollView) findViewById(R.id.bill_reminder_scroll_view);

        // text input layout instantiation
        tilDueDate = (TextInputLayout) findViewById(R.id.bill_reminder_due_date_text_input_layout);
        tilDueTime = (TextInputLayout) findViewById(R.id.bill_reminder_due_time_input_layout);
        tilDueAmount = (TextInputLayout) findViewById(R.id.bill_reminder_due_amount_input_layout);
        tilInformation = (TextInputLayout) findViewById(R.id.bill_reminder_information_input_layout);

        monthlyText.setOnClickListener(this);
        halfYearlyText.setOnClickListener(this);
        biMonthlyText.setOnClickListener(this);
        yearlyText.setOnClickListener(this);
        quarterlyText.setOnClickListener(this);

        billReminderDetailData =  getIntent().getParcelableExtra("Provider List");

        coloredView = dummyView;

        dueAmount = amountEditText.getText().toString();
        information = informationEditText.getText().toString();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveDataNValidate();

            }
        });
    }

    // Date picker function
    public void selectDueDate(View view) {
        if (view == selectDateEditText) {

            // Get Current Date

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {


                            selectDateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        dueDate = selectDateEditText.getText().toString();
    }

    // Selecting the bill frequency
    @Override
    public void onClick(View view) {

        billFrequency = true;
        if (view == monthlyText && billFrequency == true && coloredView != view) {
            billFrequency = false;
            //Toast.makeText(this, "monthly", Toast.LENGTH_SHORT).show();
            //
            monthlyText.setBackgroundColor(Color.BLUE);
            coloredView.setBackgroundColor(getResources().getColor(R.color.textColorBackground));
            coloredView = monthlyText;

            reminderType = monthlyText.getText().toString();

            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_detail_activity_textView_color));

            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            yearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        } else if (view == biMonthlyText && billFrequency == true && coloredView != view) {
            billFrequency = false;
            //Toast.makeText(this, "biMonthly", Toast.LENGTH_SHORT).show();
            biMonthlyText.setBackgroundColor(Color.BLUE);

            coloredView.setBackgroundColor(getResources().getColor(R.color.textColorBackground));
            coloredView = view;
            reminderType = biMonthlyText.getText().toString();

            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_detail_activity_textView_color));
            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            yearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        } else if (view == quarterlyText && billFrequency == true && coloredView != view) {
            billFrequency = false;
            //Toast.makeText(this, "quarterlyText", Toast.LENGTH_SHORT).show();
            quarterlyText.setBackgroundColor(Color.BLUE);

            coloredView.setBackgroundColor(getResources().getColor(R.color.textColorBackground));
            coloredView = view;
            reminderType = quarterlyText.getText().toString();

            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_detail_activity_textView_color));
            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            yearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        } else if (view == halfYearlyText && billFrequency == true && coloredView != view) {
            billFrequency = false;
            //Toast.makeText(this, "halfYearly", Toast.LENGTH_SHORT).show();
            halfYearlyText.setBackgroundColor(Color.BLUE);

            coloredView.setBackgroundColor(getResources().getColor(R.color.textColorBackground));
            coloredView = view;
            reminderType = halfYearlyText.getText().toString();

            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_detail_activity_textView_color));
            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            yearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        } else if (view == yearlyText && billFrequency == true && coloredView != view) {
            billFrequency = false;
            //Toast.makeText(this, "yearly", Toast.LENGTH_SHORT).show();
            yearlyText.setBackgroundColor(Color.BLUE);
            yearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_detail_activity_textView_color));

            coloredView.setBackgroundColor(getResources().getColor(R.color.textColorBackground));
            coloredView = view;
            reminderType = yearlyText.getText().toString();

            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        }
    }

    // To save data in local storage from floating action button
    public void saveDataNValidate() {

        boolean validation = false;
        monthly = monthlyText.getText().toString();
        biMonthly = biMonthlyText.getText().toString();
        quarterly = quarterlyText.getText().toString();
        halfYearly = halfYearlyText.getText().toString();
        yearly = yearlyText.getText().toString();

        br_parent_id = 1;
        br_parent_name = billReminderDetailData.getBillReminderDetailData();
        br_child_id = 1;
        br_child_name = "Aircel";
        br_due_date = selectDateEditText.getText().toString();
        br_due_date_time = timeEditText.getText().toString();
        br_amount = amountEditText.getText().toString();
        br_bill_id = informationEditText.getText().toString();
        br_bill_frequency = reminderType;
        br_note = addNoteEditText.getText().toString();
        br_already_paid = "No";
        br_status = null;
        br_created_date = selectDateEditText.getText().toString();
        br_edited_date = timeEditText.getText().toString();
        br_last_viewed_date = "unknown";
        br_lang_id = 1;

        if (br_due_date.length() < 5) {
            tilDueDate.setError("Please select Due Date");
            billReminderScroller.setFocusableInTouchMode(true);
            billReminderScroller.fullScroll(ViewGroup.FOCUS_UP);

            validation = false;
        } else {
            tilDueDate.setError(null);
            validation = true;
        }
        if (br_due_date_time.length() < 3) {
            billReminderScroller.setFocusableInTouchMode(true);
            billReminderScroller.fullScroll(ViewGroup.FOCUS_UP);
            tilDueTime.setError("Please select Due Time");
            validation = false;

        } else {
            tilDueTime.setError(null);
            validation = true;
        }
        if (br_amount.length() < 1) {
            billReminderScroller.setFocusableInTouchMode(true);
            billReminderScroller.fullScroll(ViewGroup.FOCUS_UP);
            tilDueAmount.setError("Please Enter Due Amount");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(selectDateEditText.getWindowToken(), 0);
            validation = false;
        } else {
            // til.setError(null);
            validation = true;
        }

        if (br_bill_id.length() < 1) {
            billReminderScroller.setFocusableInTouchMode(true);
            billReminderScroller.fullScroll(ViewGroup.FOCUS_UP);
            tilInformation.setError("Please Enter bill id");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(selectDateEditText.getWindowToken(), 0);
            validation = false;
        } else {
            tilInformation.setError(null);
            validation = true;
        }


        // Todo database updation
        if (validation) {
            ReminderDatabase rb = new ReminderDatabase(BillReminderDetailActivity.this);

            // Creating Reminder
            int ID = rb.addReminder(new Reminder(br_parent_name, br_parent_id, br_child_name, br_child_id,
                    br_due_date, br_due_date_time, br_amount, br_bill_id,
                    br_bill_frequency, br_note, br_already_paid, br_status,
                    br_created_date, br_edited_date, br_last_viewed_date, br_lang_id));

            data = rb.getAllReminders();
            Log.d("Database", data.get(0).getBr_already_paid().toString());
            Log.d("Database", data.get(0).getBr_due_date().toString());
            Log.d("Database", data.get(0).getBr_due_date_time().toString());
            Log.d("Database", data.get(0).getBr_amount().toString());
//            Log.d("Database", data.get(0).getBr_bill_frequency().toString());

            //Log.d("Database", data.toString());
// Create toast to confirm new reminder
            Toast.makeText(getApplicationContext(), "Saved",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(BillReminderDetailActivity.this, BillReminderActivity.class);
            startActivity(intent);
        }
        // Todo Set up calender for creating the notification
               /* mCalendar.set(Calendar.MONTH, --mMonth);
                mCalendar.set(Calendar.YEAR, mYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
                mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
                mCalendar.set(Calendar.MINUTE, mMinute);
                mCalendar.set(Calendar.SECOND, 0);*/

        // TODO Check repeat type
                /*if (mRepeatType.equals("Minute")) {
                    mRepeatTime = Integer.parseInt(mRepeatNo) * milMinute;
                } else if (mRepeatType.equals("Hour")) {
                    mRepeatTime = Integer.parseInt(mRepeatNo) * milHour;
                } else if (mRepeatType.equals("Day")) {
                    mRepeatTime = Integer.parseInt(mRepeatNo) * milDay;
                } else if (mRepeatType.equals("Week")) {
                    mRepeatTime = Integer.parseInt(mRepeatNo) * milWeek;
                } else if (mRepeatType.equals("Month")) {
                    mRepeatTime = Integer.parseInt(mRepeatNo) * milMonth;
                }*/

        // TODO Create a new notification
                /*if (br_status == 1) {
                    if (mRepeat.equals("true")) {
                        new AlarmReceiver().setRepeatAlarm(getApplicationContext(), mCalendar, ID, mRepeatTime);
                    } else if (mRepeat.equals("false")) {
                        new AlarmReceiver().setAlarm(getApplicationContext(), mCalendar, ID);
                    }
                }*/


        //}
    }

    // Todo to create reminder and notification
    public void fabReminderSave(View view) {

        Log.d("Floating action button", "working");

       /* monthly = monthlyText.getText().toString();
        biMonthly = biMonthlyText.getText().toString();
        quarterly = quarterlyText.getText().toString();
        halfYearly = halfYearlyText.getText().toString();
        yearly = yearlyText.getText().toString();

        ReminderDatabase rb = new ReminderDatabase(this);

        // Creating Reminder
        int ID = rb.addReminder(new Reminder( br_parent_name, br_parent_id, br_child_name, br_child_id,
                br_due_date, br_due_date_time, br_amount, br_bill_id,
                br_bill_frequency, br_note, br_already_paid, br_status,
                br_created_date, br_edited_date, br_last_viewed_date, br_lang_id));

        // Set up calender for creating the notification
        mCalendar.set(Calendar.MONTH, --mMonth);
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);

        // TODO Check repeat type
        if (mRepeatType.equals("Minute")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milMinute;
        } else if (mRepeatType.equals("Hour")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milHour;
        } else if (mRepeatType.equals("Day")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milDay;
        } else if (mRepeatType.equals("Week")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milWeek;
        } else if (mRepeatType.equals("Month")) {
            mRepeatTime = Integer.parseInt(mRepeatNo) * milMonth;
        }

        // Create a new notification
        if (br_status == 1) {
            if (mRepeat.equals("true")) {
                new AlarmReceiver().setRepeatAlarm(getApplicationContext(), mCalendar, ID, mRepeatTime);
            } else if (mRepeat.equals("false")) {
                new AlarmReceiver().setAlarm(getApplicationContext(), mCalendar, ID);
            }
        }

        // Create toast to confirm new reminder
        Toast.makeText(getApplicationContext(), "Saved",
                Toast.LENGTH_SHORT).show();
        List<Reminder> data = rb.getAllReminders();
        Log.d("Database:", data.toString());
        onBackPressed();*/
    }

    // Time picker from edittext
    public void selectDueDateTime(View view) {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(BillReminderDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeEditText.setText(hourOfDay + ":" + minute);
            }


        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }
}