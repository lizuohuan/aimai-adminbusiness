package com.magic.aimai.business.util;

import com.magic.aimai.business.cache.MemcachedUtil;
import com.magic.aimai.business.entity.User;
import com.magic.aimai.business.enums.Common;
import com.magic.aimai.business.exception.InterfaceCommonException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


public class LoginHelper {
	
	public static final String TOKEN = "token";

	public static boolean isLogin=false;

	/**SESSION USER*/
	public static final String SESSION_USER = "admin_user";

	/** key前缀 */
	public static final String KEY_LOGIN = "login_";

	public static final String VIDEO_PREFIX = "videoIds"; // 待删除云视频的标记


	public static void delObject(String token){
		MemcachedUtil.getInstance().delObj(token);
	}


	public static User getCurrentUser(){
		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		Object obj = req.getSession().getAttribute(SESSION_USER);
		if(null == obj){
			String token = req.getHeader(TOKEN);
			Object user = MemcachedUtil.getInstance().get(token);
			if(null == user){
				return null;
			}else{
				return (User) user;
			}
		}
		return (User)obj;
	}


	public static User getCurrentUserOfAPI() throws Exception{
		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		String token = req.getHeader(TOKEN);
		User user = (User)MemcachedUtil.getInstance().get(token);
		if(null == user){
			throw new InterfaceCommonException(StatusConstant.NOTLOGIN,"未登录");
		}
		if(Common.NO.ordinal() == user.getIsValid()){
			throw new InterfaceCommonException(StatusConstant.ACCOUNT_FROZEN,"帐号无效");
		}
		return user;

	}






	public static User getCurrentUser(String token){
		return (User) MemcachedUtil.getInstance().get(token);
	}
	
	public static void clearToken(String token){
		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		if(null == token){
			req.getSession().invalidate();
		}else{
			MemcachedUtil.getInstance().delObj(token);
		}
	}
	
	public static String addToken(User user){
		String token = null;
		// 用户
		if(user.getToken() != null){
			Object tempObj = MemcachedUtil.getInstance().get(user.getToken());
			if(null != tempObj){
				MemcachedUtil.getInstance().delObj(user.getToken());
			}
		}
		token = UUID.randomUUID().toString().replaceAll("-", "");
		user.setToken(token);
		MemcachedUtil.getInstance().add(token, user);
		return token;
	}

	public static void add(String key,Object value,int seconds){
		MemcachedUtil.getInstance().add(key, value,seconds);
	}

	public static void add(String key,Object value){
		MemcachedUtil.getInstance().add(key, value);
	}

	public static void del(String key){
		MemcachedUtil.getInstance().delObj(key);
	}

	public static Object get(String key){
		return MemcachedUtil.getInstance().get(key);
	}

	public static boolean  replaceToken(String token,Object obj){
		return MemcachedUtil.getInstance().set(token, obj);
	}

	
}
