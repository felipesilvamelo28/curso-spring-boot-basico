package br.com.example.springbootdemo.service;

import br.com.example.springbootdemo.entity.dto.BookDTO;
import br.com.example.springbootdemo.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class BookMessageReceiver {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BookService bookService;

    @Bean
    public CachingConnectionFactory rabbitConnectionFactory(RabbitProperties config)
            throws Exception {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.getRabbitConnectionFactory().setUri("amqps://gljpxxmk:YQA6ACRYs3kLSist2O0n2tWm1eubswtN@clam.rmq.cloudamqp.com/gljpxxmk");
        return connectionFactory;
    }
    @RabbitListener(queues = {"${queue.order.name}"})
    public void consume(Message message) throws IOException {
        System.out.println(Arrays.toString(message.getBody()));
    }
}
