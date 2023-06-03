package test.kafka.kafkaclient;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import test.kafka.controller.TextMessage;
import test.kafka.controller.TextMessageRepository;

@Component
public class MessageConsumer {

    @Autowired
    private TextMessageRepository repository;

    public MessageConsumer() {
        System.out.println("********* MessageConsumer constructor ******");
    }

    @KafkaListener(id="myid" , topics="topic1")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Record key = " + record.key() + " and value = " + record.value());
        try {
            TextMessage textMessage = new TextMessage();
            textMessage.setText(record.value());
            repository.save(textMessage);
            System.out.println("Repository save called");
        } catch (Exception e){
            System.err.println("*** DB operation failed! *** \n\n");
            e.printStackTrace();
        }
    }
}
