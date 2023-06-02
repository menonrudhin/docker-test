package test.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private TextMessageRepository repository;

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody TextMessage text) {
        repository.save(text);
    }
}
