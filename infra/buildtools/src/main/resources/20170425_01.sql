CREATE USER kcordero WITH PASSWORD 'sushimi11';
CREATE DATABASE employeedb OWNER kcordero;

CREATE TABLE addresses(
	addr_id SERIAL PRIMARY KEY,
	str_no VARCHAR(20) NOT NULL,
	street VARCHAR(25) NOT NULL,
	brgy VARCHAR(30) NOT NULL,
	city VARCHAR(30) NOT NULL,
	zipcode VARCHAR(10) NOT NULL
);

CREATE TABLE contacts(
	contact_id SERIAL PRIMARY KEY,
	landline VARCHAR(20),
	mobile VARCHAR(20),
	email VARCHAR(45)
);

CREATE TABLE employees (
	emp_id SERIAL PRIMARY KEY,
	lastname VARCHAR(25) NOT NULL,
	firstname VARCHAR(55) NOT NULL,
	middlename VARCHAR(25) NOT NULL,
	suffix VARCHAR(25),
	title VARCHAR(25),
	addr_id INT REFERENCES addresses(addr_id),
	contact_id INT REFERENCES contacts(contact_id),
	birthdate DATE NOT NULL,
	gwa FLOAT NOT NULL,
	hiredate DATE NOT NULL,
	currentlyhired BOOL NOT NULL
);

CREATE TABLE roles(
	role_id SERIAL PRIMARY KEY,
	role VARCHAR(25) NOT NULL
);

CREATE TABLE employee_role(
	emp_id INT REFERENCES employees(emp_id),
	role_id INT REFERENCES roles(role_id),
	primary key(emp_id,role_id)
);


INSERT INTO roles(role) VALUES ('CEO');
INSERT INTO roles(role) VALUES ('PRESIDENT');
INSERT INTO roles(role) VALUES ('SOFTWARE ENGINEER');
INSERT INTO roles(role) VALUES ('GRAPHICS DESIGNER');
INSERT INTO roles(role) VALUES ('WEB DEVELOPER');
INSERT INTO roles(role) VALUES ('WEB DESIGNER');

INSERT INTO addresses(str_no, street, brgy, city, zipcode) 
	VALUES (107,'Sta. Cruz Street','Brgy 45', 'Tacloban City','6500');

INSERT INTO contacts(landline, mobile, email)
	VALUES('321-3563','0926-406-7246','kencords06@gmail.com');

INSERT INTO employees(lastname, firstname, middlename, suffix, title, addr_id, contact_id, birthdate, gwa, hiredate, currentlyhired)
	VALUES('Cordero','Kenver','Rosales','','Mr.',1, 1,'1994-07-06',2,'2017-02-06','t');

INSERT INTO employee_role(emp_id, role_id) VALUES(1,3);

INSERT INTO addresses(str_no, street, brgy, city, zipcode) 
	VALUES (11,'Hearts Street','Brgy 6', 'Catbalogan City','6300');

INSERT INTO contacts(landline, mobile, email)
	VALUES('','0905-148-1830','mitz_jadz@yahoo.com');

INSERT INTO employees(lastname, firstname, middlename, suffix, title, addr_id, contact_id, birthdate, gwa, hiredate, currentlyhired)
	VALUES('Jadulco','Sushmita','Suarez','','Ms.',2, 2,'1994-10-11',1.75,'2013-04-11','f');

INSERT INTO employee_role(emp_id, role_id) VALUES(2,5);

