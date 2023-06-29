# RewardPoints

RewardsPoints is a Spring Boot Applications having REST end points for below scenario

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.

(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).


# Setup

Install Java 8+ versions

Install Maven 

Clone this repository in IDE (Inteliij/Eclipse) 

Run mvn clean install 

Run mvn spring-boot:run to run the application

REST URLS:

1. http://localhost:8081/rewardsProgram/api/v1/getRewards


<img width="1005" alt="Screen Shot 2023-06-29 at 3 31 10 PM" src="https://github.com/SamathaKusuma/RewardPoints/assets/138155529/f1cb5290-85e6-43f0-93ca-c64911eb2eda">

2. http://localhost:8081/rewardsProgram/api/v1/getRewards/customer/{customerID}

   <img width="997" alt="Screen Shot 2023-06-29 at 3 32 21 PM" src="https://github.com/SamathaKusuma/RewardPoints/assets/138155529/fdcf28a4-021d-434a-a99c-6b114e6a40ce">
