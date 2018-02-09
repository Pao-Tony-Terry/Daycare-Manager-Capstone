USE daycare_db;


INSERT INTO users (email, employee, first_name, gender, is_admin, last_name, password, phone, username)
VALUES ('pao_gomez1@hotmail.com', 1, 'Paola', 'female', 1 , 'Gomez', 'password', '2107082724', 'pgomez');
INSERT INTO users (email, employee, first_name, gender, is_admin, last_name, password, phone, username)
VALUES ('aggiefan17@yahoo.com', 1, 'Terry', 'male', 1 , 'Hale', 'password', '2102755716', 'terryhale');

UPDATE child SET teacher_id = '4' WHERE id ='3';
UPDATE child SET teacher_id = '4' WHERE id ='4';

CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL UNIQUE,
  employee BIT(1) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  gender VARCHAR(255) NOT NULL,
  is_admin BIT(1) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE child (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  gender VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  dob VARCHAR(255) NOT NULL,
  parent_id INT(20),
  teacher_id INT(20),
  PRIMARY KEY (id),
  FOREIGN KEY (parent_id) REFERENCES users(id),
  FOREIGN KEY (teacher_id) REFERENCES users(id)
);

CREATE TABLE report_card (
  id INT NOT NULL AUTO_INCREMENT,
  `change` VARCHAR(255),
  comments VARCHAR(255),
  date VARCHAR(255),
  feeling VARCHAR(255),
  how_ate VARCHAR(255),
  items_needed VARCHAR(255),
  meal VARCHAR(255),
  sleep VARCHAR(255),
  child_id INT(20),
  PRIMARY KEY (id),
  FOREIGN KEY (child_id) REFERENCES child(id)
);

CREATE TABLE notification (
  id INT AUTO_INCREMENT,
  body TEXT,
  create_date DATETI

)