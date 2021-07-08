DROP TABLE IF EXISTS PHONES;

CREATE TABLE PHONES(
  id INT AUTO_INCREMENT,
  client_id uuid NOT NULL,
  number VARCHAR(20) NOT NULL,
  city_code VARCHAR(5) NOT NULL,
  country_code VARCHAR(5) NOT NULL,
  PRIMARY KEY (id, client_id)
);

DROP TABLE IF EXISTS CLIENTS;

CREATE TABLE CLIENTS(
  id uuid default random_uuid() PRIMARY KEY,
  name varchar(200) not null,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(20) NOT NULL,
  created date NOT NULL,
  modified date,
  last_login date,
  token varchar(5000),
  active boolean
);