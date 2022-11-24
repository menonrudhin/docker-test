package test.kafka.kafkaclient;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    public MessageConsumer() {
        System.out.println("********* MessageConsumer constructor ******");
    }

    @KafkaListener(id="myid" , topics="topic1")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Record key = " + record.key() + " and value = " + record.value());
    }
}
