package com.lambdaschool.restingrabbits.rabbit;

import com.lambdaschool.restingrabbits.RestingrabbitsApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
//@Service
public class RabbitMessageSender {
  private final RabbitTemplate RT;
  private final RabbitRepository RABBIT_REPO;

  public RabbitMessageSender(RabbitTemplate rt, RabbitRepository rabbitRepo) {
    this.RT = rt;
    this.RABBIT_REPO = rabbitRepo;
  }

  @Scheduled(fixedDelay = 3000L)
  public void sendMessage() {
    ArrayList<Rabbit> rabbits = new ArrayList<>();
    rabbits.addAll(RABBIT_REPO.findAll());

    for(Rabbit r : rabbits) {
      int rand = new Random().nextInt(10);       // setup a random priority
      boolean randBool = new Random().nextBoolean();    // setup a random secret boolean
      final RabbitMessage MSG = new RabbitMessage(r.toString(), rand, randBool);

      // Put high and low priority messages in their own queues
      if(rand <= 5) {
        log.info("Sending message HIGH");
        RT.convertAndSend(RestingrabbitsApplication.QUEUE_NAME_HIGH, MSG);
      } else {
        log.info("Sending message LOW");
        RT.convertAndSend(RestingrabbitsApplication.QUEUE_NAME_LOW, MSG);
      }
    }
  }
}
