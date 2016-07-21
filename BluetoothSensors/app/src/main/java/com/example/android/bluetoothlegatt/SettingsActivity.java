package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends Activity {
    EditText edName,edService,edChar;
    Button bSave;
    public static final String MyPREFERENCES = "blePrefs" ;
    public static final String serviceName = "nameKey";
    public static final String serviceUUID = "serviceKey";
    public static final String characterUUID = "charKey";

    SharedPreferences.Editor editor;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        edName = (EditText) findViewById(R.id.serviceName);
        edService = (EditText) findViewById(R.id.serviceUUID);
        edChar = (EditText) findViewById(R.id.charUUID);

        bSave = (Button) findViewById(R.id.saveSettings);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();



        String charUUID = sharedpreferences.getString(SettingsActivity.characterUUID,"");
        String sUUID = sharedpreferences.getString(SettingsActivity.serviceUUID,"");
        String sName = sharedpreferences.getString(SettingsActivity.serviceName,"");
        if(charUUID.equals("") || sUUID.equals("") || sName.equals("")) {

            editor.putString(serviceName, "HM_10");
            editor.putString(serviceUUID, "ffe0");
            editor.putString(characterUUID, "ffe1");
            editor.commit();
        }


        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm  = edName.getText().toString();
                String sv  = edService.getText().toString();
                String ch  = edChar.getText().toString();


                editor.putString(serviceName, nm);
                editor.putString(serviceUUID, sv);
                editor.putString(characterUUID, ch);
                editor.commit();
                Toast.makeText(SettingsActivity.this,"Saved Successfully",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        edName.setText(sharedpreferences.getString(serviceName,"HM_10"));
        edService.setText(sharedpreferences.getString(serviceUUID,"ffe0"));
        edChar.setText(sharedpreferences.getString(characterUUID,"ffe1"));
    }
}
