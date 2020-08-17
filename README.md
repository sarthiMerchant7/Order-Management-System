# Order-Management-System

This project is about how to communicate with other service by using Feign client in Spring boot.

OrderManagementSystem is parent module. Which Will store the Order Details in DB and By using Feign client service it will store the Order Item detail in other DB which will hosted on other port.
In our example OrderManagementSystem microservice deployed on 8080 port of Tomcat and OrderItemManagementSystem hosed on 8081 port of Tomcat. Currently H2 DB has been used.
