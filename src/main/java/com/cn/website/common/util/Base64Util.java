package com.cn.website.common.util;

import java.io.IOException;

/**
 * base64加解密
 * @author Administrator
 *
 */
public class Base64Util {

    /**  
     * 编码  
     * @param bstr  
     * @return String  
     */    
    @SuppressWarnings("restriction")
	public static String encode(byte[] bstr){    
    	return new sun.misc.BASE64Encoder().encode(bstr);    
    }    
    
    /**  
     * 解码  
     * @param str  
     * @return string  
     */    
    @SuppressWarnings("restriction")
	public static byte[] decode(String str){    
    byte[] bt = null;    
    try {    
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
        bt = decoder.decodeBuffer( str );    
    } catch (IOException e) {    
        e.printStackTrace();    
    }    
    
        return bt;    
    }    
}
