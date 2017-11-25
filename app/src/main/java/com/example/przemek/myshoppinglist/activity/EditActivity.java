package com.example.przemek.myshoppinglist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.przemek.myshoppinglist.R;
import com.example.przemek.myshoppinglist.database.DataBaseRepository;
import com.example.przemek.myshoppinglist.model.Product;

public class EditActivity extends AppCompatActivity {

    Button buttonSave, goListButton;
    DataBaseRepository dbRepository;
    EditText et_name, et_quant, et_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        buttonSave = (Button) findViewById(R.id.bt_save);
        goListButton = (Button) findViewById(R.id.bt_goList);
        et_name = (EditText) findViewById(R.id.et_name_edit);
        et_quant = (EditText) findViewById(R.id.et_quantity_edit);
        et_price = (EditText) findViewById(R.id.et_price_edit);

        final String productName = getIntent().getStringExtra("productName");
        int productQuant = getIntent().getIntExtra("productQuant", 0);
        double productPrice = getIntent().getDoubleExtra("productPrice", 0);

        et_name.setText(productName);
        et_quant.setText(String.valueOf(productQuant));
        et_price.setText(String.valueOf(productPrice));

        dbRepository = new DataBaseRepository(this);

        goListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = String.valueOf(et_name.getText());
                int itemQuant = Integer.parseInt(String.valueOf(et_quant.getText()));
                double itemPrice = Double.parseDouble(String.valueOf(et_price.getText()));
                dbRepository.updateItem(new Product(itemName, itemQuant, itemPrice, false), productName);

                Intent intent = new Intent(v.getContext(), ListActivity.class);
                startActivity(intent);
            }
        });

    }

}
