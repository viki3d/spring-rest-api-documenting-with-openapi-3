package com.viki3d.spring.rest.api.documenting.front.controllers.rest;

import com.viki3d.spring.rest.api.documenting.front.api.CarsApi;
import com.viki3d.spring.rest.api.documenting.front.api.model.Car;
import com.viki3d.spring.rest.api.documenting.logic.services.exceptions.CarNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Serving the {@code CarsApi}.
 */
@RestController
@RequestMapping("/api/v1/cars")
public class CarsRestController {

  @Autowired
  private CarsApi carsMockService;  

  @Operation(summary = "Get all cars")
  @GetMapping(path = "", produces = "application/json")
  public List<Car> list() {
    return carsMockService.list();
  }

  @Operation(summary = "Get a car by id")
  @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", 
          description = "Success - the car was found",
          content = { @Content(mediaType = "application/json", 
              schema = @Schema(implementation = Car.class)) 
          }
      ),
      @ApiResponse(responseCode = "400",
          description = "Invalid id supplied", 
          content = @Content
      ), 
      @ApiResponse(responseCode = "404", 
          description = "Car not found", 
          content = @Content) 
  })
  @GetMapping(path = "/{id}", produces = "application/json")
  public Car get(@PathVariable final long id) {
    return carsMockService.get(id).orElseThrow(() -> new CarNotFoundException());
  }

  /**
   * Adds new car to database.
   *
   * @param car The car from this request will be de-serialized from JSON and then add to database.
   * @return The new id of the inserted car.
   */
  @Operation(summary = "Add new car to database")
  @PostMapping(path = "", consumes = "application/json")
  public String add(@NotNull @Valid @RequestBody final Car car) throws Exception {
    long newCarId = carsMockService.add(car);
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(CarsApi.NEW_CAR_ID_KEY, newCarId);
    return jsonObject.toString();
  }

  @Operation(summary = "Delete car by id")
  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable final long id) {
    carsMockService.delete(id);
  }

  @Operation(summary = "Update car by id")
  @PatchMapping(path = "/{id}", consumes = "application/json")
  public void update(@RequestBody final Car car) {
    carsMockService.update(car);
  }
  
  @Operation(summary = "Replace car by id")
  @PutMapping(path = "/{id}", consumes = "application/json")
  public void replace(@PathVariable final long id, @RequestBody final Car car) {
    carsMockService.replace(id, car);
  }
  
}
