public class InventarioLetras {
    private final int[] inventario;
    private int totalCount;
    private int nonZeroCount;

    public InventarioLetras(String data){
        inventario = new int[26];                                   //Cada posicion representa una letra
        totalCount = 0;
        nonZeroCount = 0;


        for(int i = 0; i < data.length(); i ++){
            char c = Character.toLowerCase(data.charAt(i));

            if(c >= 'a' && c <= 'z'){                                                  //Filtro de letras
                int indice = c - 'a';                                  //Calculo posicion dentro del arreglo usando el valor ASCII de los caracteres(al restarle 'a' se obtienen indices de 0 a 25)

                //Cuenta letras distintas
                if(inventario[indice] == 0){
                    nonZeroCount++;
                }

                inventario[indice]++;
                totalCount++;
            }
        }
    }

    public int size(){
        return totalCount;
    }

    public boolean isEmpty(){
        if (nonZeroCount == 0){
            return true;
        }else{
            return false;
        }
    }

    //Verificar si el caracter es valido, si no lo es lanza un error
    private char validarLetra(char letra){
        char c = Character.toLowerCase(letra);
        if (c < 'a' || c > 'z'){
            throw new IllegalArgumentException("Caracter invalido: '" + letra + "' Solo se aceptan letras del alfabeto ingles (a-z)." );
        }return c;
    }

    public int get(char letra){
        char c = validarLetra(letra);
        return inventario [c - 'a'];
    }

    public String toString(){
        StringBuilder resultado = new StringBuilder();
        resultado.append("[");

        for (int i = 0; i < 26 ; i++){
                for(int j = 0; j < inventario[i]; j++ ){
                resultado.append((char) ('a' + i));
            }
        }   resultado.append("]");
        return resultado.toString();
    }
}
