package test.kafka.kafkaclient;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import test.kafka.controller.TextMessage;
import test.kafka.controller.TextMessageRepository;

import java.nio.charset.StandardCharsets;

@Component
public class MessageConsumer {

    @Autowired
    private TextMessageRepository repository;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    public MessageConsumer() {
        System.out.println("********* MessageConsumer constructor ******");
    }

    @KafkaListener(id="myid" , topics="topic1")
    public void listen(ConsumerRecord<String, String> record) {

        try(RedisConnection redisConnection = jedisConnectionFactory.getConnection()) {

            System.out.println("Record key = " + record.key() + " and value = " + record.value());
            try {
                TextMessage textMessage = new TextMessage();
                textMessage.setText(record.value());
                boolean status = redisConnection.set("TextMessage".getBytes(StandardCharsets.UTF_8), textMessage.getText().getBytes(StandardCharsets.UTF_8));
                System.out.println("Redis set status = " + status);
            } catch (Exception e) {
                System.err.println("*** DB operation failed! *** \n\n");
                e.printStackTrace();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
