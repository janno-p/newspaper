CREATE TABLE newspaper (
    newspaper_id SERIAL NOT NULL,
    name VARCHAR(50) NOT NULL,
    founded_at DATE NOT NULL,
    description TEXT,
    CONSTRAINT newspaper_pk PRIMARY KEY (newspaper_id)
);

INSERT INTO newspaper (name, founded_at) VALUES ('Eesti Päevaleht', TO_DATE('16.12.1905', 'DD.MM.YYYY'));
INSERT INTO newspaper (name, founded_at) VALUES ('Postimees', TO_DATE('05.06.1857', 'DD.MM.YYYY'));
INSERT INTO newspaper (name, founded_at) VALUES ('The New York Times', TO_DATE('18.09.1851', 'DD.MM.YYYY'));
