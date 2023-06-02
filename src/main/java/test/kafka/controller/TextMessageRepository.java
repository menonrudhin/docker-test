package test.kafka.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextMessageRepository extends CrudRepository<TextMessage, Long> {

}
