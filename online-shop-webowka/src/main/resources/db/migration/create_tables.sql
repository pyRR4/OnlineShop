--liquibase formatted sql
--changeset igor:1-create-products-table

CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category integer REFERENCES categories(id) ON DELETE CASCADE,
    price NUMERIC(10, 2),
    weight NUMERIC(10, 2)
);