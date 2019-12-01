package com.app.tapuser;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    public void writeFileInternalStorage(View view) {
        EditText editText5 = (EditText) (findViewById(R.id.editText5));
        EditText editText6 = (EditText) (findViewById(R.id.editText6));
        EditText editText7 = (EditText) (findViewById(R.id.editText7));
        EditText editText8 = (EditText) (findViewById(R.id.editText8));
        EditText editText9 = (EditText) (findViewById(R.id.editText9));
        EditText editText10 = (EditText) (findViewById(R.id.editText10));
        EditText editText11 = (EditText) (findViewById(R.id.editText11));
        EditText editText12 = (EditText) (findViewById(R.id.editText12));
        EditText editText13 = (EditText) (findViewById(R.id.editText13));
        EditText editText14 = (EditText) (findViewById(R.id.editText14));
        String fileContent = (editText5.getText().toString() + "~" + editText6.getText().toString() + "~" + editText7.getText().toString() + "~" +
                editText8.getText().toString() + "~" + editText9.getText().toString() + "~" + editText10.getText().toString() + "~" +
                editText11.getText().toString() + "~" + editText12.getText().toString() + "~" + editText13.getText().toString() + "~" +
                editText14.getText().toString());  //Replace with the string

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("user.txt", MODE_PRIVATE);
            fos.write(fileContent.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/user.txt", Toast.LENGTH_LONG).show();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}