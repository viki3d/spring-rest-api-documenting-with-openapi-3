package com.viki3d.spring.rest.api.documenting.logic.services.mock;

import com.viki3d.spring.rest.api.documenting.front.api.CarsApi;
import com.viki3d.spring.rest.api.documenting.front.api.model.Car;
import com.viki3d.spring.rest.api.documenting.logic.services.exceptions.CarNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Servicing the {@code CarsAPI} by simulating mock database.
 */
@Service
public class CarsMockService implements CarsApi {

  private static List<Car> mockDb;
  
  static {
    mockDb = new ArrayList<Car>();
    mockDb.add(new Car(1L, "Mazda", "6", "red"));
    mockDb.add(new Car(2L, "Mazda", "3", "green"));
    mockDb.add(new Car(3L, "Infinity", "Q50", "gray"));
    mockDb.add(new Car(4L, "Infinity", "Q60", "black"));
  }
  
  private static long newId = 5L;
  
  private static long generateNewId() {
    return newId++;
  }

  @Override
  public List<Car> list() {
    return mockDb;
  }

  @Override
  public Optional<Car> get(long id) {
    return mockDb.stream().filter(car -> car.getId() == id).findFirst();
  }

  @Override
  public long add(Car car) {
    long newId = generateNewId();
    car.setId(newId);
    mockDb.add(car);
    return newId;
  }
  
  @Override
  public void delete(long id) {
    Car carToDelete = mockDb.stream().filter(c -> c.getId() == id).findFirst()
        .orElseThrow(CarNotFoundException::new);
    mockDb.remove(carToDelete);
  }
  
  @Override
  public void update(Car car) {
    Car carToUpdate = mockDb.stream()
        .filter(c -> c.getId() == car.getId()).findFirst().orElseThrow(CarNotFoundException::new);
    carToUpdate.setBrand(car.getBrand());
    carToUpdate.setModel(car.getModel());
    carToUpdate.setColor(car.getColor());
  }
  
  @Override
  public void replace(long id, Car car) {
    Car carToReplace = mockDb.stream()
        .filter(c -> c.getId() == car.getId()).findFirst().orElseThrow(CarNotFoundException::new);
    mockDb.remove(carToReplace);
    car.setId(id);
    mockDb.add(car);
  }
  
  public int countAll() {
    return mockDb.size();
  }
  
}
