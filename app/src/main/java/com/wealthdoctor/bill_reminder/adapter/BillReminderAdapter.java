package com.wealthdoctor.bill_reminder.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.wealthdoctor.R;
import com.wealthdoctor.bill_reminder.activity.BillReminderDetailListActivity;
import com.wealthdoctor.bill_reminder.model.BillReminderData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHANDAN on 1/10/2018.
 */

public class BillReminderAdapter extends RecyclerView.Adapter<BillReminderAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    private List<BillReminderData> dataListFiltered;
    private List<BillReminderData> billReminderList ;

    public BillReminderAdapter(Context mContext, List<BillReminderData> billReminderList){
        this.mContext = mContext;
        this.billReminderList = billReminderList;
        this.dataListFiltered = billReminderList;
    }

    @Override
    public BillReminderAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BillReminderAdapter.MyViewHolder viewHolder, int i){


        viewHolder.title.setText(billReminderList.get(i).getData());
            //viewHolder.title.setText("Credit Card");
    }

    @Override
    public int getItemCount(){
        return billReminderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.item);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        BillReminderData clickedBillReminderDataItem = billReminderList.get(pos);
                        Intent intent = new Intent(mContext, BillReminderDetailListActivity.class);
                        intent.putExtra("dataItem", clickedBillReminderDataItem );
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        //Toast.makeText(v.getContext(), "You clicked " + clickedBillReminderDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                Log.d("BillReminder", charString);
                if (charString.isEmpty()) {
                    billReminderList = dataListFiltered;

                } else {
                    List<BillReminderData> filteredList = new ArrayList<>();
                    for (BillReminderData row : dataListFiltered) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getData().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    billReminderList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = billReminderList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                billReminderList = (ArrayList<BillReminderData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}