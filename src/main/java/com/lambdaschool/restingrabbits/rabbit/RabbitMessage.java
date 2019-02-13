package com.lambdaschool.restingrabbits.rabbit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RabbitMessage implements Serializable {
  private final String TEXT;
  private final int PRIORITY;
  private final boolean SECRET;

  public RabbitMessage(@JsonProperty("text") String text,
                       @JsonProperty("priority") int priority,
                       @JsonProperty("secret") boolean secret) {
    this.TEXT = text;
    this.PRIORITY = priority;
    this.SECRET = secret;
  }
}
