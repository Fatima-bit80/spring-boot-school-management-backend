# Java Spring Boot School

School REST API based on Java Spring, Spring Boot, Hibernate ORM with MySQL

This project built using **Java** and the following tools:
- [Spring Boot](https://spring.io/projects/spring-boot) as server side framework
- [Maven](https://maven.apache.org/) as build automation tool
- [Hibernate](https://hibernate.org/) as ORM / JPA implementation
- [MySQL](https://www.mysql.com/) as database implementation
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) as the top layer over Hibernate

# Application Structure

### Entity

organized under the **entity** package and it consists of entity classes. Entities use various annotations that describe the
relationships between each other. All these annotations are used by JPA in order to map entities to database tables.


### DTO

DTO stands for **Data Transfer Object**  to decouple the model layer from the client side.
Transfer only the needed data using DTO, instead of populating the entire model.

### Repository

Responsible for data persistence and retrieval. The repository layer is an abstraction that provides all
CRUD functionality and keeps hidden the data related information (e.g. specific database implmentation) from the other layers. This layer
should always persist entities.

### Service

Service layer depends on the repository layer and provides separation of concern, encapsulating all the business logic implementation. It is
there to apply business rules on data sent to and from the repository layer. Service layer does not care about the specific database implementation
and provides loose coupling. This technique makes the application super flexible in a possible data source replacement.

### Controller

Controller layer depends on the service layer and is responsible for the incoming requests and the outgoing responses. A controller determines all the
available endpoints that client side (or other api) is able to call. This layer should not apply logic on the receiving or returning data.


all response is json

## REST API Endpoints




```
COURSES
GET /api/v1/courses  ADMIN views all courses + (teacher id , enrollment ids)
GET /api/v1/courses/available STUDENT view courses that student hasn't enrolled in (available)
GET /api/v1/courses/enrolled STUDENT student sees the courses he is enrolled in + the corresponding teacher
GET /api/v1/courses/taught TEACHER teacher sees all courses he teaches + their enrollments
POST /api/v1/courses/ ADMIN admin can create a course + assign a teacher to it
DELETE /api/v1/courses/code ADMIN delete a course and all the corresponding enrollments ( can't delete a course that doesn't exist)


STUDENTS
GET /api/v1/students/{studentId} STUDENT ADMIN student can view his account's info or admin cam view any student's account info
GET /api/v1/students ADMIN admin can view all student's accounts
POST /api/v1/students ALL guests can create a student's account
DELETE /api/v1/students/{studentId} ADMIN admin can delete a student's account


TEACHERS
GET /api/v1/teachers/{teacherId} TEACHER ADMIN teacher can view his account's info or admin cam view any teacher's account info
GET /api/v1/teachers ADMIN admin can view all teacher's accounts
POST /api/v1/teachers ALL guests can create a teacher's account
DELETE /api/v1/teachers/{teacherId} ADMIN admin can delete a teacher's account


ENROLLMENTS
GET /api/v1/enrollment STUDENT TEACHER ADMIN student views all his enrollments, teacher views all enrollments to courses he teaches, admin views all enrollments
GET /api/v1/enrollments/{courseCode} TEACHER ADMIN admin can view enrollments in a course, teacher can view enrollment of course only if he teaches it
POST /api/v1/enrollments STUDENT a student can request to enroll in a course
PUT /api/v1/enrollments/{enrollmentId} TEACHER teacher can update an enrollment ( approve it or grade it)
DELETE /api/v1/enrollments/{courseCode} STUDENT TEACHER ADMIN student can un enroll , teacher or admin can delete an enrollment

```
