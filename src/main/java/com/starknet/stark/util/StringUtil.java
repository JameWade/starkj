package com.starknet.stark.util;

public class StringUtil {
    public static String padStart(String input, int minLength, char padChar) {
        if (input.length() >= minLength) {
            return input;
        } else {
            StringBuilder sb = new StringBuilder(minLength);
            int paddingLength = minLength - input.length();
            for (int i = 0; i < paddingLength; i++) {
                sb.append(padChar);
            }
            sb.append(input);
            return sb.toString();
        }
    }

}
