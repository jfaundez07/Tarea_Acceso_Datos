public class Main {
    public static void main(String[] args) {
        DBQuerys dbQuerys = new DBQuerys();

        Menu menu = new Menu();
        menu.lanzarMenu();
        String tabla = menu.seleccionarTabla();

        dbQuerys.SELECT(tabla);


    }
}