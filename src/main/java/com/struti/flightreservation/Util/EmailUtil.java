package com.struti.flightreservation.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {

    @Value("${com.struti.flightreservation.itinerary.email.subject}")
    private String EMAIL_SUBJECT;
    @Value("${com.struti.flightreservation.itinerary.email.body}")
    private String EMAIL_BODY;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    private JavaMailSender sender;

    public EmailUtil(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendItinerary(String toAdress, String filePath) {
        LOGGER.info("Inside sendItinerary() toAdress: " + toAdress + ", filePath:" + filePath);
        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setTo(toAdress);
            messageHelper.setSubject(EMAIL_SUBJECT);
            messageHelper.setText(EMAIL_BODY);
            messageHelper.addAttachment("Itinerary", new File(filePath));
            sender.send(message);

        } catch (MessagingException e) {
            LOGGER.error("Exception inside sendItinerary " + e);
            e.printStackTrace();
        }
    }
}
