CREATE TYPE user_type AS ENUM ('current_student', 'former_student', 'admin');


CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    type user_type NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    display_name VARCHAR(255) NOT NULL UNIQUE,
    looking_for_mentor BOOLEAN,
    looking_for_mentee BOOLEAN,
    mentor_to SERIAL,
    mentored_by SERIAL,
    FOREIGN KEY (mentor_to) REFERENCES users(id),
    FOREIGN KEY (mentored_by) REFERENCES users(id)
);
