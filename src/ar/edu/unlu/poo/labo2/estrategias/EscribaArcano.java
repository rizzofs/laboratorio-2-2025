package ar.edu.unlu.poo.labo2.estrategias;

import ar.edu.unlu.poo.labo2.artefactos.Artefacto;

public class EscribaArcano implements EstrategiaDeMejora {
    public void mejorar(Artefacto artefacto){
        String tipo = artefacto.getTipo();
        if (tipo == "Pergamino"){
            if (artefacto.getPoder() < 30){
                artefacto.aumentarPoder(25);
            } else {
                artefacto.bajarPoder(10);
            }
        }
    }
}
