/**
 * Conjunto de pruebas manuales para verificar InventarioLetras.
 *
 * Evalua creacion de inventario, consultas, modificacion de cantidades,
 * cifrado Cesar y operaciones entre inventarios.
 */

public class PruebasInventario{

    /**
     * Ejecuta los casos de prueba definidos para el proyecto.
     */

    public static void ejecutarPruebas(){

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

        separador("Caso 9. encriptar char invalido - Error.");
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

        // ---------------------- Etapa 4 ----------------------

        // Se trabaja con nuevo inventario para probar set dado que este modifica el estado interno del mismo.

        InventarioLetras pruebaSet = new InventarioLetras("casa");

        separador("Etapa Set. Caso 1- Cambiar valores de letras existentes.");
        System.out.println("Antes de set ('a', 5): " + pruebaSet);
        System.out.println("size antes: " + pruebaSet.size());
        pruebaSet.set('a', 5);
        System.out.println("Despues de set ('a', 5): " + pruebaSet);
        System.out.println("get('a'): " + pruebaSet.get('a'));
        System.out.println("size despues: " + pruebaSet.size());

        separador("Etapa Set. Caso 2 - Agregar letra q no existe en el inventario.");
        pruebaSet.set('z', 4);
        System.out.println("Despues de set ('z',4); " + pruebaSet);
        System.out.println("get('z'): " + pruebaSet.get('z'));
        System.out.println("size actual: " + pruebaSet.size());

        separador("Etapa Set. Caso 3 - Reducir letra a 0.");
        pruebaSet.set('c', 0);
        System.out.println("Despues de set('c', 0): " + pruebaSet);
        System.out.println("get('c'): " + pruebaSet.get('c'));
        System.out.println("isEmpty(): " + pruebaSet.isEmpty());

        separador("Etapa Set. Caso 4 - Error por valor negativo.");
        try{
            pruebaSet.set('a', -1);
        }catch (IllegalArgumentException e){
            System.out.println("Error capturado: " + e.getMessage());
        }

        separador("Etapa Set. Caso 5 - Error caracter invalido ");
        try{
            pruebaSet.set('!', 3);
        }catch (IllegalArgumentException e){
            System.out.println("Error capturado: " + e.getMessage());
        }

        separador("Etapa Set. Caso 6 - Set con mayuscula.");
        pruebaSet.set('A', 10);
        System.out.println("get('a') tras set ('A', 10): " + pruebaSet.get('a'));
        System.out.println("Inventario final: " + pruebaSet);

        separador("Etapa Set. Caso 7 - Vaciar inventario usando set.");
        InventarioLetras setVacio = new InventarioLetras("x");

        System.out.println("Antes: " + setVacio);
        setVacio.set('x', 0 );
        System.out.println("Despues: " + setVacio);
        System.out.println("size: " + setVacio.size());
        System.out.println("isEmpty(): " + setVacio.isEmpty());

        // ---------------------- Etapa 5 ----------------------

        separador("Etapa 5. Caso 1 - add con dos inventarios.");
        InventarioLetras inventario1 = new InventarioLetras("xxyyzz");
        InventarioLetras inventario2 = new InventarioLetras("aabb");

        InventarioLetras suma = inventario1.add(inventario2);

        System.out.println("Inventario 1: " + inventario1);
        System.out.println("Inventario 2: " + inventario2);
        System.out.println("suma: " + suma);
        System.out.println("size post suma: " + suma.size());

        // se comprueba que el add no modifique los inventarios originales
        System.out.println("Comprobacion Inventario 1: " + inventario1);
        System.out.println("Comprobacion Inventario 2: " + inventario2);

        separador("Etapa 5. Caso 2 - amplifies con factor 3.");
        InventarioLetras inventarioAmp = new InventarioLetras("brr");
        InventarioLetras amplificado = inventarioAmp.amplifies(3);

        System.out.println("Original: " + inventarioAmp);
        System.out.println("Amplifies (3): " + amplificado);
        System.out.println("size amplificado: " + amplificado.size());

        separador("Etapa 5. Caso 3 - amplifies con 0 deja inventario vacio.");
        InventarioLetras inventarioAmpCero = inventarioAmp.amplifies(0);

        System.out.println("Original: " + inventarioAmp);
        System.out.println("Amplifies (0): " + inventarioAmpCero);
        System.out.println("size: " + inventarioAmpCero.size());
        System.out.println("isEmpty(): " + inventarioAmpCero.isEmpty());

        separador("Etapa 5. Caso 4 - amplifies negativo lanza error.");
        try{
            inventarioAmp.amplifies(-1);
        }catch(IllegalArgumentException e){
            System.out.println("Error capturado: " + e.getMessage());
        }

        separador("Etapa 5. Caso 5 - subtract resta correctamente.");
        InventarioLetras inventarioSub = new InventarioLetras("qqwweerrttyy");
        InventarioLetras inventarioSub2 = new InventarioLetras("qwerty");

        InventarioLetras resta = inventarioSub.subtract(inventarioSub2);

        System.out.println("Inventario Sub 1: " + inventarioSub);
        System.out.println("Inventario Sub 2: " + inventarioSub2);
        System.out.println("resta: " + resta);
        System.out.println("size post resta: " + resta.size());

        separador("Etapa 5. Caso 6 - subtract return null si queda negativo.");

        InventarioLetras inventarioSub3 = new InventarioLetras("miau");
        InventarioLetras inventarioSub4 = new InventarioLetras("uuuu");

        InventarioLetras restaNull = inventarioSub3.subtract(inventarioSub4);

        System.out.println("Resta Invalida: " + restaNull);

        // se comprueba que el subtract no modifique los inventarios originales
        System.out.println("Comprobacion Inventario 3: " + inventarioSub3);
        System.out.println("Comprobacion Inventario 4: " + inventarioSub4);

        separador("Etapa 5. Caso 7 - add con null lanza error.");
            try{
                inventarioSub.add(null);
            }catch(IllegalArgumentException e){
                System.out.println("Error capturado: " + e.getMessage());
            }


        separador("Etapa 5. Caso 8 - subtract con null lanza error.");
        try{
            inventarioSub.subtract(null);
        }catch(IllegalArgumentException e){
            System.out.println("Error capturado: " + e.getMessage());
        }

        separador("Etapa 5. Caso 9 - add con inventario vacio.");
        InventarioLetras inventarioVacio = new InventarioLetras("");
        InventarioLetras sumaVacio = inventarioSub.add(inventarioVacio);

        System.out.println("Original: " + inventarioSub);
        System.out.println("Inventario vacio Original: " + inventarioVacio);
        System.out.println("Resultado: " + sumaVacio);

        separador("Etapa 5. Caso 10 - subtract de inventario igual retorna vacio.");
        InventarioLetras inventarioIgual = new InventarioLetras("DRY");
        InventarioLetras inventarioIgual2 = new InventarioLetras("DRY");

        InventarioLetras restaInventariosIguales = inventarioIgual.subtract(inventarioIgual2);

        System.out.println("Resta igual: " + restaInventariosIguales);
        System.out.println("isEmpty(): " + restaInventariosIguales.isEmpty());


        separador("Etapa 5. Caso 11 - amplifies con 1 no cambia nada.");
        InventarioLetras inventarioAmplificadoPor1 = inventarioAmp.amplifies(1);

        System.out.println("ORIGINAL: " + inventarioAmp);
        System.out.println("RESULTADO: " + inventarioAmplificadoPor1);

    }

    /**
     * Imprime un separador visual para organizar la salida de las pruebas.
     *
     * @param titulo titulo del caso de prueba o seccion.
     */

    public static void separador(String titulo) {
        System.out.println("\n===============");
        System.out.println(" " + titulo);
        System.out.println("===============");
    }
}
