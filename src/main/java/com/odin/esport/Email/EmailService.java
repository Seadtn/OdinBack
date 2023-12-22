package com.odin.esport.Email;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    String sendMail(EmailDetails Email);
}
