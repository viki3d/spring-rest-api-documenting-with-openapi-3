package com.viki3d.spring.rest.api.documenting.front.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Representing car instances.
 *
 * @author User
 */
public class Car {

  private long id;
  @NotBlank
  @Size(min = 2, max = 20)  
  private String brand;
  @NotBlank
  @Size(min = 2, max = 20)  
  private String model;
  @NotBlank
  @Size(min = 3, max = 20)  
  private String color;
  
  public Car() {

  }
  
  /**
   * Constructs a specific car.
   *
   * @param brand Car's brand.
   * @param model Car's model.
   * @param color Car's color.
   */
  public Car(long id, String brand, String model, String color) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.color = color;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
  
  @Override
  public String toString() {
    return "car:["
        + "id = " + id
        + ", brand = " + brand
        + ", model = " + model
        + ", color = " + color
        + "]";
  }

}
