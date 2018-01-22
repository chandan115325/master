package com.wealthdoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wealthdoctor.bill_reminder.activity.BillReminderActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void billReminder(View view) {
        Intent intent = new Intent(MainActivity.this, BillReminderActivity.class);
        startActivity(intent);
    }
}
