package com.wealthdoctor.bill_reminder.expandable_recycler_view.viewholders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.activity.BillReminderActivity;


public class ChildViewHolder extends RecyclerView.ViewHolder  {

  TextView edit;
  ImageView editIcon;
  ImageView deleteIcon;
  CheckBox alreadyPaid;

  public ChildViewHolder(View itemView) {
    super(itemView);

    edit = (TextView)itemView.findViewById(R.id.list_item_child);
    editIcon = (ImageView)itemView.findViewById(R.id.child_edit);
    deleteIcon = (ImageView)itemView.findViewById(R.id.child_delete);
    alreadyPaid = (CheckBox) itemView.findViewById(R.id.child_already_paid);


    edit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           // Log.d("BillReminder", "edit is working");
        }
    });

    editIcon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("BillReminder", "Edit Icon working");
        }
    });

    deleteIcon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("BillReminder", "Delete Icon working");
        }
    });

    alreadyPaid.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("BillReminder", "Checkbox is working");
        }
    });
  }
}
