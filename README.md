# Spring, RESTful API, OpenApi-3/SwaggerUI

## OpenApi 3 + SwaggerUI

[OpenAPI Specification](https://swagger.io/specification/)  

The OpenAPI Specification (OAS) defines a standard, language-agnostic interface to RESTful APIs which allows both humans and computers to discover and understand the capabilities of the service without access to source code, documentation, or through network traffic inspection. When properly defined, a consumer can understand and interact with the remote service with a minimal amount of implementation logic.  

An OpenAPI definition can then be used by documentation generation tools to display the API, code generation tools to generate servers and clients in various programming languages, testing tools, and many other use cases.  

![spring-rest-api-documenting-with-openapi-3-1.png](spring-rest-api-documenting-with-openapi-3-1.png?id=1)

## This demo: spring-rest-api-documenting-with-openapi-3

OpenApi 3 (json) &rarr; **http://localhost:8080/v3/api-docs**  
![spring-rest-api-documenting-with-openapi-3-2.png](spring-rest-api-documenting-with-openapi-3-2.png?id=1)

Swagger-UI (html interface) &rarr; **http://localhost:8080/swagger-ui.html**  
![spring-rest-api-documenting-with-openapi-3-3.png](spring-rest-api-documenting-with-openapi-3-3.png?id=1)

[See all Swagger properties here](https://springdoc.org/#swagger-ui-properties).
> **application.properties**  
>  
>  \# To disable the swagger-ui endpoint (/swagger-ui.html by default).  
>  springdoc.swagger-ui.enabled=true

## Similar Tools
* GraphQL
* JsonAPI
* RAML

## Designing API approaches
* Code First  
We use this approach mainly when we have the code already written. In this case we generate the API from the existing code.  
code &rarr; json & UI(html)  
* Design First  
This is the recommended approach. First we design the API in YAML(JSON) and then generate the code stubs and continue code implementation.  
yaml &rarr; code stubs  



