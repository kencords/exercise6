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

CREATE TABLE employees (
	emp_id SERIAL PRIMARY KEY,
	lastname VARCHAR(25) NOT NULL,
	firstname VARCHAR(55) NOT NULL,
	middlename VARCHAR(25) NOT NULL,
	suffix VARCHAR(25),
	title VARCHAR(25),
	address INT REFERENCES addresses(addr_id),
	birthdate DATE NOT NULL,
	gwa FLOAT NOT NULL,
	hiredate DATE NOT NULL,
	currentlyhired BOOL NOT NULL
);

CREATE TABLE contacts(
	emp_id INT REFERENCES employees(emp_id),
	landline VARCHAR(20),
	mobile VARCHAR(20),
	email VARCHAR(45)
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
