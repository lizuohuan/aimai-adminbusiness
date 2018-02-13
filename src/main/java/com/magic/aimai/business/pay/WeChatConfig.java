package com.magic.aimai.business.pay;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 微信公众号端
 * Created by Eric Xie on 2017/3/21 0021.
 */
public class WeChatConfig {


    public static final String SESSION_WX_USER = "wx_user";

    private static final String WXCONFIG_PATH ="wx.properties";

    private static Map<String,Object> result = new HashMap<String, Object>();

    public static final String ACCESS_TOKEN= "access_token";
    public static final String ACCESS_TOKEN2= "access_token2";

    public static final String TICKET= "ticket";



    private static Logger logger = Logger.getLogger(WeChatConfig.class);

    static {
        init();
    }

    private WeChatConfig(){}

    private static void init(){

        try {
            Properties properties = new Properties();
            // 获取配置文件
            InputStream in = WeChatConfig.class.getClassLoader().getResourceAsStream(WXCONFIG_PATH);
            properties.load(in);
            Enumeration enumer = properties.keys();
            while (enumer.hasMoreElements()){
                String key = (String)enumer.nextElement();
                result.put(key,properties.getProperty(key));
            }
        }catch (Exception e){
            logger.error("初始化配置文件错误!",e);
        }

    }
    public static Object getValue(String key){
        return result.get(key);
    }

}
