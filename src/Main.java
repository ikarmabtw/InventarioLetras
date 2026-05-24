public class Main {
    public static void main(String[] args) {
        int modo = 1;

        if (modo == 1) {
            PruebasInventario.ejecutarPruebas();
        } else {
            new MenuInventario().iniciarMenu();
        }
    }
}


