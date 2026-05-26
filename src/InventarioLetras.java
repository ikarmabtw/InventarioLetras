/**
 * Representa un inventario de letras del alfabeto ingles.
 *
 * <p>La clase cuenta la frecuencia de cada letra entre 'a' y 'z',
 *      ignorando espacios, numeros, simbolos y otro caracteres.
 *      Las letras mayusculas se procesan como minusculas.</p>
 * <p>Ejemplo de uso:</p>
 * <pre>{@code
 * InventarioLetras inventario = new InventarioLetras("James Gosling");
 * inventario.get('a'); // 1
 * inventario.size(); // 12
 * inventario.isEmpty(); // false
 * }</pre>
 */

public class InventarioLetras {
    private final int[] inventario;
    private int totalCount;
    private int nonZeroCount;

    /**
     * Crea un inventario de letras a partir de un texto.
     *
     * @param data texto utilizado para construir el inventario
     * @throws IllegalArgumentException si data es null
     */

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

    /**
     * Retorna la cantidad total de letras almacenadas en el inventario.
     *
     * @return numero total de letras contadas
     */
    public int size(){
        return totalCount;
    }

    /**
     * Indica si el inventario no contiene letras.
     *
     * @return {@code true} si el inventario esta vacio; {@code false} en caso contrario
     */

    public boolean isEmpty(){
        return nonZeroCount == 0;
    }

    private char validarLetra(char letra){
        char c = Character.toLowerCase(letra);
        if (c < 'a' || c > 'z'){
            throw new IllegalArgumentException("Caracter invalido: '" + letra + "' Solo se aceptan letras del alfabeto ingles (a-z)." );
        }
        return c;
    }

    /**
     * Retorna la cantidad asociada a una letra.
     *
     * @param letra letra que se desea consultar; acepta mayusculas y minusculas,
     *              ambas se tratan como la misma entrada
     *
     * @return cantidad almacenada para esa letra; 0 si no esta presente
     * @throws IllegalArgumentException si {@code letra} no pertenece al rango del alfabeto ingles 'a' - 'z'
     */

    public int get(char letra){
        int indice = obtenerIndice(letra);
        return inventario [indice];
    }

    /**
     * Retorna una representacion ordenada del inventario.
     *
     *Las letras se muestran entre corchetes y en orden alfabetico, repitiendo la letra segun su cantidad almacenada.
     *
     * @return representacion en texto del inventario
     */

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

    /**
     * Encripta una letra usando cifrado Cesar con desplazamiento fijo de 3.
     *
     * @param letra letra que se desea encriptar
     * @return letra encriptada
     * @throws IllegalArgumentException si la letra no pertenece al rango 'a' - 'z'
     */
    public char encriptarCesar(char letra){
        return cifrarCesar(letra, 3);
    }

    /**
     * Desencripta una letra usando cifrado Cesar con desplazamiento fijo de -3.
     *
     * @param letra letra que se desea desencriptar
     * @return letra desencriptada
     * @throws IllegalArgumentException si la letra no pertenece al rango 'a' - 'z'
     */

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

    /**
     * Encripta una palabra o frase usando cifrado Cesar.
     *
     * <p> Solo se encriptan letras del alfabeto ingles. Espacios, numeros y simbolos
     * se conservan sin modificacion. El resultado siempre esta en minusculas.</p>
     *
     *
     * @param palabra palabra o frase que se desea encriptar; puede contener cualquier caracter, pero solo
     *                las letras seran transformadas; no puede ser {@code null}
     * @param desplazamiento cantidad de posiciones que se desplazara cada letra en el alfabeto;
     *                       puede ser negativo, aunque para encriptar se recomienda usar valores positivos
     *
     * @return texto encriptado con las letras desplazadas y el resto sin cambios
     * @throws IllegalArgumentException si {@code palabra} es {@code null}
     */

    public String encriptarPalabra(String palabra, int desplazamiento){
        return cifrarPalabra(palabra, desplazamiento);
    }

    /**
     * Desencripta una palabra o frase previamente cifrada usando cifrado Cesar.
     *
     * <p>Es la operacion inversa de {@link #encriptarPalabra(String, int)}.
     * El desplazamiento debe coincidir con el usado al encriptar.</p>
     *
     *
     * @param palabra palabra o frase que se desea desencriptar; no puede ser {@code null}
     * @param desplazamiento desplazamiento utilizado originalmente para encriptar;
     *                       debe ser positivo para obtener el resultado esperado
     * @return texto desencriptado
     * @throws IllegalArgumentException si {@code palabra} es {@code null}
     */


    public String desencriptarPalabra(String palabra, int desplazamiento){
        return cifrarPalabra(palabra, - desplazamiento);
    }

    /**
     * Establece la cantidad asociada a una letra del inventario, reemplazando el valor anterior.
     *
     * <p> Actualiza automaticamente {@code size()} e {@code isEmpty()}
     * segun el cambio realizado.</p>
     *
     * @param letra letra que se desea modificar; acepta mayuscula y minuscula
     * @param cantidad nueva cantidad de la letra; debe ser mayor o igual a cero.
     *                 Pasar {@code 0} elimina la letra del inventario
     * @throws IllegalArgumentException si {@code letra} no es valida o si la {@code cantidad} es negativa
     *
     */

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

    /**
     * Suma este inventario con otro inventario y retorna el resultado como un nuevo inventario.
     * <p>Los inventarios originales no se modifican</p>
     *
     * @param sumando inventario que se desea sumar; no puede ser {@code null}
     * @return nuevo inventario con las frecuencias de ambos sumadas
     * @throws IllegalArgumentException si {@code sumando } es {@null}
     */

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

    /**
     * Multiplica todas las cantidades del inventario por un factor y retorna el resultado.
     *
     * <p>El inventario original no se modifica. Pasar{@code 0} retorna un inventario vacio</p>
     *
     * @param n factor de amplificacion; debe ser mayor o igual a cero
     * @return nuevo inventario con las cantidades amplificadas por {@code n}
     * @throws IllegalArgumentException si {@code n} es negativo
     */

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

    /**
     * Resta otro inventario a este inventario.
     *
     * Si alguna cantidad queda negativa, la operacion no es valida y retorna null
     *
     * @param restando inventario que se desea restar
     * @return nuevo inventario con la resta, o null si la resta no es posible
     * @throws IllegalArgumentException si restando es null
     */

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
