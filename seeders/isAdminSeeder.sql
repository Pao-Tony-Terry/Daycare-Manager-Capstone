USE daycare_db;


INSERT INTO users (email, employee, first_name, gender, is_admin, last_name, password, phone, username)
VALUES ('pao_gomez1@hotmail.com', 1, 'Paola', 'female', 1 , 'Gomez', '$2a$10$/.TRNP9uaafBhGjc9WwSneeP9wUJhjiG.bIG.Rg68wdGqp3TQapLC', '2107082724', 'johannagomez');
INSERT INTO users (email, employee, first_name, gender, is_admin, last_name, password, phone, username)
VALUES ('aggiefan17@yahoo.com', 1, 'Terry', 'male', 1 , 'Hale', '$2a$10$/.TRNP9uaafBhGjc9WwSneeP9wUJhjiG.bIG.Rg68wdGqp3TQapLC', '2102755716', 'terryhale');


# this is just an example to assign teachers to the kids:
UPDATE child SET teacher_id = '4' WHERE id ='3';
UPDATE child SET teacher_id = '4' WHERE id ='4';
