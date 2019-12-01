package com.app.tap.handler;

public class HexHelper {
    private static final String HEX_CHARS = "0123456789ABCDEF";
    private static final char[] HEX_CHARS_ARRAY = "0123456789ABCDEF".toCharArray();

    public static byte[] hexStringToByteArray(String data) {
        byte[] result = new byte[data.length() / 2];
        for(int i = 0; i < data.length(); i += 2) {
            int firstIndex = HEX_CHARS.indexOf(data.charAt(i)); // first hexit
            int nextIndex = HEX_CHARS.indexOf(data.charAt(i+1)); // second hexit

            Integer octet = (firstIndex << 4) | nextIndex;

            result[i / 2] = octet.byteValue();
        }

        return result;
    }

    public static String toHex(byte[] byteArray) {
        StringBuffer result = new StringBuffer();
        for(byte b : byteArray) {
            int octet = ((Byte)b).intValue();
            int firstIndex = (octet & 0xF0) >>> 4;
            int nextIndex = octet & 0x0F;
            result.append(HEX_CHARS_ARRAY[firstIndex]);
            result.append(HEX_CHARS_ARRAY[nextIndex]);
        }

        return result.toString();
    }

}
