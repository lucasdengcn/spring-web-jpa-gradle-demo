CREATE TABLE IF NOT EXISTS customer (
    id int NOT NULL PRIMARY KEY,
    name varchar(20),
    email varchar(50),
    date_of_birth timestamp
);

-- create sequence
CREATE SEQUENCE customer_id_seq INCREMENT 50 OWNED BY customer.id;

-- use sequence for the target column
ALTER TABLE customer ALTER COLUMN id SET DEFAULT nextval('customer_id_seq');