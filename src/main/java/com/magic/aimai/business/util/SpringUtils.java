package com.magic.aimai.business.util;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * Spring工具类.
 * <p>用于获取Spring Bean对象，和国际化资源</p>
 * Created by Administrator on 2016/10/17 0017.
 */
@Component("springUtils")
@Lazy(false)
public final class SpringUtils implements ApplicationContextAware, DisposableBean {

    /** applicationContext */
    private static ApplicationContext applicationContext;

    /**
     * 不可实例化
     */
    private SpringUtils() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    public void destroy() throws Exception {
        applicationContext = null;
    }

    /**
     * 获取applicationContext
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取实例
     *
     * @param name
     *            Bean名称
     * @return 实例
     */
    public static Object getBean(String name) {
        Assert.hasText(name);
        return applicationContext.getBean(name);
    }

    /**
     * 获取实例
     * @param type
     *            Bean类型
     * @return 实例
     */
    public static <T> T getBean(Class<T> type) {
        Assert.notNull(type);
        return applicationContext.getBean(type);
    }

    /**
     * 获取实例
     * @param type
     *            Bean类型
     * @return 实例
     */
    public static <T> T getBean(String name, Class<T> type) {
        Assert.notNull(type);
        return applicationContext.getBean(name, type);
    }

    /**
     * 获取国际化消息
     *
     * @param code
     *            代码
     * @param args
     *            参数
     * @return 国际化消息
     */
    public static String getMessage(String code, Object... args) {
        LocaleResolver localeResolver = getBean(LocaleResolver.class);
        Locale locale = localeResolver.resolveLocale(null);
        return applicationContext.getMessage(code, args, locale);
//        return applicationContext.getMessage(code, args, Locale.CHINA);
    }

}