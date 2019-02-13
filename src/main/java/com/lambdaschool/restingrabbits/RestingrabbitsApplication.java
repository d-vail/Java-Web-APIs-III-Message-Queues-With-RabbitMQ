package com.lambdaschool.restingrabbits;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestingrabbitsApplication {
  public static final String EXCHANGE_NAME = "LambdaServer";    // Message Server
  public static final String QUEUE_NAME_LOW = "LowPriority";    // Low Priority Queue within server
  public static final String QUEUE_NAME_HIGH = "HighPriority";  // High Priority Queue within server

  public static void main(String[] args) {
    SpringApplication.run(RestingrabbitsApplication.class, args);
  }

  // Instantiate Message Server
  @Bean
  public TopicExchange appExchange() {
    return new TopicExchange(EXCHANGE_NAME);
  }

  // Instantiate Low Priority Queue
  @Bean
  public Queue appQueueLow() {
    return new Queue(QUEUE_NAME_LOW);
  }

  // Instantiate High Priority Queue
  @Bean
  public Queue appQueueHigh() {
    return new Queue(QUEUE_NAME_HIGH);
  }

  // Bind Low Priority Queue to Message Server
  @Bean
  public Binding declareBindingLow() {
    return BindingBuilder.bind(appQueueLow()).to(appExchange()).with(QUEUE_NAME_LOW);
  }

  // Bind High Priority Queue to Message Server
  @Bean
  public Binding declareBindingHigh() {
    return BindingBuilder.bind(appQueueHigh()).to(appExchange()).with(QUEUE_NAME_HIGH);
  }

  // Instantiate a Jackson2JsonMessageConverter so that Spring can use it
  @Bean
  public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}

