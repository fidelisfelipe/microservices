package com.example.microservices.currencyconversionservice.msg;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Log4j2
@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageReceiver messageReceiver;

    public void sendMessage(String msg) {
        log.error("Sending message: {}", msg);

        rabbitTemplate.convertAndSend(MessageManager.topicExchangeName, "foo.bar.baz", msg);
        messageReceiver.setTimeout(10000);

    }


}
