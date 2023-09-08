package com.jackson.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class JsonParserTest {

  @Test
  void given_string_when_convert_to_instance_then_success() throws JsonProcessingException {
    String json = "{\"brand\":\"ford\", \"doors\":5}";
    ObjectMapper objectMapper = new ObjectMapper();
    Car car = objectMapper.readValue(json, Car.class);

    assertEquals("ford", car.getBrand());
    assertEquals(5, car.getDoors());
  }

  @Test
  void given_string_when_convert_to_instances_then_success() throws JsonProcessingException {
    String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
    ObjectMapper objectMapper = new ObjectMapper();
    Car[] cars = objectMapper.readValue(jsonArray, Car[].class);

    assertEquals("ford", cars[0].getBrand());
    assertEquals("Fiat", cars[1].getBrand());
  }

  @Test
  void given_string_when_convert_to_instance_by_java_type_then_success()
      throws JsonProcessingException {
    String json = "{\"brand\":\"ford\", \"doors\":5}";
    ObjectMapper objectMapper = new ObjectMapper();
    JavaType javaType = objectMapper.getTypeFactory().constructType(Car.class);
    Car car = objectMapper.readValue(json, javaType);

    assertEquals("ford", car.getBrand());
    assertEquals(5, car.getDoors());
  }

  @Test
  void given_string_when_convert_to_instances_by_java_type_then_success()
      throws JsonProcessingException {
    String jsonArray = "[{\"chars\":\"abcdef\"}, {\"chars\":\"Leet Code\"}]";
    ObjectMapper objectMapper = new ObjectMapper();
    Car[] cars = objectMapper.readValue(jsonArray, Car[].class);

    assertEquals(Arrays.toString(new char[]{'a', 'b', 'c', 'd', 'e', 'f'}),
        Arrays.toString(cars[0].getChars()));
    assertEquals(Arrays.toString("Leet Code".toCharArray()), Arrays.toString(cars[1].getChars()));
  }
}
