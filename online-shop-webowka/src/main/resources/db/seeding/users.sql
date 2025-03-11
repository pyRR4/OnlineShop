--liquibase formatted sql
--changeset igor:4-seed-users

INSERT INTO users (username, password, role) VALUES
    ('john_doe', 'password123', (SELECT id FROM roles WHERE name = 'user')),
    ('jane_smith', 'securepass', (SELECT id FROM roles WHERE name = 'user')),
    ('admin_igor', 'adminpass', (SELECT id FROM roles WHERE name = 'admin'));