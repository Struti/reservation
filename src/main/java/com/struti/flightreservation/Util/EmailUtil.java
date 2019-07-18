package com.struti.flightreservation.Util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {


    private JavaMailSender sender;

    public EmailUtil(JavaMailSender sender){
        this.sender = sender;
    }

    public void sendItinerary(String toAdress, String filePath){

        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setTo(toAdress);
            messageHelper.setSubject("Itinerary fo your Flight");
            messageHelper.setText("Please find your Itinerary attached");
            messageHelper.addAttachment("Itinerary",new File(filePath));
            sender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
