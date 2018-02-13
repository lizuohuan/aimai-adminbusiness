package com.magic.aimai.business.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Eric Xie on 2018/1/19 0019.
 */
public class IpConfig {


    private static final String IP_PATH = "ip.properties";

    private static Logger logger = Logger.getLogger(IpConfig.class);

    private static Map<String,String> result = new HashMap<String,String>();

    static {
        init();
    }

    private IpConfig(){}

    private static void init() {
        try {
            Properties properties = new Properties();
            InputStream in = IpConfig.class.getClassLoader().getResourceAsStream(IP_PATH);
            properties.load(in);
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()){
                String key = (String) keys.nextElement();
                result.put(key,properties.getProperty(key));
            }
        } catch (IOException e) {
            logger.error("获取IP配置初始化失败!",e);
        }
    }

    public static String getValue(String key){
        return result.get(key);
    }

   public static void getCity(){

       String host = "https://dm-81.data.aliyun.com";
       String path = "/rest/160601/ip/getIpInfo.json";
       String method = "GET";
       String appcode = "你自己的AppCode";
       Map<String, String> headers = new HashMap<String, String>();
       //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
       headers.put("Authorization", "APPCODE " + appcode);
       Map<String, String> querys = new HashMap<String, String>();
       querys.put("ip", "0.0.0.0");
       try {

           HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
           String s = EntityUtils.toString(response.getEntity());
       } catch (Exception e) {
           e.printStackTrace();
       }

   }


}
