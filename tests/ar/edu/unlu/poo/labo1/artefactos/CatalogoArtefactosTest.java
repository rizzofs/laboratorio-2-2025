package ar.edu.unlu.poo.labo1.artefactos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CatalogoArtefactosTest {

  private CatalogoArtefactos catalogo;
  private Artefacto varitaSauco;
  private Artefacto capaInvisibilidad;
  private Artefacto pocionMultijugos;
  private Artefacto piedraFilosofal;

  @BeforeEach
  void setUp() {
    catalogo = new CatalogoArtefactos();
    varitaSauco = new Artefacto("Varita de Sauco", 100, "Varita");
    capaInvisibilidad = new Artefacto("Capa de Invisibilidad", 95, "Amuleto");
    pocionMultijugos = new Artefacto("Poción Multijugos", 50, "Poción");
    piedraFilosofal = new Artefacto("Piedra Filosofal", 98, "Amuleto");

    catalogo.agregarArtefacto(varitaSauco);
    catalogo.agregarArtefacto(capaInvisibilidad);
    catalogo.agregarArtefacto(pocionMultijugos);
    catalogo.agregarArtefacto(piedraFilosofal);
  }

  @Test
  @DisplayName("Debe evitar añadir artefactos duplicados por nombre")
  void testNoAgregaDuplicados() {
    Artefacto varitaRepetida = new Artefacto("Varita de Sauco", 100, "Varita");
    catalogo.agregarArtefacto(varitaRepetida);
    assertEquals(4, catalogo.obtenerArtefactosUnicos().size(), "El tamaño del catálogo no debe cambiar al agregar un duplicado.");
  }

  @Test
  @DisplayName("Debe devolver un conjunto de artefactos únicos")
  void testObtenerArtefactosUnicos() {
    Set<Artefacto> unicos = catalogo.obtenerArtefactosUnicos();
    assertNotNull(unicos);
    assertEquals(4, unicos.size());
    assertTrue(unicos.contains(varitaSauco));
  }

  @Test
  @DisplayName("Debe buscar y ordenar artefactos por tipo correctamente")
  void testBuscarArtefactosPorTipoOrdenados() {
    List<Artefacto> amuletos = catalogo.buscarArtefactosPorTipo("Amuleto");
    assertNotNull(amuletos);
    assertEquals(2, amuletos.size());
    assertEquals(piedraFilosofal, amuletos.get(0), "El primer amuleto debe ser el de mayor poder.");
    assertEquals(capaInvisibilidad, amuletos.get(1));
  }

  @Test
  @DisplayName("Debe devolver una lista vacía si el tipo no existe")
  void testBuscarArtefactosPorTipoInexistente() {
    List<Artefacto> pergaminos = catalogo.buscarArtefactosPorTipo("Pergamino");
    assertNotNull(pergaminos);
    assertTrue(pergaminos.isEmpty());
  }

  @Test
  @DisplayName("Debe contar artefactos por tipo correctamente")
  void testContarArtefactosPorTipo() {
    Map<String, Integer> conteo = catalogo.contarArtefactosPorTipo();
    assertNotNull(conteo);
    assertEquals(3, conteo.size());
    assertEquals(1, conteo.get("Varita"));
    assertEquals(1, conteo.get("Poción"));
    assertEquals(2, conteo.get("Amuleto"));
  }

  @Test
  @DisplayName("Debe devolver el artefacto más poderoso")
  void testObtenerArtefactoMasPoderoso() {
    Artefacto masPoderoso = catalogo.obtenerArtefactoMasPoderoso();
    assertNotNull(masPoderoso);
    assertEquals("Varita de Sauco", masPoderoso.getNombre());
    assertEquals(100, masPoderoso.getPoder());
  }

  @Test
  @DisplayName("Debe devolver null si el catálogo está vacío al buscar el más poderoso")
  void testObtenerMasPoderosoCatalogoVacio() {
    CatalogoArtefactos catalogoVacio = new CatalogoArtefactos();
    assertNull(catalogoVacio.obtenerArtefactoMasPoderoso());
  }

  @Test
  @DisplayName("equals y hashCode deben funcionar como se espera en Artefacto")
  void testEqualsAndHashCode() {
    Artefacto a1 = new Artefacto("Giratiempo", 80, "Amuleto");
    Artefacto a2 = new Artefacto("Giratiempo", 85, "Colgante");
    assertEquals(a1, a2, "Dos artefactos con el mismo nombre deben ser iguales.");
    assertEquals(a1.hashCode(), a2.hashCode(), "Los hashCodes deben ser iguales si los objetos son iguales.");
  }
}
