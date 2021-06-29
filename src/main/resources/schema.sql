
DROP TABLE IF EXISTS BOOKINGS;

CREATE TABLE  BOOKINGS(ID serial, FIRST_NAME varchar(5) NOT NULL);

DROP TABLE IF EXISTS city;
CREATE TABLE city
(
  id      INT PRIMARY KEY auto_increment,
  name    VARCHAR,
  state   VARCHAR,
  country VARCHAR
);

DROP TABLE IF EXISTS Products;

CREATE TABLE Products
(
    prod_id     IDENTITY        PRIMARY KEY,
    prod_name   VARCHAR(255)    NOT NULL,
    prod_price  INT             NOT NULL
);
