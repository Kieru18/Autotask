package z21.autotask.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MailService {

@Autowired
private JavaMailSender javaMailSender;

public void sendEmail(String to, String subject, String body) {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo(to);
    mail.setSubject(subject);
    mail.setText(body);

    javaMailSender.send(mail);
}
}

