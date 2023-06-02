package test.kafka.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TextMessageRepository extends CrudRepository<TextMessage, Long> {

}
