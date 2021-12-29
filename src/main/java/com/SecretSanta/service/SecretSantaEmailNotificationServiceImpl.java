package com.SecretSanta.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SecretSanta.entity.Participant;

@Service
public class SecretSantaEmailNotificationServiceImpl implements SecretSantaEmailNotificationService{
	
	@Autowired
	SecretSantaExcelReaderService secretSantaExcelReaderService;
		
	public void sendEmailNotification() {
		Participant[][] participants= secretSantaExcelReaderService.getSecretSantaPairs();
		for(Participant[] p: participants) {
			String receipent= p[0].getEmail();
			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("sarkar.abhilash0999@gmail.com", "rahul@123");
	            }
	        });
			MimeMessage message= new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress("sarkar.abhilash0999@gmail.com"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receipent));
				message.setSubject("***Secret Santa Notification***");
				message.setText("Hi "+ p[0].getName()+",/n"+ "You are the Secret Santa for "+ p[1].getName()
						+". Please send your gift to the following address/n/n"+ p[1].getEmail());
				Transport.send(message);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	
}
