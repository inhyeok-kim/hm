package com.seaweed.hm.common.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailContent {
    private String receiver;
    private String subject;
    private String content;
}
