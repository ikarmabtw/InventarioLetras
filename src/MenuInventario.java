import java.util.Scanner;

public class MenuInventario{
    private final Scanner scanner;
    private InventarioLetras inventarioActual;

    public MenuInventario(){
        scanner = new Scanner(System.in);
        inventarioActual = null;
    }

    public void iniciarMenu(){
        int opcion = -1;

        while(opcion != 0){
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            ejecutarOpcion(opcion);
        }
    }

    private void mostrarMenu(){
        System.out.println("\n ========== MENU INVENTARIO LETRAS ==========");
        System.out.println("1. Crear nuevo inventario");
        System.out.println("2. Ver size()");
        System.out.println("3. Ver isEmpty()");
        System.out.println("4. Mostrar cantidad de una letra");
        System.out.println("5. Cambiar cantidad de una letra");
        System.out.println("6. Mostrar Inventario");
        System.out.println("7. Encriptar letra");
        System.out.println("8. Desencriptar letra");
        System.out.println("9. Encriptar palabra");
        System.out.println("10. Desencriptar palabra");
        System.out.println("11. Sumar inventarios");
        System.out.println("12. Restar inventarios");
        System.out.println("13. Ampliar inventario ");
        System.out.println("0. Salir");
        System.out.println("============================================");
    }

    private void ejecutarOpcion(int opcion){
        try {
            if (opcionNecesitaInventario(opcion) && noHayInventario()) {
                return;
            }

            //Se puede sobreescribir el inventario si apreta la opcion 1 otra vez

            if (opcion == 1){
                crearInventario();
            }else if(opcion == 2){
                mostrarSize();
            }else if (opcion == 3){
                mostrarIsEmpty();
            }else if (opcion == 4){
                consultarLetra();
            }else if (opcion == 5){
                cambiarCantidad();
            }else if(opcion == 6){
                mostrarInventario();
            }else if(opcion == 7){
                encriptarLetra();
            }else if (opcion == 8){
                desencriptarLetra();
            }else if (opcion == 9){
                encriptarPalabra();
            }else if (opcion == 10){
                desencriptarPalabra();
            }else if(opcion == 11 ) {
                sumarInventarios();
            }else if(opcion == 12) {
                restarInventarios();
            }else if(opcion == 13){
                amplificarInventario();
            }else if(opcion == 0){
                System.out.println("Saliendo del programa...");
            }else{
                System.out.println("Opción invalida");
            }
        }catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }


    private boolean opcionNecesitaInventario(int opcion){
        return opcion >= 2 && opcion <= 13;
    }

    private boolean noHayInventario(){
        if(inventarioActual == null){
            System.out.println("Primero debe crear un inventario.");
            return true;
        }
        return false;
    }

    private void crearInventario(){
        System.out.print("Ingrese el texto para crear el inventario: ");
        String texto = scanner.nextLine();

        inventarioActual = new InventarioLetras(texto);
        System.out.println("Inventario creado: " + inventarioActual);
    }

    private void mostrarSize(){
        System.out.println("size(): " + inventarioActual.size());
    }

    private void mostrarIsEmpty(){
        System.out.println("isEmpty(): " + inventarioActual.isEmpty());
    }

    private void consultarLetra(){
        char letra = leerCaracter("Ingrese una letra: ");
        System.out.println("get('" + letra +  "'): " + inventarioActual.get(letra));
    }

    private void cambiarCantidad(){
        char letra = leerCaracter("Ingrese una letra (a - z, acepta mayusculas): ");
        int cantidad = leerEntero("Ingrese la nueva cantidad (numero entero positivo): ");

        inventarioActual.set(letra, cantidad);

        System.out.println("Inventario actualizado: " + inventarioActual);
    }

    private void mostrarInventario(){
        System.out.println("Inventario actual: " + inventarioActual);
    }

    private void encriptarLetra(){
        char letra = leerCaracter("Ingrese una letra para encriptar (a - z, acepta mayusculas): ");
        System.out.println("Resultado: " + inventarioActual.encriptarCesar(letra));
    }

    private void desencriptarLetra(){
        char letra = leerCaracter("Ingrese una letra para desencriptar (a - z, acepta mayusculas): ");
        System.out.println("Resultado: " + inventarioActual.desencriptarCesar(letra));
    }

    private void encriptarPalabra(){

        System.out.println("Ingrese una palabra o frase: ");
        String palabra = scanner.nextLine();

        int desplazamiento = leerEntero("Ingrese el desplazamiento (debe ser positivo, 0 sin cambio): ");

        if (desplazamiento < 0 ){
            System.out.println("Advertencia: se ingreso un valor negativo, el resultado no sera el esperado.");
        }
        System.out.println("Resultado: " + inventarioActual.encriptarPalabra(palabra, desplazamiento));

    }

    private void desencriptarPalabra(){
        System.out.println("Ingrese una palabra o frase: ");
        String palabra = scanner.nextLine();

        int desplazamiento = leerEntero("Ingrese el desplazamiento (debe ser positivo, 0 sin cambio): ");

        if (desplazamiento < 0){
            System.out.println("Advertencia: se ingreso un valor negativo, el resultado no sera el esperado.");
        }
        System.out.println("Resultado: " + inventarioActual.desencriptarPalabra(palabra, desplazamiento));

    }

    private char leerCaracter(String c){
        String entrada = "";

        while(entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))){
            System.out.print(c);
            entrada = scanner.nextLine();

            if (entrada.length() != 1  || !Character.isLetter(entrada.charAt(0))){
                System.out.println("Debe ingresar exactamente un caracter.");
            }
        }

        return entrada.charAt(0);
    }

    private int leerEntero(String n){
        while(true){
            System.out.print(n);
            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada);
            }catch (NumberFormatException e){
                System.out.println("Debe ingresar un numero entero.");
            }
        }
    }

    private void sumarInventarios(){
        System.out.println("Ingrese el texto para el segundo inventario: ");
        String text = scanner.nextLine();

        InventarioLetras inventario2 = new InventarioLetras(text);
        InventarioLetras inventarioFinal = inventarioActual.add(inventario2);

        System.out.println("Inventario 1: " + inventarioActual);
        System.out.println("Inventario 2: " + inventario2);
        System.out.println("Suma: " + inventarioFinal);

    }

    private void restarInventarios(){
        System.out.println("Ingrese el texto para el segundo inventario: ");
        String text = scanner.nextLine();

        InventarioLetras inventario2 = new InventarioLetras(text);
        InventarioLetras inventarioFinal = inventarioActual.subtract(inventario2);

        if(inventarioFinal == null ){
            System.out.println("No es posible realizar la resta, quedan valores negativos.");
        }else{
            System.out.println("Resta: " + inventarioFinal);
        }
    }

    private void amplificarInventario(){
        int factor = leerEntero("Ingrese el factor de amplificacion (0 o entero positivo): ");

        InventarioLetras inventarioFinal = inventarioActual.amplifies(factor);

        System.out.println("Original: " + inventarioActual);
        System.out.println("Amplificado por " + factor + ": " + inventarioFinal);
    }


}