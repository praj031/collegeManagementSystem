package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.dbRelationpics;

public class Documentation {
    /*

College Management System - API Documentation
Overview
This document contains complete API documentation for the College Management System including entities, relationships, API endpoints, and Postman samples.
Entities & Relationships

1. **Student**
   - Many-to-Many: Student ↔ Subject
   - Many-to-Many: Student ↔ Professor
   - One-to-One: AdmissionRecord → Student

2. **Professor**
   - One-to-Many: Professor → Subjects

3. **Subject**
   - Many-to-One: Subject → Professor

4. **AdmissionRecord**
   - One-to-One: AdmissionRecord → Student

API Testing Sequence -- from chat gpt

1. Create Professor
2. Create Subject (with professorId)
3. Create Student
4. Assign Subject to Student
5. Assign Professor to Student
6. Create AdmissionRecord for Student

1. Create Professor

**POST /professors**

Sample Body:
{
  "name": "Dr. Smith",
  "title": "Senior Lecturer"
}

Expected Response:
{
  "id": 1,
  "name": "Dr. Smith",
  "title": "Senior Lecturer",
  "subjects": []
}

2. Create Subject (Assign Professor)

**POST /subjects**

Sample Body:
{
  "title": "Mathematics 101",
  "subjectName": "Basic Math",
  "professorId": 1
}

Expected Response:
{
  "id": 1,
  "title": "Mathematics 101",
  "subjectName": "Basic Math",
  "professor": {
    "id": 1,
    "name": "Dr. Smith",
    "title": "Senior Lecturer"
  }
}

3. Create Student

**POST /students**

Sample Body:
{
  "name": "Amit Sharma"
}

Expected Response:
{
  "id": 1,
  "name": "Amit Sharma",
  "subjects": [],
  "professors": []
}

4. Assign Subject to Student

**PUT /students/1/subjects/1**

Expected Response:
{
  "id": 1,
  "name": "Amit Sharma",
  "subjects": [
    {
      "id": 1,
      "title": "Mathematics 101",
      "subjectName": "Basic Math"
    }
  ]
}

5. Assign Professor to Student

**PUT /students/1/professors/1**

Expected Response:
{
  "id": 1,
  "name": "Amit Sharma",
  "professors": [
    {
      "id": 1,
      "name": "Dr. Smith",
      "title": "Senior Lecturer"
    }
  ],
  "subjects": [
    {
      "id": 1,
      "title": "Mathematics 101"
    }
  ]
}

6. Create Admission Record

**POST /admissions**

Sample Body:
{
  "fees": 50000,
  "studentId": 1
}

Expected Response:
{
  "id": 1,
  "fees": 50000,
  "student": {
    "id": 1,
    "name": "Amit Sharma"
  }
}

GET APIs

**GET /students**
Returns list of students

**GET /students/1**
Returns student with subjects & professors

**GET /professors/1**
Returns professor with subjects

**GET /subjects/1**
Returns subject with professor



     */

}
