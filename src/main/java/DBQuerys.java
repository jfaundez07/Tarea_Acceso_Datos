import java.sql.*;

public class DBQuerys {

    Connection connection;

    public DBQuerys(){

        try {
            this.connection =  DB.connect();
            System.out.println("Connected to the PostgreSQL database.");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void SELECT(String tabla) {

        try {

            String sql = "SELECT * FROM " + tabla + ";";
            System.out.println(sql);

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
                                "Numero de Carrera: " + numeroCarrera + "\n" +
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
                                "ID Circuito: " + idCircuito + "\n" +
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void INSERT(String tabla, String[] valores) {
        try {
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
                System.out.println("A new record was inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void UPDATE() {

    }

    public void DELETE() {

    }
}
