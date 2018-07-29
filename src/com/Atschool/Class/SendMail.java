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
     * ���ı���ʽ�����ʼ�
     *  
     * @param mailSenderInfo 
     * 		�����͵��ʼ�����Ϣ ,������1.�ռ�������
     * 								2.�ʼ�����
     * 								3.�ʼ�����
     */  
    public static boolean sendTextMail(MailSenderInfo mailSenderInfo) {
//    	LogInfo.output("׼�������ʼ�֪ͨ�û�...");
    	if(mailSenderInfo.getToAddress()=="" ||  mailSenderInfo.getSubject()=="" ||  mailSenderInfo.getContent()==""){
    		return false;
    	}
        // �ж��Ƿ���Ҫ�����֤
        MyAuthenticator authenticator = null;
        Properties pro = mailSenderInfo.getProperties();
        if (mailSenderInfo.isValidate()) {
            // �����Ҫ�����֤���򴴽�һ��������֤��  
            authenticator = new MyAuthenticator(mailSenderInfo.getUserName(),
                    mailSenderInfo.getPassword());
        }
        // �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // ����session����һ���ʼ���Ϣ
            Message mailMessage = new MimeMessage(sendMailSession);
            // �����ʼ������ߵ�ַ
            Address from = new InternetAddress(mailSenderInfo.getFromAddress());
            // �����ʼ���Ϣ�ķ�����
            mailMessage.setFrom(from);
            // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��  
            Address to = new InternetAddress(mailSenderInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // �����ʼ���Ϣ������ 
            mailMessage.setSubject(mailSenderInfo.getSubject());
            // �����ʼ���Ϣ���͵�ʱ�� 
            mailMessage.setSentDate(new Date());
            // �����ʼ���Ϣ����Ҫ����
            mailMessage.setText(mailSenderInfo.getContent());
            // �����ʼ�  
            Transport.send(mailMessage);
//            LogInfo.output("�û��ļ����ͳɹ���");
            return true;
        } catch (MessagingException ex) {  
//        	LogInfo.output("�û��ļ�����ʧ�ܣ�");
            ex.printStackTrace();
        }
        return false;
    }
  
    /** 
     * ��HTML��ʽ�����ʼ� 
     *  
     * @param mailSenderInfo �����͵��ʼ���Ϣ 
     */  
    public static boolean sendHtmlMail(MailSenderInfo mailSenderInfo) {
//    	LogInfo.output("׼�������ʼ�֪ͨ�û�...");
        // �ж��Ƿ���Ҫ�����֤  
        MyAuthenticator authenticator = null;
        Properties pro = mailSenderInfo.getProperties();
        // �����Ҫ�����֤���򴴽�һ��������֤��
        if (mailSenderInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailSenderInfo.getUserName(),mailSenderInfo.getPassword());
        }
        // �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // ����session����һ���ʼ���Ϣ
            Message mailMessage = new MimeMessage(sendMailSession);
            // �����ʼ������ߵ�ַ
            Address from = new InternetAddress(mailSenderInfo.getFromAddress());
            // �����ʼ���Ϣ�ķ�����
            mailMessage.setFrom(from);
            // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
            Address to = new InternetAddress(mailSenderInfo.getToAddress());
            // Message.RecipientType.TO���Ա�ʾ�����ߵ�����ΪTO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // �����ʼ���Ϣ������
            mailMessage.setSubject(mailSenderInfo.getSubject());
            // �����ʼ���Ϣ���͵�ʱ��
            mailMessage.setSentDate(new Date());
              
            // MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���  
            Multipart mainPart = new MimeMultipart();
            // ����һ������HTML���ݵ�MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // ����HTML����
            html.setContent(mailSenderInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
              
            //�����ż��ĸ���(�ñ����ϵ��ļ���Ϊ����)
            html = new MimeBodyPart();
            FileDataSource fds = new FileDataSource("D:\\...javamail.doc");
            DataHandler dh = new DataHandler(fds);
            html.setFileName("javamail.doc");
            html.setDataHandler(dh);
            mainPart.addBodyPart(html);
              
            // ��MiniMultipart��������Ϊ�ʼ�����
            mailMessage.setContent(mainPart);
            mailMessage.saveChanges();
            
            // �����ʼ�
            Transport.send(mailMessage);
//            LogInfo.output("�û��ļ����ͳɹ���");
            return true;
        } catch (MessagingException ex) {
//        	LogInfo.output("�û��ļ�����ʧ�ܣ�");
            ex.printStackTrace();
        }
        return false;
    } 
}
