package com.flightreservation.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	private String EMAIL_BODY ="Please find your Itinerary attached.";
	private String EMAIL_SUBJECT="Itinerary for your Flight";
	
	@Autowired
	private JavaMailSender sender;
	public void sendItinerary(String toAddress,String filePath) {
		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
		messageHelper.setTo(toAddress);
		messageHelper.setSubject(EMAIL_SUBJECT);
		messageHelper.setText(EMAIL_BODY);
		messageHelper.addAttachment("Itinearary",new File(filePath));
		sender.send(message);
	}catch (MessagingException e) {
}
	}
}
