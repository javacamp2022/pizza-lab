package pizzalab.rest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pizzalab.DeliveryService;
import pizzalab.domain.Customer;
import pizzalab.rest.dto.CustomerOutputDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

  private final DeliveryService deliveryService;
  private final ModelMapper modelMapper;

  @GetMapping("customers")
  public List<CustomerOutputDTO> getCustomers() {
    List<Customer> customersWithPrivateStuff = deliveryService.findAll();
    List<CustomerOutputDTO> customersDto = customersWithPrivateStuff.stream()
        .map(customer -> modelMapper.map(customer, CustomerOutputDTO.class))
        .collect(Collectors.toList());

    log.debug("hey, our first log -> current customers={}", customersDto);
    return customersDto;
  }

  @PostMapping("customers")
  public void createCustomer(@RequestBody Customer customer) {
    // TODO validation
    deliveryService.addCustomer(customer);
  }

  @GetMapping("customers/{customerId}")
  public CustomerOutputDTO getCustomer(@PathVariable String customerId) {
    return modelMapper.map(deliveryService.findById(customerId), CustomerOutputDTO.class);
  }

}
