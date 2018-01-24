package com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter;


import com.wealthdoctor.bill_reminder.expandable_recycler_view.models.ExpandableGroup;

import java.util.List;

public class ParentProvider extends ExpandableGroup<ChildProvider> {

    private int iconResId;

    public ParentProvider(String title, String dueDate, String billInformation, String billStatus, String billAmount,
                          List<ChildProvider> items, int iconResId) {
        super(title, items);
        this.iconResId = iconResId;
    }

    public int getIconResId() {
        return iconResId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentProvider)) return false;

        ParentProvider parentProvider = (ParentProvider) o;

        return getIconResId() == parentProvider.getIconResId();

    }

    @Override
    public int hashCode() {
        return getIconResId();
    }
}

