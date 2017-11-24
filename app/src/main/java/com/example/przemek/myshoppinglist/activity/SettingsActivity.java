package com.example.przemek.myshoppinglist.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.przemek.myshoppinglist.R;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PREFERENCES_TEXT_FIELD = "textField";
    private static final String PREFERENCES_NAME1 = "myPreferences1";
    private static final String PREFERENCES_TEXT_FIELD1 = "textField1";

    private SharedPreferences preferences, preferences1;

    private RelativeLayout settingsLayout;

    private Button mainButton;
    private Button applyButton;
    private Button buttonRed, buttonWhite, buttonYellow;

    private EditText fontSizeToSave;
    private int backgroundColorToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        preferences1 = getSharedPreferences(PREFERENCES_NAME1, Activity.MODE_PRIVATE);
        fontSizeToSave = (EditText) findViewById(R.id.fontSize);

        settingsLayout = (RelativeLayout) findViewById(R.id.settingsLayout);

        mainButton = (Button) findViewById(R.id.goMainActivity);
        applyButton = (Button) findViewById(R.id.applyChanges);
        buttonRed = (Button) findViewById(R.id.buttonBackgroundRed);
        buttonWhite = (Button) findViewById(R.id.buttonBackgroundWhite);
        buttonYellow = (Button) findViewById(R.id.buttonBackgroundYellow);

        restoreData();
        fontSizeToSave.setTextSize(Float.parseFloat(fontSizeToSave.getText().toString()));

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
                fontSizeToSave.setTextSize(Float.parseFloat(fontSizeToSave.getText().toString()));

                showToast("Zapisano");
            }
        });

        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backgroundColorToSave = Color.RED;
                settingsLayout.setBackgroundColor(backgroundColorToSave);
            }
        });

        buttonWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColorToSave = Color.WHITE;
                settingsLayout.setBackgroundColor(backgroundColorToSave);
            }
        });

        buttonYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColorToSave = Color.YELLOW;
                settingsLayout.setBackgroundColor(backgroundColorToSave);
            }
        });
    }

    private void saveData() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        SharedPreferences.Editor preferencesEditor1 = preferences1.edit();

        String editTextData = fontSizeToSave.getText().toString();

        preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextData);
        preferencesEditor1.putInt(PREFERENCES_TEXT_FIELD1, backgroundColorToSave);
        preferencesEditor.commit();
        preferencesEditor1.commit();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void restoreData() {
        String textFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        int bgColorFromPreferences = preferences1.getInt(PREFERENCES_TEXT_FIELD1, -1);
        fontSizeToSave.setText(textFromPreferences);
        settingsLayout.setBackgroundColor(bgColorFromPreferences);
    }


}
