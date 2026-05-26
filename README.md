# Inventario Letras

Proyecto en Java que implementa una estructura de datos para contar y manipular la frecuencia de letras del
alfabeto ingles (a-z) en un texto dado.

---

### Descripción

`InventarioLetras` permite construir un inventario a partir de cualquier otro texto, ignorando espacios, numeros y símbolos. Las letras mayúsculas se tratan como minusculas. Ademas de las operaciones basicas sobre el inventario, incluye funcionalidad de cifrado y descifrado mediante el **Cifrado Cesar**

---

### Estructura del proyecto

```text
InventarioLetras/
├── InventarioLetras.java #Clase principal con la lógica del inventario
├── Main.java #Punto de entrada del programa
├── MenuInventario.java #Menu interactivo por consola
└── PruebasInventario.java #Casos de prueba manuales
```

### Funcionalidades 

### Inventario

|Metodo | Descripción |
|---|---|
| `InventarioLetras(String data)` | Construye el inventario a partir de texto|
| `int size()` | Retorna el total de letras almacenadas|
| `boolean isEmpty()` | Indica si el inventario esta vacio|
| `int get(char letra)` | Retorna la frecuencia de la letra|
| `void set(char letra, int cantidad)` | Establece la cantidad de una letra|
| `String toString()` | Muestra el inventario ordenado|

### Cifrado Cesar

|Metodo | Descripción |
|---|---|
|`char encriptarCesar(char letra)` | Encripta la letra con desplazamiento fijo de 3|
|`char desencriptarCesar(char letra)` | Desencripta la letra con desplazamiento fijo de 3|
|`String encriptarPalabra(String palabra, int desplazamiento)` | Encripta la palabra o frase que se ingrese con el desplazamiento ingresado|
|`char encriptarCesar(String palabra, int desplazamiento)` | Desencripta la palabra o frase que se ingrese con el desplazamiento ingresado|

### Operaciones entre inventarios

|Metodo | Descripción |
|---|---|
|`InventarioLetras add(InventarioLetras sumando)` | Suma dos inventarios |
|`InventarioLetras amplifies(int n)` | Multiplica frecuencias por un factor |
|``InventarioLetras subtract(InventarioLetras restando)` | Resta dos inventarios; null se retorna si el resultado es invalido |

---

## Ejemplo de uso

```java
InventarioLetras inventario = new InventarioLetras("James Gosling");

inventario.get('a); // 1
inventario.size(); // 12
inventario.isEmpty(); // false
System.out.println(inventario); // [aeggijlmnoss]

//Cifrado Cesar
inventario.encriptarCesar('x');  // 'a'
inventario.encriptarPalabra("Richard Feynman", 3); // "ulfkudg ihbqpdq"
inventario.desencriptarPalabra("ulfkudg ihbqpdq", 3); // "richard feynman"

//Operaciones

InventarioLetras nuevo = new InventarioLetras("abc");
InventarioLetras suma = inventario.add(nuevo);
InventarioLetras doble = inventario.amplifies(2);
```

## Modo pruebas 
Por defecto, el programa ejecuta todos los casos de prueba definidos en `PruebasInventario`.

---

## Modo menú interactivo
Permite interactuar con el inventario desde la consola.

---

## Desiciones de Diseño
- El arreglo interno `inventario` tiene siempre exactamente 26 posiciones, una por letra del alfabeto. Garantizando esto acceso O(1) para cualquier consulta.
- El metodo `subtract` retorna `null` en lugar de lanzar una excepción cuando la resta es invalida permitiendo manejar el caso sin necesidad de un bloque `try-catch`.
- El cifrado César usa `Math.floorMod` en lugar del operador `%` para manejar correctamente si alguien ingresa desplazamientos negativos sin producir indices negativos (no deberia en este proyecto).
- La validación de letras se centraliza en metodos privados como `validarLetra` y `obtenerIndice`, evitando repetir la misma logica en varios metodos publicos.
- El metodo `set` no modifica directamente el arreglo, usa `actualizarContadores` para mantener sincronizado el arreglo interno.
- Se usa `cifrarCesar` y `cifrarPalabra` como metodos privados, toda la logica del cifrado vive en estos metodos que reciben el desplazamiento como párametro, los metodos publicos solo fijan el desplazamiento o lo invierten.
  Esto elimina duplicación de codigo entre encriptar y desencriptar.
  
