package com.seaweed.hm.infrastucture.message.mail;

import lombok.Getter;

@Getter
public class MailMessage {
    private MailMessage(Builder builder){
        this.receiverEmails = builder.receiverEmails;
        this.senderEmail = builder.senderEmail;
        this.reffererEmails = builder.reffererEmails;
        this.senderName = builder.senderName;
        this.html = builder.html;
        this.subject = builder.subject;
    }

    private String[] receiverEmails;
    private String senderEmail;
    private String senderName;
    private String[] reffererEmails;
    private String html;
    private String subject;

    public static class Builder{
        private String[] receiverEmails = {};
        private String senderEmail = "";
        private String senderName = "";
        private String[] reffererEmails = {};
        private String html = "";
        private String subject = "";

        public MailMessage build(){
            return new MailMessage(this);
        }

        public Builder setSenderEmail(String email){
            this.senderEmail = email;
            return this;
        }

        public Builder setSenderName(String name){
            this.senderName = name;
            return this;
        }

        public Builder setReceiverEmails(String[] emails){
            this.receiverEmails = emails;
            return this;
        }

        public Builder setReffererEmails(String[] emails){
            this.reffererEmails = emails;
            return this;
        }
        public Builder setHtml(String html){
            this.html = html;
            return this;
        }
        public Builder setSubject(String subject){
            this.subject = subject;
            return this;
        }
    }

}
