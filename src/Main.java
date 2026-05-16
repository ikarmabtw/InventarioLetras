public class Main {
    public static void main(String[] args) {

        // ---------------------- Etapa 1 ----------------------

        InventarioLetras n = new InventarioLetras("Caarlos");
        separador("Caso 1. Texto normal.");
        System.out.println("size(): " + n.size());
        System.out.println("isEmpty(): " + n.isEmpty());
        System.out.println("get('a'): " + n.get('a'));
        System.out.println("toString(): " + n);

        //letra que no esta dentro de la palabra
        separador("Caso 2. buscar letra que no esta en la palabra.");
        System.out.println("get(z); " + n.get('z'));

        //Error caracter invalido
        separador("Caso 3. Error.");
        try {
            System.out.println(n.get('!'));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }


        separador("Caso 4. Letras iguales.");
        InventarioLetras n2 = new InventarioLetras("aaaaaa");
        System.out.println("size(): " + n2.size());
        System.out.println("get('a'): " + n2.get('a'));
        System.out.println("toString(): " + n2);

        separador("Caso 5. Espacios y Mayusculas.");
        InventarioLetras n3 = new InventarioLetras("A A a B b C c C");
        System.out.println("get(a): " + n3.get('a'));
        System.out.println("get(b): " + n3.get('b'));
        System.out.println("get(c): " + n3.get('c'));

        separador("Caso 6. Vacio.");
        InventarioLetras n4 = new InventarioLetras("");
        System.out.println("size(): " + n4.size());
        System.out.println("get('a'): " + n4.get('a'));
        System.out.println("toString(): " + n4);




        // Desde la etapa 2 hasta la 3 se trabaja sobre el mismo inventario n ya que lo que se prueba no depende del contenido
        // del inventario, los metodos operan sobre String y char que se les entrega.


        // ---------------------- Etapa 2 ----------------------

        separador("Caso 7. encriptarCesar - char.");
        System.out.println("encriptarCesar ('a') : " + n.encriptarCesar('a')); // d
        System.out.println("encriptarCesar ('x') : " + n.encriptarCesar('x')); // a

        separador("Caso 8. desencriptarCesar - char.");
        System.out.println("desencriptarCesar ('d') : " + n.desencriptarCesar('d')); // a
        System.out.println("desencriptarCesar ('a') : " + n.desencriptarCesar('a')); // x

        separador("Caso 9. desencriptar char invalido - Error.");
        try{
            n.encriptarCesar('3');

        }catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }

        // ---------------------- Etapa 3 ----------------------

        separador("Caso 10. encriptarPalabra - desplazamiento 3.");
        System.out.println(n.encriptarPalabra("trini", 3));  // wulql
        System.out.println(n.encriptarPalabra("beto", 3));  // ehwr

        separador("Caso 11. Inverso Caso 10.");
        System.out.println(n.desencriptarPalabra("wulql", 3)); //trini
        System.out.println(n.desencriptarPalabra("ehwr", 3));   //beto

        separador("Caso 12. encriptarPalabra - xyz -> abc.");
        System.out.println(n.encriptarPalabra("xyz", 3)); //abc

        separador("Caso 13. Inverso caso 12.");
        System.out.println(n.desencriptarPalabra("abc", 3)); //xyz

        separador("Caso 14. encriptarPalabra - espacios y mayusculas.");
        System.out.println(n.encriptarPalabra("Hola POO", 3)); // krod srr

        separador("Caso 15. encriptarPalabra - desplazamiento 0.");
        System.out.println(n.encriptarPalabra("papa", 0)); // papa

        separador("Caso 16. encriptarPalabra - Espacios y numeros.");
        System.out.println(n.encriptarPalabra("123  !!", 3));

        separador("Caso 17. String vacio ");
        System.out.println(n.encriptarPalabra("", 3)); // vacio






    }
    public static void separador(String titulo) {
        System.out.println("\n===============");
        System.out.println(" " + titulo);
        System.out.println("===============");
    }

}


