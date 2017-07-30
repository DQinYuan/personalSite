package org.du.personalSite.domain.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 燃烧杯 on 2017/7/27.
 */
public class MD5Utils {
    public static String encode(String message){
        // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // 2 将消息变成byte数组
        byte[] input = new byte[0];
        try {
            input = message.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        // 3 计算后获得字节数组,这就是那128位了
        byte[] buff = md.digest(input);

        // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
        String md5str = bytesToHex(buff);

        return md5str;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(encode("hhhas324432"));
    }
}
