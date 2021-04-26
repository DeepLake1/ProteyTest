DELETE FROM user_status;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, phone_number)
VALUES ('User', 'user@yandex.ru', '791111111'),
       ('Admin', 'admin@gmail.com', '792222222');

INSERT INTO user_status (status, user_id)
VALUES ('ONLINE', 100000),
       ('OFFLINE', 100001);