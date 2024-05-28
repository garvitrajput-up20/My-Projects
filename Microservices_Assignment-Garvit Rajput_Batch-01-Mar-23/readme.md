# Banking Application - Microservices

This project is mainly concerned with the microservices associated with banking application.

## Main Services

### 1. Central Configuration Management System

The Central Configuration Management System service is responsible for managing the configurations of all microservices within the banking application. 
It serves as a centralized repository for configurations related to database connections, security settings, Git configurations, and other environment-specific parameters. 
This service ensures consistency and facilitates easy management and updating of configurations across all microservices.

### 2. API Gateway

The API Gateway service acts as a single entry point for all client requests to the banking application. It handles routing, authentication, authorization, and other cross-cutting concerns for incoming requests. 
The API Gateway enables clients to interact with the various microservices in a unified manner without needing to know the specific endpoints or implementation details of each service. It also provides features like request aggregation, rate limiting, and logging.

### 3. Customer Management Service

The Customer Management Service is responsible for managing customer-related operations within the banking application. This includes functionalities such as creating new customer profiles, updating customer information, retrieving customer details, and managing customer accounts. The service ensures data integrity and security while providing seamless interactions with the customers.
### 4. Account Management Services

The Account Management Services encompass a set of microservices responsible for managing banking accounts and related transactions. These services handle tasks such as creating new accounts, performing account transfers, processing deposits and withdrawals, and generating account statements. They enforce business rules, maintain transactional consistency, and ensure compliance with regulatory requirements.
### 5. Eureka Server

The Eureka Server service implements the service registry and discovery mechanism within the banking application's microservices architecture.
By leveraging these microservices, the Banking Application Microservices project achieves modularity, scalability, and maintainability while enabling agility and flexibility in the development and deployment of banking-related functionalities.


## Running the Services

### 1. Eureka Server
- Build the Eureka Server project.
- Run the Eureka Server application.
- URL: `http://localhost:8761/`

### 2. Config Server
- Build the Config Server project.
- Run the Config Server application.
- URL: `http://localhost:8083/application/default`

### 3. Account Management Service
- Build the Account Management Service project.
- Run the Account Management Service application.
- URL: `http://localhost:8082/`

### 4. Customer Management Service
- Build the Customer Management Service project.
- Run the Customer Management Service application.
- URL: `http://localhost:8081/`

### 5. API Gateway
- Build the API Gateway project.
- Run the API Gateway application.
- URL: `http://localhost:8999/`

Ensure dependencies are installed, configurations are set up correctly, and services are registered with Eureka for seamless communication.

## API Endpoints By API Gateway

#### Use the below API Endpoints with the url: `http://localhost:8999/`

## Customer Controller

The `CustomerController` class in the `com.bank.cms.controller` package manages endpoints related to customer operations.

### Endpoints

#### 1. Add Customer

- **HTTP Method:** POST
- **Endpoint:** `/customer/add-customer`
- **Description:** Adds a new customer to the system.
- **Request Body:** `Customer` object
- **Response:**
    - **Success:** HTTP status code 200 with a success message.
    - **Failure:** HTTP status code 400 with an error message if an exception occurs.

#### 2. Fetch All Customers

- **HTTP Method:** GET
- **Endpoint:** `/customer/fetch-all`
- **Description:** Fetches all customers from the system.
- **Response:**
    - **Success:** HTTP status code 200 with a list of `Customer` objects.
    - **Failure:** HTTP status code 404 with a message "No Customers Found" if no customers are found.

#### 3. Fetch Customer by ID

- **HTTP Method:** GET
- **Endpoint:** `/customer/fetch-by-id`
- **Description:** Fetches a customer by their ID.
- **Query Parameter:** `customerId` (String)
- **Response:**
    - **Success:** HTTP status code 200 with the `Customer` object.
    - **Failure:** HTTP status code 404 with a message "Customer not found with Account ID: {customerId}" if the customer is not found.

#### 4. Update Customer

- **HTTP Method:** PUT
- **Endpoint:** `/customer/update-customer`
- **Description:** Updates an existing customer's information.
- **Query Parameter:** `customerId` (String)
- **Request Body:** Updated `Customer` object
- **Response:**
    - **Success:** HTTP status code 200 with a success message.
    - **Failure:**
        - HTTP status code 400 with an error message if a custom exception occurs.
        - HTTP status code 500 with an error message if an internal server error occurs.

#### 5. Delete Customer by ID

- **HTTP Method:** DELETE
- **Endpoint:** `/customer/delete-by-id`
- **Description:** Deletes a customer by their ID.
- **Query Parameter:** `customerId` (String)
- **Response:**
    - **Success:**
        - HTTP status code 200 with a success message if the customer is successfully deleted.
        - HTTP status code 404 with a message "Customer not found with Account ID: {customerId}" if the customer is not found.
    - **Failure:**
        - HTTP status code 400 with an error message if a custom exception occurs.
        - HTTP status code 500 with an error message if an internal server error occurs.

## Account Controller

The `AccountController` class in the `com.bank.ams.controller` package manages endpoints related to account operations.

### Endpoints

#### 1. Create Account

- **HTTP Method:** POST
- **Endpoint:** `/account/add-account`
- **Description:** Creates a new account.
- **Request Body:** `Account` object
- **Response:**
    - **Success:** HTTP status code 200 if the account is successfully created.
    - **Failure:**
        - HTTP status code 409 with a message "An account already exists for customer with ID: {customerId}" if an account already exists for the customer.
        - HTTP status code 404 with a message "Customer not found with ID: {customerId}" if the customer does not exist.
        - HTTP status code 500 with an error message if an internal server error occurs.

#### 2. List Accounts

- **HTTP Method:** GET
- **Endpoint:** `/account/list-accounts`
- **Description:** Retrieves a list of all accounts.
- **Response:**
    - **Success:** HTTP status code 200 with a list of `Account` objects.
    - **Failure:** HTTP status code 404 with a message "No Accounts Found" if no accounts are found.
    - HTTP status code 500 with an error message if an internal server error occurs.

#### 3. Get Account with Customer

- **HTTP Method:** GET
- **Endpoint:** `/account/fetch-by-id`
- **Description:** Retrieves an account along with its associated customer.
- **Query Parameter:** `accountId` (String)
- **Response:**
    - **Success:** HTTP status code 200 with the `Account` object including the associated `Customer`.
    - **Failure:** HTTP status code 500 with a message "Account Not Found" if the account is not found or an internal server error occurs.

#### 4. Add Money to Account

- **HTTP Method:** POST
- **Endpoint:** `/account/add-money`
- **Description:** Adds money to the specified account.
- **Query Parameters:**
    - `accountId` (String): The ID of the account.
    - `amount` (double): The amount to be added.
- **Response:**
    - **Success:** HTTP status code 200 with a message indicating the money was added successfully along with the updated balance.
    - **Failure:**
        - HTTP status code 404 with a message "Account not found or unable to process transaction" if the account is not found or the transaction cannot be processed.
        - HTTP status code 500 with an error message if an internal server error occurs.

#### 5. Withdraw Money from Account

- **HTTP Method:** POST
- **Endpoint:** `/account/withdraw-money`
- **Description:** Withdraws money from the specified account.
- **Query Parameters:**
    - `accountId` (String): The ID of the account.
    - `amount` (double): The amount to be withdrawn.
- **Response:**
    - **Success:** HTTP status code 200 if the money is successfully withdrawn.
    - **Failure:** HTTP status code 500 with an error message if an internal server error occurs.
