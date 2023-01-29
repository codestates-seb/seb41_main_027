package main027.server.global.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final  JavaMailSender mailSender;

    public void sendMail(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("echogreenseoul@gmail.com");
        message.setTo("echogreenseoul@gmail.com");
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getContent());
        log.info("[TITLE]= [{}] [CONTENT]= [{}]", mailDto.getTitle(), mailDto.getContent());
        mailSender.send(message);
    }
}
