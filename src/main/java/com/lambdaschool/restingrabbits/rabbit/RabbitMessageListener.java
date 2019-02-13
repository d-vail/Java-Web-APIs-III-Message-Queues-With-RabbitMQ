package com.lambdaschool.restingrabbits.rabbit;

import com.lambdaschool.restingrabbits.RestingrabbitsApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMessageListener {
  @RabbitListener(queues = RestingrabbitsApplication.QUEUE_NAME_HIGH)
  public void receiveMessage(RabbitMessage msg) {
    log.info("Received Message: {} ", msg.toString());
  }
}
