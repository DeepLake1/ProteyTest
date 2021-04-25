DROP TABLE IF EXISTS user_status;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    phone            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_status
(
    user_id INTEGER NOT NULL,
    status    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, status),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
