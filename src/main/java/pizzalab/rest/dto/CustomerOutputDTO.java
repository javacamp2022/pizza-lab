package pizzalab.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerOutputDTO {
  @JsonProperty
  private String name;
}
