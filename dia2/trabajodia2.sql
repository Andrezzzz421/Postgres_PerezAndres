create database trabajo;

create table personas(
	id_persona SERIAL primary key,
	nombre varchar(100),
	apellido varchar(100),
	municipio_nacimiento varchar(100),
	municipio_domicilio varchar(100)
);

create table regiones_municipios_departamentos(
	id_region SERIAL primary key,
	region varchar(80),
	departamento varchar(80),
	codigo_departamento int,
	municipio varchar(80),
	codigo_municipio int 
);



-- ##########################################################################################################
-- 1. crear una vista que muestr elas regiones con sus respectivos departamentos
-- en esta vista generar una columna que muestre la cantidad de municipios por cada departamento
drop view vista_departamentos_municipios;

create view vista_regiones_departamentos as
select 
    region,
    departamento,
    codigo_departamento,  
    count(municipio) as cantidad_municipios
from 
    regiones_municipios_departamentos
group by 
    region, departamento, codigo_departamento;
    
select * from vista_regiones_departamentos;

-- ##########################################################################################################
   
-- 2. crear una vista que muestre los departamentos con sus respectivos municipios
-- En esta vista generar la columna de codigo de municipio completo, esto es, codigo
-- de departamento concatenado con el codigo de municipio.
create view vista_departamentos_municipios as
select 
    departamento,
    municipio,
    codigo_departamento,
    codigo_municipio,
    concat(codigo_departamento, codigo_municipio) AS codigo_municipio_completo
from 
    regiones_municipios_departamentos;
    
   select * from vista_departamentos_municipios;
  
  -- ##########################################################################################################
   
  -- 3.Agregar dos columnas a la tabla municipios que permitan llevar el conteo de personas que viven y trabajan 
  -- en cada municipio, y con base en esas columnas, implementar un disparador que actualice esos conteos toda vez 
  -- que se agregue, modifique o elimine un dato de municpio de nacimiento y/o de domicilio.
  
ALTER TABLE regiones_municipios_departamentos
ADD COLUMN cantidad_residentes INT DEFAULT 0,
ADD COLUMN cantidad_trabajadores INT DEFAULT 0;
  
-- 4. Crear el disparador (trigger) para actualizar los conteos cada vez que se agregue, modifique o elimine un dato en las columnas 
-- de municipio de nacimiento y domicilio de la tabla personas.
  
CREATE OR REPLACE FUNCTION actualizar_conteos() RETURNS TRIGGER AS $$
BEGIN
    -- Si se inserta o actualiza una persona
    IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
        -- Actualizar el municipio de nacimiento (cantidad_trabajadores)
        IF (NEW.municipio_nacimiento IS DISTINCT FROM OLD.municipio_nacimiento) THEN
            -- Decrementar conteo en el municipio anterior
            IF (TG_OP = 'UPDATE' AND OLD.municipio_nacimiento IS NOT NULL) THEN
                UPDATE regiones_municipios_departamentos 
                SET cantidad_trabajadores = cantidad_trabajadores - 1 
                WHERE municipio = OLD.municipio_nacimiento;
            END IF;
            -- Incrementar conteo en el nuevo municipio
            UPDATE regiones_municipios_departamentos 
            SET cantidad_trabajadores = cantidad_trabajadores + 1 
            WHERE municipio = NEW.municipio_nacimiento;
        END IF;

        -- Actualizar el municipio de domicilio (cantidad_residentes)
        IF (NEW.municipio_domicilio IS DISTINCT FROM OLD.municipio_domicilio) THEN
            -- Decrementar conteo en el municipio anterior
            IF (TG_OP = 'UPDATE' AND OLD.municipio_domicilio IS NOT NULL) THEN
                UPDATE regiones_municipios_departamentos 
                SET cantidad_residentes = cantidad_residentes - 1 
                WHERE municipio = OLD.municipio_domicilio;
            END IF;
            -- Incrementar conteo en el nuevo municipio
            UPDATE regiones_municipios_departamentos 
            SET cantidad_residentes = cantidad_residentes + 1 
            WHERE municipio = NEW.municipio_domicilio;
        END IF;
    END IF;

    -- Si se elimina una persona
    IF (TG_OP = 'DELETE') THEN
        -- Decrementar ambos contadores en el municipio correspondiente
        UPDATE regiones_municipios_departamentos 
        SET cantidad_trabajadores = cantidad_trabajadores - 1 
        WHERE municipio = OLD.municipio_nacimiento;

        UPDATE regiones_municipios_departamentos 
        SET cantidad_residentes = cantidad_residentes - 1 
        WHERE municipio = OLD.municipio_domicilio;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_actualizar_conteos
AFTER INSERT OR UPDATE OR DELETE ON personas
FOR EACH ROW
EXECUTE FUNCTION actualizar_conteos();

CREATE OR REPLACE VIEW vista_departamentos_municipios AS
SELECT 
    departamento,
    municipio,
    codigo_departamento,
    codigo_municipio,
    CONCAT(codigo_departamento, codigo_municipio) AS codigo_municipio_completo,
    cantidad_residentes,
    cantidad_trabajadores
FROM 
    regiones_municipios_departamentos;
   
INSERT INTO personas (nombre, apellido, municipio_nacimiento, municipio_domicilio)
VALUES ('Juan', 'Pérez', 'Girón', 'Bucaramanga');

   SELECT * FROM vista_departamentos_municipios;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  