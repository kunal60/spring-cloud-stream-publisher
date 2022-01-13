package com.kunal.cloud.stream.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableBinding(Source.class)
// Adding this annotation EnableBinding tells spring cloud stream that this will be my publisher, which will be source
@RestController
public class SpringCloudStreamPublisherApplication {

    //To send the ,essage over the network, we need one message channel.
    @Autowired
    private MessageChannel output;

    @PostMapping("/publish")
    public Book publishEvent(@RequestBody Book book) {
        // This publishEvent will publish the message over the message channel.As we are using kafka here,
        //we need to create one more topic because kafka consumer and publisher can talk oer the topic.We will create it later
        output.send(MessageBuilder.withPayload(book).build());
        return book;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamPublisherApplication.class, args);
    }

}
