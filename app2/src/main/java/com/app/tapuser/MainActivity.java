package com.app.tapuser;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;

import android.widget.Button;

import android.widget.EditText;

import android.view.View;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.app.tapuser.handler.HexHelper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {

    private NfcAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = NfcAdapter.getDefaultAdapter(this);

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                System.out.println("Button Clicked");

                Intent activity2Intent = new
                        Intent(getApplicationContext(), Activity2.class);
                startActivity(activity2Intent);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.enableReaderMode(this, this,
                NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null);
    }
    public String load(String fileName) {
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        try {
            fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;

            while((text = br.readLine()) != null) {
                sb.append(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.disableReaderMode(this);
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        IsoDep isoDep = IsoDep.get(tag);
        try {
            isoDep.connect();
            String data = load("user.txt");
            String message = "00A4040007A0000002471001" + HexHelper.toHex(data.getBytes());
            final byte[] response = isoDep.transceive(HexHelper.hexStringToByteArray(message));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "\nCard Response: " + HexHelper.toHex(response), Toast.LENGTH_LONG).show();
                }
            });
            isoDep.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
