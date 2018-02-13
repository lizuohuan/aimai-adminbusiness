package com.magic.aimai.business.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *  XML 工具
 * @author QimouXie
 *
 */
public class XMLUtil {
	
	
	public static String toXML(Map<String,Object> map) throws UnsupportedEncodingException{
		StringBuilder sb = new StringBuilder();
		if(null == map){
			return null;
		}
		sb.append("<xml>");
		Iterator<String> iterator =  map.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			if(CommonUtil.isEmpty(String.valueOf(map.get(key)))){
				continue;
			}
			if("sign".equals(key)){
				continue;
			}
			sb.append("<"+key+">");
			sb.append(map.get(key));
			sb.append("</"+key+">");
		}
		sb.append("<sign>");
		sb.append(map.get("sign"));
		sb.append("</sign>");
		sb.append("</xml>");
		return new String(sb.toString().getBytes(),"UTF-8");
	}
	
	public static Map<String, Object> decodeXml(String content) {

        Map<String, Object> xml = new HashMap<String, Object>();
        try {
            //创建一个SAXBuilder对象
            SAXBuilder saxBuilder = new SAXBuilder();
            byte[] bytes = content.getBytes("UTF-8");
            InputStream xmlInput = new ByteArrayInputStream(bytes);
            //读取prop.xml资源
            Document doc = saxBuilder.build(xmlInput);
            //获取根元素(prop)
            Element root = doc.getRootElement();
            //获取根元素下面的所有子元素(mess)
            @SuppressWarnings("unchecked")
			List<Element> messList = root.getChildren();
            for (Element element : messList) {
                xml.put(element.getName(), element.getValue());
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return xml;

    }

}
