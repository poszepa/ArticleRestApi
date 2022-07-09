# Spring Boot "Microservice" Article API

This is a JAVA / MAVEN / SPRING BOOT ( 2.7.1 ) API. That application can be used to CRUD with Article.

## How to Run 

This application is packaged as a jar. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 11, Maven 3.2.2 and MySQL
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by those method:
```
        java -jar target/ArticleRestApi-0.0.1-SNAPSHOT.jar --port=<yourPort> --spring.datasource.username=<yourUserNameInMySQL> --spring.datasource.password=<YourPasswordToMySQL>
        
        <yourPort> -> in default can you pass = 8080
        <yourUserNameInMySQL> -> your userName to login to MySQL server
        <YourPasswordToMySQL> -> if you don't have none password you can remove those parameter: "--spring.datasource.password=" , if you have password, pass password.
        
```

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```

### Validation

```
Author:
  firstName: size between 2-20
  lastName : size between 2-20
 
 Article:
  title: size between 2-30
  description: minimum size 5
  dataPublication: pattern "dd-MM-yyyy"
  magazine: size between 2-30
  author: only accept Class like a Author.class
```


### Create a Author resource

```
POST /Author
Accept: application/json
Content-Type: application/json

{
"firstName" : "Author name",
"lastName" : "Author surname",
}

FistName validation : size between 2 - 20;
LastName validation : size between 2 - 20;

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/Author/1
```

### Retrieve a Article by ID

```
GET /article/1
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)
Location header: http://localhost:8080/Article/1

Body = 
{
  "id":1,
  "title":"test",
  "description":"test",
  "datePublication":"2022-10-12",
  "magazine":"test",
  "author":{
            "id":null,
            "firstName":"kamil",
            "lastName":"poszepczynski",
            "articleList":null
            },
  "dateSavedArticle":10-11-2022,
  "timeSavedArticle":22:15:22
}
```

### Retrieve a Article List sorted by date publication desc

```
GET /article/desc
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)
Location header: http://localhost:8080/Article/desc

Body = 
{
  "id":1,
  "title":"test",
  "description":"test",
  "datePublication":"2022-10-12",
  "magazine":"test",
  "author":{
            "id":1,
            "firstName":"kamil",
            "lastName":"poszepczynski",
            "articleList":null
            },
  "dateSavedArticle":10-11-2022,
  "timeSavedArticle":22:15:22
}

```
### Retrieve a Article List with founded key which was setted in param

```
GET /article/desc/findByKey/{key}
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200 (OK)
Location header: http://localhost:8080/Article/findByKey/{key}

Body = 
{
  "id":1,
  "title":"test",
  "description":"test",
  "datePublication":"2022-10-12",
  "magazine":"test",
  "author":{
            "id":1,
            "firstName":"kamil",
            "lastName":"poszepczynski",
            "articleList":null
            },
  "dateSavedArticle":10-11-2022,
  "timeSavedArticle":22:15:22
}
```

### Save a new Article

```
POST /article
Accept: application/json
Content-Type: application/json

{
  "title":"test",
  "description":"test",
  "datePublication":"2022-10-12",
  "magazine":"test",
  "author":{
            "id":1,
            "firstName":"kamil",
            "lastName":"poszepczynski",
            "articleList":null
            }
}
RESPONSE: HTTP 204 (NoContent)
response body:
{
  "id": 1,
  "title":"test",
  "description":"test",
  "datePublication":"2022-10-12",
  "magazine":"test",
  "author":{
            "id":1,
            "firstName":"kamil",
            "lastName":"poszepczynski",
            "articleList":null
            },
  "dateSavedArticle":10-11-2022,
  "timeSavedArticle":22:15:22
}

```
### Update a Article resource

```
PUT /article/{id}
Accept: application/json
Content-Type: application/json

{
  "id": 1,
  "title":"test",
  "description":"test",
  "datePublication":"2022-10-12",
  "magazine":"test",
  "author":{
            "id":1,
            "firstName":"kamil",
            "lastName":"poszepczynski",
            "articleList":null
            },
  "dateSavedArticle":10-11-2022,
  "timeSavedArticle":22:15:22
}

RESPONSE: HTTP 204 (NoContent)
response body:
{
  "id": 1,
  "title":"test",
  "description":"test",
  "datePublication":"2022-10-12",
  "magazine":"test",
  "author":{
            "id":1,
            "firstName":"kamil",
            "lastName":"poszepczynski",
            "articleList":null
            },
  "dateSavedArticle":10-11-2022,
  "timeSavedArticle":22:15:22
}
```

### Update a Article resource

```
DELETE /article/{id}
Accept: application/json
Content-Type: application/json

{
  "id": 1,
  "title":"test",
  "description":"test",
  "datePublication":"2022-10-12",
  "magazine":"test",
  "author":{
            "id":1,
            "firstName":"kamil",
            "lastName":"poszepczynski",
            "articleList":null
            },
  "dateSavedArticle":10-11-2022,
  "timeSavedArticle":22:15:22
}

RESPONSE: HTTP 204 (NoContent)






