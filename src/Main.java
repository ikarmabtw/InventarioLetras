public class Main {
    public static void main(String[] args) {
        int modo = 2;

        if (modo == 1) {
            PruebasInventario.ejecutarPruebas();
        } else {
            new MenuInventario().iniciarMenu();
        }
    }
}


