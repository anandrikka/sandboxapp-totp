ALTER TABLE users
  ADD CONSTRAINT unique_email UNIQUE(email),
  ADD CONSTRAINT unique_phone_number UNIQUE(phone_number);