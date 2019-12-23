23-Dec-2019

Spring Documentation: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

Spring Boot Actuator:  Pluralsite -> Spring Framework: Spring Boot Actuator
----------------------

Actuator: An actuator is a component that is responsible for controlling a mechanism or a system.
An Actuator receives a signal and generates an output.

Springboot actuator brings production ready features to application:
1) Monitoring the application
2) Gathering Metrics

Actuator is mainly used to expose operational information about the running application like 
health, metrics, info, dump, env etc

Diff b/w 1.x and 2.x

SB actuator 1.x - SB actuator 2.x
Spring MVC   -  Spring MVC, JAX-RS and Webflux
Extension-based - Annotation Driven
Hierarchical metrics - Hierarchical & Dimentional metrics
Custom Metrics Collector - Micrometer based metrics collection
Less secure by default - More secure by default

Default Actuator Endpoints:
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-endpoints

Most Commly used enpoints: 
Health Endpoint -> http://localhost:8080/actuator/health 
Metrics Endpoint -> http://localhost:8080/actuator/metrics, http://localhost:8080/actuator/metrics/jvm.memory.used
Loggers Endpoint -> http://localhost:8080/actuator/loggers
http://localhost:8080/actuator/configprops

Application.properties -> management.endpoints.web.exposure.include=*

Enabling/Disabling endpoints through configuration in application.properties
management.endpoint.<NAME>.enabled = true

Exposing endpoint: management.endpoint.<Protocol>.expose.include = metrics
                   management.endpoint.http.expose.include = health
enable jms endpointd: management.endpoint.jmx.expose.include = beans
enable all endpoints : management.endpoint.http.expose.include = *

Enabling vs Exposing endpoints:
Controls Creation - Controls Consumption
Almost all the endpoints are enabled by default - Almost all endpoints are not exposed by default
Efficiency purposes - Security Purposes

Securing endpoints
-------------------

gradle -> spring-boot-starter-security -> generates user -> 'user' and password -> dynamic value

The following overrides the credentials
application.properties -> spring.security.user.name=admin
                          spring.security.user.password=password

To customize security of endpoints create @Configuration class extending 
WebSecurityConfigurerAdapter and override configure(HttpSecurity http) method.

Creating custom Health Indicator -> @Component implementing HealthIndicator and 
override health() method.

Creating custom Info  -> @Component implementing InfoContributor and 
override contribute() method.

creating Custom Metrics -> Inject MeterRegistry and use the metric. e.g timer.record(..)

Implementing Custom Actuator endpoints-> @Component @Endpoint(id="container") @ReadOperation, @WriteOperation