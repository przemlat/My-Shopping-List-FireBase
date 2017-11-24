package com.example.przemek.myshoppinglist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.przemek.myshoppinglist.R;
import com.example.przemek.myshoppinglist.model.Product;

import java.util.ArrayList;

/**
 * Created by Przemek on 24.11.2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    ArrayList<Product> productsList = new ArrayList<>();

    public ProductAdapter(Context context, int textViewResourceId, ArrayList<Product> objects) {
        super(context, textViewResourceId, objects);
        productsList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_row_layout, null);
        TextView textView = (TextView) v.findViewById(R.id.tv_name);
        TextView textView2 = (TextView) v.findViewById(R.id.tv_quant);
        TextView textView3 = (TextView) v.findViewById(R.id.tv_price);

        textView.setText(productsList.get(position).getName());
        textView2.setText(String.valueOf(productsList.get(position).getQuantity()));
        textView3.setText(String.valueOf(productsList.get(position).getPrice()));

        return v;
    }
}