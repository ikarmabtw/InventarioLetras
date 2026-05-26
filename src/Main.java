/**
 * Clase principal del programa.
 *
 * Permite ejecutar las pruebas manuales del proyecto o iniciar
 * el menu interactivo por consola.
 */

public class Main {

    /**
     * Punto de entrada del programa.
     * @param args argumentos recibidos desde la linea de comandos
     */
    public static void main(String[] args) {
        int modo = 1;

        if (modo == 1) {
            PruebasInventario.ejecutarPruebas();
        } else {
            new MenuInventario().iniciarMenu();
        }
    }
}


