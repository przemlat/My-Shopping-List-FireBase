package com.example.przemek.myshoppinglist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.przemek.myshoppinglist.R;
import com.example.przemek.myshoppinglist.adapter.ProductAdapter;
import com.example.przemek.myshoppinglist.model.Product;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private Button goMainButton;
    private ListView simpleList;
    ArrayList<Product> productList = new ArrayList<>();
    Button bAdd;
    EditText et_name, et_quant, et_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        goMainButton = (Button) findViewById(R.id.mainButton);

        simpleList = (ListView) findViewById(R.id.list_view);
        bAdd = (Button) findViewById(R.id.bt_dodaj);
        et_name = (EditText) findViewById(R.id.et_nazwa);
        et_quant = (EditText) findViewById(R.id.et_ilosc);
        et_price = (EditText) findViewById(R.id.et_cena);


        productList.add(new Product("Milk", 1, 2.90));
        productList.add(new Product("Banana", 2, 3.20));
        productList.add(new Product("Water", 3, 1.00));

        final ProductAdapter adapter = new ProductAdapter(this, R.layout.item_row_layout, productList);
        simpleList.setAdapter(adapter);

        goMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nazwa = String.valueOf(et_name.getText());
                int ilosc = Integer.parseInt(String.valueOf(et_quant.getText()));
                double cena = Double.parseDouble(String.valueOf(et_price.getText()));

                productList.add(new Product(nazwa, ilosc, cena));

                adapter.notifyDataSetChanged();
                et_name.setText("");
                et_price.setText("");
                et_quant.setText("");

            }
        });
    }

}
