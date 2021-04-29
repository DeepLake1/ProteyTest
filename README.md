#### Protey Test Application


## Steps to run
1. Change DB properties in 'application.properties' file
2. Build the project using
  `mvn clean install`
3. Run using `mvn spring-boot:run`
4. The web application is accessible via localhost:8080
5. After the start of the application, you can test API on [Swagger Link](http://localhost:8080/swagger-ui-custom.html)

##API Documentation

**Save User**
----
  Saves User in the database.

* **URL**

  /rest/user/save

* **Method:**

  `POST`
  
* **Data Params**

  `{"name" :"User",
   "email" : "email@gmail.com",
   "phoneNumber" : "+79876543210"}`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `100000`
 
**Get User**
----
  Returns json data about a single user.

* **URL**

  /rest/user/{id}

* **Method:**

  `GET`

* **Success Response:**

* **Code:** 200 <br />
   **Content:**  
   `{"name" :"User",
    "email" : "email@gmail.com",
    "phoneNumber" : "+79876543210",
    "registered": "2021-04-29T04:11:17.271362"}`
    
**Get and Update User's status**
----
 Get and Update User's status from the database.

* **URL**
    
/rest/user/100000
    
* **Method:**
    
    `POST`
      
* **URL Params**

   **Required:**
 
   `status=['ONLINE' or 'OFFLINE' or 'AWAY']`
    
* **Success Response:**

* **Code:** 200 <br />
    **Content:** 
    `{
    "User id": "100004",
    "OldStatus": "AWAY",
    "NewStatus": "ONLINE"
     }`
    
**Delete User**
----
 Delete User from database.

* **URL**
    
/rest/user/{id}
    
* **Method:**
    
    `DELETE`
      
* **Code:** 204 <br />

    
