package ar.edu.unlu.poo.labo2.artefactos;

import ar.edu.unlu.poo.labo2.artefactos.Artefacto;
import ar.edu.unlu.poo.labo2.artefactos.CatalogoArtefactos;
import ar.edu.unlu.poo.labo2.estrategias.EstrategiaDeMejora;
import ar.edu.unlu.poo.labo2.estrategias.ForjadorDeRunas;
import ar.edu.unlu.poo.labo2.estrategias.JoyeroElfico;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Tests del Catálogo de Artefactos Mejorado")
class CatalogoMejoradoTest {

  private Artefacto varitaBasica;
  private Artefacto amuletoDebil;
  private Artefacto amuletoFuerte;
  private Artefacto pergaminoSimple;
  private Artefacto pergaminoComplejo;
  private Artefacto pocionCurativa;
  private Artefacto pocionPoderosa;

  @BeforeEach
  void setUp() {
    varitaBasica = new Artefacto("Varita de Aprendiz", 50, "Varita");
    amuletoDebil = new Artefacto("Colgante de Valor", 40, "Amuleto");
    amuletoFuerte = new Artefacto("Amuleto de los Reyes", 80, "Amuleto");
    pergaminoSimple = new Artefacto("Hechizo de Luz", 20, "Pergamino");
    pergaminoComplejo = new Artefacto("Invocación Mayor", 50, "Pergamino");
    pocionCurativa = new Artefacto("Poción de Salud", 30, "Poción");
    pocionPoderosa = new Artefacto("Elixir de Gigante", 85, "Poción");
  }

  @Test
  @DisplayName("El Forjador de Runas debe incrementar el poder de las Varitas en 15")
  void testMejorarConForjadorDeRunasIncrementaPoderDeVaritas() {
    EstrategiaDeMejora estrategiaForja = new ForjadorDeRunas();
    CatalogoArtefactos catalogo = new CatalogoArtefactos(estrategiaForja);
    catalogo.agregarArtefacto(varitaBasica);
    catalogo.agregarArtefacto(amuletoDebil);

    catalogo.aplicarMejoras();

    assertEquals(65, varitaBasica.getNivelDePoder(), "La varita debería tener 15 puntos más de poder.");
    assertEquals(40, amuletoDebil.getNivelDePoder(), "El amuleto no debería haber sido modificado por el forjador.");
  }

  @Test
  @DisplayName("El Joyero Élfico debe duplicar el poder de Amuletos con poder menor a 50")
  void testMejorarConJoyeroElficoDuplicaPoderDeAmuletosDebiles() {
    EstrategiaDeMejora estrategiaJoyería = new JoyeroElfico();
    CatalogoArtefactos catalogo = new CatalogoArtefactos(estrategiaJoyería);
    catalogo.agregarArtefacto(amuletoDebil);
    catalogo.agregarArtefacto(varitaBasica); // Se agrega para asegurar que no la modifica.

    catalogo.aplicarMejoras();

    assertEquals(80, amuletoDebil.getNivelDePoder(), "El amuleto débil debería haber duplicado su poder.");
    assertEquals(50, varitaBasica.getNivelDePoder(), "La varita no debería haber sido modificada por el joyero.");
  }

  @Test
  @DisplayName("El Joyero Élfico debe incrementar en 25% el poder de Amuletos con poder mayor o igual a 50")
  void testMejorarConJoyeroElficoAumentaPoderDeAmuletosFuertes() {
    EstrategiaDeMejora estrategiaJoyería = new JoyeroElfico();
    CatalogoArtefactos catalogo = new CatalogoArtefactos(estrategiaJoyería);
    catalogo.agregarArtefacto(amuletoFuerte);
    catalogo.agregarArtefacto(pergaminoInutil); // Se agrega para asegurar que no lo modifica.

    catalogo.aplicarMejoras();

    assertEquals(100, amuletoFuerte.getNivelDePoder(), "El amuleto fuerte debería tener un 25% más de poder.");
    assertEquals(10, pergaminoInutil.getNivelDePoder(), "El pergamino no debería haber sido modificado por el joyero.");
  }

  @Test
  @DisplayName("El Escriba Arcano debe potenciar Pergaminos de bajo poder (+25)")
  void testEscribaArcanoMejoraPergaminosDebiles() {
    EstrategiaDeMejora estrategiaEscriba = new EscribaArcano();
    CatalogoArtefactos catalogo = new CatalogoArtefactos(estrategiaEscriba);
    catalogo.agregarArtefacto(pergaminoSimple);
    catalogo.agregarArtefacto(varitaBasica); // Artefacto de otro tipo para control

    catalogo.aplicarMejoras();

    assertEquals(45, pergaminoSimple.getNivelDePoder(), "El pergamino simple debería haber ganado 25 de poder.");
    assertEquals(50, varitaBasica.getNivelDePoder(), "La varita no debe ser afectada por el escriba.");
  }

  @Test
  @DisplayName("El Escriba Arcano debe debilitar Pergaminos de alto poder por inestabilidad (-10)")
  void testEscribaArcanoDebilitaPergaminosComplejos() {
    EstrategiaDeMejora estrategiaEscriba = new EscribaArcano();
    CatalogoArtefactos catalogo = new CatalogoArtefactos(estrategiaEscriba);
    catalogo.agregarArtefacto(pergaminoComplejo);

    catalogo.aplicarMejoras();

    assertEquals(40, pergaminoComplejo.getNivelDePoder(), "El pergamino complejo debería haber perdido 10 de poder.");
  }

  @Test
  @DisplayName("El Alquimista debe mejorar Pociones que no superen el límite de poder (+20)")
  void testAlquimistaMejoraPocionesNormales() {
    EstrategiaDeMejora estrategiaAlquimia = new AlquimistaExperto();
    CatalogoArtefactos catalogo = new CatalogoArtefactos(estrategiaAlquimia);
    catalogo.agregarArtefacto(pocionCurativa);
    catalogo.agregarArtefacto(amuletoDebil); // Artefacto de otro tipo para control

    catalogo.aplicarMejoras();

    assertEquals(50, pocionCurativa.getNivelDePoder(), "La poción curativa debería haber ganado 20 de poder.");
    assertEquals(40, amuletoDebil.getNivelDePoder(), "El amuleto no debe ser afectado por el alquimista.");
  }

  @Test
  @DisplayName("El Alquimista no debe mejorar Pociones por encima del poder máximo de 100")
  void testAlquimistaRespetaElPoderMaximoDePociones() {
    EstrategiaDeMejora estrategiaAlquimia = new AlquimistaExperto();
    CatalogoArtefactos catalogo = new CatalogoArtefactos(estrategiaAlquimia);
    catalogo.agregarArtefacto(pocionPoderosa);

    catalogo.aplicarMejoras();

    assertEquals(100, pocionPoderosa.getNivelDePoder(), "La poción poderosa debería haber alcanzado el límite de 100, no 105.");
  }

  @Test
  @DisplayName("Aplicar mejoras sobre múltiples artefactos del mismo tipo funciona correctamente")
  void testAplicarMejorasAfectaATodosLosArtefactosCorrespondientes() {
    EstrategiaDeMejora estrategiaForja = new ForjadorDeRunas();
    CatalogoArtefactos catalogo = new CatalogoArtefactos(estrategiaForja);
    Artefacto otraVarita = new Artefacto("Vara de Tejo", 70, "Varita");
    catalogo.agregarArtefacto(varitaBasica);
    catalogo.agregarArtefacto(otraVarita);
    catalogo.agregarArtefacto(amuletoFuerte);

    catalogo.aplicarMejoras();

    assertEquals(65, varitaBasica.getNivelDePoder(), "La primera varita debería tener +15 de poder.");
    assertEquals(85, otraVarita.getNivelDePoder(), "La segunda varita también debería tener +15 de poder.");
    assertEquals(80, amuletoFuerte.getNivelDePoder(), "El amuleto no debería haber cambiado.");
  }

  @Test
  @DisplayName("Aplicar mejoras en un catálogo vacío no debe producir errores")
  void testAplicarMejorasEnCatalogoVacioNoLanzaExcepcion() {
    EstrategiaDeMejora cualquierEstrategia = new ForjadorDeRunas();
    CatalogoArtefactos catalogoVacio = new CatalogoArtefactos(cualquierEstrategia);

    assertDoesNotThrow(
        catalogoVacio::aplicarMejoras,
        "Llamar a aplicarMejoras() en un catálogo vacío no debería lanzar ninguna excepción."
    );
  }
}