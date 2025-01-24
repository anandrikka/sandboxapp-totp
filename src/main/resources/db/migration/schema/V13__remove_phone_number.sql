ALTER TABLE users
  DROP COLUMN phone_number,
  ADD COLUMN activated bool DEFAULT FALSE;

ALTER TABLE access_tokens
  ADD COLUMN verified bool DEFAULT FALSE;

