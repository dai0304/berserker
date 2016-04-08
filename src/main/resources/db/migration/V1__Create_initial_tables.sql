CREATE TABLE users (
    username VARCHAR(32) PRIMARY KEY,
    password CHAR(60) NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

CREATE TABLE events (
    event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(128) NOT NULL,
    event_owner VARCHAR(32),
    FOREIGN KEY (event_owner) REFERENCES users (username)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

CREATE TABLE participations (
    username VARCHAR(32),
    event_id BIGINT,
    PRIMARY KEY (username, event_id),
    FOREIGN KEY (username) REFERENCES users (username),
    FOREIGN KEY (event_id) REFERENCES events (event_id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

CREATE TABLE pictures (
    picture_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    event_id BIGINT NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events (event_id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

INSERT INTO users
    (username, password)
VALUES
    ('miyamoto', '$2a$10$cPnF0sq.bCPHeGuzVagOgOmbe2spT1Uh1k9LyuS0jzb5F3Lm.9kEy'),
    ('yokota',   '$2a$10$nkvNPCb3Y1z/GSearD7s7OBdS9BoLBss3D4enbFQIgNJDvr0Xincm');
