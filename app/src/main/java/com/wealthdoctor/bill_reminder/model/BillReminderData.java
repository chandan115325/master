package com.wealthdoctor.bill_reminder.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CHANDAN on 1/10/2018.
 */

public class BillReminderData implements Parcelable {


    private String billReminderData;

    protected BillReminderData(Parcel in) {
        billReminderData = in.readString();
    }

    public static final Creator<BillReminderData> CREATOR = new Creator<BillReminderData>() {
        @Override
        public BillReminderData createFromParcel(Parcel in) {
            return new BillReminderData(in);
        }

        @Override
        public BillReminderData[] newArray(int size) {
            return new BillReminderData[size];
        }
    };

    public String getData() {
        return billReminderData;
    }
    public void setBillReminderData(String billReminderData) {
        this.billReminderData = billReminderData;
    }

    public BillReminderData(String billReminderData) {
        this.billReminderData = billReminderData;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(billReminderData);
    }
}
