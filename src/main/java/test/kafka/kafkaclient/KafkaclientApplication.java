package test.kafka.kafkaclient;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
public class KafkaclientApplication {

	public static void main(String[] args) {
		System.out.println("Inside Main"); // 192.168.0.3:29092
		System.out.println("Kafka broker is : " + System.getProperty("bootstrap.servers")); 
		SpringApplication.run(KafkaclientApplication.class, args);
		System.out.println("Exit NewTopic");
	}

	@Bean
	public NewTopic topic() {
		System.out.println("Inside NewTopic");
		return TopicBuilder.name("topic1")
				.partitions(10)
				.replicas(1)
				.build();
	}


}
