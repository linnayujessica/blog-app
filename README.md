# Blog application with new API: GET - /admin

### Insructions
blog-app is a Spring Boot RESTful web service 

Prerequisites for this project:
* Eclipse or IntelliJ
* JDK 11 and Maven
* Postman or SoapUI

How to run app:
* Open Eclipse or IntelliJ
* Import Pojects from Git, URI is https://github.com/linnayujessica/blog-app.git
* Open command prompt window and 'cd' to /blog-app directory
* Run <b>mvn clean package</b>
* If everything goes well, you could see result: <b>BUILD SUCCESS</b>
* Now run the server in cmd: <b>java -jar target/blog-app-0.0.1-SNAPSHOT.jar</b>
* Once server starts successfully, open Postman or SoapUI to test GET requests http://localhost:8080/admin

### API documents
* `GET - /admin`

To present all user information including user profile and user's post info.

* `GET - /admin/{userId}` 

To present one specific user information including user profile and user's post info.

* `GET - /users`

To present all user profiles including name, email, phone, address and others

* `GET - /users/{id}`

To present one specific user profile by user Id

* `GET - /posts`

To present all posts 

* `GET - /posts/{id}`

To present one particular post info by post Id

*(*notes: /posts and /users are existing APIs)*
