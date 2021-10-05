CREATE TABLE accident_types (
  id serial PRIMARY KEY,
  name VARCHAR (2000) NOT NULL
);

 CREATE TABLE rules (
  id serial PRIMARY KEY,
   name VARCHAR (2000) NOT NULL
    );

 CREATE TABLE accidents (
   id serial PRIMARY KEY,
   name VARCHAR (2000) NOT NULL,
   text VARCHAR (2000) NOT NULL,
   address VARCHAR (2000) NOT NULL,
   status VARCHAR (50) NOT NULL DEFAULT 'Accepted',
   id_accident_types INTEGER REFERENCES accident_types(id)
 );

 CREATE TABLE accidents_rules (
   accidents_id INT NOT NULL,
  rules_id INT NOT NULL,
     CONSTRAINT FK_ACCIDENT_ID FOREIGN KEY (accidents_id)
       REFERENCES accidents (id),
   CONSTRAINT FK_RULE_ID FOREIGN KEY (rules_id)
       REFERENCES rules (id)
 );

 INSERT INTO accident_types (id, name) VALUES(1, 'Две машины');
 INSERT INTO accident_types (id, name) VALUES(2, 'Машина и человек');
 INSERT INTO accident_types (id, name) VALUES(3, 'Машина и велосипед');

 INSERT INTO rules (id, name) VALUES(1, 'Статья. 1');
 INSERT INTO rules (id, name) VALUES(2, 'Статья. 2');
 INSERT INTO rules (id, name) VALUES(3, 'Статья. 3');