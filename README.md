# Olmero Construction app
API is built with the Java 8 in Spring Boot, using MySQL database.
Here are the steps to run the application:
1. First you need to import SQL data structure - **construction_2020-03-30.sql** located in resource folder of project. (_/src/main/resources/construction_2020-03-30.sql_)
NOTE: There is also some data which will be imported via insert statements. I used those for testings. You can clear the tables.
2. _**gradle build**_ to build the app and make JAR file
3. cd _build/libs_ to find the jar and run it with
4. java -jar construction-0.0.1-SNAPSHOT.jar
5. Application will run on port 8082 as configured in _/src/main/resources/application.properties_

## API User guide
To get you easier into using application (Rest API), I exported postman collection with all endpoints created to resource folder: _/src/main/resources/Olmero.postman_collection.json_
Here you can find body fields and query parameters, with examples for all of the following endpoints:
1. [POST] Create tender
http://localhost:8082/tender/add
2. Get tenders
http://localhost:8082/tender
3. [POST] Create offer
http://localhost:8082/offer/add
4. Get tenders for issuer
http://localhost:8082/tender/issuer?id={issuer_id}
5. Get offers for tender
http://localhost:8082/offer/tender?id={tender_id}
6. Get offers a bidder submitted for a specific tender
http://localhost:8082/offer?bidderId={bidder_id}&tenderId={tender_id}
7. Get offers a bidder submitted for ALL tenders
http://localhost:8082/offer/bidder?id={bidder_id}
8. [GET] Close tender (For choosing the best offer and closing tender)
http://localhost:8082/offer/close?tenderId={tender_id}

## Additional notes
- In professional environment, there will be more branches, pull requests, and commit messages with issues number and some message pattern, release tags. Here I can not simulate this situation
- Exception handling and logging for application in different cases is the big thing which is missing and I know that. Sorry... :) 
- There are no authentication and security so far. Some of the endpoints should be accessible only for respective Issuer or Bidder and some of them for all (public).
- Issuers and Bidders have to be created directly in DB, as you said it is not necessary