package com.magic.aimai.business.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

/**
 * 视频
 * @author lzh
 * @create 2017/7/24 17:49
 */
public class ReadVideo {

    /**
     * 获取视频时长
     * @param path
     * @return
     */
    public static Long getVideoLs(String path) {
        File source = new File(path);
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration() / 1000;
            return ls;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        getVideoLs("");
    }
}
