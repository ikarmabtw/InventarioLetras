public class Main{
    public static void main(String[] args){
        InventarioLetras n = new InventarioLetras("Caarlos");

        separador("Caso 1. Texto normal");
        System.out.println("size(): " + n.size());
        System.out.println("isEmpty(): " + n.isEmpty());
        System.out.println("get('a'): " + n.get('a'));
        System.out.println("toString(): " + n);

        //letra que no esta dentro de la palabra
        separador("Caso 2. buscar letra que no esta en la palabra");
        System.out.println("get(z); " + n.get('z'));

        //Error caracter invalido
        separador("Caso 3. Error");
        try{
            System.out.println(n.get('!'));
        }catch(IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }


        separador("Caso 4. Letras iguales");

        InventarioLetras n2 = new InventarioLetras("aaaaaa");
        System.out.println("size(): " + n2.size());
        System.out.println("get('a'): " + n2.get('a'));
        System.out.println("toString(): " + n2);

        separador("Caso 5. Espacios y Mayusculas");
        InventarioLetras n3 = new InventarioLetras("A A a B b C c C");
        System.out.println("get(a): " + n3.get('a'));
        System.out.println("get(b): " + n3.get('b'));
        System.out.println("get(c): " + n3.get('c'));

        separador("Caso 6. Vacio");
        InventarioLetras n4 = new InventarioLetras("");
        System.out.println("size(): " + n4.size());
        System.out.println("get('a'): " + n4.get('a'));
        System.out.println("toString(): " + n4);




    }

    public static void separador(String titulo){
        System.out.println("\n===============");
        System.out.println(" " + titulo );
        System.out.println("===============");
    }
}
