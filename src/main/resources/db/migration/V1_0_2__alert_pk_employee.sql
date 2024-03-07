-- create sequence
CREATE SEQUENCE employee_id_seq OWNED BY employee.id;

-- use sequence for the target column
ALTER TABLE employee ALTER COLUMN id SET DEFAULT nextval('employee_id_seq');
