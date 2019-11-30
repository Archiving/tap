package com.app.tap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.tap.handler.HexHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hex 0xF0: " + HexHelper.toHex(new byte[] {0x00, 0x01}));
    }
}
