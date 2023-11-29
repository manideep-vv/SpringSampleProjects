# Edureka-Assignments

H2 Url's
-----------------
http://localhost:8080/h2-console/login.jsp

jdbc url:jdbc:h2:mem:testdb
user name: sa
password: 1234

Queries
==============


swagger ui urls
===============
you can get swagger ui url from console also when app ran
http://localhost:8080/swagger-ui.html


notification service urls and payloads
=================================
1) GET request to fetch all notification
   http://localhost:8080/api/v1/Notifications/getAllNotifications

2) POST - to create an notification

http://localhost:8080/api/v1/Notifications/register
{
"message": "customer registration has been successful",
"type": "email",
"customerId": 2
}
