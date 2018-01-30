package com.wealthdoctor.bill_reminder.expandable_recycler_view.viewholders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.activity.BillReminderActivity;
import com.wealthdoctor.bill_reminder.activity.BillReminderDetailActivity;
import com.wealthdoctor.bill_reminder.activity.BillReminderDetailEditActivity;
import com.wealthdoctor.bill_reminder.reminder.Reminder;
import com.wealthdoctor.bill_reminder.reminder.ReminderDatabase;


public class ChildViewHolder extends RecyclerView.ViewHolder {

    TextView edit;
    ImageView editIcon;
    ImageView deleteIcon;
    CheckBox alreadyPaid;
    private Context mContext;
    ReminderDatabase rb;
    BillReminderActivity billReminderActivity = new BillReminderActivity();
    Reminder temp;
    boolean status;

    public ChildViewHolder(View itemView) {
        super(itemView);

        mContext = itemView.getContext();

        //edit = (TextView) itemView.findViewById(R.id.list_item_child);
        editIcon = (ImageView) itemView.findViewById(R.id.child_edit);
        deleteIcon = (ImageView) itemView.findViewById(R.id.child_delete);
        alreadyPaid = (CheckBox) itemView.findViewById(R.id.child_already_paid);

        rb = new ReminderDatabase(mContext);

       /* edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mContext = v.getContext();
                Intent intent = new Intent(mContext, BillReminderDetailActivity.class);
                mContext.startActivity(intent);
                Log.d("BillReminder", "edit is working");

            }
        });
*/
        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = getAdapterPosition()-1 ;
                //if (i >= 0)
                for( int j = billReminderActivity.IDmap.size(); j>=0; j--){
                    if(j == i) {
                        int id = billReminderActivity.IDmap.get(j);

                        // Get reminder from reminder database using id

                        temp = rb.getReminder(id);
                        //temp.getBr_amount();
                        //temp.getBr_id();
                        // Delete reminder
                        // rb.deleteReminder(temp);
                        // Remove reminder from recycler view
                        BillReminderActivity.adapter.notifyItemRemoved(getAdapterPosition());
                        BillReminderActivity.br_reminder_list.remove(i);
                        BillReminderActivity.adapter.notifyDataSetChanged();

                        //billReminderActivity.deleteReminder(i);
                        // Delete reminder alarm
                        // mAlarmReceiver.cancelAlarm(getApplicationContext(), id);
                    }

                }

                Intent intent = new Intent(mContext, BillReminderDetailEditActivity.class);
                intent.putExtra("edit", temp);
                mContext.startActivity(intent);

                Log.d("BillReminder", "Edit Icon working");
            }
        });

        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //billReminderActivity = new BillReminderActivity();
                //billReminderActivity.deleteReminder(getAdapterPosition());

                int i = getAdapterPosition()-1 ;
                //if (i >= 0)
                for( int j = billReminderActivity.IDmap.size(); j>=0; j--){
                   if(j == i) {
                       int id = billReminderActivity.IDmap.get(j);

                       // Get reminder from reminder database using id

                       Reminder temp = rb.getReminder(id);
                       //temp.getBr_amount();
                       //temp.getBr_id();
                       // Delete reminder
                       rb.deleteReminder(temp);
                       // Remove reminder from recycler view
                       BillReminderActivity.adapter.notifyItemRemoved(getAdapterPosition());
                       BillReminderActivity.br_reminder_list.remove(i);
                       BillReminderActivity.adapter.notifyDataSetChanged();

                       //billReminderActivity.deleteReminder(i);
                       // Delete reminder alarm
                       // mAlarmReceiver.cancelAlarm(getApplicationContext(), id);
                   }

                }
                billReminderActivity.finish();
                Intent intent = new Intent(mContext, BillReminderActivity.class);

                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                mContext.startActivity(intent);
            }
        });
        alreadyPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BillReminder", "Checkbox is working");

                status = alreadyPaid.isChecked();
                Log.d("Billreminder checkbox", String.valueOf(status));
            }
        });
    }

    /*// On clicking a reminder item
    private void selectReminder(int mClickID) {
        String mStringClickID = Integer.toString(mClickID);

        // Create intent to edit the reminder
        // Put reminder id as extra
        Intent i = new Intent(mContext, BillReminderDetailEditActivity.class);
        i.putExtra(BillReminderDetailEditActivity.EXTRA_REMINDER_ID, mStringClickID);
       // mContextstartActivityForResult(i, 1);
    }*/
}
