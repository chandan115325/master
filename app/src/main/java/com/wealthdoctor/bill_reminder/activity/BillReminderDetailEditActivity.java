package com.wealthdoctor.bill_reminder.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.reminder.AlarmReceiver;
import com.wealthdoctor.bill_reminder.reminder.Reminder;
import com.wealthdoctor.bill_reminder.reminder.ReminderDatabase;

import java.util.Calendar;


public class BillReminderDetailEditActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText selectDateEditText;
    private TextInputEditText amountEditText;
    private TextInputEditText informationEditText;
    private TextInputEditText addNoteEditText;
    private TextInputEditText time;

    private FloatingActionButton fab;

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

    private String br_parent_name = "datacard";
    private int br_parent_id = 1;
    private String br_child_name = "aircel";
    private int br_child_id = 1;
    private String br_due_date ;
    private String br_due_date_time;
    private String br_amount;
    private String br_bill_id ;
    private String br_bill_frequency;
    private String br_note;
    private String br_already_paid = "No";
    private String br_status ;
    private String br_created_date;
    private String br_edited_date;
    private String br_last_viewed_date;
    private int br_lang_id;

    private Calendar mCalendar;
    private int mYear, mMonth, mHour, mMinute, mDay;
    public static final String EXTRA_REMINDER_ID = "Reminder_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_reminder_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Bill Account");

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthly = monthlyText.getText().toString();
                biMonthly = biMonthlyText.getText().toString();
                quarterly = quarterlyText.getText().toString();
                halfYearly = halfYearlyText.getText().toString();
                yearly = yearlyText.getText().toString();

                ReminderDatabase rb = new ReminderDatabase(BillReminderDetailEditActivity.this);

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
               /* if (br_status == 1) {
                    if (mRepeat.equals("true")) {
                        new AlarmReceiver().setRepeatAlarm(getApplicationContext(), mCalendar, ID, mRepeatTime);
                    } else if (mRepeat.equals("false")) {
                        new AlarmReceiver().setAlarm(getApplicationContext(), mCalendar, ID);
                    }
                }*/

                // Create toast to confirm new reminder
                Toast.makeText(getApplicationContext(), "Saved",
                        Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });

        selectDateEditText = (TextInputEditText) findViewById(R.id.bill_reminder_due_date_edittext);
        //time = (TextInputEditText)findViewById(R.id.bill_reminder_due_date_time_edittext);
        amountEditText = (TextInputEditText) findViewById(R.id.bill_reminder_amount_edittext);
        informationEditText = (TextInputEditText) findViewById(R.id.bill_reminder_information_edittext);
        dummyView = (View) findViewById(R.id.dummyView);
        addNoteEditText = (TextInputEditText) findViewById(R.id.bill_reminder_note);

        monthlyText = (TextView) findViewById(R.id.bill_reminder_monthly);
        halfYearlyText = (TextView) findViewById(R.id.bill_reminder_half_yearly);
        biMonthlyText = (TextView) findViewById(R.id.bill_reminder_bi_monthly);
        yearlyText = (TextView) findViewById(R.id.bill_reminder_yearly);
        quarterlyText = (TextView) findViewById(R.id.bill_reminder_quarterly);

        monthlyText.setOnClickListener(this);
        halfYearlyText.setOnClickListener(this);
        biMonthlyText.setOnClickListener(this);
        yearlyText.setOnClickListener(this);
        quarterlyText.setOnClickListener(this);

        coloredView = dummyView;

        dueAmount = amountEditText.getText().toString();
        information = informationEditText.getText().toString();


    }


    public void selectDueDate(View view) {
        if (view == selectDateEditText) {

            // Get Current Date
            selectDateEditText.setFocusableInTouchMode(true);
            selectDateEditText.setFocusable(true);


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

            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        }
    }

    public void resetBackground(View view) {


    }

    public void fabReminderSave(View view) {

        /*monthly = monthlyText.getText().toString();
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

        onBackPressed();*/
    }


    public void selectDueDateTime(View view) {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(BillReminderDetailEditActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time.setText(hourOfDay + ":" + minute);
                    }


                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }
}