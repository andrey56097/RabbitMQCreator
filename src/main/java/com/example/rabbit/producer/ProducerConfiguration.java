package com.example.rabbit.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfiguration {

    private String queueName;
    private String routingKey;
    private RabbitTemplate rabbitTemplate;

    public ProducerConfiguration() {

    }

    public ProducerConfiguration(String queueName, String routingKey) {
        this.queueName = queueName;
        this.routingKey = routingKey;
        this.rabbitTemplate = rabbitTemplate();
        RabbitAdmin admin = new RabbitAdmin(this.rabbitTemplate.getConnectionFactory());
        admin.declareQueue(new Queue(this.queueName));
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(this.routingKey);
        template.setDefaultReceiveQueue(this.queueName);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    public void send(String s) {
        this.rabbitTemplate.convertAndSend(s);
    }
}
