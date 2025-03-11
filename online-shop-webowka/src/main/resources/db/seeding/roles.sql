--liquibase formatted sql
--changeset igor:3-seed-roles

INSERT INTO roles (name) VALUES
 ('user'),
 ('admin');