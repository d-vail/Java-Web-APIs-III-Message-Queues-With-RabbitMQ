package com.lambdaschool.restingrabbits;

import com.lambdaschool.restingrabbits.rabbit.Rabbit;
import com.lambdaschool.restingrabbits.rabbit.RabbitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SeedDatabase {
  @Bean
  public CommandLineRunner initDatabase(RabbitRepository rabbitRepo) {
    return args -> {
      log.info("Seeding " + rabbitRepo.save(new Rabbit("Barn Barn", 5.1)));
      log.info("Seeding " + rabbitRepo.save(new Rabbit("Cinnamon", 4.3)));
      log.info("Seeding " + rabbitRepo.save(new Rabbit("Jessica", 4.7)));
    };
  }
}
