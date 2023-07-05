package com.starknet.stark.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

public class Base64Utils {
    public static String base64Gzipped(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte[] original=str.getBytes();
        System.out.println("original:"+original.length);
        //byteArrayOutputStream.write(original);
        GZIPOutputStream gzipOutputStream=new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(original);
        //这里一定要先把gzipOutputStream流关闭了，否则得到的是部分数据,并且下面在解压缩的时候会出现EOFException异常
        gzipOutputStream.close();
        byteArrayOutputStream.close();
        byte[] compressByteArray = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(compressByteArray);

    }
}
