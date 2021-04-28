DELETE
FROM user_status;
DELETE
FROM users;

INSERT INTO users (name, email, phone_number)
VALUES ('User', 'user@yandex.ru', '791111111'),
       ('User2', 'user222@yandex.ru', '79555555'),
       ('Admin', 'admin@gmail.com', '792222222');

INSERT INTO user_status (status_type, status_last_time_changed, user_id)
VALUES ('ONLINE',  '2021-04-27 10:00:00',1),
       ('OFFLINE',  '2021-04-27 11:00:00',2),
       ('ONLINE', '2021-04-28 12:37:00',3);
