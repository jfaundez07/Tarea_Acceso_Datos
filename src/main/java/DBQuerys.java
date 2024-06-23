import java.sql.*;

public class DBQuerys {

    Connection connection;

    public DBQuerys(){
        try {
            this.connection =  DB.connect();
            System.out.println("Conexion exitosa a la base de datos");

        } catch (SQLException e) {
            System.err.println( "Error de conexion: " + e.getMessage());
        }
    }

    public void SELECT(String tabla) { //* Este select es para mostrar todos los registros de una tabla
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            startTransaction();

            String sql = "SELECT * FROM " + tabla + ";";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            switch (tabla) {
                case "PILOTO":
                    while (resultSet.next()) {
                        int numeroCarrera = resultSet.getInt("numeroCarrera");
                        String nombre = resultSet.getString("nombre");
                        String edad = resultSet.getString("apellido");
                        String nacionalidad = resultSet.getString("nacionalidad");
                        String fechaNac = resultSet.getString("fechaNac");
                        int poles = resultSet.getInt("poles");
                        int victorias = resultSet.getInt("victorias");
                        int idEscuderia = resultSet.getInt("idEscuderia");
                        System.out.println(
                                "\n------------------------------------\n" +
                                " Numero de Carrera: " + numeroCarrera + "\n" +
                                " Nombre: " + nombre + "\n" +
                                " Apellido: " + edad + "\n" +
                                " Nacionalidad: " + nacionalidad + "\n" +
                                " Fecha de Nacimiento: " + fechaNac + "\n" +
                                " Poles: " + poles + "\n" +
                                " Victorias: " + victorias + "\n" +
                                " ID Escuderia: " + idEscuderia
                        );
                    }
                    break;

                case "ESCUDERIA":
                    while (resultSet.next()) {
                        int idEscuderia = resultSet.getInt("idEscuderia");
                        String nombre = resultSet.getString("nombre");
                        String pais = resultSet.getString("pais");
                        String director = resultSet.getString("director");
                        int campeonatos = resultSet.getInt("campeonatos");
                        String monoplaza = resultSet.getString("monoplaza");
                        System.out.println(
                                "\n------------------------------------\n" +
                                " ID Escuderia: " + idEscuderia + "\n" +
                                " Nombre: " + nombre + "\n" +
                                " Pais: " + pais + "\n" +
                                " Director: " + director + "\n" +
                                " Campeonatos: " + campeonatos + "\n" +
                                " Monoplaza: " + monoplaza
                        );
                    }
                    break;

                case "CIRCUITO":
                    while (resultSet.next()) {
                        int idCircuito = resultSet.getInt("idCircuito");
                        String nombre = resultSet.getString("nombre");
                        String pais = resultSet.getString("pais");
                        String ciudad = resultSet.getString("ciudad");
                        float longitud = resultSet.getFloat("longitud");
                        int vueltas = resultSet.getInt("vueltas");
                        System.out.println(
                                "\n------------------------------------\n" +
                                " ID Circuito: " + idCircuito + "\n" +
                                " Nombre: " + nombre + "\n" +
                                " Pais: " + pais + "\n" +
                                " Ciudad: " + ciudad + "\n" +
                                " Longitud: " + longitud + "\n" +
                                " Vueltas: " + vueltas
                        );
                    }
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

            preparedStatement.close();
            resultSet.close();
            commitTransaction();

        } catch (SQLException e) {
            rollbackTransaction();
            e.printStackTrace();

        }
    }

    public void CHOISE(String tabla) { //* este es parecido al SELECT pero solo muestra la id y el nombre de los registros para poder actualizar o eliminar
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            startTransaction();

            String sql = "SELECT * FROM " + tabla + ";";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            switch (tabla) {
                case "PILOTO":
                    while (resultSet.next()) {
                        int numeroCarrera = resultSet.getInt("numeroCarrera");
                        String nombre = resultSet.getString("nombre");
                        String apellido = resultSet.getString("apellido");
                        System.out.println("[" + numeroCarrera + "] " + nombre + " " + apellido);
                    }
                    break;

                case "ESCUDERIA":
                    while (resultSet.next()) {
                        int idEscuderia = resultSet.getInt("idEscuderia");
                        String nombre = resultSet.getString("nombre");
                        System.out.println("[" + idEscuderia + "] " + nombre);
                    }
                    break;

                case "CIRCUITO":
                    while (resultSet.next()) {
                        int idCircuito = resultSet.getInt("idCircuito");
                        String nombre = resultSet.getString("nombre");
                        System.out.println("[" + idCircuito + "] " + nombre);
                    }
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

            preparedStatement.close();
            resultSet.close();
            commitTransaction();

        } catch (SQLException e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    public void INSERT(String tabla, String[] valores) {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            startTransaction();

            String sql = "";
            PreparedStatement preparedStatement = null;

            switch (tabla) {
                case "PILOTO":
                    sql = "INSERT INTO PILOTO (numeroCarrera, nombre, apellido, nacionalidad, fechaNac, poles, victorias, idEscuderia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    preparedStatement = this.connection.prepareStatement(sql);
                    preparedStatement.setInt(1, Integer.parseInt(valores[0]));
                    preparedStatement.setString(2, valores[1]);
                    preparedStatement.setString(3, valores[2]);
                    preparedStatement.setString(4, valores[3]);
                    preparedStatement.setDate(5, Date.valueOf(valores[4]));
                    preparedStatement.setInt(6, Integer.parseInt(valores[5]));
                    preparedStatement.setInt(7, Integer.parseInt(valores[6]));
                    preparedStatement.setInt(8, Integer.parseInt(valores[7]));
                    break;

                case "ESCUDERIA":
                    sql = "INSERT INTO ESCUDERIA (nombre, pais, director, campeonatos, monoplaza) VALUES (?, ?, ?, ?, ?)";
                    preparedStatement = this.connection.prepareStatement(sql);
                    preparedStatement.setString(1, valores[0]);
                    preparedStatement.setString(2, valores[1]);
                    preparedStatement.setString(3, valores[2]);
                    preparedStatement.setInt(4, Integer.parseInt(valores[3]));
                    preparedStatement.setString(5, valores[4]);
                    break;

                case "CIRCUITO":
                    sql = "INSERT INTO CIRCUITO (nombre, pais, ciudad, longitud, vueltas) VALUES (?, ?, ?, ?, ?)";
                    preparedStatement = this.connection.prepareStatement(sql);
                    preparedStatement.setString(1, valores[0]);
                    preparedStatement.setString(2, valores[1]);
                    preparedStatement.setString(3, valores[2]);
                    preparedStatement.setFloat(4, Float.parseFloat(valores[3]));
                    preparedStatement.setInt(5, Integer.parseInt(valores[4]));
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("El nuevo registro se inserto correctamente!");
            }

            commitTransaction();
            preparedStatement.close();

        } catch (SQLException e) {
            rollbackTransaction();
            e.printStackTrace();
        }

    }

    public void UPDATE(String tabla, String columna, String condicion, String valor ) {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            startTransaction();

            String sql = "UPDATE " + tabla + " SET " + columna + "="; // este es generico
            switch (tabla) {
                case "PILOTO": // como piloto solo modifica poles y victorias, el valor en ambos es un entero
                    sql += Integer.parseInt(valor) + " WHERE numeroCarrera=" + Integer.parseInt(condicion) + ";";
                    break;

                case "ESCUDERIA": // como escuderia modifica campeonatos y monoplaza, el valor en campeonatos es un entero y en monoplaza es un string
                    if (columna.equals("campeonatos")) {
                        sql += Integer.parseInt(valor) + " WHERE idEscuderia=" + Integer.parseInt(condicion) + ";";
                    }

                    if (columna.equals("monoplaza")) {
                        sql += "'" + valor + "'" + " WHERE idEscuderia=" + Integer.parseInt(condicion) + ";";
                    }
                    break;
            }

            System.out.println(sql);

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El registro fue actualizado correctamente!");
            }

            commitTransaction();
            preparedStatement.close();

        } catch (SQLException e) {
            rollbackTransaction();
            e.printStackTrace();
        }

    }

    public void DELETE(String tabla, String valor) {
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            startTransaction();

            String sql = "DELETE FROM " + tabla + " WHERE "; // este es generico
            switch (tabla) {
                case "PILOTO": // como piloto solo se puede eliminar por numeroCarrera
                    sql += "numeroCarrera=" + Integer.parseInt(valor) + ";";
                    break;

                case "ESCUDERIA": // como escuderia solo se puede eliminar por idEscuderia
                    sql += "idEscuderia=" + Integer.parseInt(valor) + ";";
                    break;

                case "CIRCUITO": // como circuito solo se puede eliminar por idCircuito
                    sql += "idCircuito=" + Integer.parseInt(valor) + ";";

            }

            System.out.println(sql);
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("El registro se elimino correctamente!");
            }

            commitTransaction();
            preparedStatement.close();

        } catch (SQLException e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    //* Métodos para el manejo de transacciones
    private void startTransaction() throws SQLException { //* est metodo inicia la transaccion seteando el autocommit a false
        connection.setAutoCommit(false);
    }

    private void commitTransaction() throws SQLException { //* este metodo hace commit de la transaccion
        connection.commit();
    }

    private void rollbackTransaction() { //* este metodo hace rollback de la transaccion en caso de error
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
