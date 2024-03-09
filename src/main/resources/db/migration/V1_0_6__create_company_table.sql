CREATE TABLE IF NOT EXISTS companies (
    id int NOT NULL PRIMARY KEY,
    name varchar(20),
    created_by varchar(20),
    created_time timestamp,
    updated_by varchar(20),
    updated_time timestamp
);

-- create sequence
CREATE SEQUENCE company_id_seq INCREMENT 50 OWNED BY companies.id;

-- use sequence for the target column
ALTER TABLE companies ALTER COLUMN id SET DEFAULT nextval('company_id_seq');