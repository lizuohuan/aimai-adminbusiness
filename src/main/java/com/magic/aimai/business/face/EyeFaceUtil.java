package com.magic.aimai.business.face;

import com.eyekey.http.EyeKeyHttp;
import com.magic.aimai.business.util.SendRequestUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 人脸信息获取类
 * Created by Eric Xie on 2017/7/17 0017.
 */
public class EyeFaceUtil {


    /**
     * 获取 faceId
     * @param faceImage
     * @return
     * @throws Exception
     */
    public static String  getFaceInfo(String faceImage) throws Exception{
        if(null == faceImage){
            return null;
        }
        String url = EyeFaceConfig.getFacePath() + faceImage;

        if(EyeFaceConfig.getIsDebug()){
            EyeFaceUtil eyeFaceUtil = new EyeFaceUtil();
            String localFilePath = "D:\\" + faceImage;
            eyeFaceUtil.downloadFile(url, localFilePath);


            File file = new File(localFilePath);
            return checkImgBybase64(encodeImgageToBase64(file));
        }
        else {
            return checkImgByUrl(url);
        }
    }


    /**
     * 人脸 相似度 匹配
     * @param originalFaceId
     * @param matchFaceId
     */
    public static  String matchFace(String originalFaceId,String matchFaceId){
        EyeKeyHttp e=new EyeKeyHttp();
        return e.matchCompare(originalFaceId, matchFaceId).toJSONString();

    }





    public static String createPeople(String faceId) throws Exception {
        Map<String,String> data = new HashMap<String, String>();
        data.put("face_id",faceId);
        data.put("app_id",EyeFaceConfig.getAppId());
        data.put("app_key",EyeFaceConfig.getAppKey());
        String url = SendRequestUtil.geturl(data,EyeFaceConfig.PEOPLE_API_URL);
        return SendRequestUtil.sendRequest(url, "GET");
    }




    private static String checkImgBybase64(String base64){
        EyeKeyHttp e=new EyeKeyHttp();
        return e.checkingImgB64(base64, null).toJSONString();
    }

    /**
     * 1.通过网络图片url进行人脸检测
     * @param url 网络图片地址
     * @return 人脸检测的json字符串
     * @author Jaylee
     */
    private static String checkImgByUrl(String url){
        EyeKeyHttp e=new EyeKeyHttp();
        return e.checkingImgUrl(url, null).toJSONString();
    }


    /**
     * File文件转base64格式
     * @param imageFile
     * @author Jaylee
     * @return string
     */
    private static String encodeImgageToBase64(File imageFile) {

        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }




    private void downloadFile(String remoteFilePath, String localFilePath)
    {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File file = new File(localFilePath);
        try
        {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(file));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bis.close();
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
