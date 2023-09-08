package com.jackson.json;

public class Car {

  private String brand = null;
  private int doors = 0;

  private char[] chars;

  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public int getDoors() {
    return this.doors;
  }

  public void setDoors(int doors) {
    this.doors = doors;
  }

  public char[] getChars() {
    return this.chars;
  }

  public void setChars(char[] chars) {
    this.chars = chars;
  }
}
