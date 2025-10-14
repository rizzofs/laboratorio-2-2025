package ar.edu.unlu.poo.labo2.estrategias;

import ar.edu.unlu.poo.labo2.artefactos.Artefacto;

public class ForjadorDeRunas implements EstrategiaDeMejora {
    public void mejorar(Artefacto artefacto){
        String tipo = artefacto.getTipo();
        if (tipo == "Varita"){
            artefacto.aumentarPoder(15);
        }
    }
}
