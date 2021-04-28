DELETE
FROM user_status;
DELETE
FROM users;

INSERT INTO user_status (status_type, status_last_time_changed, user_id)
VALUES ('ONLINE',  '2021-04-27 10:00:00',90000),
       ('OFFLINE',  '2021-04-27 11:00:00',90001),
       ('ONLINE', '2021-04-28 12:37:00',90002);

INSERT INTO users (name, email, phone_number,status_user_id)
VALUES ('User', 'user@yandex.ru', '791111111', 90000),
       ('User2', 'user222@yandex.ru', '79555555',90001),
       ('Admin', 'admin@gmail.com', '792222222',90002);

