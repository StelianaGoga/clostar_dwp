package com.clostar.business;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

	static final String FROM = "steliana.goga@gmail.com";
	static final String HOST = "localhost";
	private Session session;
	private MimeMessage message; 
	private String to;
	
	public SendEmail(String to){
		setTo(to);
		// Get system properties
	    Properties properties = System.getProperties();
	    //Setup mail server
	    properties.setProperty("mail.smtp.host", HOST);
	    // Get the default Session object.
	    setSession(Session.getDefaultInstance(properties));
        // Create a default MimeMessage object.new MimeMessage(getSession());
	    setMessage(new MimeMessage(session));
        // Set From: header field of the header.
        try {
			getMessage().setFrom(new InternetAddress(FROM));
			// Set To: header field of the header.
			getMessage().addRecipient(Message.RecipientType.TO,
                                 new InternetAddress(getTo()));
        }
        catch (MessagingException mex) {
        	mex.printStackTrace();
        }
	}

	public void sendHtmlMail(String subject, String text) {
	      try{
	         // Set Subject: header field
	         getMessage().setSubject(subject);

	         // Send the actual HTML message, as big as you like
	         getMessage().setContent(text, "text/html");

	         // Send message
	         Transport.send(getMessage());
	         System.out.println("Sent message successfully....");
	      }
	      catch (MessagingException mex) {
	    	  mex.printStackTrace();
	      }
	   }

	public void sendTextMail(String subject, String text) {
	      try{
	         // Set Subject: header field
	         getMessage().setSubject(subject);

	         // Send the actual HTML message, as big as you like
	         getMessage().setText(text);

	         // Send message
	         Transport.send(getMessage());
	         System.out.println("Sent message successfully....");
	      }
	      catch (MessagingException mex) {
	    	  mex.printStackTrace();
	      }
	   }

	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public MimeMessage getMessage() {
		return message;
	}
	
	public void setMessage(MimeMessage message) {
		this.message = message;
	}
}