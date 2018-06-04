package com.example.user.ydata.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.ydata.model.Bill;
import com.example.user.ydata.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BillRecyclerViewAdapter extends RecyclerView.Adapter<BillRecyclerViewAdapter.billViewHolder> {

    private Context mContext;
    private List<Bill> mData;
    Dialog billDialog;

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
        final billViewHolder vHolder = new billViewHolder(view);

        // Dialog init
        billDialog = new Dialog(mContext);
        billDialog.setContentView(R.layout.dialog_bill_details);
        billDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_bill_name_on_bill = (TextView) billDialog.findViewById(R.id.bill_name_on_bill);
                TextView dialog_bill_address = (TextView) billDialog.findViewById(R.id.bill_address);
                TextView dialog_bill_pay_date = (TextView) billDialog.findViewById(R.id.bill_pay_date);
                TextView dialog_bill_usage = (TextView) billDialog.findViewById(R.id.bill_usage);
                TextView dialog_bill_pay_code = (TextView) billDialog.findViewById(R.id.bill_pay_code);
                TextView dialog_bill_customer_code = (TextView) billDialog.findViewById(R.id.bill_customer_code);
                TextView dialog_bill_watermeter_code = (TextView) billDialog.findViewById(R.id.bill_watermeter_code);
                TextView dialog_bill_pin = (TextView) billDialog.findViewById(R.id.bill_pin);
                Button dialog_close_button = (Button) billDialog.findViewById(R.id.bill_close_button);

                dialog_bill_name_on_bill.setText(mData.get(vHolder.getAdapterPosition()).getLiableFullName());
                dialog_bill_address.setText(mData.get(vHolder.getAdapterPosition()).getBillingAddress());
                dialog_bill_pay_date.setText(mData.get(vHolder.getAdapterPosition()).getPaymentDueDate());
                dialog_bill_usage.setText(mData.get(vHolder.getAdapterPosition()).getUsagePeriod().getFrom() + " - " + mData.get(vHolder.getAdapterPosition()).getUsagePeriod().getTo());
                dialog_bill_pay_code.setText(mData.get(vHolder.getAdapterPosition()).getBarcode());
                dialog_bill_customer_code.setText(mData.get(vHolder.getAdapterPosition()).getKodYdr());
                dialog_bill_watermeter_code.setText(mData.get(vHolder.getAdapterPosition()).getWatermeterNumber());
                dialog_bill_pin.setText(mData.get(vHolder.getAdapterPosition()).getWatermeterPin());

                dialog_close_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        billDialog.hide();
                        billDialog.dismiss();
                    }
                });

                billDialog.show();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull billViewHolder holder, int position) {
        String issueDate = formatDate(mData.get(position).getIssuedDate());
        String paymentDueDate = formatDate(mData.get(position).getPaymentDueDate());

        holder.tv_barcode.setText(mData.get(position).getBarcode());
        holder.tv_issueDate.setText("Ημ. Έκδοσης: " + issueDate);
        holder.tv_paymentDueDate.setText("Ημ. Λήξης Πληρωμής: " + paymentDueDate);
        holder.tv_remainingAmount.setText("Πληρ. Ποσό: " + mData.get(position).getRemainingAmount());
        holder.tv_chargedConsumption.setText("Χρέωση: " + mData.get(position).getChargedConsumption());

    }

    // Converts the date that response returns("2016-06-25T00:00:00")
    private String  formatDate(String time) {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return str;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class billViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout item_bill;
        TextView tv_barcode;
        TextView tv_issueDate;
        TextView tv_paymentDueDate;
        TextView tv_remainingAmount;
        TextView tv_chargedConsumption;



        public billViewHolder(View itemView) {
            super(itemView);

            item_bill = (LinearLayout) itemView.findViewById(R.id.bill_item);
            tv_barcode = itemView.findViewById(R.id.barcode);
            tv_issueDate = itemView.findViewById(R.id.issuedDate);
            tv_paymentDueDate = itemView.findViewById(R.id.paymentDueDate);
            tv_remainingAmount = itemView.findViewById(R.id.remainingAmount);
            tv_chargedConsumption = itemView.findViewById(R.id.chargedConsumption);
        }
    }
}
