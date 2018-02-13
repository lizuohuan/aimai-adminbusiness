package com.magic.aimai.business.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;



/**
 *   微信支付工具
 * @author QimouXie
 *
 */
public class WXUtil {
	
	
	/**
	 *  获取签名
	 * @param params
	 * @param key
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getSign(Map<String,Object> params,String key) throws UnsupportedEncodingException{
		
		ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:params.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        result = MD5.MD5Encode(result).toUpperCase();
        return URLEncoder.encode(result,"UTF-8");
	}
	
	/**
	 *  微信通知后，返回处理结果
	 * @param returnCode
	 * @param returnMsg
	 * @return
	 */
	public static String noticeStr(String returnCode,String returnMsg){
		return "<xml><return_code><![CDATA["+returnCode+"]]></return_code> <return_msg><![CDATA["+returnMsg+"]]></return_msg></xml>";
	}

}
