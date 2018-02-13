package com.magic.aimai.business.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;



/**
 * 发起HTTP请求
 * 
 * @author QimouXie
 *
 */
public class SendRequestUtil {

	public static final String POST = "POST";
	public static final String GET = "GET";

	/**
	 *  发送网络请求
	 * @param url_req URL
	 * @param requestMethod 请求方式
	 * @return
	 * @throws Exception
	 */
	public static String sendRequest(String url_req, String requestMethod) throws Exception {
		StringBuffer buffer = new StringBuffer();
		System.setProperty ("jsse.enableSNIExtension", "false");
		URL url = new URL(url_req);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setUseCaches(false);
		urlConnection.setRequestMethod(requestMethod);
		urlConnection.connect();

		// 将返回的输入流转换成字符串
		InputStream inputStream = urlConnection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		// 释放资源
		inputStream.close();
		inputStream = null;
		urlConnection.disconnect();
		return buffer.toString();
	}

	/**
	 * 生成 完成请求的URL
	 * 
	 * @param map
	 *            参数
	 * @param url
	 *            初始URL
	 * @return
	 */
	public static String geturl(Map<String, String> map, String url) {
		String para = "";
		if (map != null && map.size() > 0) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				para += key + "=" + value + "&";
			}
		}
		return url  + para;
	}
	
	
	
	public static String httpPost(String url, String entity) {
		if (url == null || url.length() == 0) {
			return null;
		}
		 PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(entity);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	} 
	public static void main(String[] args) {
		try {
//			Map<String,String> map = new HashMap<String, String>();
//			map.put("origins", MapUtil.getValue("origins"));
//			map.put("destinations", MapUtil.getValue("destinations"));
//			map.put("ak", MapUtil.getValue("ak"));
//			map.put("output", MapUtil.getValue("output"));
//			String url = geturl(MapUtil.getMap(), "http://api.map.baidu.com/routematrix/v2/driving?");
//			System.out.println(url);
			System.out.println(httpPost("https://api.mch.weixin.qq.com/secapi/pay/refund",""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
