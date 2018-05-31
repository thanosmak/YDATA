package com.example.user.ydata.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ydata.model.Waterconnection;
import com.example.user.ydata.R;

import java.util.List;

public class IndicationsRecuclerViewAdapter extends  RecyclerView.Adapter<IndicationsRecuclerViewAdapter.indicationViewHolder>{

    private Context mContext;
    private List<Waterconnection.Indications> mData;


    public IndicationsRecuclerViewAdapter(Context mContext, List<Waterconnection.Indications> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public indicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.watermeter_indication_item, parent,false);

        return new indicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull indicationViewHolder holder, int position) {

        holder.tv_newIndicationDate.setText(mData.get(position).getNewIndicationDate());
        holder.tv_usage.setText(mData.get(position).getUsage());
        holder.tv_newIndication.setText("Κυβικά: " + mData.get(position).getNewIndication());
        holder.tv_cubicCharged.setText("Χρέωση Κυβικών: " + mData.get(position).getCubicCharged());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class indicationViewHolder extends RecyclerView.ViewHolder {

        TextView tv_newIndicationDate;
        TextView tv_usage;
        TextView tv_newIndication;
        TextView tv_cubicCharged;


        public indicationViewHolder(View itemView) {
            super(itemView);

            tv_newIndicationDate = itemView.findViewById(R.id.newIndicationDate);
            tv_usage = itemView.findViewById(R.id.usage);
            tv_newIndication = itemView.findViewById(R.id.newIndication);
            tv_cubicCharged = itemView.findViewById(R.id.cubicCharged);
        }
    }
}
