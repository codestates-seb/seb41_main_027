package main027.server.global.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendMail(@RequestBody MailDto mailDto) {
        mailService.sendMail(mailDto);

    }

}
