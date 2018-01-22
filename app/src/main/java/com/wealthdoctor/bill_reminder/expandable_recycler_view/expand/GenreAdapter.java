package com.wealthdoctor.bill_reminder.expandable_recycler_view.expand;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.expandable_recycler_view.models.ExpandableGroup;
import com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter.ChildProvider;
import com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter.ExpandableRecyclerViewAdapter;
import com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter.ParentProvider;

import java.util.List;

public class GenreAdapter extends ExpandableRecyclerViewAdapter<GenreViewHolder, ArtistViewHolder> {

  public GenreAdapter(List<? extends ExpandableGroup> groups) {
    super(groups);
  }

  @Override
  public GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_genre, parent, false);
    return new GenreViewHolder(view);
  }

  @Override
  public ArtistViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_child, parent, false);
    return new ArtistViewHolder(view);
  }

  @Override
  public void onBindChildViewHolder(ArtistViewHolder holder, int flatPosition,
      ExpandableGroup group, int childIndex) {

    final ChildProvider childProvider = ((ParentProvider) group).getItems().get(childIndex);
    holder.setArtistName(childProvider.getName());
  }

  @Override
  public void onBindGroupViewHolder(GenreViewHolder holder, int flatPosition,
      ExpandableGroup group) {

    holder.setGenreTitle(group);
  }
}
