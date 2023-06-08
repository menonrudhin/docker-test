package test.kafka.kafkaclient;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

@Component
public class MessageProducer {

    private KafkaTemplate<Long, String> template;

    public MessageProducer(KafkaTemplate<Long, String> template) {
        this.template = template;
    }

    public void produce(String text){
        try {
            ListenableFuture listenableFuture = template.send("topic-1", text);
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
