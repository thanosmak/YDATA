package com.example.user.ydata.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ydata.model.Waterconnection;
import com.example.user.ydata.R ;

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

        return new MyViewHolder(view);
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





        public MyViewHolder(View itemView) {
            super(itemView);

            watermeterDeya = itemView.findViewById(R.id.watermeterDeya);
            watermeterAddress = itemView.findViewById(R.id.watermeterAddress);
            watermeterPin = itemView.findViewById(R.id.watermeterPin);
        }
    }

}
