CREATE TABLE users
(
    user_id SERIAL NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    salt VARCHAR(100) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    role VARCHAR(20) NOT NULL
);
