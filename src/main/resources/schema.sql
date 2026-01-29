CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL
);