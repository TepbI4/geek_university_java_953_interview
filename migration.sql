DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students (id bigserial PRIMARY KEY, name VARCHAR(255), score numeric(6, 0));
INSERT INTO students (name, score) VALUES
('John Smith', 100),
('Jake Gyllenhaal', 80),
('Paul Newman', 87),
('Kate Hudson', 99),
('Alex Ferguson', 100),
('Khaal Drogo', 20),
('Batman', 110),
('Peter Pan ', 77),
('Johnny Depp', 97),
('Kate Beckinsale', 93);