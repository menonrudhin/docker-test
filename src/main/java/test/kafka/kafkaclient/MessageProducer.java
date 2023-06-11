package test.kafka.kafkaclient;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

@Component
@PropertySource(value="application.yaml")
public class MessageProducer {

    private KafkaTemplate<Long, String> template;

    @Value("${inbound.topic}")
    private String topic;

    public MessageProducer(KafkaTemplate<Long, String> template) {
        this.template = template;
    }

    public void produce(String text){
        System.out.println("\n\t *** topic name = " + this.topic + " *** \n");
        try {
            ListenableFuture listenableFuture = template.send(this.topic, text);
            listenableFuture.addCallback(new SuccessCallback() {
                @Override
                public void onSuccess(Object result) {
                    System.out.println("\n\t *** Success *** \n");
                }
            }, new FailureCallback() {
                @Override
                public void onFailure(Throwable ex) {
                    System.err.println("\n\t *** Failed *** \n");
                }
            });
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
