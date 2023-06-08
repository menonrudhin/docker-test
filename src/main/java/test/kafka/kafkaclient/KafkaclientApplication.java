package test.kafka.kafkaclient;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.internals.Sender;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {"test.kafka.controller", "test.kafka.kafkaclient"})
@EnableRedisRepositories(basePackages = {"test.kafka.controller"})
@EnableKafka
@PropertySource(value="classpath:application.yaml")
public class KafkaclientApplication {

	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaBroker;

	public static void main(String[] args) {
		System.out.println("Inside Main"); // 192.168.0.3:29092
		SpringApplication.run(KafkaclientApplication.class, args);
		System.out.println("Exit Main");
	}

	@Bean
	public NewTopic topic() {
		System.out.println("Inside NewTopic");
		return TopicBuilder.name("topic1")
				.partitions(10)
				.replicas(1)
				.build();
	}

	@Bean
	public MessageProducer sender(KafkaTemplate<Long, String> template) {
		return new MessageProducer(template);
	}

	@Bean
	public ProducerFactory<Long, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(senderProps());
	}

	private Map<String, Object> senderProps() {
		System.out.println("\n\t ***** kafka broker " + kafkaBroker + " *** \n");
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker); // from docker
		props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		return props;
	}

	@Bean
	public KafkaTemplate<Long, String> kafkaTemplate(ProducerFactory<Long, String> producerFactory) {
		return new KafkaTemplate<Long, String>(producerFactory);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("redis_stack_server", 6379);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
