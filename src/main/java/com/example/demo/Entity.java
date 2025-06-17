package com.example.demo;

public class Entity {
  private int id;
  private String label;

  public Entity(int id, String label) {
    this.id = id;
    this.label = label;
  }

  public int getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }
}