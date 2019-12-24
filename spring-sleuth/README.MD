Spring Sleuth:
--------------
https://cloud.spring.io/spring-cloud-sleuth/reference/html/#introduction

Sample application: https://www.baeldung.com/spring-cloud-sleuth-single-application

Spring Sleuth is a library that makes it possible to identify logs pertaining to a specific job, thread or a request. Sleuth integrates effortlessly with logging frameworks like Logback and slf4j to add unique identifiers that help track and diagnose issues using logs.

dependency -> org.springframework.cloud:spring-cloud-starter-sleuth

Add application name in application.properties to instruct sleuth to identify this application logs 

spring.application.name = Spring Sleuth Tutorial

Strting 2.0 Spring Cloud Sleuth uses Brave as the tracing library which adds unique id's to each web request that enters our application.

Traces can be thought of as a single request or job in an application. All the various steps related to the request will have the same traceId.

Spans on the other hand can be thought of as a section of job or a request. A single trace can be composed of multiple spans each correlating to a specific section of the request.

Using trace and span ids we can pinpoint exactly when and where our application is as it processes a request. Making reading our logs much easier.

