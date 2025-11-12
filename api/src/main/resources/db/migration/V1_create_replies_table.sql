CREATE TABLE replies (
    id SERIAL PRIMARY KEY,
    bulletin_id SERIAL NOT NULL,
    posted_on DATETIME NOT NULL,
    posted_by SERIAL NOT NULL,
    content VARCHAR(300),
    FOREIGN KEY (bulletin_id) REFERENCES bulletins(id),
    FOREIGN KEY (posted_by) REFERENCES users(id)
);
