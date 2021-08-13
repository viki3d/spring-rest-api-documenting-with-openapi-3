package com.viki3d.spring.rest.api.documenting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * The default SpringBoot configuration class.
 *
 * <p><a href="http://localhost:8080/api/v1/cars">test link - CarsAPI</a></p>
 * <p><a href="http://localhost:8080/v3/api-docs">test link - OpenAPI</a></p>
 * <p><a href="http://localhost:8080/swagger-ui.html">test link - Swagger UI</a></p>
 *
 * @author Victor Kirov
 */
@SpringBootApplication
public class Main {

  /**
   * The entry point of this SpringBoot application.
   *
   * @param args The command line parameters passed to this application. Currently no such 
   *     parameters are supported by this application.
   */
  public static void main(String[] args) {

    // Get the Spring Context
    ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

    // Ensures a graceful shutdown and calls the relevant destroy methods on your singleton 
    // beans so that all resources are released.
    context.registerShutdownHook();

  }

}
