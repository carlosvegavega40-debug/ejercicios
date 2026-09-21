# Registro de estudiantes (Java + MySQL)

Proyecto NetBeans (Java with Ant) que guarda datos en MySQL.

- Base de datos: `Registro`
- Tabla: `estudiante` (id, nombre, apellido, edad, ciudad)
- Formulario: `registro.FormRegistro` (Nombre, Apellido, Edad, Ciudad, Agregar, Limpiar, Salir)
- Conexión: `registro.Conexion` (usuario `root`, clave vacía; cambiar en `PASS` si hace falta)
- Script SQL: `registro.sql`

## Cómo ejecutarlo
1. Ejecutar `registro.sql` en MySQL (crea la base y la tabla).
2. Copiar `mysql-connector-j-9.7.0.jar` a la carpeta `lib/` (ver `lib/LEEME.txt`).
3. Abrir el proyecto en NetBeans y ejecutar `FormRegistro`.
