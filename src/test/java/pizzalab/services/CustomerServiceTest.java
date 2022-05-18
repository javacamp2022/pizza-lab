package pizzalab.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import pizzalab.entity.Customer;
import pizzalab.exception.CustomerNotFoundException;
import pizzalab.repository.CustomerRepository;
import pizzalab.repository.ProductRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private CustomerService underTest;

  @Captor
  private ArgumentCaptor<Customer> customerCaptor;

  @Test
  public void test_addCustomer_alreadyExists() {
    // given
    Customer customer = Customer.builder().name("Sergiu").build();

    Mockito.when(customerRepository.findFirstByName("Sergiu")).thenReturn(customer);

    // when
    Customer result = underTest.addCustomer(customer);

    // then
    assertNull("result should be null", result);
    verify(customerRepository, times(1)).findFirstByName("Sergiu");
    verifyNoMoreInteractions(customerRepository);
    verifyNoInteractions(productRepository);
  }

  @Test
  public void test_addCustomer_success() {
    // given
    Customer customer = Customer.builder().name("Sergiu").build();
    Mockito.when(customerRepository.findFirstByName("Sergiu")).thenReturn(null);
    Mockito.when(customerRepository.save(eq(customer))).thenReturn(customer);

    // when
    Customer result = underTest.addCustomer(customer);

    // then
    assertNotNull("result should not be null", result);
    assertEquals("wrong result", customer, result);
    verify(customerRepository).findFirstByName("Sergiu");
    verify(customerRepository).save(customerCaptor.capture());
    verifyNoMoreInteractions(customerRepository);
    verifyNoInteractions(productRepository);

    assertEquals("wrong captured value", customer, customerCaptor.getValue());
  }

  @Test
  public void test_findAll_success() {
    // given
    when(customerRepository.findAll()).thenReturn(new LinkedList<>());

    // when
    List<Customer> result = underTest.findAll();

    // then
    assertNotNull("result should not be null", result);
    assertTrue("wrong list size", result.isEmpty());
    verify(customerRepository).findAll();
    verifyNoMoreInteractions(customerRepository);
    verifyNoInteractions(productRepository);
  }

  @Test //(expected = CustomerNotFoundException.class)
  public void test_findById_customerNotFound() {
    // given
    Long customerId = 1L;
    when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

    try {
      // when
      underTest.findById(customerId);
      fail("should have failed");
    } catch (CustomerNotFoundException e) {
      // then
      assertEquals("wrong exception message", "Customer not found", e.getMessage());
    }
  }
}
