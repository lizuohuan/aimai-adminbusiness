package com.magic.aimai.business.entity;

/**
 * 邮件 entity
 * Created by Eric Xie on 2017/6/14 0014.
 */
public class Email {


    private String emailAddress;

    private String subject;

    private String content;


    public Email(String emailAddress, String subject, String content) {
        this.emailAddress = emailAddress;
        this.subject = subject;
        this.content = content;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
