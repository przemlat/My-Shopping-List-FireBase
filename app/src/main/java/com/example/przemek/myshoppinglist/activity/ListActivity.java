package com.example.przemek.myshoppinglist.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.przemek.myshoppinglist.R;
import com.example.przemek.myshoppinglist.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private Button mainButton = null;

    private ListView listView;
//    ListAdapterProduct listAdapterProduct;
    List<Product> productList = new ArrayList<>();

    Product mleko = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mainButton = (Button) findViewById(R.id.goMainActivity);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

//        mleko.setName("Mleko");
//        mleko.setPrice(4.0);
//        mleko.setQuantity(2);
//        productList.add(mleko);
//
//        listView = (ListView) findViewById(R.id.listView);
//
//        listAdapterProduct = new ListAdapterProduct(this, productList);
//        listView.setAdapter(listAdapterProduct);

    }


}
