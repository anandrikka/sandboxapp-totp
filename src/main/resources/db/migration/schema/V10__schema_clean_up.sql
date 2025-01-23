ALTER TABLE users
  DROP COLUMN phone_number;

ALTER TABLE users
  ADD COLUMN phone_number VARCHAR(20),
  ADD CONSTRAINT unique_phone_number UNIQUE(phone_number);

