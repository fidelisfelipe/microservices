package com.example.microservices.currencyconversionservice.msg;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.support.ActiveObjectCounter;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
public class MessageReceiver {

    private String msg;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
        msg = message;
        latch.countDown();
    }

    public CountDownLatch getLatch() throws InterruptedException {
        return latch;
    }

    public void setTimeout(int timeout)  {
        try {
            latch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error("Error: {}", e.getMessage());
        }
    }

    public String getMsg() {
        return msg;
    }
}
