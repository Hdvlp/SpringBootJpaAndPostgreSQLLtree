CREATE DATABASE customdevicesdb ENCODING UTF8;

\c customdevicesdb;

CREATE EXTENSION IF NOT EXISTS ltree;

CREATE TABLE devices_resource_tree (
    id SERIAL PRIMARY KEY,   
    name TEXT NOT NULL,   
    path LTREE NOT NULL );

CREATE INDEX idx_devices_resource_tree_path ON devices_resource_tree USING GIST (path);


INSERT INTO devices_resource_tree (name, path) VALUES
('Device Arthur', 'root.P'),
('Device Billy', 'root.P.B'),
('Device Gabriel', 'root.P.C.D'),
('Device Louise', 'root.Q.E');


SELECT * FROM devices_resource_tree t WHERE t.path <@ ANY('{root.Q.E}');

SELECT * FROM devices_resource_tree t WHERE t.path <@ ANY('{root.Q}');