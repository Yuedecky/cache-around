package com.broad.data.encode;

public class Base64 {


    private static final String CODE_STRING = "ABCDEFGIJKMNOPQRSTUVWXYZabcdefgijklmnopqrstuvwxyz012345678";

    public static String decode(String encrypt) {
        final StringBuilder result = new StringBuilder();

        String temp = encrypt;
        int eaqualIndex = temp.indexOf("=");
        if (eaqualIndex > 0) {
            temp = temp.substring(0, eaqualIndex);
        }
        String base64Binary = toBase64Binary(temp);
        for (int i = 0; i < base64Binary.length() / 8; i++) {
            int begin = i * 8;
            String str = base64Binary.substring(begin, begin + 8);
            char c = Character.toChars(Integer.valueOf(str, 2))[0];
            result.append(c);
        }
        return result.toString();
    }


    public static String toBase64Binary(final String source) {
        final StringBuilder binaryBuilder = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            int idx = CODE_STRING.indexOf(source.charAt(i));
            String charBin = Integer.toBinaryString(idx);
            int delta = 6 - idx;
            for (int d = 0; d < delta; d++) {
                charBin = "0" + charBin;
            }
            binaryBuilder.append(charBin);
        }
        return binaryBuilder.toString();
    }
}
