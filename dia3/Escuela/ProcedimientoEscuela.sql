-- ##################################
-- ######## procedimientos ##########
-- ##################################
-- 1.Crear un nuevo alumno:
CREATE OR REPLACE PROCEDURE crear_alumno(
    p_nif VARCHAR(9),
    p_nombre VARCHAR(25),
    p_apellido1 VARCHAR(50),
    p_apellido2 VARCHAR(50),
    p_ciudad VARCHAR(25),
    p_direccion VARCHAR(50),
    p_telefono VARCHAR(9),
    p_fecha_nacimiento DATE,
    p_sexo sexo
)
AS
BEGIN
    INSERT INTO alumno (nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fecha_nacimiento, sexo)
    VALUES (p_nif, p_nombre, p_apellido1, p_apellido2, p_ciudad, p_direccion, p_telefono, p_fecha_nacimiento, p_sexo);
END;

-- 2.Actualizar datos de un alumno:
CREATE OR REPLACE PROCEDURE actualizar_alumno(
    p_id INT,
    p_nif VARCHAR(9),
    p_nombre VARCHAR(25),
    p_apellido1 VARCHAR(50),
    p_apellido2 VARCHAR(50),
    p_ciudad VARCHAR(25),
    p_direccion VARCHAR(50),
    p_telefono VARCHAR(9),
    p_fecha_nacimiento DATE,
    p_sexo sexo
)
AS
BEGIN
    UPDATE alumno
    SET nif = p_nif,
        nombre = p_nombre,
        apellido1 = p_apellido1,
        apellido2 = p_apellido2,
        ciudad = p_ciudad,
        direccion = p_direccion,
        telefono = p_telefono,
        fecha_nacimiento = p_fecha_nacimiento,
        sexo = p_sexo
    WHERE id = p_id;
END;

-- 3.Eliminar un alumno por ID:
CREATE OR REPLACE PROCEDURE eliminar_alumno(p_id INT)
AS
BEGIN
    DELETE FROM alumno WHERE id = p_id;
END;

-- 4.Buscar un alumno por NIF:
CREATE OR REPLACE FUNCTION buscar_alumno_por_nif(p_nif VARCHAR(9))
RETURNS TABLE (id INT, nif VARCHAR(9), nombre VARCHAR(25), apellido1 VARCHAR(50), apellido2 VARCHAR(50), ciudad VARCHAR(25), direccion VARCHAR(50), telefono VARCHAR(9), fecha_nacimiento DATE, sexo sexo)
AS
$$
BEGIN
    RETURN QUERY
    SELECT * FROM alumno WHERE nif = p_nif;
END;
$$
LANGUAGE plpgsql;

-- 5.Crear un nuevo profesor:
CREATE OR REPLACE PROCEDURE crear_profesor(
    p_nif VARCHAR(25),
    p_nombre VARCHAR(25),
    p_apellido1 VARCHAR(50),
    p_apellido2 VARCHAR(50),
    p_ciudad VARCHAR(25),
    p_direccion VARCHAR(50),
    p_telefono VARCHAR(9),
    p_fecha_nacimiento DATE,
    p_sexo sexo,
    p_id_departamento INT
)
AS
BEGIN
    INSERT INTO profesor (nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fecha_nacimiento, sexo, id_departamento)
    VALUES (p_nif, p_nombre, p_apellido1, p_apellido2, p_ciudad, p_direccion, p_telefono, p_fecha_nacimiento, p_sexo, p_id_departamento);
END;

-- 6.Actualizar datos de un profesor:
CREATE OR REPLACE PROCEDURE actualizar_profesor(
    p_id_profesor INT,
    p_nif VARCHAR(25),
    p_nombre VARCHAR(25),
    p_apellido1 VARCHAR(50),
    p_apellido2 VARCHAR(50),
    p_ciudad VARCHAR(25),
    p_direccion VARCHAR(50),
    p_telefono VARCHAR(9),
    p_fecha_nacimiento DATE,
    p_sexo sexo,
    p_id_departamento INT
)
AS
BEGIN
    UPDATE profesor
    SET nif = p_nif,
        nombre = p_nombre,
        apellido1 = p_apellido1,
        apellido2 = p_apellido2,
        ciudad = p_ciudad,
        direccion = p_direccion,
        telefono = p_telefono,
        fecha_nacimiento = p_fecha_nacimiento,
        sexo = p_sexo,
        id_departamento = p_id_departamento
    WHERE id_profesor = p_id_profesor;
END;

-- 7.Eliminar un profesor por ID:
CREATE OR REPLACE PROCEDURE eliminar_profesor(p_id_profesor INT)
AS
BEGIN
    DELETE FROM profesor WHERE id_profesor = p_id_profesor;
END;

-- 8.Buscar un profesor por NIF:
CREATE OR REPLACE FUNCTION buscar_profesor_por_nif(p_nif VARCHAR(25))
RETURNS TABLE (id_profesor INT, nif VARCHAR(25), nombre VARCHAR(25), apellido1 VARCHAR(50), apellido2 VARCHAR(50), ciudad VARCHAR(25), direccion VARCHAR(50), telefono VARCHAR(9), fecha_nacimiento DATE, sexo sexo, id_departamento INT)
AS
$$
BEGIN
    RETURN QUERY
    SELECT * FROM profesor WHERE nif = p_nif;
END;
$$
LANGUAGE plpgsql;

-- 9.Crear una nueva asignatura:
CREATE OR REPLACE PROCEDURE crear_asignatura(
    p_nombre VARCHAR(100),
    p_creditos FLOAT,
    p_tipo tipo_asignatura,
    p_curso SMALLINT,
    p_cuatrimestre SMALLINT,
    p_id_profesor INT,
    p_id_grado INT
)
AS
BEGIN
    INSERT INTO asignatura (nombre, creditos, tipo, curso, cuatrimestre, id_profesor, id_grado)
    VALUES (p_nombre, p_creditos, p_tipo, p_curso, p_cuatrimestre, p_id_profesor, p_id_grado);
END;

-- 10.Actualizar una asignatura:
CREATE OR REPLACE PROCEDURE actualizar_asignatura(
    p_id INT,
    p_nombre VARCHAR(100),
    p_creditos FLOAT,
    p_tipo tipo_asignatura,
    p_curso SMALLINT,
    p_cuatrimestre SMALLINT,
    p_id_profesor INT,
    p_id_grado INT
)
AS
BEGIN
    UPDATE asignatura
    SET nombre = p_nombre,
        creditos = p_creditos,
        tipo = p_tipo,
        curso = p_curso,
        cuatrimestre = p_cuatrimestre,
        id_profesor = p_id_profesor,
        id_grado = p_id_grado
    WHERE id = p_id;
END;
                                        



















