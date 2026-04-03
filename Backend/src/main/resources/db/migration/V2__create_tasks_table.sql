CREATE TABLE tasks (
    task_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    priority VARCHAR(50),
    due_date DATE,
    google_event_id VARCHAR(255),
    user_id INTEGER,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);