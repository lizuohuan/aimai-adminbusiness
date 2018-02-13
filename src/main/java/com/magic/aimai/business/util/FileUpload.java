package com.magic.aimai.business.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 *  上传文件
 * @author QimouXie
 *
 */
public class FileUpload {
	
	

	/**
	 * @param file 			//文件对象
	 * @param filePath		//上传路径
	 * @param fileName		//文件名
	 * @return  文件名
	 */
	public static String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			if(extName!=null&&extName.length()>0){
				
			}else{
				extName = getExtName(file);
			}
			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}
	
	public static String getExtName(MultipartFile mf) {
		String originalFilename[] =  mf.getContentType().split("/");
//		fileName = fileName+"."+originalFilename[1];
		return "."+originalFilename[1];
	}
	
	/**
	 * 写文件到当前目录的upload目录中
	 * 
	 * @param in
	 * @throws IOException
	 */
	private static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		ServletContext servletContext = request.getSession().getServletContext();
		File file = new File(servletContext.getRealPath("/"+dir + realName));
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}


	//删除文件
	public static void delete(String dir) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		ServletContext servletContext = request.getSession().getServletContext();
		File file = new File(servletContext.getRealPath("/"+dir));
		if (file.exists()) {
			file.delete();
		}
	}


	/**
	 * 删除多个文件
	 * @param dirs
     */
	public static void deletes(String[] dirs) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		ServletContext servletContext = request.getSession().getServletContext();
		File file;
		for (String dir : dirs) {
			file = new File(servletContext.getRealPath("/"+dir));
			if (file.exists()) {
				file.delete();
			}
		}

	}
}
