package com.app.tapuser;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.widget.TextView;
import com.app.tapuser.handler.HexHelper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {

    private NfcAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        String data = "Hello World!";
        adapter.enableReaderMode(this, this,
                NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null);
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
            String data = "Hello World!";
            String message = "00A4040007A0000002471001" + HexHelper.toHex(data.getBytes());
            final byte[] response = isoDep.transceive(HexHelper.hexStringToByteArray(message));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView textView = (TextView)findViewById(R.id.textView);
                    textView.append("\nCard Response: " + HexHelper.toHex(response));
                }
            });
            isoDep.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
