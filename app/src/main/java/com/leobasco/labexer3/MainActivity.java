package com.leobasco.labexer3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etData, et_Filename;
    Button btn_Shared, btn_IS, btn_IC, btn_EC, btn_ES, btn_EPS, btn_Next;
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etData = (EditText) findViewById(R.id.etData);
        et_Filename = (EditText) findViewById(R.id.etFilename);
        btn_Shared = (Button) findViewById(R.id.btn_Shared);
        btn_IS = (Button) findViewById(R.id.btn_IS);
        btn_IC = (Button) findViewById(R.id.btn_IC);
        btn_EC = (Button) findViewById(R.id.btn_EC);
        btn_ES = (Button) findViewById(R.id.btn_ES);
        btn_EPS = (Button) findViewById(R.id.btn_EPS);
        btn_Next = (Button) findViewById(R.id.btn_Next);
    }

    public void next (View view) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }

    private void writeData(File file, String message){
        fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void sharedPref (View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data", etData.getText().toString());
        editor.commit();
        Toast.makeText(this, "Preferences Saved!", Toast.LENGTH_SHORT).show();
    }

    public void internalStore (View view) {
        String message = etData.getText().toString();
        String filename = et_Filename.getText().toString();
        try {
            fos = openFileOutput(filename + ".txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Storage saved!", Toast.LENGTH_SHORT).show();
    }

    public void intCache(View view){
        String filename = et_Filename.getText().toString();
        File folder = getCacheDir();
        File file = new File(folder, filename + ".txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this,"Successfully written to internal cache!", Toast.LENGTH_LONG).show();
    }

    public void extCache (View view) {
        String filename = et_Filename.getText().toString();
        File folder = getExternalCacheDir();
        File file = new File(folder, filename + ".txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to external cache!", Toast.LENGTH_LONG).show();
    }

    public void extStorage (View view) {
        String filename = et_Filename.getText().toString();
        File folder = getExternalFilesDir("Leo Basco");
        File file = new File(folder, filename + ".txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to external storage!", Toast.LENGTH_LONG).show();
    }

    public void extPublic (View view) {
        String filename = et_Filename.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File (folder, filename + ".txt");
        String message = etData.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to external public storage!", Toast.LENGTH_LONG).show();
    }
}