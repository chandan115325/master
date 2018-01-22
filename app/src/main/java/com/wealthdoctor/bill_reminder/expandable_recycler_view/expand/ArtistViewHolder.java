package com.wealthdoctor.bill_reminder.expandable_recycler_view.expand;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.expandable_recycler_view.viewholders.ChildViewHolder;


public class ArtistViewHolder extends ChildViewHolder {

  private TextView childTextView;
  private ImageView childDeleteImage;
  private ImageView childEditImage;

  public ArtistViewHolder(View itemView) {
    super(itemView);
    childTextView = (TextView) itemView.findViewById(R.id.list_item_child);
    childDeleteImage = (ImageView)itemView.findViewById(R.id.child_edit);
    childDeleteImage = (ImageView)itemView.findViewById(R.id.child_delete);
  }

  public void setArtistName(String name) {
    childTextView.setText(name);
  }

}
