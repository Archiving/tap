package com.app.tap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class Database extends AppCompatActivity {

//    myDBMaker helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        helper = new myDBmaker(this);

    }

    static void uploadData(String data) {
        String[] parsedData = data.split("~");
        String firstName = parsedData[0];
        String lastName = parsedData[1];
        String email = parsedData[2];
        String phoneNum = parsedData[3];
        String gender = parsedData[4];
        String race = parsedData[5];
        String access = parsedData[6];
        String diet = parsedData[7];
        String extra = parsedData[8];

   /*     long id = helper.insertData(firstName, lastName, email,
                phoneNum, gender, race, access, diet, extra);
        if(id <= 0) {
            Toast.makeText(getInstance(), "Insertion Unsuccessful!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getInstance(), "Insertion Unsuccessful!", Toast.LENGTH_LONG).show();
        }*/
    }

    public static Database getInstance() {
        return new Database();
    }
}

