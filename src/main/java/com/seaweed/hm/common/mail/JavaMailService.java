package com.seaweed.hm.common.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Properties;

@Service
@Slf4j
public class JavaMailService {

    public void sendMail(String fromMail, String fromName, String receiver, String subject, String content){
        Properties props = new Properties();
        props.put("mail.smtp.host", "220.81.248.13"); // use Gmail
        props.put("mail.smtp.port", "25"); // set port

        Session mailSession = Session.getInstance(props);

        try {
            MimeMessage message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(fromMail, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 한글의 경우 encoding 필요
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receiver)
            );
            message.setSubject(subject,"utf-8");
            message.setContent(content, "text/html;charset=UTF-8"); // 내용 설정 (HTML 형식)
            message.setSentDate(new java.util.Date());

            Transport.send(message);

            log.info("Success Send Mail to "+ receiver);

        } catch (Exception e) {
            log.error("Failed Send Mail");
            log.error(e.getMessage());
        }

    }
}
