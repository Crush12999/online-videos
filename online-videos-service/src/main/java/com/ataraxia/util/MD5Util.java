package com.ataraxia.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author Ataraxia
 * @create 2022/4/21 00:06
 * @description MD5加密
 * 单向加密算法，加密后是不可逆的
 * 特点：加密速度快，不需要秘钥，但是安全性不高，需要搭配随机盐值使用
 */
public class MD5Util {

    /**
     * 加密方法
     * @param content 待加密文本
     * @param salt 随机盐
     * @param charset 字符编码
     * @return 加密后的文本
     */
    public static String sign(String content, String salt, String charset) {
        content = content + salt;
        return DigestUtils.md5Hex(getContentBytes(content, charset));
    }

    /**
     * 验证方法
     * @param content 待验证文本
     * @param sign 已加密过的文本
     * @param salt 随机盐
     * @param charset 字符编码
     * @return 比对结果
     */
    public static boolean verify(String content, String sign, String salt, String charset) {
        content = content + salt;
        String mySign = DigestUtils.md5Hex(getContentBytes(content, charset));
        return mySign.equals(sign);
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (!"".equals(charset)) {
            try {
                return content.getBytes(charset);
            } catch (UnsupportedEncodingException var3) {
                throw new RuntimeException("MD5签名过程中出现错误，指定的编码集错误");
            }
        } else {
            return content.getBytes();
        }
    }
}
