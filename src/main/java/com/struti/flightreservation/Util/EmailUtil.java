package com.struti.flightreservation.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {

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
            messageHelper.setSubject("Itinerary fo your Flight");
            messageHelper.setText("Please find your Itinerary attached");
            messageHelper.addAttachment("Itinerary", new File(filePath));
            sender.send(message);

        } catch (MessagingException e) {
            LOGGER.error("Exception inside sendItinerary " + e);
            e.printStackTrace();
        }
    }
}
