package com.example.przemek.myshoppinglist.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.przemek.myshoppinglist.R;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PREFERENCES_TEXT_FIELD = "textField";
    private SharedPreferences preferences;

    private Button mainButton;
    private Button applyButton;
    private EditText editTextToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        editTextToSave = (EditText) findViewById(R.id.fontSize);

        mainButton = (Button) findViewById(R.id.goMainActivity);
        applyButton = (Button) findViewById(R.id.applyChanges);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                editTextToSave.setTextSize(Float.parseFloat(editTextToSave.getText().toString()));
                showToast("Zapisano");
            }
        });

        restoreData();
        editTextToSave.setTextSize(Float.parseFloat(editTextToSave.getText().toString()));
    }

    private void saveData() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        String editTextData = editTextToSave.getText().toString();
        preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextData);
        preferencesEditor.commit();
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void restoreData(){
        String textFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        editTextToSave.setText(textFromPreferences);
    }


}
