package com.wealthdoctor.bill_reminder.expandable_recycler_view.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * The backing data object for an {@link ExpandableGroup}
 */
public class ExpandableGroup<T extends Parcelable> implements Parcelable {
  private String title;
  private String dueDate;
  private String dueAmount;
  private String billID;
  private String status;

  public ExpandableGroup(String title, String dueDate,  String billID, String dueAmount, String status, List<T> items) {
    this.title = title;
    this.dueDate = dueDate;
    this.dueAmount = dueAmount;
    this.billID = billID;
    this.status = status;
    this.items = items;
  }

  private List<T> items;

  protected ExpandableGroup(Parcel in) {
    title = in.readString();
    dueDate = in.readString();
    dueAmount = in.readString();
    billID = in.readString();
    status = in.readString();
    //items = in.createTypedArrayList(T.CREATOR);
  }

 /* public static final Creator<ExpandableGroup> CREATOR = new Creator<ExpandableGroup>() {
    @Override
    public ExpandableGroup createFromParcel(Parcel in) {
      return new ExpandableGroup(in);
    }

    @Override
    public ExpandableGroup[] newArray(int size) {
      return new ExpandableGroup[size];
    }
  };*/

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public String getDueAmount() {
    return dueAmount;
  }

  public void setDueAmount(String dueAmount) {
    this.dueAmount = dueAmount;
  }

  public String getBillID() {
    return billID;
  }

  public void setBillID(String billID) {
    this.billID = billID;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<T> getItems() {
    return items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }







  public int getItemCount() {
    return items == null ? 0 : items.size();
  }

  @Override
  public String toString() {
    return "ExpandableGroup{" +
        "title='" + title + '\'' +
        ", items=" + items +
        '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeString(dueDate);
    dest.writeString(dueAmount);
    dest.writeString(billID);
    dest.writeString(status);
    //dest.writeTypedList(items);
    if (items == null) {
      dest.writeByte((byte) (0x00));
      dest.writeInt(0);
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(items.size());
      final Class<?> objectsType = items.get(0).getClass();
      dest.writeSerializable(objectsType);
      dest.writeList(items);
    }
  }

 /* protected ExpandableGroup(Parcel in) {
    title = in.readString();
    byte hasItems = in.readByte();
    int size = in.readInt();
    if (hasItems == 0x01) {
      items = new ArrayList<T>(size);
      Class<?> type = (Class<?>) in.readSerializable();
      in.readList(items, type.getClassLoader());
    } else {
      items = null;
    }
  }*/

  /*@Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    if (items == null) {
      dest.writeByte((byte) (0x00));
      dest.writeInt(0);
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(items.size());
      final Class<?> objectsType = items.get(0).getClass();
      dest.writeSerializable(objectsType);
      dest.writeList(items);
    }
  }*/

  @SuppressWarnings("unused")
  public static final Creator<ExpandableGroup> CREATOR =
      new Creator<ExpandableGroup>() {
        @Override
        public ExpandableGroup createFromParcel(Parcel in) {
          return new ExpandableGroup(in);
        }

        @Override
        public ExpandableGroup[] newArray(int size) {
          return new ExpandableGroup[size];
        }
      };
}
