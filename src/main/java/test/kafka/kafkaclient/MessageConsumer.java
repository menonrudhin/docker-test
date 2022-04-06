package test.kafka.kafkaclient;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @KafkaListener(id="myid" , topics="topic1")
    public void listen(String in) {
        System.out.println(in);
    }
}
