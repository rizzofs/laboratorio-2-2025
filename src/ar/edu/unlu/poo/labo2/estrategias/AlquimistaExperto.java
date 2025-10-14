package ar.edu.unlu.poo.labo2.estrategias;

import ar.edu.unlu.poo.labo2.artefactos.Artefacto;

public class AlquimistaExperto implements EstrategiaDeMejora {
    public void mejorar(Artefacto artefacto){
        String tipo = artefacto.getTipo();
        if (tipo == "Poción"){
            artefacto.aumentarPoderConLimite(20, 100);
        }
    }

}
