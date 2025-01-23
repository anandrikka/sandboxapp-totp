CREATE TABLE access_tokens
(
  id            UUID PRIMARY KEY DEFAULT uuid_generate_v1(), -- Unique identifier for each login token record
  user_id       UUID NOT NULL, -- The user attempting to log in
  device_token  VARCHAR(300) NOT NULL, -- The associated device token
  auth_token   VARCHAR(10) NOT NULL, -- The token generated for login (not unique)
  expires_at    TIMESTAMP NOT NULL, -- When the token will expire
  created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp of when the token was created
  updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp of when the token was updated
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE -- Delete tokens when the user is deleted
);

ALTER TABLE devices
  ADD COLUMN remember_me BOOLEAN DEFAULT FALSE;