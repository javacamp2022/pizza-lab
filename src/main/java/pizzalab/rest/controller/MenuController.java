package pizzalab.rest.controller;

import static pizzalab.Main.pantryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pizzalab.domain.Menu;

@RestController
class MenuController {
  @GetMapping("/menu")
  public Menu getMenu() {
    return pantryService.listMenu();
  }
}

