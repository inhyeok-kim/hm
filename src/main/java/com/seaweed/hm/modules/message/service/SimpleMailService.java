package com.seaweed.hm.modules.message.service;

import com.seaweed.hm.comm.config.WebEnviornment;
import com.seaweed.hm.modules.message.domain.MailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Properties;

@Slf4j
@Component
public class SimpleMailService {

    @Autowired
    private WebEnviornment webEnv;

    public void sendMail(MailMessage mailMessage){

        if(webEnv.isProd()){
            String fromMail = mailMessage.getSenderEmail();
            String fromName = mailMessage.getSenderName();

            Properties props = new Properties();
            props.put("mail.smtp.host", "123.123.123.123"); // use Gmail
            props.put("mail.smtp.port", "25"); // set port

            Session mailSession = Session.getInstance(props);

            try {
                MimeMessage message = new MimeMessage(mailSession);

                message.setFrom(new InternetAddress(fromMail, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 한글의 경우 encoding 필요
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(String.join(",",mailMessage.getReceiverEmails()))
                );
                message.setRecipients(
                        Message.RecipientType.CC,
                        InternetAddress.parse(String.join(",",mailMessage.getReffererEmails()))
                );
                message.setSubject(mailMessage.getSubject(),"utf-8");
                message.setContent(mailMessage.getHtml(), "text/html;charset=UTF-8"); // 내용 설정 (HTML 형식)
                message.setSentDate(new java.util.Date());

                Transport.send(message);

            } catch (Exception e) {
                log.error("Failed Send Mail");
                log.error(e.getMessage());
            }
        } else if(webEnv.isTest()){
            log.info("sender : " + mailMessage.getSenderName() + " / " + mailMessage.getSenderEmail());
            log.info("receivers : " + String.join(", ",mailMessage.getReceiverEmails()));
            log.info("refferers : " + String.join(", ",mailMessage.getReffererEmails()));
            log.info("subject : " + mailMessage.getSubject());
            log.info("content : " + mailMessage.getHtml());
        }
    }

}
