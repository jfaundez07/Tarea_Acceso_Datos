import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                        String numeroCarrera = resultSet.getString("numeroCarrera");
                        String nombre = resultSet.getString("nombre");
                        String edad = resultSet.getString("apellido");
                        String nacionalidad = resultSet.getString("nacionalidad");
                        String fechaNac = resultSet.getString("fechaNac");
                        String poles = resultSet.getString("poles");
                        String victorias = resultSet.getString("victorias");
                        String idEscuderia = resultSet.getString("idEscuderia");
                        System.out.println(
                                "Numero de Carrera: " + numeroCarrera +
                                " Nombre: " + nombre +
                                " Apellido: " + edad +
                                " Nacionalidad: " + nacionalidad +
                                " Fecha de Nacimiento: " + fechaNac +
                                " Poles: " + poles +
                                " Victorias: " + victorias +
                                " ID Escuderia: " + idEscuderia
                        );
                    }
                    break;

                case "ESCUDERIA":
                    while (resultSet.next()) {
                        String idEscuderia = resultSet.getString("idEscuderia");
                        String nombre = resultSet.getString("nombre");
                        String pais = resultSet.getString("pais");
                        String director = resultSet.getString("director");
                        String campeonatos = resultSet.getString("campeonatos");
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
                        String idCircuito = resultSet.getString("idCircuito");
                        String nombre = resultSet.getString("nombre");
                        String pais = resultSet.getString("pais");
                        String ciudad = resultSet.getString("ciudad");
                        String longitud = resultSet.getString("longitud");
                        String vueltas = resultSet.getString("vueltas");
                        System.out.println(
                                "ID Circuito: " + idCircuito +
                                " Nombre: " + nombre +
                                " Pais: " + pais +
                                " Ciudad: " + ciudad +
                                " Longitud: " + longitud +
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

    public static void INSERT(String tabla) {

    }

    public static void UPDATE() {

    }

    public static void DELETE() {

    }
}
