package com.viki3d.spring.rest.api.documenting.logic.services;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Project utilities here. 
 */
public class Utils {

  /**
   * Converts POJO to JSON. 
   */
  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
