-- Users Table
CREATE TABLE users
(
  id         UUID PRIMARY KEY DEFAULT uuid_generate_v1(),
  username   VARCHAR(255) NOT NULL UNIQUE,
  email      VARCHAR(255) NOT NULL UNIQUE,
  created_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);

-- Accounts Table
CREATE TABLE accounts
(
  id           UUID PRIMARY KEY DEFAULT uuid_generate_v1(),
  user_id      UUID         NOT NULL,
  account_name VARCHAR(255) NOT NULL,
  issuer       VARCHAR(255),
  totp_secret  VARCHAR(255) NOT NULL,
  created_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- Devices Table
CREATE TABLE devices
(
  id           UUID PRIMARY KEY DEFAULT uuid_generate_v1(),
  user_id      UUID         NOT NULL,
  device_name  VARCHAR(255) NOT NULL,
  device_token VARCHAR(255) NOT NULL UNIQUE,
  public_key   TEXT, -- Optional for additional security
  created_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- User Sessions Table
CREATE TABLE user_sessions
(
  id                       UUID PRIMARY KEY DEFAULT uuid_generate_v1(),
  user_id                  UUID         NOT NULL,
  device_id                UUID         NOT NULL,
  refresh_token            VARCHAR(255) NOT NULL UNIQUE,
  refresh_token_expires_at TIMESTAMP    NOT NULL,
  created_at               TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_at               TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (device_id) REFERENCES devices (id) ON DELETE CASCADE
);

-- OTP Table
CREATE TABLE authentication_codes
(
  id         UUID PRIMARY KEY DEFAULT uuid_generate_v1(),
  user_id    UUID       NOT NULL,
  otp_code   VARCHAR(6) NOT NULL,
  expires_at TIMESTAMP  NOT NULL,
  consumed   BOOLEAN          DEFAULT FALSE,
  created_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
