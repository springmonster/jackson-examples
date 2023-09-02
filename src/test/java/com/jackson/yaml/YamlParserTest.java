package com.jackson.yaml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.jupiter.api.Test;

class YamlParserTest {

  @Test
  void given_yaml_when_read_then_compare() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    objectMapper.findAndRegisterModules();

    InputStream resourceAsStream = this.getClass().getClassLoader()
        .getResourceAsStream("yaml/orderInput.yaml");
    Order order = objectMapper.readValue(new InputStreamReader(resourceAsStream), Order.class);

    assertEquals(
        "Order{orderNo='A001', date=2019-04-17, customerName='Customer, Joe', orderLines=[OrderLine{item='No. 9 Sprockets', quantity=12, unitPrice=1.23}, OrderLine{item='Widget (10mm)', quantity=4, unitPrice=3.45}]}",
        order.toString());
  }
}
