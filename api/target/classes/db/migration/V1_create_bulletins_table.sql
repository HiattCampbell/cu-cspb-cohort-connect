CREATE TYPE bulletin_type AS ENUM ('event', 'announcement', 'question', 'misc');


CREATE TABLE bulletins (
    id SERIAL PRIMARY KEY,
    type bulletin_type NOT NULL,
    posted_on TIMESTAMP NOT NULL,
    posted_by INTEGER NOT NULL,
    title VARCHAR(30) NOT NULL,
    content VARCHAR(300),
    FOREIGN KEY (posted_by) REFERENCES users(id)
);