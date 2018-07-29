package com.Atschool.JavaBean;

import java.util.Properties;

public class MailSenderInfo {
	// 发送邮件的服务器的IP和端口
    private String mailServerHost="smtp.163.com";
    private String mailServerPort = "25";
    // 邮件发送者的地址&&登陆邮件发送服务器的用户名和密码 1369wangyi
    private String fromAddress="jiangbiaoah@163.com";
    private String userName="jiangbiaoah@163.com";
    private String password="1369wangyi";
    // 是否需要身份验证
    private boolean validate = true;
    
    //--------------------发送需要的信息-----------------
    //http://feedback.mail.126.com/antispam/complain.php?user=****@163.com若发送失败，请再次页面反馈
    // 1.邮件接收者的地址
    private String toAddress;
    // 2.邮件主题
    private String subject;
    // 3.邮件的文本内容
    private String content;
    // 4.邮件附件的文件名
    private String[] attachFileNames;
  
    /** 
     * 获得邮件会话属性 
     */  
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }
  
    public String getMailServerHost() {
        return mailServerHost;
    }  
  
//    public void setMailServerHost(String mailServerHost) {  
//        this.mailServerHost = mailServerHost;  
//    }  
  
    public String getMailServerPort() {  
        return mailServerPort;  
    }  
  
//    public void setMailServerPort(String mailServerPort) {  
//        this.mailServerPort = mailServerPort;  
//    }  
  
    public boolean isValidate() {  
        return validate;  
    }  
  
//    public void setValidate(boolean validate) {  
//        this.validate = validate;  
//    }  
  
    public String[] getAttachFileNames() {  
        return attachFileNames;  
    }  
  
//    public void setAttachFileNames(String[] fileNames) {  
//        this.attachFileNames = fileNames;  
//    }  
  
    public String getFromAddress() {  
        return fromAddress;  
    }  
  
//    public void setFromAddress(String fromAddress) {  
//        this.fromAddress = fromAddress;  
//    }  
  
    public String getPassword() {  
        return password;  
    }  
  
//    public void setPassword(String password) {  
//        this.password = password;  
//    }  
  
    public String getToAddress() {  
        return toAddress;  
    }  
  
    public void setToAddress(String toAddress) {  
        this.toAddress = toAddress.trim();
    }  
  
    public String getUserName() {  
        return userName;  
    }  
  
//    public void setUserName(String userName) {  
//        this.userName = userName;  
//    }  
  
    public String getSubject() {  
        return subject;  
    }  
  
    public void setSubject(String subject) {  
        this.subject = subject;  
    }  
  
    public String getContent() {  
        return content;  
    }  
  
    public void setContent(String textContent) {  
        this.content = textContent;  
    }  
}
