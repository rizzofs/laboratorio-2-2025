package ar.edu.unlu.poo.labo1.libreria;

import java.util.Objects;

public class Libro {
  private String titulo;
  private String artista;

  public Libro(String titulo, String artista) {
    this.titulo = titulo;
    this.artista = artista;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getArtista() {
    return artista;
  }

  @Override
  public boolean equals(Object o) {
    // 1. ¿Somos el mismo objeto en memoria?
    if (this == o) return true;
    // 2. ¿Es nulo o de una clase diferente?
    if (o == null || getClass() != o.getClass()) return false;
    // 3. Convertimos el objeto y comparamos los campos clave
    Libro libro = (Libro) o;
    return Objects.equals(titulo, libro.titulo) &&
        Objects.equals(artista, libro.artista);
  }

  @Override
  public int hashCode() {
    // 4. Generamos un hash code a partir de los mismos campos
    return Objects.hash(titulo, artista);
  }
}
