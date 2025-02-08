CREATE TABLE activation_tokens (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v1(),
  user_id UUID NOT NULL,
  token CHAR(512) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  expires_at TIMESTAMP NOT NULL,
  used_at TIMESTAMP,
  CONSTRAINT fk_activation_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  UNIQUE(token)
);

ALTER TABLE access_tokens
  DROP COLUMN device_token,
  DROP COLUMN verified,
  ADD COLUMN device_id UUID NOT NULL,
  ADD COLUMN used_at TIMESTAMP,
  ADD CONSTRAINT fk_access_token_device FOREIGN KEY (device_id)
    REFERENCES devices(id) ON DELETE CASCADE;

