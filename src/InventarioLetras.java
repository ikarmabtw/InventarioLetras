public class InventarioLetras {
    private final int[] inventario;
    private int totalCount;
    private int nonZeroCount;

    public InventarioLetras(String data){
        if(data == null){
            throw new IllegalArgumentException("El texto no puede ser null");
        }


        inventario = new int[26];                                   //Cada posicion representa una letra
        totalCount = 0;
        nonZeroCount = 0;


        for(int i = 0; i < data.length(); i ++){
            char c = Character.toLowerCase(data.charAt(i));

            if(esLetraValida(c)) {                                                  //Filtro de letras
                int indice = c - 'a';                                  //Calculo posicion dentro del arreglo usando el valor ASCII de los caracteres(al restarle 'a' se obtienen indices de 0 a 25)
                actualizarContadores(indice, inventario[indice] + 1);
            }
        }
    }
    private boolean esLetraValida(char c){
        return c >= 'a' && c <= 'z';
    }


    public int size(){
        return totalCount;
    }

    public boolean isEmpty(){
        return nonZeroCount == 0;
    }

    //Verificar si el caracter es valido, si no lo es lanza un error
    private char validarLetra(char letra){
        char c = Character.toLowerCase(letra);
        if (c < 'a' || c > 'z'){
            throw new IllegalArgumentException("Caracter invalido: '" + letra + "' Solo se aceptan letras del alfabeto ingles (a-z)." );
        }
        return c;
    }

    public int get(char letra){
        int indice = obtenerIndice(letra);
        return inventario [indice];
    }

    @Override
    public String toString(){
        StringBuilder resultado = new StringBuilder("[");

        for (int i = 0; i < inventario.length ; i++){
                for(int j = 0; j < inventario[i]; j++ ){
                resultado.append((char) ('a' + i));
            }
        }
        resultado.append("]");
        return resultado.toString();
    }

    private char cifrarCesar(char letra, int desplazamiento){
        char c = validarLetra(letra);
        return (char)('a' + Math.floorMod(c - 'a' + desplazamiento, 26));
    }

    public char encriptarCesar(char letra){
        return cifrarCesar(letra, 3);
    }

    public char desencriptarCesar(char letra){
        return cifrarCesar(letra, -3);
    }

    private String cifrarPalabra(String palabra, int desplazamiento){
        if (palabra == null){
            throw new IllegalArgumentException("La palabra no puede ser null.");
        }


        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < palabra.length(); i++){
            char c = Character.toLowerCase(palabra.charAt(i));
            if (esLetraValida(c)){
                resultado.append(cifrarCesar(c, desplazamiento));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public String encriptarPalabra(String palabra, int desplazamiento){
        return cifrarPalabra(palabra, desplazamiento);
    }
    public String desencriptarPalabra(String palabra, int desplazamiento){
        return cifrarPalabra(palabra, - desplazamiento);
    }

    public void set(char letra, int cantidad){
        int indice = obtenerIndice(letra);
        actualizarContadores(indice, cantidad);
    }

    private void actualizarContadores (int indice, int nuevaCantidad){
        if(nuevaCantidad < 0 ){
            throw new IllegalArgumentException("El valor no puede ser negativo." );
        }

        int cantidadAnterior = inventario[indice];

        if(cantidadAnterior == nuevaCantidad){
            return;
        }

        if (cantidadAnterior == 0 ){
            nonZeroCount++;
        }else if (nuevaCantidad == 0 ){
            nonZeroCount--;
        }

        totalCount+= nuevaCantidad - cantidadAnterior;
        inventario[indice] = nuevaCantidad;
    }

    private int obtenerIndice(char letra){
        char c = validarLetra(letra);
        return c - 'a';
    }

    public InventarioLetras add(InventarioLetras sumando){
        if(sumando == null){
            throw new IllegalArgumentException("El inventario a sumar no puede ser null.");
        }

        InventarioLetras inventarioFinal = new InventarioLetras("");

        for(int i = 0; i < inventario.length; i++){
            inventarioFinal.actualizarContadores(i, inventario[i] + sumando.inventario[i]);
        }

        return inventarioFinal;
    }

    public InventarioLetras amplifies(int n){
        if(n < 0){
            throw new IllegalArgumentException("El valor no puede ser negativo.");
        }

        InventarioLetras inventarioFinal = new InventarioLetras("");

        for(int i = 0; i < inventario.length; i++){
            inventarioFinal.actualizarContadores(i, inventario[i] * n);
        }

        return inventarioFinal;
    }

    public InventarioLetras subtract(InventarioLetras restando){
        if (restando == null){
            throw new IllegalArgumentException("El inventario a restar no puede ser null.");
        }

        InventarioLetras inventarioFinal = new InventarioLetras("");

        for(int i = 0; i < inventario.length; i++){
            int diferencia = inventario[i] - restando.inventario[i];

            if (diferencia < 0) {
                return null;
            }

            inventarioFinal.actualizarContadores(i, diferencia);
        }

        return inventarioFinal;
    }

}
