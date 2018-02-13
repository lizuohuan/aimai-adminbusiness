package com.magic.aimai.business.mail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by Eric Xie on 2017/3/8 0008.
 */
public class SendEmail {

    private final transient Properties props = System.getProperties();

    private transient EmailAuthenticator authenticator;

    private transient Session session;


    SendEmail(final String smtpHostName, final String username,
                         final String password,final String auth,final String port) {
        init(username, password, smtpHostName,auth,port);
    }

    private void init(String username, String password, String smtpHostName,String auth,String port) {
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.host", smtpHostName);
        props.put("mail.smtp.port", port);
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        authenticator = new EmailAuthenticator(username, password);
        session = Session.getInstance(props, authenticator);
    }

    void send(String recipient, String subject, Object content)throws AddressException, MessagingException {
        final MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(authenticator.getUserName()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setContent(content.toString(), "text/html;charset=utf-8");
        Transport.send(message);
    }

//    public static void main(String[] args) {
//        try {
//            List<String> emails = new ArrayList<String>();
//            emails.add("616650704@qq.com");
//            if(emails.size() > 0){
//                new Thread(new SendEmailThread(emails,"您有新的订单等待处理","您有新的订单等待接受")).start();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
