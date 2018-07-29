package com.Atschool.JavaBean;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {  
    String userName;  
    String password;  
  
    public MyAuthenticator() {  
    }  
  
    public MyAuthenticator(String username, String password) {  
        this.userName = username;  
        this.password = password;  
    }  
  
    @Override
	protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(userName, password);  
    }  
} 
