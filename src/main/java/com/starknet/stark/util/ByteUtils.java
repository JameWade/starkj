package com.starknet.stark.util;

import java.math.BigInteger;

public class ByteUtils {

    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    public static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * hex转byte数组
     * @param hex
     * @return
     */
    public static byte[] hexToByte(String hex){
        int m = 0, n = 0;
        int byteLen = hex.length() / 2; // 每两个字符描述一个字节
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = Byte.valueOf((byte)intVal);
        }
        return ret;
    }

    public static  byte[] positiveNumberToBytesLeftPadded(BigInteger bigInteger, int i) {
        int length;
        int i2;
        boolean z = true;
        if (!(bigInteger.signum() >= 0)) {
            throw new IllegalArgumentException(("Can't convert negative number to Bytes, value: " + bigInteger).toString());
        }
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            length = byteArray.length - 1;
            i2 = 1;
        } else {
            length = byteArray.length;
            i2 = 0;
        }
        if (length > i) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException(("Bitsize " + i + " too small for the value " + bigInteger).toString());
        }
        int i3 = i - length;
        byte[] bArr = new byte[i];
        System.arraycopy(byteArray, i2, bArr, i3, length);
        return  bArr;
    }
}
