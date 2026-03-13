CREATE DATABASE streakify_db;

-- Users Table
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) UNIQUE NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Habits Table
CREATE TABLE habits (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(150) NOT NULL,
                        target_days_per_week INT NOT NULL,
                        user_id INT NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Habit Logs Table
CREATE TABLE habit_logs (
                            id SERIAL PRIMARY KEY,
                            habit_id INT NOT NULL,
                            log_date DATE NOT NULL,
                            completed BOOLEAN DEFAULT FALSE,
                            FOREIGN KEY (habit_id) REFERENCES habits(id) ON DELETE CASCADE,
                            UNIQUE (habit_id, log_date)
);