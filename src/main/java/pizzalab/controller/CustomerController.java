package pizzalab.controller;

import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
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
  public List<CustomerOutputDTO> getCustomers() {
    List<Customer> customersWithPrivateStuff = customerService.findAll();
    List<CustomerOutputDTO> customersDto = customersWithPrivateStuff.stream()
        .map(customer -> modelMapper.map(customer, CustomerOutputDTO.class))
        .collect(Collectors.toList());
    return customersDto;
  }

  @PostMapping("customers")
  public void createCustomer(@RequestBody Customer customer) {
    // TODO validation
    customerService.addCustomer(customer);
  }

  @GetMapping("customers/{customerId}")
  public CustomerOutputDTO getCustomer(@PathVariable String customerId) {
    return modelMapper.map(customerService.findById(customerId), CustomerOutputDTO.class);
  }

}
