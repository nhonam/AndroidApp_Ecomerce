package com.example.ecomerceshoppe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ecomerceshoppe.R;
import com.example.ecomerceshoppe.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {
    Context myContext;
    int myLayout;
    ArrayList<Order> data;
    ArrayList<Order> data_tmp;


    public OrderAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Order> listOrder) {
        super(context, resource, listOrder);
        this.myContext = context;
        this.myLayout = resource;
        this.data = listOrder;
        data_tmp = data;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        OrderAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(myContext).inflate(myLayout, null);
            viewHolder = new OrderAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OrderAdapter.ViewHolder) convertView.getTag();
        }
        Order order = data.get(position);
        viewHolder.tvIDOrder.setText(order.getId());
        viewHolder.tvCustomer.setText(order.getCustomer());
        viewHolder.tvtotalOrder.setText(String.valueOf(order.getTotal_Price()));
        viewHolder.tvadrressOrder.setText(order.getAdress());
        viewHolder.tvdayOrder.setText(String.valueOf(order.getCreatAtOrder()));
        viewHolder.tvstatusOrder.setText(order.getStatus());

        return convertView;

    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searchStr = charSequence.toString();
                if (searchStr.isEmpty()) {
                    data_tmp = data;
                } else {
                    ArrayList<Order> list = new ArrayList<>();
                    for (Order order : data
                    ) {

                        if (order.getCustomer().toLowerCase().contains(searchStr.toLowerCase()) ||order.getId().toLowerCase().contains(searchStr.toLowerCase()) || order.getStatus().toLowerCase().contains(searchStr.toLowerCase())) {
                            list.add(order);
                        }
                    }
                    data_tmp = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = data_tmp;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data = (ArrayList<Order>) filterResults.values;
                notifyDataSetChanged();
            }
        };


    }

    private class ViewHolder {

        TextView tvIDOrder, tvCustomer, tvtotalOrder, tvadrressOrder, tvdayOrder, tvstatusOrder;
        public ViewHolder(View view) {
            tvCustomer = view.findViewById(R.id.idCustomer);
            tvIDOrder = view.findViewById(R.id.idOrder);
            tvtotalOrder = view.findViewById(R.id.totalOrder);
            tvadrressOrder = view.findViewById(R.id.adrressOrder);
            tvdayOrder = view.findViewById(R.id.dayOrder);
            tvstatusOrder = view.findViewById(R.id.statusOrder);

        }
    }

}
