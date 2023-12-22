package com.odin.esport.Email;


import java.util.Properties;


import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService{
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("kyassminek@gmail.com");
        mailSender.setPassword("awfvjmykptoqnrxg");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
   private JavaMailSender javaMailSender=getJavaMailSender();

    @Override
    public String sendMail(EmailDetails Email) {
    	/*  MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("kyassminek@gmail.com");
            mimeMessageHelper.setTo(Email.getRecipient());
            mimeMessageHelper.setText(Email.getMsgBody());
            mimeMessageHelper.setSubject(
                    Email.getSubject());
            javaMailSender.send(mimeMessage);
            return "Mail envoyé avec succes!";
        }

        catch (MessagingException e) {

            return "Erreur !!!";
        }*/
    	return "Erreur !!!";
    }

}
