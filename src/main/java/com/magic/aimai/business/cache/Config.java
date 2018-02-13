package com.magic.aimai.business.cache;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Eric Xie on 2017/2/13 0013.
 */
public class Config {

    private static Logger logger = Logger.getLogger(Config.class);

    /**配置文件路径*/
    private static final String MEMCACHED_PATH = "memcached.properties";
    /**结果集*/
    private static Map<String,String> result = new HashMap<String, String>();

    static {
        init();
    }

    private Config(){}

    protected static void init() {

        try {
            Properties properties = new Properties();
            // 获取配置文件
            InputStream in = Config.class.getClassLoader().getResourceAsStream(MEMCACHED_PATH);
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

    /**
     *  获取配置文件里的值
     * @param key
     * @return
     */
    public static String getValue(String key){
        return result.get(key);
    }


}
