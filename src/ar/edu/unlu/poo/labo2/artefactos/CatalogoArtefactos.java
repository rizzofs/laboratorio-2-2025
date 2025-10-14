package ar.edu.unlu.poo.labo2.artefactos;

import ar.edu.unlu.poo.labo2.estrategias.EstrategiaDeMejora;

import java.util.*;

public class CatalogoArtefactos {
    private Set<Artefacto> l_artefactos;
    private EstrategiaDeMejora estrategiaDeMejora;

    public CatalogoArtefactos(EstrategiaDeMejora estrategiaDeMejora) {
        this.l_artefactos = new HashSet<>();
        this.estrategiaDeMejora = estrategiaDeMejora;
    }

    public CatalogoArtefactos() {
        this.l_artefactos = new HashSet<>();
    }

    public void agregarArtefacto(Artefacto artefacto) {
        l_artefactos.add(artefacto);
    }

    public Set<Artefacto> obtenerArtefactosUnicos() {
        return new HashSet<>(l_artefactos);
        
    }

    public List<Artefacto> buscarArtefactosPorTipo(String tipo) {
        List<Artefacto> lista = new ArrayList<>();

        l_artefactos.forEach(artefacto -> {
            if (artefacto.getTipo() == tipo){
                lista.add(artefacto);
            }
        });

        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getPoder() > lista.get(j + 1).getPoder()) {
                    Artefacto temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }

        return lista;
    }

    public Map<String, Integer> contarArtefactosPorTipo() {
        Map<String, Integer> mapa = new HashMap<>();

        for (Artefacto artefacto:l_artefactos){
            String tipoAux = artefacto.getTipo();
            mapa.put(tipoAux, mapa.getOrDefault(tipoAux,0) + 1);
        }
        return mapa;
    }

    public Artefacto obtenerArtefactoMasPoderoso() {
        if(l_artefactos.isEmpty()){
            return null;
        }
        Artefacto mayorAux = null;
        for(Artefacto art : l_artefactos){
            if(mayorAux == null || art.getPoder() > mayorAux.getPoder()){
                mayorAux = art;
            }

        }
        return  mayorAux;

    }

    public void aplicarMejoras() {
        for (Artefacto artefacto : l_artefactos){
            artefacto.mejorar(estrategiaDeMejora);
        }
    }
}
