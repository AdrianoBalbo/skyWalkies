package com.mindhub.skywalkies.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class VerificationEmail {
  @Autowired
  private JavaMailSender javaMailSender;

  public void sendVerificationMail(String email,String subject, String body){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("skywalkies.store@gmail.com");
    message.setTo(email);
    message.setText(body);
    message.setSubject(subject);
    javaMailSender.send(message);
  }
}
