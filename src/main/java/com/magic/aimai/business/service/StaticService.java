package com.magic.aimai.business.service;

import com.magic.aimai.business.util.SpringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.Map;

/**
 * @author Holen
 * @create 2016/12/18 18:56.
 */
@Component
public class StaticService {

    private FreeMarkerConfigurer freeMarkerConfigurer;
    /*public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }
*/
    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public int build(String templatePath, String staticPath, Map<String, Object> model) {
        if (null == freeMarkerConfigurer) {
            freeMarkerConfigurer = SpringUtils.getBean(FreeMarkerConfigurer.class);
        }
        if (null == servletContext) {
            servletContext = SpringUtils.getBean(ServletContext.class);
        }
        Assert.hasText(templatePath);
        Assert.hasText(staticPath);
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        Writer writer = null;
        try {
            freemarker.template.Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
            File staticFile = new File(servletContext.getRealPath(staticPath));
            File staticDirectory = staticFile.getParentFile();
            if (!staticDirectory.exists()) {
                staticDirectory.mkdirs();
            }
            fileOutputStream = new FileOutputStream(staticFile);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            writer = new BufferedWriter(outputStreamWriter);
            template.process(model, writer);
            writer.flush();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(outputStreamWriter);
            IOUtils.closeQuietly(fileOutputStream);
        }
        return 0;
    }

    public void build(String templatePath, Map<String, Object> model, Writer writer) {
        Assert.hasText(templatePath);
        if (null == freeMarkerConfigurer) {
            freeMarkerConfigurer = SpringUtils.getBean(FreeMarkerConfigurer.class);
        }
        if (null == servletContext) {
            servletContext = SpringUtils.getBean(ServletContext.class);
        }
//        OutputStreamWriter outputStreamWriter = null;
        try {
            freemarker.template.Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath,"UTF-8");

//            writer = new BufferedWriter(outputStreamWriter);
            template.process(model, writer);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
//            IOUtils.closeQuietly(outputStreamWriter);
        }

    }

    public void buildIndex() {

    }

}
