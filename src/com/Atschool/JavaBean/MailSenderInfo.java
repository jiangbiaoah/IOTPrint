package com.Atschool.JavaBean;

import java.util.Properties;

public class MailSenderInfo {
	// �����ʼ��ķ�������IP�Ͷ˿�
    private String mailServerHost="smtp.163.com";
    private String mailServerPort = "25";
    // �ʼ������ߵĵ�ַ&&��½�ʼ����ͷ��������û��������� 1369wangyi
    private String fromAddress="jiangbiaoah@163.com";
    private String userName="jiangbiaoah@163.com";
    private String password="1369wangyi";
    // �Ƿ���Ҫ�����֤
    private boolean validate = true;
    
    //--------------------������Ҫ����Ϣ-----------------
    //http://feedback.mail.126.com/antispam/complain.php?user=****@163.com������ʧ�ܣ����ٴ�ҳ�淴��
    // 1.�ʼ������ߵĵ�ַ
    private String toAddress;
    // 2.�ʼ�����
    private String subject;
    // 3.�ʼ����ı�����
    private String content;
    // 4.�ʼ��������ļ���
    private String[] attachFileNames;
  
    /** 
     * ����ʼ��Ự���� 
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
