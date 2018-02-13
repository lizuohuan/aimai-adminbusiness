package com.magic.aimai.business.media;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Eric Xie on 2018/1/23 0023.
 */
public class MediaConfig {

    private static Logger logger = Logger.getLogger(MediaConfig.class);


    private static String MEDIA_PATH = "media.properties";

    private static Map<String,String> result = new HashMap<String, String>();

    public static String DEL_VIDEO_GATEWAY = "http://vod.cn-shanghai.aliyuncs.com?Action=DeleteVideo";

    static {
        init();
    }


    private MediaConfig(){}


    private static void init() {
        try {
            InputStream in = MediaConfig.class.getClassLoader().getResourceAsStream(MEDIA_PATH);
            Properties properties = new Properties();
            properties.load(in);
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()){
                String key = keys.nextElement().toString();
                result.put(key,properties.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("资源服务器配置初始化失败！",e);
        }
    }

    public static String getAccessKeyId(){
        return result.get("AccessKeyId");
    }

    public static String getAccessKeySecret(){
        return result.get("AccessKeySecret");
    }

    public static DefaultAcsClient buildAliyunClient(){
        return new DefaultAcsClient(
                DefaultProfile.getProfile("cn-shanghai",result.get("AccessKeyId"),
                        result.get("AccessKeySecret")));
    }

    public static String generateTimestamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    public static String generateRandom() {
        String signatureNonce = UUID.randomUUID().toString();
        return signatureNonce;
    }

}
