

package com.wealthdoctor.bill_reminder.reminder;

// Reminder class
public class Reminder {
    private int br_id;
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



    public Reminder(){}

    public Reminder(String br_parent_name, int br_parent_id, String br_child_name, int br_child_id, String br_due_date,
                    String br_due_date_time, String br_amount, String br_bill_id, String br_bill_frequency, String br_note,
                    String br_already_paid,
                    String br_status, String br_created_date, String br_edited_date, String br_last_viewed_date, int br_lang_id) {
        this.br_parent_name = br_parent_name;
        this.br_parent_id = br_parent_id;
        this.br_child_name = br_child_name;
        this.br_child_id = br_child_id;
        this.br_due_date = br_due_date;
        this.br_due_date_time = br_due_date_time;
        this.br_amount = br_amount;
        this.br_bill_id = br_bill_id;
        this.br_bill_frequency = br_bill_frequency;
        this.br_note = br_note;
        this.br_already_paid = br_already_paid;
        this.br_status = br_status;
        this.br_created_date = br_created_date;
        this.br_edited_date = br_edited_date;
        this.br_last_viewed_date = br_last_viewed_date;
        this.br_lang_id = br_lang_id;
    }



    public Reminder(int br_id, String br_parent_name, int br_parent_id, String br_child_name, int br_child_id,
                    String br_due_date, String br_due_date_time, String br_amount, String br_bill_id,
                    String br_bill_frequency, String br_note, String br_already_paid,
                    String br_status, String br_created_date, String br_edited_date, String br_last_viewed_date, int br_lang_id) {
        this.br_id = br_id;
        this.br_parent_name = br_parent_name;
        this.br_parent_id = br_parent_id;
        this.br_child_name = br_child_name;
        this.br_child_id = br_child_id;
        this.br_due_date = br_due_date;
        this.br_due_date_time = br_due_date_time;
        this.br_amount = br_amount;
        this.br_bill_id = br_bill_id;
        this.br_bill_frequency = br_bill_frequency;
        this.br_note = br_note;
        this.br_already_paid = br_already_paid;
        this.br_status = br_status;
        this.br_created_date = br_created_date;
        this.br_edited_date = br_edited_date;
        this.br_last_viewed_date = br_last_viewed_date;
        this.br_lang_id = br_lang_id;
    }

    public int getBr_id() {
        return br_id;
    }

    public void setBr_id(int br_id) {
        this.br_id = br_id;
    }

    public String getBr_parent_name() {
        return br_parent_name;
    }

    public void setBr_parent_name(String br_parent_name) {
        this.br_parent_name = br_parent_name;
    }

    public int getBr_parent_id() {
        return br_parent_id;
    }

    public void setBr_parent_id(int br_parent_id) {
        this.br_parent_id = br_parent_id;
    }

    public String getBr_child_name() {
        return br_child_name;
    }

    public void setBr_child_name(String br_child_name) {
        this.br_child_name = br_child_name;
    }

    public int getBr_child_id() {
        return br_child_id;
    }

    public void setBr_child_id(int br_child_id) {
        this.br_child_id = br_child_id;
    }

    public String getBr_due_date() {
        return br_due_date;
    }

    public void setBr_due_date(String br_due_date) {
        this.br_due_date = br_due_date;
    }

    public String getBr_due_date_time() {
        return br_due_date_time;
    }

    public void setBr_due_date_time(String br_due_date_time) {
        this.br_due_date_time = br_due_date_time;
    }

    public String getBr_amount() {
        return br_amount;
    }

    public void setBr_amount(String br_amount) {
        this.br_amount = br_amount;
    }

    public String getBr_bill_id() {
        return br_bill_id;
    }

    public void setBr_bill_id(String br_bill_id) {
        this.br_bill_id = br_bill_id;
    }

    public String getBr_bill_frequency() {
        return br_bill_frequency;
    }

    public void setBr_bill_frequency(String br_bill_frequency) {
        this.br_bill_frequency = br_bill_frequency;
    }

    public String getBr_note() {
        return br_note;
    }

    public void setBr_note(String br_note) {
        this.br_note = br_note;
    }

    public String getBr_already_paid() {
        return  br_already_paid;
    }

    public void setBr_already_paid(String br_already_paid) {
        this.br_already_paid = br_already_paid;
    }

    public String getBr_status() {
        return br_status;
    }

    public void setBr_status(String br_status) {
        this.br_status = br_status;
    }

    public String getBr_created_date() {
        return br_created_date;
    }

    public void setBr_created_date(String br_created_date) {
        this.br_created_date = br_created_date;
    }

    public String getBr_edited_date() {
        return br_edited_date;
    }

    public void setBr_edited_date(String br_edited_date) {
        this.br_edited_date = br_edited_date;
    }

    public String getBr_last_viewed_date() {
        return br_last_viewed_date;
    }

    public void setBr_last_viewed_date(String br_last_viewed_date) {
        this.br_last_viewed_date = br_last_viewed_date;
    }

    public int getBr_lang_id() {
        return br_lang_id;
    }

    public void setBr_lang_id(int br_lang_id) {
        this.br_lang_id = br_lang_id;
    }


}
