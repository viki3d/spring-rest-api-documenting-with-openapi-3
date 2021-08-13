package com.viki3d.spring.rest.api.documenting.front.api;

import com.viki3d.spring.rest.api.documenting.front.api.model.Car;
import java.util.List;
import java.util.Optional;

/**
 * All car operations defined here.
 *
 * @author Victor Kirov
 * @version 1 
 */
public interface CarsApi {

  public static final String NEW_CAR_ID_KEY = "newCarId";

  List<Car> list();
  
  Optional<Car> get(long id);

  long add(Car car);

  void delete(long id);

  void update(Car car);

  void replace(long id, Car car);
  
}
