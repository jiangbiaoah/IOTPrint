package com.Atschool.Class;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.Atschool.JavaBean.MailSenderInfo;
import com.Atschool.JavaBean.MyAuthenticator;


public class SendMail {
	/** 
     * 以文本格式发送邮件
     *  
     * @param mailSenderInfo 
     * 		待发送的邮件的信息 ,包含：1.收件人邮箱
     * 								2.邮件主题
     * 								3.邮件正文
     */  
    public static boolean sendTextMail(MailSenderInfo mailSenderInfo) {
//    	LogInfo.output("准备发送邮件通知用户...");
    	if(mailSenderInfo.getToAddress()=="" ||  mailSenderInfo.getSubject()=="" ||  mailSenderInfo.getContent()==""){
    		return false;
    	}
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailSenderInfo.getProperties();
        if (mailSenderInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器  
            authenticator = new MyAuthenticator(mailSenderInfo.getUserName(),
                    mailSenderInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailSenderInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中  
            Address to = new InternetAddress(mailSenderInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题 
            mailMessage.setSubject(mailSenderInfo.getSubject());
            // 设置邮件消息发送的时间 
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            mailMessage.setText(mailSenderInfo.getContent());
            // 发送邮件  
            Transport.send(mailMessage);
//            LogInfo.output("用户文件发送成功。");
            return true;
        } catch (MessagingException ex) {  
//        	LogInfo.output("用户文件发送失败！");
            ex.printStackTrace();
        }
        return false;
    }
  
    /** 
     * 以HTML格式发送邮件 
     *  
     * @param mailSenderInfo 待发送的邮件信息 
     */  
    public static boolean sendHtmlMail(MailSenderInfo mailSenderInfo) {
//    	LogInfo.output("准备发送邮件通知用户...");
        // 判断是否需要身份认证  
        MyAuthenticator authenticator = null;
        Properties pro = mailSenderInfo.getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        if (mailSenderInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailSenderInfo.getUserName(),mailSenderInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailSenderInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailSenderInfo.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailSenderInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
              
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象  
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mailSenderInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
              
            //设置信件的附件(用本地上的文件作为附件)
            html = new MimeBodyPart();
            FileDataSource fds = new FileDataSource("D:\\...javamail.doc");
            DataHandler dh = new DataHandler(fds);
            html.setFileName("javamail.doc");
            html.setDataHandler(dh);
            mainPart.addBodyPart(html);
              
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            mailMessage.saveChanges();
            
            // 发送邮件
            Transport.send(mailMessage);
//            LogInfo.output("用户文件发送成功。");
            return true;
        } catch (MessagingException ex) {
//        	LogInfo.output("用户文件发送失败！");
            ex.printStackTrace();
        }
        return false;
    } 
}
