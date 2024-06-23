# ¡Tarea acceso a datos!
## Descripcion de avance:
Actualmente el programa es capaz de: 
- Mostrar todos los registros
- Insertar un nuevo registro
- Actualizar un registro (*)
- Eliminar un registro

(*) Se considera que el programa es capaz de actualizar un registro si es capaz de modificar los siguientes campos:

| Tabla     | Columna                 |
|-----------|-------------------------|
| PILOTO    | Poles, Victorias        |
| ESCUDERIA | Campeonatos, Monoplaza  | 

## Descripcion manejo de transacciones:
El programa maneja las transacciones implementando 3 metodos:
1.- startTransaction(): Establece que la conexión no se auto-confirme (setAutoCommit(false)), indicando el inicio de una transacción.
2.- commitTransaction(): Confirma todas las operaciones realizadas durante la transacción (commit()), haciendo persistentes los cambios.
3.- rollbackTransaction(): En caso de error, este método revierte todas las operaciones realizadas durante la transacción (rollback()).

## Descripcion control de concurrencia:
Para evitar que operaciones simultáneas sobre la base de datos generen inconsistencias, se utilizan 2 niveles de aisalamiento de transacciones:
1.- Read Committed: Se asegura que una transacción no pueda leer datos que hayan sido modificados por otra transacción que aún no ha confirmado sus cambios.  
```java
connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
startTransaction();
```
2.- Serializable: Se asegura que una transacción no pueda leer datos que hayan sido modificados por otra transacción que aún no ha confirmado sus cambios, y que otra transacción no pueda modificar datos que hayan sido leídos por otra transacción que aún no ha confirmado sus cambios.
```java
connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
startTransaction();
```


