# Java Spring Boot School

School REST API based on Java Spring, Spring Boot, Hibernate ORM with MySQL

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
