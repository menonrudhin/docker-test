package test.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.kafka.kafkaclient.MessageProducer;

@RestController
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String text) {
        System.out.println("\n\t *** Called sendMessage rest method *** \n");
        messageProducer.produce(text);
    }
}
