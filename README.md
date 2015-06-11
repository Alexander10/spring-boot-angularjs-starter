# Rapid prototyping with Spring Boot and AngularJS

## Frameworks

### Front-end

#### Twitter Bootstrap
For rapidly creating prototypes of a web application, a UI toolkit or library will become really handy. There are many choices available, and for this example I chose Twitter Bootstrap.

#### AngularJS
AngularJS is a MVC based framework for web applications, written in JavaScript. It makes it possible to use the Model-View-Controller pattern on the front-end. It also comes with several additional modules. In this example I'm also using **angular-resource**, which is a simple factory-pattern based module for creating REST clients.

### Back-end





## Installation
Installation is quite easy, first you will have to install some front-end dependencies using Bower:
```
bower install
```

Then you can run Maven to package the application:
```
mvn clean package
```

Now you can run the Java application quite easily:
```
cd target
java -jar spring-boot-angularjs-starter-1.0.0.jar
```

This example is built on the basis of tutorial: http://g00glen00b.be/prototyping-spring-boot-angularjs/
