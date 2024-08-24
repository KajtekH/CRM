package com.kajtekh.notificationservice;

import com.kajtekh.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class Controller {

    private final NotificationService notificationService;

    @GetMapping
    public String getHello() {
        return "Hello!";
    }

    @PostMapping("/send/{to}")
    @ResponseStatus(CREATED)
    public void sendEmail(@PathVariable String to) {
        notificationService.sendEmail(to, "Welcome!", "Welcome to our service!");
    }
}
