package com.wealthdoctor.bill_reminder.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CHANDAN on 1/10/2018.
 */

public class BillReminderDetailData implements Parcelable {


    protected BillReminderDetailData(Parcel in) {
        billReminderDetailData = in.readString();
    }

    public static final Creator<BillReminderDetailData> CREATOR = new Creator<BillReminderDetailData>() {
        @Override
        public BillReminderDetailData createFromParcel(Parcel in) {
            return new BillReminderDetailData(in);
        }

        @Override
        public BillReminderDetailData[] newArray(int size) {
            return new BillReminderDetailData[size];
        }
    };

    public String getBillReminderDetailData() {
        return billReminderDetailData;
    }

    private String billReminderDetailData;


    public BillReminderDetailData(String billReminderData) {
        this.billReminderDetailData = billReminderData;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(billReminderDetailData);
    }
}
