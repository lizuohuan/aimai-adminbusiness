package com.magic.aimai.business.face;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 人脸识别 配置
 * Created by Eric Xie on 2017/7/17 0017.
 */
public class EyeFaceConfig {


    private static Logger logger = Logger.getLogger(EyeFaceConfig.class);

    /**配置文件路径*/
    private static final String FACE_PATH = "face.properties";

    /** 获取faceId  URL */
    public static final String API_URL = "http://api.eyekey.com/face/Check/checking?";

    /** 创建People URL */
    public static final String PEOPLE_API_URL = "http://api.eyekey.com/People/people_create?";
    /** 人脸对比 URL */
    public static final String FACE_MATCH_API = "http://api.eyekey.com/face/Match/match_compare?";


    /**结果集*/
    private static Map<String,String> result = new HashMap<String, String>();

    static {
        init();
    }

    private EyeFaceConfig(){}

    protected static void init() {

        try {
            Properties properties = new Properties();
            // 获取配置文件
            InputStream in = EyeFaceConfig.class.getClassLoader().getResourceAsStream(FACE_PATH);
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


    public static String getAppId(){
        return result.get("app_id");
    }

    public static String getAppKey(){
        return result.get("app_key");
    }


    public static String getFacePath(){
        return result.get("face_path");
    }

    public static boolean getIsDebug(){
        return "true".equals(result.get("debug"));
    }

}
