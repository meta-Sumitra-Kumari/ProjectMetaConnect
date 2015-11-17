CREATE DATABASE IF NOT EXISTS meta_connect;
USE meta_connect;
CREATE TABLE IF NOT EXISTS user
(   
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_fname VARCHAR(50) NOT NULL,
    user_Lname VARCHAR(50) NOT NULL,
    user_email VARCHAR(100) NOT NULL UNIQUE,
    user_image VARCHAR(200),
    user_phone_number INT,
    user_addresss VARCHAR(1000),
    user_state VARCHAR(100),
    user_city VARCHAR(100),
    user_gender VARCHAR(1) CHECK( user_gender IN('M','F')),
    user_created_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS groups
(
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(50) NOT NULL,
    group_description VARCHAR(1000) NOT NULL,
    group_admin_id INT NOT NULL,
    group_created_time TIMESTAMP,
    FOREIGN KEY(group_admin_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS group_members
(
        user_id INT,
        group_id INT,
        group_joined_time TIMESTAMP,
        PRIMARY KEY(user_id,group_id),
        FOREIGN KEY(user_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY(group_id) REFERENCES groups(group_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS post
(
    post_id INT PRIMARY KEY AUTO_INCREMENT,
    post_description VARCHAR(1000) NOT NULL,
    user_id INT,
    group_id INT,
    post_created_time TIMESTAMP,
    FOREIGN KEY(user_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(group_id) REFERENCES groups(group_id) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE IF NOT EXISTS comments
(
    comments_id INT PRIMARY KEY AUTO_INCREMENT,
    comments_description VARCHAR(1000) NOT NULL,
    user_id INT,
    post_id INT,
    comment_created_time TIMESTAMP,
    FOREIGN KEY(user_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(post_id) REFERENCES post(post_id) ON DELETE CASCADE ON UPDATE CASCADE

);


CREATE TABLE IF NOT EXISTS request
(
    user_id INT,
    group_id INT,
    status VARCHAR(20) CHECK( status IN('ACCEPTED','DENIED','PENDING')),
    request_created_time TIMESTAMP,
    PRIMARY KEY(user_id,group_id),
    FOREIGN KEY(user_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(group_id) REFERENCES groups(group_id) ON DELETE CASCADE ON UPDATE CASCADE

);