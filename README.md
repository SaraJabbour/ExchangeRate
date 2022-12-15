# ExchangeRate

## Table of contents

- [Overview](#overview)
- [My process](#my-process)
  - [Built with](#built-with)
  - [Configurations](#configurations)
- [Links](#links)


## Overview
This project is a web application that compares daily the current value of USD based on EUR. It displays the number of increases and decreases of the dollar rate. It is updated daily.
It also provides API services for the basic CRUD operations.

## My Process
  ### Built with
  - Java
  - Spring Boot
  - PostgreSQL
  
  ### Configurations
  1. Regarding the properties folder, environment variables need to be added to exchange.api, db_username and db_password depending on local configurations
  2. Cron scheduler can be disabled manually by adding scheduler.enabled property. By default, scheduler will execute at 1 A.M every day
  
  
  ## Links
  Link to external API : https://apilayer.com/marketplace/exchangerates_data-api <br>
  Link to swagger generated API documentation: http://localhost:8080/swagger-ui.html
  
