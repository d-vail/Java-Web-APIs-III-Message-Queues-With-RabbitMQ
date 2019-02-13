package com.lambdaschool.restingrabbits.rabbit;

import com.lambdaschool.restingrabbits.RestingrabbitsApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/rabbits")
public class RabbitController {
  private final RabbitRepository RABBIT_REPO;
  private final RabbitTemplate RT;

  public RabbitController(RabbitRepository rabbitRepo, RabbitTemplate rt) {
    this.RABBIT_REPO = rabbitRepo;
    this.RT = rt;
  }

  @GetMapping()
  public void findSome() {
    ArrayList<Rabbit> rabbits = new ArrayList<>(RABBIT_REPO.findAll());

    for (Rabbit r : rabbits) {
      int rand = new Random().nextInt(10);
      boolean randBool = new Random().nextBoolean();
      final RabbitMessage MSG = new RabbitMessage(r.toString(), rand, randBool);

      log.info("Sending Message...");
      if(rand <= 5) {
        RT.convertAndSend(RestingrabbitsApplication.QUEUE_NAME_HIGH, MSG);
      } else {
        RT.convertAndSend(RestingrabbitsApplication.QUEUE_NAME_LOW, MSG);
      }
    }
  }
}
