package pizzalab.controller;

import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pizzalab.services.CustomerService;
import pizzalab.entity.Customer;
import pizzalab.dto.CustomerOutputDTO;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class CustomerController {

  private final CustomerService customerService;
  private final ModelMapper modelMapper;

  public CustomerController(CustomerService deliveryService, ModelMapper modelMapper) {
    this.customerService = deliveryService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("customers")
  public ResponseEntity<?> getCustomers() {
    List<Customer> customersWithPrivateStuff = customerService.findAll();
    List<CustomerOutputDTO> customersDto = customersWithPrivateStuff.stream()
        .map(customer -> modelMapper.map(customer, CustomerOutputDTO.class))
        .collect(Collectors.toList());
    return ResponseEntity.ok(customersDto);
  }

  @PostMapping("customers")
  public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
    // TODO validation
    Customer added = customerService.addCustomer(customer);
    return added != null ? ResponseEntity.ok(added) : ResponseEntity.badRequest().body("Customer already exists!");
  }

  @GetMapping("customers/{customerId}")
  public ResponseEntity<?> getCustomer(@PathVariable Long customerId) {
    return ResponseEntity.ok(modelMapper.map(customerService.findById(customerId), CustomerOutputDTO.class));
  }

}
