# ExchangeRate

## Table of contents

- [Overview](#overview)
- [Process](#process)
  - [Restful API Services](#restful-api-services)
  - [Security](#security)
  - [Built with](#built-with)
  - [Configurations](#configurations)
- [Links](#links)


## Overview
This project is a web application that compares daily the current value of USD based on EUR. It displays the number of increases and decreases of the dollar rate. It is updated daily.
Basic CRUD operations are implemented.

## Process
  ### Functionalities
  - Users can access all daily rates starting from 2022-01-01 til present
  - Users can check dates on a specific date
  - Admins can modify, add and delete rates
    
  ### Security
  Access is secured using JWTs, signup or signin is required to be able to access the API
  
  ### Built with
  - Java
  - Spring Boot
  - PostgreSQL
  
  ### Configurations
  1. Regarding the properties folder, environment variables need to be added to spring.datasource.username, spring.datasource.password and exchangeRate.app.jwtSecret
  2. Cron scheduler can be disabled manually by adding scheduler.enabled property. By default, scheduler will execute at 1 A.M every day
  3. Roles need to be manually added in table. Run the following SQL commands: <br>
    INSERT INTO roles(name) VALUES('ROLE_USER');<br>
    INSERT INTO roles(name) VALUES('ROLE_ADMIN');<br>
  
  
  ## Links
  Link to external API : https://apilayer.com/marketplace/exchangerates_data-api <br>
  Link to swagger generated API documentation: http://localhost:8080/swagger-ui.html <br>
  Link to endpoint: http://localhost:8080/api/v1/dailyrate
  
