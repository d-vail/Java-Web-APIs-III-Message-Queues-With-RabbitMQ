package com.lambdaschool.restingrabbits.rabbit;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Rabbit {
  private @Id @GeneratedValue Long id;
  private String name;
  private double weight;

  // Default Constructor
  public Rabbit() { }

  public Rabbit(String name, double weight) {
    this.name = name;
    this.weight = weight;
  }
}
