package com.example.przemek.myshoppinglist.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.przemek.myshoppinglist.R;

public class EditActivity extends AppCompatActivity {

    Button buttonSave, goListButton;
    EditText et_name, et_price, et_quant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        buttonSave = (Button) findViewById(R.id.bt_save);
        goListButton = (Button) findViewById(R.id.bt_goList);
        et_name = (EditText) findViewById(R.id.et_name);
        et_price = (EditText) findViewById(R.id.et_price);
        et_quant = (EditText) findViewById(R.id.et_quant);

        goListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String name = intent.getStringExtra("name");
            et_name.setText(name);
            String quant = intent.getStringExtra("quant");
            et_quant.setText(quant);
            String price = intent.getStringExtra("price");
            et_quant.setText(price);

        }
    };
}
