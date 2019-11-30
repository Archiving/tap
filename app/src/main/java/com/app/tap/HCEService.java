package com.app.tap;

import android.app.Service;
import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.tap.handler.HexHelper;

public class HCEService extends HostApduService {

    public static final String TAG = "Host Card Emulator";
    public static final String STATUS_SUCCESS = "9000";
    public static final String STATUS_FAILED = "6F00";
    public static final String CLA_NOT_SUPPORTED = "6E00";
    public static final String INS_NOT_SUPPORTED = "6D00";
    public static final String AID = "A0000002471001";
    public static final String SELECT_INS = "A4";
    public static final String DEFAULT_CLA = "00";
    public static final int MIN_APDU_LENGTH = 12;

    private void printByteArray(byte[] data) {
        for(int i = 0; i < data.length; i++) {
            System.out.println("data[" + i + "] = " + String.format("%02x", data[i]));
        }
    }

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        if(commandApdu == null) {
            return HexHelper.hexStringToByteArray(STATUS_FAILED);
        }

        printByteArray(commandApdu);

        String hexApdu = HexHelper.toHex(commandApdu);
        System.out.println(hexApdu);
        if(hexApdu.length() < MIN_APDU_LENGTH) {
            return HexHelper.hexStringToByteArray(STATUS_FAILED);
        }

        if(!hexApdu.substring(0,2).equals(DEFAULT_CLA)) {
            return HexHelper.hexStringToByteArray(CLA_NOT_SUPPORTED);
        }

        if(!hexApdu.substring(2,4).equals(SELECT_INS)) {
            return HexHelper.hexStringToByteArray(INS_NOT_SUPPORTED);
        }

        if(hexApdu.substring(10, 24).equals(AID)) {
            return HexHelper.hexStringToByteArray(STATUS_SUCCESS);
        }
        return HexHelper.hexStringToByteArray(STATUS_FAILED);
    }

    @Override
    public void onDeactivated(int reason) {
        Log.d(TAG, "Deactivated: " + reason);
    }
}
