CREATE USER kcordero WITH PASSWORD 'sushimi11';
DROP TABLE IF EXIST employeedb;
CREATE DATABASE employeedb OWNER kcordero;

USE employeedb;

CREATE TABLE employees(
	emp_id SERIAL PRIMARY KEY,
	lastname VARCHAR(25) NOT NULL,
	firstname VARCHAR(55) NOT NULL,
	middlename VARCHAR(25) NOT NULL,
	suffix VARCHAR(25),
	title VARCHAR(25),
	str_no INTEGER NOT NULL,
	street VARCHAR(25) NOT NULL,
	brgy VARCHAR(30) NOT NULL,
	city VARCHAR(30) NOT NULL,
	zipcode VARCHAR(10) NOT NULL,
	birthdate DATE NOT NULL,
	gwa FLOAT NOT NULL,
	hiredate DATE NOT NULL,
	currentlyhired BOOL NOT NULL
);

CREATE TABLE contacts(
	contact_id SERIAL PRIMARY KEY,
	emp_id SERIAL REFERENCES employees(emp_id),
	contact_type VARCHAR(20),
	contact_value VARCHAR(30)
);

CREATE TABLE roles(
	role_id SERIAL PRIMARY KEY,
	role VARCHAR(25) NOT NULL
);

CREATE TABLE employee_role(
	emp_id INT REFERENCES employees(emp_id),
	role_id INT REFERENCES roles(role_id),
	PRIMARY KEY(emp_id,role_id)
);

INSERT INTO roles(role) VALUES ('CEO');
INSERT INTO roles(role) VALUES ('PRESIDENT');
INSERT INTO roles(role) VALUES ('SOFTWARE ENGINEER');
INSERT INTO roles(role) VALUES ('GRAPHICS DESIGNER');
INSERT INTO roles(role) VALUES ('WEB DEVELOPER');
INSERT INTO roles(role) VALUES ('WEB DESIGNER');
INSERT INTO roles(role) VALUES ('GAME DEVELOPER');
INSERT INTO roles(role) VALUES ('GUNDAM PILOT');

INSERT INTO employees(lastname, firstname, middlename, suffix, title, str_no, street, brgy, city, zipcode, birthdate, gwa, hiredate, currentlyhired)
	VALUES('Cordero','Kenver','Rosales','','Mr.',107,'Sta Cruz Street','Brgy 45', 'Tacloban City','6500','1994-07-06',2,'2017-02-06','t');

INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(1,'Landline','321-3563');
INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(1,'Mobile','0926-406-7246');
INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(1,'Email','kencords06@gmail.com');
INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(1,'Email','kencords@yahoo.com');

INSERT INTO employee_role(emp_id, role_id) VALUES(1,3);
INSERT INTO employee_role(emp_id, role_id) VALUES(1,4);
INSERT INTO employee_role(emp_id, role_id) VALUES(1,8);

INSERT INTO employees(lastname, firstname, middlename, suffix, title, str_no, street, brgy, city, zipcode, birthdate, gwa, hiredate, currentlyhired)
	VALUES('Jadulco','Sushmita','Suarez','','Ms.',11,'Hearts Street','Brgy 6', 'Catbalogan City','6300','1994-10-11',1.75,'2013-04-11','f');

INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(2,'Mobile','0905-148-1830');
INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(2,'Email','mitz_jadz@yahoo.com');

INSERT INTO employee_role(emp_id, role_id) VALUES(2,4);
INSERT INTO employee_role(emp_id, role_id) VALUES(2,5);

INSERT INTO employees(lastname, firstname, middlename, suffix, title, str_no, street, brgy, city, zipcode, birthdate, gwa, hiredate, currentlyhired)
	VALUES('Augus','Mikazuki','Tekkadan','','Mr.',09,'CGS','Brgy 01', 'Chryse City','2330','2000-07-11',2.75,'2015-10-04','t');

INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(3,'Email','barbatosLupus06@gmail.com');

INSERT INTO employee_role(emp_id, role_id) VALUES(3,8);

INSERT INTO employees(lastname, firstname, middlename, suffix, title, str_no, street, brgy, city, zipcode, birthdate, gwa, hiredate, currentlyhired)
	VALUES('Cordero','Kent Neil','Rosales','','Master',107,'Sta Cruz Street','Brgy 45', 'Tacloban City','6500','2005-10-11',1.0,'2013-04-11','f');

INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(4,'Landline','321-3563');
INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(4,'Email','kentneil11@gmail.com');

INSERT INTO employee_role(emp_id, role_id) VALUES(4,1);

INSERT INTO employees(lastname, firstname, middlename, suffix, title, str_no, street, brgy, city, zipcode, birthdate, gwa, hiredate, currentlyhired)
	VALUES('Cordero','Kurt','Rosales','','Master',107,'Sta Cruz Street','Brgy 45', 'Tacloban City','6500','2003-02-03',1.25,'2013-04-02','f');

INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(5,'Landline','321-3563');
INSERT INTO contacts(emp_id,contact_type,contact_value) VALUES(5,'Email','iamkurtcords03@gmail.com');

INSERT INTO employee_role(emp_id, role_id) VALUES(5,2);