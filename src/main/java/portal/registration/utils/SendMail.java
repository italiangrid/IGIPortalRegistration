package portal.registration.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMail {
 
	@Override
	public String toString() {
		return "SendMail [from=" + from + ", to=" + to + ", subject=" + subject
				+ ", text=" + text + "]";
	}

	private String from;
	private String to;
	private String subject;
	private String text;
	private boolean isHtml;
 
	public SendMail(String from, String to, String subject, String text, boolean isHtml){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.isHtml = isHtml;
	}
 
	public void send(){
 
		Properties props = new Properties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25");
 
		Session mailSession = Session.getDefaultInstance(props);
		Message simpleMessage = new MimeMessage(mailSession);
 
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;
		try {
			fromAddress = new InternetAddress(from);
			toAddress = new InternetAddress(to);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		try {
			simpleMessage.setFrom(fromAddress);
			simpleMessage.setRecipient(RecipientType.TO, toAddress);
			simpleMessage.setSubject(subject);
			if(isHtml){
				simpleMessage.setContent(text, "text/html; charset=utf-8");
			}else{
				simpleMessage.setText(text);
			}
 
			Transport.send(simpleMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendList() throws MessagingException{
		 
		Properties props = new Properties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25");
 
		Session mailSession = Session.getDefaultInstance(props);
		Message simpleMessage = new MimeMessage(mailSession);
 
		InternetAddress fromAddress = null;
		InternetAddress[] toAddress = null;
		
		fromAddress = new InternetAddress(from);
		toAddress = InternetAddress.parse(to);
		
		simpleMessage.setFrom(fromAddress);
		simpleMessage.setRecipients(RecipientType.BCC, toAddress);
		simpleMessage.setSubject(subject);
		if(isHtml){
			simpleMessage.setContent(text, "text/html; charset=utf-8");
		}else{
			simpleMessage.setText(text);
		}
 
		Transport.send(simpleMessage);
		
	}
}
