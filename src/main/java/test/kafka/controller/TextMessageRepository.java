package test.kafka.controller;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
interface TextMessageRepository extends JpaRepository<TextMessage, Long> {

}
