package com.magic.aimai.business.mail;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric Xie on 2017/3/10 0010.
 */
public class SendEmailThread implements Runnable {

    private List<String> emails = new ArrayList<String>();

    private String content;
    private String subject;

    private Logger logger = Logger.getLogger(this.getClass());

    protected SendEmailThread(List<String> emails,String subject,String content){
        this.emails = emails;
        this.content = content;
        this.subject = subject;
    }

    protected SendEmailThread(String email,String subject,String content){
        List<String> emails = new ArrayList<String>();
        emails.add(email);
        this.emails = emails;
        this.content = content;
        this.subject = subject;
    }

    public void run() {
        try {
            for (String email : emails){
                SendEmail sendEmail = new SendEmail(Config.getValue("hostName"),Config.getValue("userName"),
                        Config.getValue("password"),"true",Config.getValue("port"));
                sendEmail.send(email,subject,content);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }




}
