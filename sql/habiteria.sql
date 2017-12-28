DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS habits CASCADE;
DROP TABLE IF EXISTS calendar_records CASCADE;
DROP TABLE IF EXISTS karma CASCADE;

CREATE TABLE users
(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  password VARCHAR(255) NOT NULL
);

CREATE TABLE karma
(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  owner_id BIGINT NOT NULL,
  value INT,
  actual_time TIMESTAMP
);

CREATE TABLE habits
(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  description VARCHAR(255),
  name VARCHAR(255),
  user_id BIGINT NOT NULL,
  start TIMESTAMP NOT NULL,
  "end" TIMESTAMP NOT NULL,
  type VARCHAR(32) NOT NULL
);

CREATE TABLE calendar_records
(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  end_doing TIMESTAMP,
  end_verifying TIMESTAMP,
  repeat INTEGER NOT NULL,
  required BOOLEAN,
  start_doing TIMESTAMP,
  start_verifying TIMESTAMP,
  status VARCHAR(255),
  habit_id BIGINT
);

CREATE UNIQUE INDEX uk_username ON users (username);
CREATE UNIQUE INDEX uk_email ON users (email);
CREATE UNIQUE INDEX uk_karma ON karma (owner_id);

ALTER TABLE calendar_records ADD CONSTRAINT fk_records_habit FOREIGN KEY (habit_id) REFERENCES habits (id);
ALTER TABLE habits ADD CONSTRAINT fk_habits_user FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE karma ADD CONSTRAINT fk_karma_owner FOREIGN KEY (owner_id) REFERENCES users (id);