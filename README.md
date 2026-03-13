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


# Project Structure

```
streakify-backend
│
├── docs
│   └── screenshots
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.streakify.streakify_backend
│   │   │       │   StreakifyBackendApplication.java
│   │   │
│   │   │       ├── controller
│   │   │       │   ├── DashboardController.java
│   │   │       │   ├── HabitController.java
│   │   │       │   ├── HabitLogController.java
│   │   │       │   ├── StreakController.java
│   │   │       │   └── UserController.java
│   │   │
│   │   │       ├── dto
│   │   │       │   ├── HabitLogRequestDTO.java
│   │   │       │   ├── HabitLogResponseDTO.java
│   │   │       │   ├── HabitProgressDTO.java
│   │   │       │   ├── HabitRequestDTO.java
│   │   │       │   ├── HabitResponseDTO.java
│   │   │       │   ├── StreakDTO.java
│   │   │       │   ├── StreakSummaryDTO.java
│   │   │       │   ├── UserDashboardResponseDTO.java
│   │   │       │   ├── UserRequestDTO.java
│   │   │       │   └── UserResponseDTO.java
│   │   │
│   │   │       ├── entity
│   │   │       │   ├── Habit.java
│   │   │       │   ├── HabitLog.java
│   │   │       │   └── User.java
│   │   │
│   │   │       ├── exception
│   │   │       │   ├── GlobalExceptionHandler.java
│   │   │       │   └── ResourceNotFoundException.java
│   │   │
│   │   │       ├── repository
│   │   │       │   ├── HabitLogRepository.java
│   │   │       │   ├── HabitRepository.java
│   │   │       │   └── UserRepository.java
│   │   │
│   │   │       └── service
│   │   │           ├── DashboardService.java
│   │   │           ├── HabitLogService.java
│   │   │           ├── HabitService.java
│   │   │           ├── StreakService.java
│   │   │           └── UserService.java
│   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── pom.xml
└── README.md
```


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
## 📸 Screenshots

### User APIs

#### Create User
![Create User](docs/screenshots/Create%20User.png)

#### Fetch User
![Fetch User](docs/screenshots/FetchUser.png)

#### Delete User
![Delete User](docs/screenshots/DeleteUser.png)

#### Invalid Email
![Invalid Email](docs/screenshots/InvalidEmail.png)

#### Duplicate Email
![Duplicate Email](docs/screenshots/Duplicate_Email.png)

#### User Not Found
![User Not Found](docs/screenshots/UserNotFound.png)

---

### Habit APIs

#### Create Habit
![Create Habit](docs/screenshots/CreateHabit.png)

#### Get All Habits
![Get All Habits](docs/screenshots/GetAllHabits.png)

#### Delete Habit
![Delete Habit](docs/screenshots/DeleteHabit.png)

#### Habit Not Found
![Habit Not Found](docs/screenshots/HabitNotFound.png)

---

### Habit Log APIs

#### Create Log
![Create Log](docs/screenshots/CreateLog.png)

#### Update Log
![Update Log](docs/screenshots/UpdateLog.png)

#### All Logs
![All Logs](docs/screenshots/AllLogs.png)

#### Already Logged
![Already Logged](docs/screenshots/AlreadyLogged.png)

#### Future Date Validation
![Future Date](docs/screenshots/FutureDate.png)

#### Target Achieved
![Target Achieved](docs/screenshots/Targetachieved.png)

#### Target Exceed Validation
![Log Exceed](docs/screenshots/LogExceed.png)

---

### Streak APIs

#### Fetch Streak
![Fetch Streak](docs/screenshots/FetchStreak.png)

#### Break Streak
![Break Streak](docs/screenshots/BreakStreak.png)



---

### Dashboard

#### Productivity Dashboard
![Dashboard](docs/screenshots/Dashboard.png)

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


### Run Application

Start the backend application using the appropriate command based on the framework used:

#### Spring Boot

```bash
mvn spring-boot:run
```
Application will be available at:
```
http://localhost:8080
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

 1. Authentication & Security
- Implement JWT-based authentication
- Add login functionality
- Secure APIs with role-based access control

 2. Notification System
- Send daily reminders for habits
- Email or push notifications for streak updates

 3. Mobile App Integration
- Integrate the backend with a mobile frontend application
- Provide real-time habit tracking

 4. Advanced Analytics
- Weekly and monthly productivity reports
- Habit completion trends
- Personalized improvement suggestions

 5. Performance Improvements
- Add caching for dashboard analytics
- Optimize streak calculation queries




##  Author

Christeena Johny
B.Tech Computer Science Engineering
