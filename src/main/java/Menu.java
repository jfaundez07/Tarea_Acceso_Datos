import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void lanzarMenu(DBQuerys dbQuerys) {

        String opción;

        do {
            opcionesMenu();
            opción = scanner.nextLine();

            switch (opción) {
                case "1":
                    System.out.println("Ver registros");
                    dbQuerys.SELECT(seleccionarTabla());
                    break;
                case "2":
                    System.out.println("Insertar registros");
                    String tabla = seleccionarTabla();
                    String[] valores = ingresarValoresINSERT(tabla);
                    dbQuerys.INSERT(tabla, valores);
                    break;
                case "3":
                    System.out.println("Actualizar registros");
                    String tablaUPDATE = seleccionarTabla();
                    if (tablaUPDATE.equals("CIRCUITO")) {
                        System.out.println("No se pueden actualizar registros de la tabla Circuito");
                        return;
                    }
                    String columna = seleccionarColumna(tablaUPDATE);
                    System.out.println("Ingrese el ID del registro a modificar:");
                    String id = scanner.nextLine();
                    System.out.println("Ingrese el nuevo valor:");
                    String valor = scanner.nextLine();
                    dbQuerys.UPDATE(tablaUPDATE, columna, id, valor);
                    break;
                case "4":
                    System.out.println("Eliminar registros");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (!opción.equals("0"));

        System.out.println("Saliendo del menú");
    }

    public void opcionesMenu() {
        System.out.println("\n------------------------------------\n");
        System.out.println("Menú:");
        System.out.println("[0] Salir");
        System.out.println("[1] Ver registros");
        System.out.println("[2] Insertar registros");
        System.out.println("[3] Actualizar registros");
        System.out.println("[4] Eliminar registros");
        System.out.println("Seleccione una opción:");
    }

    public String seleccionarTabla() { // metodo que selecciona la tabla a consultar
        System.out.println("Tablas:");
        System.out.println("[1] Piloto");
        System.out.println("[2] Escuderia");
        System.out.println("[3] Circuito");
        System.out.println("Seleccione una tabla:");

        String tabla = "";
        String opción = scanner.nextLine();

        while (!opción.equals("1") && !opción.equals("2") && !opción.equals("3")) {
            System.out.println("Opción no válida");
            System.out.println("Seleccione una tabla:");
            opción = scanner.nextLine();
        }

        switch (opción) {
            case "1":
                tabla = "PILOTO";
                break;
            case "2":
                tabla = "ESCUDERIA";
                break;
            case "3":
                tabla = "CIRCUITO";
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

        return tabla;
    }

    public String seleccionarColumna(String tabla) { // Método que selecciona la columna a modificar en una tabla en específico
        mostrarOpcionesUPDATE(tabla);
        String columna = "";
        String opción = scanner.nextLine();

        if (tabla.equals("PILOTO")) {
            switch (opción) {
                case "1":
                    columna = "poles";
                    break;
                case "2":
                    columna = "victorias";
                    break;
                case "3":
                    columna = "idEscuderia";
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }

        if (tabla.equals("ESCUDERIA")) {
            switch (opción) {
                case "1":
                    columna = "campeonatos";
                    break;
                case "2":
                    columna = "monoplaza";
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }

        return columna;
    }

    public void mostrarOpcionesUPDATE(String tabla) { // Método que muestra las opciones de campos a modificar en una tabla
        System.out.println("Posibles campos a modifcar en " + tabla + ":");
        switch (tabla) {
            case "PILOTO":
                System.out.println("[1] Poles de un piloto");
                System.out.println("[2] Victorias de un piloto");
                System.out.println("[3] Escuderia a la cual pertenece un piloto");
                break;

            case "ESCUDERIA":
                System.out.println("[1] Campeonatos de la escuderia");
                System.out.println("[2] Monoplaza de la escuderia");
                break;

            default:
                System.out.println("Opción no válida");
                break;
        }

        System.out.println("Seleccione una columna:");
    }

    public String[] ingresarValoresINSERT(String tabla) { // Método que ingresa los valores a insertar en una tabla en específico
        String[] valores = new String[0];
        switch (tabla) {
            case "PILOTO":
                valores = new String[8];
                System.out.println("Número de carrera: ");
                valores[0] = scanner.nextLine();
                System.out.println("Nombre (apellido se ingresa aparte): ");
                valores[1] = scanner.nextLine();
                System.out.println("Apellido: ");
                valores[2] = scanner.nextLine();
                System.out.println("Nacionalidad: ");
                valores[3] = scanner.nextLine();
                System.out.println("Fecha de nacimiento: (aaaa-mm-dd)");
                valores[4] = scanner.nextLine();
                System.out.println("Poles: ");
                valores[5] = scanner.nextLine();
                System.out.println("Victorias: ");
                valores[6] = scanner.nextLine();
                System.out.println("id de la escuderia: ");
                valores[7] = scanner.nextLine();
                break;

            case "ESCUDERIA":
                valores = new String[5];
                System.out.println("Nombre");
                valores[0] = scanner.nextLine();
                System.out.println("Pais");
                valores[1] = scanner.nextLine();
                System.out.println("Director");
                valores[2] = scanner.nextLine();
                System.out.println("Campeonatos");
                valores[3] = scanner.nextLine();
                System.out.println("Monoplaza");
                valores[4] = scanner.nextLine();
                break;

            case "CIRCUITO":
                valores = new String[5];
                System.out.println("Nombre");
                valores[0] = scanner.nextLine();
                System.out.println("Pais");
                valores[1] = scanner.nextLine();
                System.out.println("Ciudad");
                valores[2] = scanner.nextLine();
                System.out.println("Longitud");
                valores[3] = scanner.nextLine();
                System.out.println("Vueltas");
                valores[4] = scanner.nextLine();
                break;
        }
        return valores;
    }
}
