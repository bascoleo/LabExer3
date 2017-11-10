package com.leobasco.labexer3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2Activity extends AppCompatActivity {

    TextView tv_Display;
    FileInputStream fis;
    BufferedReader br;
    Button btn_Shared, btn_IS, btn_IC, btn_EC, btn_ES, btn_EPS, btn_Prev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_Display = (TextView)findViewById(R.id.tvDisplay);
        btn_Shared = (Button)findViewById(R.id.btn_SP);
        btn_IS = (Button)findViewById(R.id.btn_IS);
        btn_IC = (Button)findViewById(R.id.btn_IC);
        btn_EC = (Button)findViewById(R.id.btn_EC);
        btn_ES = (Button)findViewById(R.id.btn_ES);
        btn_EPS = (Button)findViewById(R.id.btn_EP);
        btn_Prev = (Button)findViewById(R.id.btn_Previous);

    }

    public void previous (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loadSharedPreference(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());;
        String data = preferences.getString("data","");
        tv_Display.setText(data);
    }

    public void loadInternalStorage (View view) throws IOException {
        String newline = "";
        String data = "";
        try{
            fis = openFileInput("storage.txt");
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Display.setText(data);
    }

    public void loadInternalCache(View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = getCacheDir();
        File file = new File(folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Display.setText(data);
    }

    public void loadExternalCache(View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = getExternalCacheDir();
        File file = new File(folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Display.setText(data);
    }

    public void loadExternalStorage (View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = getExternalFilesDir("Leo Basco");
        File file = new File(folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Display.setText(data);
    }

    public void loadExternalPublic (View view) throws FileNotFoundException {
        String newline = "";
        String data = "";
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File (folder, "storage.txt");
        fis = new FileInputStream(file);
        try{
            br = new BufferedReader(new InputStreamReader(fis));
            if ((newline = br.readLine()) != null)
                data = newline;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Display.setText(data);

    }
}