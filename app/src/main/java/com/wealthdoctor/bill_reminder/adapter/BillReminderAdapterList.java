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
import com.wealthdoctor.bill_reminder.activity.BillReminderDetailActivity;
import com.wealthdoctor.bill_reminder.model.BillReminderDetailData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHANDAN on 1/10/2018.
 */

public class BillReminderAdapterList extends RecyclerView.Adapter<BillReminderAdapterList.MyViewHolder>
            implements Filterable{

    private Context mContext;

    private List<BillReminderDetailData> dataListFiltered;
    private List<BillReminderDetailData> billReminderDataList ;

    public BillReminderAdapterList(Context mContext, List<BillReminderDetailData> billReminderDataList){
        this.mContext = mContext;
        this.billReminderDataList = billReminderDataList;
        this.dataListFiltered = billReminderDataList;
    }

    @Override
    public BillReminderAdapterList.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BillReminderAdapterList.MyViewHolder viewHolder, int i){


        viewHolder.title.setText(billReminderDataList.get(i).getBillReminderDetailData());
            //viewHolder.title.setText("Credit Card");
    }

    @Override
    public int getItemCount(){
        return billReminderDataList.size();
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
                        BillReminderDetailData clickedBillReminderDataItem = billReminderDataList.get(pos);
                        Intent intent = new Intent(mContext, BillReminderDetailActivity.class);
                        intent.putExtra("Provider List", clickedBillReminderDataItem );
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
                    billReminderDataList = dataListFiltered;

                } else {
                    List<BillReminderDetailData> filteredList = new ArrayList<>();
                    for (BillReminderDetailData row : dataListFiltered) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getBillReminderDetailData().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }

                    }

                    billReminderDataList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = billReminderDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                billReminderDataList = (ArrayList<BillReminderDetailData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}