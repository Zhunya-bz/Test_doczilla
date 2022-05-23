CREATE TABLE IF NOT EXISTS students (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    surname         VARCHAR(100) NOT NULL,
    patronymic      VARCHAR(100) NOT NULL,
    dateOfBirth     DATE NOT NULL,
    groups          INT NOT NULL
);
