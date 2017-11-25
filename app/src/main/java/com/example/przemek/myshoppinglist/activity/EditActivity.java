package com.example.przemek.myshoppinglist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.przemek.myshoppinglist.R;

public class EditActivity extends AppCompatActivity {

    Button buttonSave, goListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        buttonSave = (Button) findViewById(R.id.bt_save);
        goListButton = (Button) findViewById(R.id.bt_goList);

        goListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                startActivity(intent);
            }
        });

    }

}
