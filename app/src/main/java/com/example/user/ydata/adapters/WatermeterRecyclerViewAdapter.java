package com.example.user.ydata.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.ydata.Activities.WatermeterDetailsActivity;
import com.example.user.ydata.model.Waterconnection;
import com.example.user.ydata.R ;

import java.io.Serializable;
import java.util.List;

/**
 * Created by makar on 17/5/2018.
 */

public class WatermeterRecyclerViewAdapter extends RecyclerView.Adapter<WatermeterRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Waterconnection> mData;

    public WatermeterRecyclerViewAdapter(Context mContext, List<Waterconnection> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.watermeter_connection_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_watermeter_item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Waterconnection waterconnection = mData.get(viewHolder.getAdapterPosition());
                List<Waterconnection.Indications> indicationsList = waterconnection.getIndicationsList();
                Bundle bundle = new Bundle();


                bundle.putString("deya", waterconnection.getDeya());
                bundle.putString("pin", waterconnection.getPin());
                bundle.putString("address", waterconnection.getAddress());
                bundle.putString("owner", waterconnection.getOwner());
                bundle.putString("status", waterconnection.getStatus());
                bundle.putString("watermeter_number", waterconnection.getWatermeter().getWatermeterNumber());
                bundle.putString("consumer_code", waterconnection.getWatermeter().getConsumerCode());
                bundle.putString("name_on_bill", waterconnection.getWatermeter().getFullNameOnBill());
                bundle.putSerializable("indications", (Serializable) indicationsList);

                Intent i = new Intent(mContext, WatermeterDetailsActivity.class);
                i.putExtras(bundle);

                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.watermeterDeya.setText(mData.get(position).getDeya());
        holder.watermeterAddress.setText(mData.get(position).getAddress());
        holder.watermeterPin.setText(mData.get(position).getPin());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView watermeterDeya;
        TextView watermeterAddress;
        TextView watermeterPin;
        LinearLayout view_watermeter_item_container;


        public MyViewHolder(View itemView) {
            super(itemView);

            view_watermeter_item_container = itemView.findViewById(R.id.watermeterItemContainer);
            watermeterDeya = itemView.findViewById(R.id.watermeterDeya);
            watermeterAddress = itemView.findViewById(R.id.watermeterAddress);
            watermeterPin = itemView.findViewById(R.id.watermeterPin);
        }
    }

}
