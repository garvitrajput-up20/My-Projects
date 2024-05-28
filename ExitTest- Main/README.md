# Java + Angular Exit Test 2 - Product Catalogue

## Description
This project consists of two applications: a backend REST API built with Spring Boot and a frontend application developed using Angular 11. The purpose of this project is to create a product catalogue system with user authentication, registration, product search, and product details functionality.

## Backend (REST API - Spring Boot)
The backend application focuses on providing the necessary APIs to support user authentication, user registration, and product-related operations.

### User Authentication API
- Endpoint: `/auth`
- Method: `POST`
- Description: This API validates user credentials for successful authentication.

### User Registration API
- Endpoint: `/auth/register`
- Method: `POST`
- Description: This API allows users to register and create a new account.


### User Login API
- Endpoint: `/auth/login`
- Method: `POST`
- Description: This API allows users to login into an existing account.

### Search Product API
- Endpoint: `/products?parameter=value`
- Available parameters : 'productName', 'brand', 'productCode'
- Method: `GET`
- Description: This API enables users to search for products based on name, product code, or brand.

### Product Details API
- Endpoint: `/products/{productId}`
- Method: `GET`
- Description: This API retrieves detailed information about a specific product.

### Get Serviceability API
- Endpoint: `/products/{productId}/delivery?pincode={pincode}`
- Method: `GET`
- Description: This API checks if a product is serviceable or deliverable to a specific pincode and provides the expected delivery time.

## Frontend (Angular)
The frontend application is built using Angular 11 and provides a user-friendly interface for interacting with the product catalogue system.

### Homepage
- URL: `/`
- Description: The landing page contains links for user registration and login. It also features a search box where customers can search for products.

### Login Page
- URL: `/login`
- Description: This page allows users to log in to the application. Proper error messages are displayed in case of invalid authentication.

### Registration Page
- URL: `/register`
- Description: This page enables users to register and create a new account. It includes validation for email address, password policy, and mandatory fields.


### Results Page
- URL: `/search-results`
- Description: This page displays the search results obtained from the backend API. It includes product images, names, and brands. Logged-in users can view prices, click on product details, and filter products by price range.

### Product Details Popup
- URL: `/product/{productId}`
- Description: This popup shows detailed information about a specific product, including images, name, description, brand, and price. Logged-in users can enter their pincode to check serviceability and receive information on whether the product can be delivered and the expected delivery time.

### Logout
- URL: `/logout`
- Description: This option allows users to log out of the application.

## Installation Instructions
> To run the applications locally, follow these steps:
>     Download the zip file that contains two folders:
>         1. ExitTest [Backend | Spring Boot App]
>         2. ExitTest-FE [Frontend | Angular]
>         3. Sample database is provided in this repository

### Images are stored in BLOB Format

        
### App 1: Spring Boot App
1. Open the Spring Boot app [ExitTest] in your preferred IDE e.g ECLIPSE.
2. Configure the database connection properties in the `application.properties` file.
3. Build and run the application. 
4. Right Clik on "ExitTest" -> Click on "Run As" -> Select "Spring Boot App"
4. The Spring Boot app should start running on the specified port, and the APIs will be accessible at the respective endpoints.

### App 2: Angular App
1. Open "ExitTest-FE" in Terminal
2. Run `npm install` to get required packages, then
3. Run the command : `ng serve`, it will give you the URL to access the application


### Make Sure to Import the dummy data from database provided 'ecommerce.sql'


## Created By:

Garvit Rajput - Java Trainee

garvit.rajput@nagarro.com
