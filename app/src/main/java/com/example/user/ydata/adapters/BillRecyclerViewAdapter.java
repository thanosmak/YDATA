package com.example.user.ydata.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ydata.model.Bill;
import com.example.user.ydata.R;

import java.util.List;

public class BillRecyclerViewAdapter extends RecyclerView.Adapter<BillRecyclerViewAdapter.billViewHolder> {

    private Context mContext;
    private List<Bill> mData;

    public BillRecyclerViewAdapter(Context mContext, List<Bill> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public billViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.bill_item, parent, false);

        return new billViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull billViewHolder holder, int position) {

        holder.tv_barcode.setText(mData.get(position).getBarcode());
        holder.tv_issueDate.setText(mData.get(position).getIssuedDate());
        holder.tv_paymentDueDate.setText(mData.get(position).getPaymentDueDate());
        holder.tv_remainingAmount.setText(mData.get(position).getRemainingAmount());
        holder.tv_chargedConsumption.setText(mData.get(position).getChargedConsumption());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class billViewHolder extends RecyclerView.ViewHolder {

        TextView tv_barcode;
        TextView tv_issueDate;
        TextView tv_paymentDueDate;
        TextView tv_remainingAmount;
        TextView tv_chargedConsumption;



        public billViewHolder(View itemView) {
            super(itemView);

            tv_barcode = itemView.findViewById(R.id.barcode);
            tv_issueDate = itemView.findViewById(R.id.issuedDate);
            tv_paymentDueDate = itemView.findViewById(R.id.paymentDueDate);
            tv_remainingAmount = itemView.findViewById(R.id.remainingAmount);
            tv_chargedConsumption = itemView.findViewById(R.id.chargedConsumption);
        }
    }
}
