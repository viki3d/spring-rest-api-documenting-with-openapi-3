package com.viki3d.spring.rest.api.documenting.front.controllers.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.viki3d.spring.rest.api.documenting.front.api.CarsApi;
import com.viki3d.spring.rest.api.documenting.front.api.model.Car;
import com.viki3d.spring.rest.api.documenting.logic.services.Utils;
import org.json.JSONArray;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Testing CarsRestController. 
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarsRestControllerTests {

  @Autowired
  private MockMvc mockMvc;

  // Since this is not a static field, it is not named like BASE_PATH
  private final String basePath = "/api/v1/cars/";

  @Test
  @Order(1)
  void smokeTestServiceIsOk() throws Exception {
    this.mockMvc.perform(get(basePath))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @Order(2)
  void testGetCars() throws Exception {
    MvcResult result = this.mockMvc.perform(get(basePath))
        .andExpect(status().isOk())
        .andReturn();
    String jsonResponse = result.getResponse().getContentAsString();
    JSONArray jsonArray = new JSONArray(jsonResponse);
    assertEquals(jsonArray.length(), 4);
  }

  @Test
  @Order(3)
  void testGetCar1() throws Exception {
    this.mockMvc.perform(get(basePath + "/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.brand", is("Mazda")))
        .andExpect(jsonPath("$.model", is("6")))
        .andExpect(jsonPath("$.color", is("red")));
  }

  @Test
  @Order(3) //yes, 3 again
  void testGetCar2() throws Exception {
    this.mockMvc.perform(get(basePath + "/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(2)))
        .andExpect(jsonPath("$.brand", is("Mazda")))
        .andExpect(jsonPath("$.model", is("3")))
        .andExpect(jsonPath("$.color", is("green")));
  }

  @Test
  @Order(4)
  void testAdd() throws Exception {
    Car car = new Car(0L, "Ferrari", "Testarossa", "red");
    String reqJson = Utils.asJsonString(car);
    this.mockMvc.perform(post(basePath)
            .contentType(MediaType.APPLICATION_JSON)
            .content(reqJson))
        //.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$." + CarsApi.NEW_CAR_ID_KEY, is(5)));
  }

  @Test
  @Order(5)
  void testDeleteExisting() throws Exception {
    long carIdToDelete = 1L;
    this.mockMvc.perform(delete(basePath + carIdToDelete).contentType(MediaType.APPLICATION_JSON))
        //.andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @Order(6)
  void testDeleteUnexising() throws Exception {
    long carIdToDelete = 9L;
    this.mockMvc.perform(delete(basePath + carIdToDelete).contentType(MediaType.APPLICATION_JSON))
        //.andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  @Order(7)
  void testUpdate() throws Exception {
    long carIdToUpdate = 2L;
    Car car = new Car(carIdToUpdate, "Mercedes", "c220", "silver");
    String reqJson = Utils.asJsonString(car);
    this.mockMvc.perform(patch(basePath + carIdToUpdate)
        .contentType(MediaType.APPLICATION_JSON)
        .content(reqJson))
        //.andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @Order(8)
  void testReplace() throws Exception {
    Long carIdToReplace = 2L;
    Car newCar = new Car(carIdToReplace, "Ferrari", "Testarossa", "red");
    String reqJson = Utils.asJsonString(newCar);
    this.mockMvc.perform(put(basePath + carIdToReplace)
        .contentType(MediaType.APPLICATION_JSON)
        .content(reqJson))
        .andDo(print())
        .andExpect(status().isOk());
  }


}
