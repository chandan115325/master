package com.wealthdoctor.bill_reminder.expandable_recycler_view.expand;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.expandable_recycler_view.models.ExpandableGroup;
import com.wealthdoctor.bill_reminder.expandable_recycler_view.viewholders.GroupViewHolder;
import com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter.ParentProvider;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class MainListViewHolder extends GroupViewHolder {

    private TextView billReminderDueDate;
    private TextView billID;
    private TextView providerName;
    private TextView billDueAmount;
    private ImageView arrow;
    private ImageView icon;

    public MainListViewHolder(View itemView) {
        super(itemView);
    //Todo to edit the main list item inialization
        providerName = (TextView) itemView.findViewById(R.id.list_item_provider_name);
        arrow = (ImageView) itemView.findViewById(R.id.list_item_genre_arrow);
        icon = (ImageView) itemView.findViewById(R.id.list_item_provider_icon);
        billReminderDueDate = (TextView) itemView.findViewById(R.id.list_item_reminder_date);
        billID = (TextView) itemView.findViewById(R.id.list_item__bill_id);
        billDueAmount = (TextView) itemView.findViewById(R.id.list_item_due_amount);
    }

    // Todo to edit the main list item in bill reminder main screen
    public void setGenreTitle(ExpandableGroup genre) {
        if (genre instanceof ParentProvider) {
            providerName.setText(genre.getTitle());
            icon.setBackgroundResource(((ParentProvider) genre).getIconResId());
            billReminderDueDate.setText(genre.getDueDate());
            billDueAmount.setText(genre.getDueAmount());
            billID.setText(genre.getBillID());
        }

    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        //arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        //arrow.setAnimation(rotate);
    }
}
