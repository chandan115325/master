


package com.wealthdoctor.bill_reminder.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class ReminderDatabase extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ReminderDatabase";

    // Table name
    private static final String TABLE_REMINDERS = "ReminderTable";

    // Table Columns names
    private static final String br_id = "id";
    private static final String br_parent_name = "br_parent_name";
    private static final String br_parent_id = "br_parent_id";
    private static final String br_child_name = "br_child_name";
    private static final String br_child_id = "br_child_id";
    private static final String br_due_date = "br_due_date";
    private static final String br_due_date_time = "br_due_date_time";
    private static final String br_amount = "br_amount";
    private static final String br_bill_id = "br_bill_id";
    private static final String br_bill_frequency = "br_bill_frequency";
    private static final String br_note = "br_note";
    private static final String br_already_paid = "br_already_paid";
    private static final String br_status = "br_status";
    private static final String br_created_date = "br_created_date";
    private static final String br_edited_date = "br_edited_date";
    private static final String br_last_viewed_date = "br_last_viewed_date";
    private static final String br_lang_id = "br_lang_id";


    public ReminderDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REMINDERS_TABLE = "CREATE TABLE " + TABLE_REMINDERS +
                "("
                + br_id + " INTEGER PRIMARY KEY,"
                + br_parent_name + " TEXT,"
                + br_parent_id + " INTEGER,"
                + br_child_name + " TEXT,"
                + br_child_id + " INTEGER,"
                + br_due_date + " TEXT,"
                + br_due_date_time + " TEXT,"
                + br_amount + " TEXT,"
                + br_bill_id + " TEXT,"
                + br_bill_frequency + " TEXT,"
                + br_note + " TEXT,"
                + br_already_paid + " TEXT,"
                + br_status + " TEXT,"
                + br_created_date + " TEXT,"
                + br_edited_date + " TEXT,"
                + br_last_viewed_date + " TEXT,"
                + br_lang_id + " INTEGER" + ")";


        db.execSQL(CREATE_REMINDERS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDERS);

        // Create tables again
        onCreate(db);
    }

    // Adding new Reminder
    public int addReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(br_parent_name , reminder.getBr_parent_name());
        values.put(br_parent_id , reminder.getBr_parent_id());
        values.put(br_child_name , reminder.getBr_child_name());
        values.put(br_child_id , reminder.getBr_bill_id());
        values.put(br_due_date , reminder.getBr_due_date());
        values.put(br_due_date_time, reminder.getBr_due_date_time());
        values.put(br_amount, reminder.getBr_amount());
        values.put(br_bill_id, reminder.getBr_bill_id());
        values.put(br_bill_frequency, reminder.getBr_bill_frequency());
        values.put(br_note, reminder.getBr_note());
        values.put(br_already_paid, reminder.getBr_already_paid());
        values.put(br_status, reminder.getBr_status());
        values.put(br_created_date, reminder.getBr_created_date());
        values.put(br_edited_date, reminder.getBr_edited_date());
        values.put(br_last_viewed_date, reminder.getBr_last_viewed_date());
        values.put(br_lang_id, reminder.getBr_lang_id());

        // Inserting Row
        long ID = db.insert(TABLE_REMINDERS, null, values);
        db.close();
        return (int) ID;
    }

    // Getting single Reminder
    public Reminder getReminder(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REMINDERS, new String[]
                        {
                                 br_id,
                                 br_parent_name,
                                 br_parent_id,
                                 br_child_name,
                                 br_child_id,
                                 br_due_date,
                                 br_due_date_time,
                                 br_amount,
                                 br_bill_id,
                                 br_bill_frequency,
                                 br_note,
                                 br_already_paid,
                                 br_status,
                                 br_created_date,
                                 br_edited_date,
                                 br_last_viewed_date,
                                 br_lang_id

                        }, br_id + "=?",

                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Reminder reminder = new Reminder(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                Integer.parseInt(cursor.getString(2)), cursor.getString(3), Integer.parseInt(cursor.getString(4)),
                cursor.getString(5), cursor.getString(6), cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9),cursor.getString(10) ,cursor.getString(11),
                cursor.getString(12),cursor.getString(13),cursor.getString(14)
                ,cursor.getString(15),Integer.parseInt(cursor.getString(8)));

        return reminder;
    }

    // Getting all Reminders
    public List<Reminder> getAllReminders(){
        List<Reminder> reminderList = new ArrayList<>();

        // Select all Query
        String selectQuery = "SELECT * FROM " + TABLE_REMINDERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Reminder reminder = new Reminder();
                reminder.setBr_id(Integer.parseInt(cursor.getString(0)));
                reminder.setBr_parent_name(cursor.getString(1));
                reminder.setBr_parent_id(Integer.parseInt(cursor.getString(2)));
                reminder.setBr_child_name(cursor.getString(3));
                reminder.setBr_child_id(Integer.parseInt(cursor.getString(4)));
                reminder.setBr_due_date(cursor.getString(5));
                reminder.setBr_due_date_time(cursor.getString(6));
                reminder.setBr_amount(cursor.getString(7));
                reminder.setBr_bill_id(cursor.getString(8));
                reminder.setBr_bill_frequency(cursor.getString(9));
                reminder.setBr_note(cursor.getString(10));
                reminder.setBr_already_paid(cursor.getString(11));
                reminder.setBr_status(cursor.getString(12));
                reminder.setBr_created_date(cursor.getString(13));
                reminder.setBr_edited_date(cursor.getString(14));
                reminder.setBr_last_viewed_date(cursor.getString(15));
                reminder.setBr_lang_id(Integer.parseInt(cursor.getString(16)));


                // Adding Reminders to list
                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }
        return reminderList;
    }

    // Getting Reminders Count
    public int getRemindersCount(){
        String countQuery = "SELECT * FROM " + TABLE_REMINDERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single Reminder
    public int updateReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(br_parent_name , reminder.getBr_parent_name());
        values.put(br_parent_id , reminder.getBr_parent_id());
        values.put(br_child_name , reminder.getBr_child_name());
        values.put(br_child_id , reminder.getBr_bill_id());
        values.put(br_due_date , reminder.getBr_due_date());
        values.put(br_due_date_time, reminder.getBr_due_date_time());
        values.put(br_amount, reminder.getBr_amount());
        values.put(br_bill_id, reminder.getBr_bill_id());
        values.put(br_bill_frequency, reminder.getBr_bill_frequency());
        values.put(br_note, reminder.getBr_note());
        values.put(br_already_paid, reminder.getBr_already_paid());
        values.put(br_status, reminder.getBr_status());
        values.put(br_created_date, reminder.getBr_created_date());
        values.put(br_edited_date, reminder.getBr_edited_date());
        values.put(br_last_viewed_date, reminder.getBr_last_viewed_date());
        values.put(br_lang_id, reminder.getBr_lang_id());

        // Updating row
        return db.update(TABLE_REMINDERS, values, br_id + "=?",
                new String[]{String.valueOf(reminder.getBr_id())});
    }

    // Deleting single Reminder
    public void deleteReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REMINDERS, br_id + "=?",
                new String[]{String.valueOf(reminder.getBr_id())});
        db.close();
    }
}
