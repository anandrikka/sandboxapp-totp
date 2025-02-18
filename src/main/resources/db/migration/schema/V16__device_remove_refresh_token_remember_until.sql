ALTER TABLE devices
  DROP COLUMN refresh_token,
  ADD COLUMN remember_until TIMESTAMP;

ALTER TABLE access_tokens
  DROP COLUMN device_id,
  ADD COLUMN device_token VARCHAR(300) NOT NULL;
