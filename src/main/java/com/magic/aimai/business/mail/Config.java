package com.magic.aimai.business.mail;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Eric Xie on 2017/5/10 0010.
 */
public class Config {


    private static final String MAIL_PATH = "mail.properties";

    private static Logger logger = Logger.getLogger(Config.class);

    private static Map<String,String> result = new HashMap<String,String>();

    /**邮件正文内容 话术*/
    public static String content = "你的验证码是{0},{1}分钟内有效.";


    static {
        init();
    }

    private Config(){}

    private static void init() {
        try {
            Properties properties = new Properties();
            InputStream in = Config.class.getClassLoader().getResourceAsStream(MAIL_PATH);
            properties.load(in);
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()){
                String key = (String) keys.nextElement();
                result.put(key,properties.getProperty(key));
            }
        } catch (IOException e) {
            logger.error("邮件配置初始化失败!",e);
        }
    }


    public static String getValue(String key){

        return result.get(key);
    }


    /**
     * 获取随机数
     * @param num 需要的位数
     * @return
     */
    public static String getRandom(int num){
        int j = num == 0 ? 4 : num;
        Random random = new Random();
        String code = "";
        for (int i = 0; i < j; i++){
            code += random.nextInt(10);
        }
        return code;
    }
}
