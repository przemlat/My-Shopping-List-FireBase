package com.example.przemek.myshoppinglist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.example.przemek.myshoppinglist.R;
import com.example.przemek.myshoppinglist.adapter.ProductAdapter;
import com.example.przemek.myshoppinglist.database.DataBaseRepository;
import com.example.przemek.myshoppinglist.model.Product;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private ListView sampleList;
    Button buttonAdd, buttonEdit;
    EditText et_name, et_quant, et_price;
    ProductAdapter adapter;
    private DataBaseRepository dbRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbRepository = new DataBaseRepository(getBaseContext());
        sampleList = (ListView) findViewById(R.id.list_view);
        buttonAdd = (Button) findViewById(R.id.bt_add);
        buttonEdit = (Button) findViewById(R.id.buttonEdit);
        et_name = (EditText) findViewById(R.id.et_name_edit);
        et_quant = (EditText) findViewById(R.id.et_quant);
        et_price = (EditText) findViewById(R.id.et_price_edit);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        adapter = new ProductAdapter(this, R.layout.item_row_layout, dbRepository.getAllItems());

        sampleList.setAdapter(adapter);
        sampleList.setLongClickable(true);
        sampleList.setOnItemLongClickListener(itemLongClickListener);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName = String.valueOf(et_name.getText());
                int itemQuant = Integer.parseInt(String.valueOf(et_quant.getText()));
                double itemPrice = Double.parseDouble(String.valueOf(et_price.getText()));
                boolean itemIsChecked = false;

                dbRepository.addItem(new Product(itemName, itemQuant, itemPrice, itemIsChecked));

                adapter.notifyDataSetChanged();
                et_name.setText("");
                et_price.setText("");
                et_quant.setText("");

                updateData();

            }
        });

    }

    private void updateData(){
        ArrayList<Product> items = dbRepository.getAllItems();
        adapter.clear();
        adapter.addAll(items);
        adapter.notifyDataSetChanged();
    }

    private final AdapterView.OnItemLongClickListener itemLongClickListener
            = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Product item = adapter.getItem(position);
            dbRepository.removeItem(item);

            ArrayList<Product> products = dbRepository.getAllItems();
            adapter.clear();
            adapter.addAll(products);
            adapter.notifyDataSetChanged();

            return true;
        }
    };

}
