package pizzalab.integration;


import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import pizzalab.controller.CustomerController;
import pizzalab.dto.CustomerOutputDTO;
import pizzalab.entity.Customer;

import java.util.List;

@SpringBootTest
public class CustomerIT {

  @Autowired
  private CustomerController customerController;

  @Test
  public void test_customersComponent() {
    // list all customers and check that there is no customer added
    ResponseEntity<?> getCustomersResponse = customerController.getCustomers();

    assertNotNull("response should not be null", getCustomersResponse);
    assertEquals("wrong http status code", SC_OK, getCustomersResponse.getStatusCode().value());
    assertNotNull("response body should not be null", getCustomersResponse.getBody());

    assertTrue("wrong response body type", getCustomersResponse.getBody() instanceof List);
    List<CustomerOutputDTO> result = (List<CustomerOutputDTO>) getCustomersResponse.getBody();
    assertTrue("customers list should be empty", result.isEmpty());

    // add new customer
    Customer ion = Customer.builder().name("Ion").phone("0790909090").build();
    ResponseEntity<?> addCustomerResponse = customerController.createCustomer(ion);

    assertNotNull("response should not be null", addCustomerResponse);
    assertEquals("wrong http status code", SC_OK, addCustomerResponse.getStatusCode().value());
    assertTrue("wrong response body type", addCustomerResponse.getBody() instanceof Customer);

    Customer added = (Customer) addCustomerResponse.getBody();
    assertEquals("wrong customer name", "Ion", added.getName());
    assertEquals("wrong customer phone", "0790909090", added.getPhone());

    // get the customer
    Long ionId = added.getId();
    ResponseEntity<?> getCustomerResponse = customerController.getCustomer(ionId);
    assertEquals("wrong http status code", SC_OK, getCustomerResponse.getStatusCode().value());
    assertTrue("wrong response body type", getCustomerResponse.getBody() instanceof CustomerOutputDTO);

    CustomerOutputDTO getIon = (CustomerOutputDTO) getCustomerResponse.getBody();
    assertEquals("wrong customer name", "Ion", getIon.getName());

    // add the same customer and check that 400 is returned
    addCustomerResponse = customerController.createCustomer(ion);
    assertEquals("wrong http status code", SC_BAD_REQUEST, addCustomerResponse.getStatusCode().value());
    assertEquals("wrong response body", "Customer already exists!", addCustomerResponse.getBody());
  }
}
