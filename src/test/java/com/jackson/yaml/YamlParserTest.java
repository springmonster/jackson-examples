package com.jackson.yaml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

  @Test
  void given_object_when_write_then_compare() {
    Order order = new Order("A001", LocalDate.parse("2019-04-18", DateTimeFormatter.ISO_DATE),
        "Customer, Joe", null);

    OrderLine orderLine1 = new OrderLine("No. 9 Sprockets", 12,
        new BigDecimal("50.67").setScale(2, RoundingMode.HALF_UP));
    OrderLine orderLine2 = new OrderLine("Widget (10mm)", 4,
        new BigDecimal(".15").setScale(2, RoundingMode.HALF_UP));

    order.setOrderLines(List.of(orderLine1, orderLine2));

    ObjectMapper objectMapper = new ObjectMapper(
        new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER));
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.findAndRegisterModules();

    String yaml = null;
    try {
      yaml = objectMapper.writeValueAsString(order);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals(
        """
            orderNo: "A001"
            date: "2019-04-18"
            customerName: "Customer, Joe"
            orderLines:
            - item: "No. 9 Sprockets"
              quantity: 12
              unitPrice: 50.67
            - item: "Widget (10mm)"
              quantity: 4
              unitPrice: 0.15
            """,
        yaml);
  }
}
