package com.viki3d.spring.rest.api.documenting.logic.services.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.viki3d.spring.rest.api.documenting.front.api.model.Car;
import com.viki3d.spring.rest.api.documenting.logic.services.exceptions.CarNotFoundException;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Testing CarsMockService.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarsMockServiceTests {

  @Autowired
  private CarsMockService carsMockService;

  @Test
  @Order(1)
  void smokeTestContextLoaded() {
    assertThat(carsMockService).isNotNull();
  }

  @Test
  @Order(2)
  void testList() {
    List<Car> allCars = carsMockService.list();
    assertTrue(allCars.size() == 4);
  }

  @Test
  @Order(3)
  void testGet() {
    Car car = carsMockService.get(1L).get();
    assertTrue(car.getBrand().equals("Mazda"));
    assertTrue(car.getModel().equals("6"));
    assertTrue(car.getColor().equals("red"));
  }

  @Test
  @Order(4)
  void testAdd() {
    Car car = new Car(0L, "Ferrari", "Testarossa", "red");
    long newId = carsMockService.add(car);
    assertTrue(carsMockService.countAll() == 5);
    
    Car lastCar = carsMockService.get(newId).get();
    assertTrue(car == lastCar); //check references are pointing the same object
  }

  @Test
  @Order(5)
  void testDeleteExisting() {
    carsMockService.delete(1L);
    List<Car> allCars = carsMockService.list();
    assertTrue(allCars.size() == 4);
  }

  @Test
  @Order(6)
  void testDeleteUnexising() {
    assertThrows(CarNotFoundException.class, () -> {
      carsMockService.delete(9L); });
  }

  @Test
  @Order(7)
  void testUpdate() {
    long idToUpdate = 2L;
    Car car = new Car(idToUpdate, "Mercedes", "c220", "silver");
    carsMockService.update(car);
    Car updatedCar = carsMockService.get(idToUpdate).get();
    assertTrue(updatedCar != car);
    assertTrue(updatedCar.getId() == car.getId()
        && updatedCar.getBrand().equals(car.getBrand())
        && updatedCar.getModel().equals(car.getModel())
        && updatedCar.getColor().equals(car.getColor())
    );
  }
  
  @Test
  @Order(8)
  void testReplace() {
    long idToReplace = 2L;
    Car newCar = new Car(idToReplace, "Ferrari", "Testarossa", "red");
    Car oldCar = carsMockService.get(idToReplace).get();
    carsMockService.replace(idToReplace, newCar);

    assertTrue(newCar != oldCar); //check references are NOT pointing the same object
  }
  
}
