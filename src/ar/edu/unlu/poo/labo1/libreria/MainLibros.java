package ar.edu.unlu.poo.labo1.libreria;

import java.util.*;

public class MainLibros {
  public static void main(String[] args) {
    // ArrayList
    List<Libro> misLibros = new ArrayList<>();

    Libro libro1 = new Libro("Cien Años de Soledad", "Gabriel García Márquez");
    Libro libro2 = new Libro("El Señor de los Anillos", "J.R.R. Tolkien");
    Libro libro3 = new Libro("Cien Años de Soledad", "Gabriel García Márquez");

    misLibros.add(libro1);
    misLibros.add(libro2);
    misLibros.add(libro1); // ¡Un duplicado es válido! Posición 2

    System.out.println(misLibros.get(0).getTitulo()); // Imprime "Cien Años de Soledad"
    System.out.println(misLibros.size()); // Imprime 3

    // HashSet
    Set<Libro> catalogo = new HashSet<>();

    catalogo.add(libro1);
    catalogo.add(libro2);
    catalogo.add(libro3); // Se ignora, porque ya existe según equals()

    System.out.println(catalogo.size()); // Imprime 2

    // HashMap
    Map<String, Integer> librosPorGenero = new HashMap<>();

    // Agregamos libros y contamos...
    librosPorGenero.put("Fantasía", 1);
    librosPorGenero.put("Realismo Mágico", 1);

    System.out.println(librosPorGenero.get("Fantasía")); // Imprime 1

    // Si llega otro de Fantasía
    int conteoActual = librosPorGenero.get("Fantasía");
    librosPorGenero.put("Fantasía", conteoActual + 1);

    System.out.println(librosPorGenero.get("Fantasía")); // Imprime 2

  }
}
