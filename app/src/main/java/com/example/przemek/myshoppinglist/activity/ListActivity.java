package com.example.przemek.myshoppinglist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.przemek.myshoppinglist.R;
import com.example.przemek.myshoppinglist.adapter.ProductAdapter;
import com.example.przemek.myshoppinglist.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private Button mainButton = null;

    private ListView listView, simpleList;

    ArrayList<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mainButton = (Button) findViewById(R.id.goMainActivity);

        simpleList = (ListView) findViewById(R.id.listView);


        productList.add(new Product("Milk", 1, 2.90, false));
        productList.add(new Product("Banana", 2, 3.20, false));
        productList.add(new Product("Water", 3, 1.00, false));

        ProductAdapter adapter = new ProductAdapter(this, R.layout.item_row_layout, productList);
        simpleList.setAdapter(adapter);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
