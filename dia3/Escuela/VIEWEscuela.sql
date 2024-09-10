-- ########################
-- ######## VIEW ##########
-- ########################
-- 1.Listado de alumnas matriculadas en un grado específico:
CREATE VIEW vistas_alumnas_grado AS
SELECT DISTINCT a.*
FROM alumno a
JOIN alumno_se_matricula_asignatura am ON a.id = am.id_alumno
JOIN asignatura asig ON am.id_asignatura = asig.id
JOIN grado g ON asig.id_grado = g.id
WHERE g.nombre = 'Grado en Ingeniería Informática (Plan 2015)' AND a.sexo = 'M';

-- 2.Asignaturas ofertadas en un grado específico:
CREATE VIEW vistas_asignaturas_grado AS
SELECT asig.nombre
FROM asignatura asig
JOIN grado g ON asig.id_grado = g.id
WHERE g.nombre = 'Grado en Ingeniería Informática (Plan 2015)';

-- 3.Profesores y departamentos asociados:
CREATE VIEW vistas_profesores_departamentos AS
SELECT p.apellido1, p.apellido2, p.nombre, d.nombre AS nombre_departamento
FROM profesor p
JOIN departamento d ON p.id_departamento = d.id;

-- 4.Asignaturas y años del curso escolar para un alumno específico:
CREATE VIEW vistas_asignaturas_alumno AS
SELECT asig.nombre, cs.anyo_inicio, cs.anyo_fin
FROM alumno_se_matricula_asignatura am
JOIN asignatura asig ON am.id_asignatura = asig.id
JOIN curso_escolar cs ON am.id_curso_escolar = cs.id
JOIN alumno a ON am.id_alumno = a.id
WHERE a.nif = '26902806M';

-- 5.Departamentos con profesores que imparten asignaturas:
CREATE VIEW vistas_departamentos_con_asignaturas AS
SELECT DISTINCT d.nombre
FROM departamento d
JOIN profesor p ON d.id = p.id_departamento
JOIN asignatura a ON p.id_profesor = a.id_profesor;

-- 6.Alumnos matriculados en el curso escolar 2018/2019:
CREATE VIEW vistas_alumnos_curso_2018_2019 AS
SELECT DISTINCT a.*
FROM alumno a
JOIN alumno_se_matricula_asignatura am ON a.id = am.id_alumno
JOIN curso_escolar cs ON am.id_curso_escolar = cs.id
WHERE cs.anyo_inicio = 2018 AND cs.anyo_fin = 2019;

-- 7.Profesores y departamentos asociados, incluyendo profesores sin departamento:
CREATE VIEW vistas_profesores_departamentos_incluidos AS
SELECT d.nombre AS nombre_departamento, p.apellido1, p.apellido2, p.nombre
FROM profesor p
LEFT JOIN departamento d ON p.id_departamento = d.id;

-- 8.Departamentos sin profesores asociados:
CREATE VIEW vistas_departamentos_sin_profesores AS
SELECT d.*
FROM departamento d
LEFT JOIN profesor p ON d.id = p.id_departamento
WHERE p.id_profesor IS NULL;

-- 9.Asignaturas sin profesor asignado:
CREATE VIEW vistas_asignaturas_sin_profesor AS
SELECT a.*
FROM asignatura a
LEFT JOIN profesor p ON a.id_profesor = p.id_profesor
WHERE p.id_profesor IS NULL;

-- 10.Departamentos con asignaturas no impartidas en ningún curso escolar:
CREATE VIEW vistas_departamentos_asignaturas_no_impartidas AS
SELECT DISTINCT d.nombre AS nombre_departamento, a.nombre AS nombre_asignatura
FROM departamento d
JOIN profesor p ON d.id = p.id_departamento
JOIN asignatura a ON p.id_profesor = a.id_profesor
LEFT JOIN alumno_se_matricula_asignatura am ON a.id = am.id_asignatura
WHERE am.id_curso_escolar IS NULL;












