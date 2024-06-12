--changeset nikola:1
CREATE TABLE IF NOT EXISTS sessions (
                                        id SERIAL PRIMARY KEY,
                                        user_id INT NOT NULL,
                                        token VARCHAR(255) NOT NULL,
                                        expires TIMESTAMP NOT NULL,
                                        FOREIGN KEY (user_id) REFERENCES users(id)
);
