import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void lanzarMenu() {

        String opción;

        do {
            opcionesMenu();
            opción = scanner.nextLine();

            switch (opción) {
                case "1":
                    System.out.println("Ver registros");
                    break;
                case "2":
                    System.out.println("Insertar registros");
                    break;
                case "3":
                    System.out.println("Actualizar registros");
                    break;
                case "4":
                    System.out.println("Eliminar registros");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (opción.equals("0"));

        System.out.println("Saliendo del menú");
    }

    public void opcionesMenu() {
        System.out.println("Menú:");
        System.out.println("[0] Salir");
        System.out.println("[1] Ver registros");
        System.out.println("[2] Insertar registros");
        System.out.println("[3] Actualizar registros");
        System.out.println("[4] Eliminar registros");
        System.out.println("Seleccione una opción:");
    }

    public String seleccionarTabla() {
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
}
