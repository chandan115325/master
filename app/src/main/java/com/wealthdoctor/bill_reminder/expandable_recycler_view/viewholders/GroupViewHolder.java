package com.wealthdoctor.bill_reminder.expandable_recycler_view.viewholders;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.RotateAnimation;

import com.wealthdoctor.bill_reminder.activity.BillReminderActivity;
import com.wealthdoctor.bill_reminder.expandable_recycler_view.listeners.OnGroupClickListener;

import static android.view.animation.Animation.RELATIVE_TO_SELF;


public abstract class GroupViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
    BillReminderActivity billReminderActivity = new BillReminderActivity();
    private OnGroupClickListener listener;

    public GroupViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onGroupClick(getAdapterPosition());

        }
    }

    public void setOnGroupClickListener(OnGroupClickListener listener) {
        this.listener = listener;
    }

    public void expand() {
    }

    public void collapse() {
    }


}
