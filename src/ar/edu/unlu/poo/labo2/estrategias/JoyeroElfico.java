package ar.edu.unlu.poo.labo2.estrategias;

import ar.edu.unlu.poo.labo2.artefactos.Artefacto;

public class JoyeroElfico implements EstrategiaDeMejora {
    public void mejorar(Artefacto artefacto){
        String tipo = artefacto.getTipo();
        if (tipo == "Amuleto"){
            if (artefacto.getPoder() < 50){
                artefacto.duplicarPoder();
            } else {
                artefacto.aumentarPoderConPorcentaje(25);
            }
        }
    }
}
