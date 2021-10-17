package br.com.example.springbootdemo.service;

import br.com.example.springbootdemo.entity.dto.BookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BookMessageReceiver {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    BookService service;

    @Bean
    public CachingConnectionFactory rabbitConnectionFactory() throws Exception {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.getRabbitConnectionFactory().setUri("amqps://gljpxxmk:YQA6ACRYs3kLSist2O0n2tWm1eubswtN@clam.rmq.cloudamqp.com/gljpxxmk");
        return connectionFactory;
    }

    @RabbitListener(queues = {"${queue.order.name}"})
    public void consume(Message message) throws IOException {
        //o try pega a exception custom do json validador
        try {
            //1 - convertendo a msg para json
            String json = new String(message.getBody());
            //2 - validando a msg
            JsonValidatorService.validateBookDTO(json);
            //3 - convertendo o json para dto
            BookDTO dto = objectMapper.readValue(json, BookDTO.class);
            //4 - salvando o dto no banco
            service.save(dto);
        } catch (ParseBookDTOException e){
            System.out.println("erro na msg: " + e.getMessage());
        }
    }
}
