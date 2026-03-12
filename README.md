# Streakify

## Project Overview

Streakify is a habit-tracking backend system designed to help users build consistent habits through streak tracking and productivity insights.

This project implements the **Version 1.0 MVP backend** for the Streakify application using **REST APIs** and **PostgreSQL**.

## Features

Users can:

- Register and manage accounts
- Create and manage habits
- Log daily habit progress
- Track habit streaks
- View productivity analytics through a dashboard
  

## System Architecture

The application follows **Layered Architecture**:
```
Controller Layer
      ↓
Service Layer
      ↓
Repository Layer
      ↓
Database (PostgreSQL)
```


## Layers Description

| Layer      | Responsibility                      |
| ---------- | ----------------------------------- |
| Controller | Handles HTTP requests and responses |
| Service    | Contains business logic             |
| Repository | Handles database operations         |
| Database   | Stores application data             |



## Technology Stack

| Technology                      | Purpose           |
| ------------------------------- | ----------------- |
| Spring Boot / Node.js / FastAPI | Backend Framework |
| PostgreSQL                      | Database          |
| Postman                         | API Testing       |
| GitHub                          | Version Control   |

# Database Design

 Database Name : streakify_db

Tables:

## users

| Column     | Type        |
| ---------- | ----------- |
| id         | Primary Key |
| name       | String      |
| email      | Unique      |
| created_at | Timestamp   |


## habits

| Column               | Type        |
| -------------------- | ----------- |
| id                   | Primary Key |
| name                 | String      |
| target_days_per_week | Integer     |
| user_id              | Foreign Key |
| created_at           | Timestamp   |


## habit_logs


| Column    | Type        |
| --------- | ----------- |
| id        | Primary Key |
| habit_id  | Foreign Key |
| log_date  | Date        |
| completed | Boolean     |

Constraint : UNIQUE(habit_id, log_date)  // This prevents duplicate logs for the same habit on the same day.


## API Endpoints

### User APIs

#### Create User
**Endpoint:**  
POST /users  

**Sample Request**

```json
{
  "name": "John Doe",
  "email": "john@example.com"
}
```
### Get User Profile
**Endpoint:**  
GET /users/{id}

### Delete User
**Endpoint:**  
DELETE /users/{id}

---

## Habit APIs

### Create Habit
**Endpoint:**  
POST /habits

**Request**

```json
{
  "name": "Morning Workout",
  "targetDaysPerWeek": 5,
  "userId": 1
}
```

### Get User Habits
**Endpoint:**  
GET /users/{userId}/habits

### Delete Habit
**Endpoint:**  
DELETE /habits/{id}

---

## Habit Log APIs

### Log Habit
**Endpoint:**  
POST /habits/{habitId}/logs

**Request**

```json
{
  "logDate": "2026-02-11",
  "completed": true
}
```

### Business Rules

- Cannot log future dates  
- Only one log per day  
- Habit must belong to the user

### Update Habit Log
**Endpoint:**  
PUT /habits/{habitId}/logs/{date}

### Get Habit Logs
**Endpoint:**  
GET /habits/{habitId}/logs

## Streak API

### Get Habit Streak
**Endpoint:**  
GET /habits/{habitId}/streak

**Response**

```json
{
  "currentStreak": 5,
  "longestStreak": 9
}
```
## Dashboard API

### Productivity Dashboard
**Endpoint:**  
GET /users/{userId}/dashboard  

**Response**

```json
{
  "totalHabits": 4,
  "activeHabits": 3,
  "completedToday": 2,
  "currentStreaks": [
    {
      "habitName": "Workout",
      "currentStreak": 5,
      "longestStreak": 9
    }
  ],
  "consistencyScore": 82
}
```
## Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/yourusername/streakify-backend.git
```
### 2. Setup PostgreSQL

Create the database:

```sql
CREATE DATABASE streakify_db;
```
### 3. Configure Database

Update the configuration file.

Example (Spring Boot):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/streakify_db
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```
### 4. Run Application

Run the backend application using the appropriate command based on the framework used:

```bash
mvn spring-boot:run
```
or
```
npm start
```
or
```
uvicorn main:app --reload
```
##  Postman Testing

The following test cases were implemented:

### Positive Tests

-  Create User  
-  Create Habit  
-  Log Habit  
-  Fetch Streak  
-  Fetch Dashboard  

### Negative Tests

-  Duplicate log entry  
-  Future date log  
-  Non-existing user  
-  Invalid email  

```
streakify-backend
│
├── controller
│   ├── UserController
│   ├── HabitController
│
├── service
│   ├── UserService
│   ├── HabitService
│
├── repository
│   ├── UserRepository
│   ├── HabitRepository
│
├── model
│
├── exception
│
├── config
│
└── application.properties
```


## Features Implemented

- User Management
- Habit Management
- Habit Logging
- Streak Calculation
- Productivity Dashboard
- Validation
- Exception Handling
- PostgreSQL Integration
- Postman API Testing
  
## Future Improvements

The current version of the Streakify backend implements the core functionality required for the MVP. The following improvements can be implemented in future versions:

### 1. Authentication & Security
- Implement JWT-based authentication
- Add login functionality
- Secure APIs with role-based access control

### 2. Notification System
- Send daily reminders for habits
- Email or push notifications for streak updates

### 3. Mobile App Integration
- Integrate the backend with a mobile frontend application
- Provide real-time habit tracking

### 4. Advanced Analytics
- Weekly and monthly productivity reports
- Habit completion trends
- Personalized improvement suggestions

### 5. Performance Improvements
- Add caching for dashboard analytics
- Optimize streak calculation queries

### 6. Docker Support
- Containerize the application using Docker
- Simplify deployment across environments

### 7. Unit Testing
- Implement JUnit / Integration tests
- Increase reliability of APIs
  
## Deployment / Hosting

Currently, the application runs locally for development and testing.

### Local Hosting

The backend server runs locally using:
```
localhost:8080
```

##  Author

Christeena Johny
B.Tech Computer Science Engineering
