# BookMyBnB 😎🤙

A microservice-based 🤯 hotel room booking 🏨 backend application made with Spring boot 🍃❕

# Setup
1) Download the zipped repository or clone it.
2) Install IntelliJ Idea (my recommendation, you can use whatever IDE you wish!)
3) Set up MySQL on your machine since the database requires a local setup.
4) Create a database named "BookMyBnb"
5) In the configuration files of the booking service and payment service, change the credentials to reflect your own.
6) Run the discovery service and api gateway, followed by the booking service and payment service.
7) Voila! Now you can test the endpoints mentioned below via Postman or any other tool of your choice!

# **Architecture 👷‍♂️**

    API Gateway 🚪
    This service is exposed to the outer world and is responsible for delegating requests to internal microservices.

    Discovery service 🔍
    This service acts as a registry for internal services of the application to register themselves on. 
    Much like a telephone registry in the old days 👴, it keeps crucial information about the services,
    mainly their ports among other important things. This helps in failsafe inter-communication of services.

    Booking Service 🛏
    This service is responsible for collecting all information related to user booking
    and sending a confirmation message once the booking is confirmed.

    Payment Service 🧾
    This is a dummy payment service; this service is called by the booking service
    for initiating payment after confirming rooms.

![image](https://github.com/SwaggyXO/BookMyBnB/assets/76209941/3d7fabd0-ea54-4edb-918d-942a8674b692)

# Endpoints 🔌
**Eureka Server 🖥**

/eureka/web
![image](https://github.com/SwaggyXO/BookMyBnB/assets/76209941/8eb3f657-a880-451e-9132-3254565f59e4)


**Booking Service 🛏**

Make a booking

POST /api/booking/
![image](https://github.com/SwaggyXO/BookMyBnB/assets/76209941/eaedeee5-6357-4121-bbd4-5d2875bfa9da)

POST /api/booking/transaction

Pay for a booking
![image](https://github.com/SwaggyXO/BookMyBnB/assets/76209941/56a00374-bb6c-4528-88f8-d2c866ff6dbd)


Exception 1 - Invalid Payment Method
![image](https://github.com/SwaggyXO/BookMyBnB/assets/76209941/000bf61a-74b6-4f0e-905b-b1b9da54952a)

Exception 2 - Invalid Booking ID
![image](https://github.com/SwaggyXO/BookMyBnB/assets/76209941/d899da37-5166-45d1-acb3-19960918dbb3)

**Payment Service 💰**

Fetch transaction details

GET /api/payment/{transactionID}
![image](https://github.com/SwaggyXO/BookMyBnB/assets/76209941/493d5c2f-82ef-4c4f-a82b-01108fb2ed6e)

**Thanks for reading 🥂**
