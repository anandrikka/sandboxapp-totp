DROP TABLE authentication_codes;

ALTER TABLE user_sessions RENAME TO user_device_sessions;

ALTER TABLE user_device_sessions
  DROP COLUMN refresh_token,
  DROP COLUMN refresh_token_expires_at,
  ADD COLUMN refresh_until TIMESTAMP NOT NULL;