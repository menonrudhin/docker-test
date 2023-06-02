package test.kafka.controller;

import org.springframework.data.redis.core.RedisHash;
import org.w3c.dom.Text;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@RedisHash("TextMessage")
public class TextMessage implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    private String userName;

    private Timestamp timestamp;

    public TextMessage(){
        // capture the current timestamp
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof TextMessage))
            return false;
        TextMessage TextMessage = (TextMessage) o;
        return Objects.equals(this.id, TextMessage.id) && Objects.equals(this.userName, TextMessage.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.userName);
    }

    @Override
    public String toString() {
        return "TextMessage{" + "id=" + this.id + ", userName='" + this.userName + '\'' + ", text='" + this.text + '\'' + '}';
    }
}
