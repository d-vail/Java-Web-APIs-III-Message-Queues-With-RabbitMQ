package com.lambdaschool.restingrabbits.rabbit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RabbitRepository extends JpaRepository<Rabbit, Long> {
}
