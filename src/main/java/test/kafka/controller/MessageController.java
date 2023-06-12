package test.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.kafka.exception.ControllerProcessException;
import test.kafka.kafkaclient.MessageProducer;

@RestController
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/sendMessage")
    @ExceptionHandler({ControllerProcessException.class})
    public void sendMessage(@RequestBody String text) {
        System.out.println("\n\t *** Called sendMessage rest method *** \n");
        messageProducer.produce(text);
    }
}
