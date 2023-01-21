CREATE TABLE IF NOT EXISTS type (
  id serial primary key,
  name varchar
);

CREATE TABLE IF NOT EXISTS rule (
  id SERIAL PRIMARY KEY,
  name VARCHAR
);

CREATE TABLE IF NOT EXISTS accident (
  id serial primary key,
  name varchar,
  text varchar,
  address varchar,
  type_id int not null references type(id)
);


