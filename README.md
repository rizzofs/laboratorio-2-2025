# Sistema de Gestión de Artefactos Mágicos de Instanciaveria

* Actividad: Laboratorio 2 - POO 2025 (11[02]76)
* Tipo de trabajo: Laboratorio en clase
* Fecha: 14-10-2025
* Duración: de 18 a 21.
* Entregable: Si.

# Grupo

- Estudiante 1 (Legajo 1) - cor...@estudiante1.com
- Estudiante 2 (Legajo 2) - cor...@estudiante2.com

# Contexto / Escenario

¡El Torneo de los Tres Reinos es inminente! La Academia de Hechicería Arcana ha reunido a un concilio de los más grandes artesanos para potenciar su arsenal. Un maestro **Herrero Enano** aplicará su magia rúnica a las _varitas_, y un delicado **Artesano Élfico** es especialista en encantar _amuletos_. Además, un sabio **Escriba Arcano** ha sido convocado para re-inscribir los _pergaminos_ de poder, aunque su magia es volátil y arriesgada. Finalmente, un **Alquimista Experto** está 
destilando pociones para fortalecer a los campeones, asegurándose de que no excedan los límites mortales.

El sistema actual del `CatalogoArtefactos` es demasiado rígido para manejar estas especialidades. Tu misión es refactorizar el sistema para que pueda colaborar con las distintas **Estrategias de Mejora** sin necesidad de modificar su código interno cada vez que un nuevo artesano se una a la causa.

# Objetivo del Laboratorio

1. Aplicar el Principio de **Inversión de Control** para desacoplar el `CatalogoArtefactos` de la lógica concreta de mejora.
2. Implementar el patrón de **Inyección de Dependencias** por constructor para proporcionar al catálogo su estrategia de mejora.
3. Utilizar interfaces y polimorfismo para crear un sistema flexible y extensible de mejoras de artefactos.
4. Validar la solución utilizando los tests de `JUnit` provistos, que ahora verificarán tanto el funcionamiento del catálogo como la correcta aplicación de las mejoras.

# Enunciado

Debes refactorizar y agregar las clases necesarias para manejar los nuevos requisitos.

1.  **Crear la Abstracción (El Contrato):**
    *   Define una nueva interfaz llamada `EstrategiaDeMejora`.
    *   Esta interfaz debe tener un único método: `void mejorar(Artefacto artefacto)`.

2.  **Crear las Implementaciones Concretas (Los Especialistas):**
    *   Implementa una clase `ForjadorDeRunas` que aplique la interfaz `EstrategiaDeMejora`. Su lógica de `mejorar` será: si el artefacto es de tipo "Varita", incrementa su poder en 15 puntos.
    *   Implementa una clase `JoyeroElfico` que también aplique `EstrategiaDeMejora`. Su lógica será: si el artefacto es de tipo "Amuleto", y su poder es menor a 50, duplica su poder. Si es mayor o igual a 50, lo incrementa en un 25%.
    *   Implementa una clase `EscribaArcano` que aplique `EstrategiaDeMejora`. Su lógica de `mejorar` es arriesgada: si el artefacto es de tipo "Pergamino" y su poder es menor a 30, incrementa su poder en 25. Si su poder es 30 o más, el encantamiento es inestable y pierde 10 puntos de poder.
    *   Implementa una clase `AlquimistaExperto` que aplique `EstrategiaDeMejora`. Su lógica de `mejorar` es cuidadosa: si el artefacto es de tipo "Poción", incrementa su poder en 20 puntos, pero **sin que el nivel de poder final supere los 100**. (Ej: si una poción tiene 85 de poder, su nuevo poder será 100, no 105).

3.  **Refactorizar `CatalogoArtefactos` (El Cliente):**
    *   Modifica la clase `CatalogoArtefactos` para que reciba en su constructor un objeto de tipo `EstrategiaDeMejora`.
    *   Almacena esta estrategia en un campo privado. El catálogo ya no debe crear, conocer ni depender de ninguna clase de mejora concreta.
    *   Añade un nuevo método público al catálogo: `void aplicarMejoras()`. Este método debe recorrer **todos** los artefactos del catálogo y aplicarles la estrategia de mejora que recibió en el constructor.

4.  **Validar con Pruebas:**
    *   El repositorio incluye un archivo `CatalogoMejoradoTest.java` con tests que están diseñados para fallar al principio.
    *   Estos tests crearán instancias del `CatalogoArtefactos` inyectándole las diferentes estrategias (`ForjadorDeRunas`, `JoyeroElfico`) y verificarán que los artefactos se modifican correctamente después de llamar a `aplicarMejoras()`.
    *   Tu objetivo es escribir el código necesario para que todos los tests pasen a verde.


### Restricciones de Tiempo y Forma de Entrega

- **Duración**: 2 horas. (Hasta las 21 Hs)
- **Entrega**: Al finalizar la clase, debe realizar un *Pull Request* al repositorio oficial *forkeado* originalmente.