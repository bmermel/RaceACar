/*
package com.Equipo2.RaceACar.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendMailAttach(List<String> mails, MailStructure mailStructure, String file) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);


        for (String mail : mails){
            FileSystemResource fileSystemResource = new FileSystemResource(new File(file));
            helper.setFrom(fromMail,"Administración Crece");
            helper.setTo(mail);
            helper.setText(MailTemplate.generateMail(),true);
            helper.setSubject(mailStructure.getSubject());
            helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()),fileSystemResource);
            mailSender.send(mimeMessage);
            System.out.println("mail enviado con attach");

        }
    }
}
*/
