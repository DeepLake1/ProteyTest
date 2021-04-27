DELETE FROM user_status;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, phone_number)
VALUES ('User', 'user@yandex.ru', '791111111'),
       ('Admin', 'admin@gmail.com', '792222222');

INSERT INTO user_status (status_type, user_id, status_last_time_changed)
VALUES ('ONLINE', 100000, '2021-04-27 10:00:00'),
       ('OFFLINE', 100001, '2021-04-27 11:00:00');
