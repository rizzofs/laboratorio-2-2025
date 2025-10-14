package ar.edu.unlu.poo.labo2.artefactos;

import ar.edu.unlu.poo.labo2.estrategias.EstrategiaDeMejora;

import java.util.Objects;

public class Artefacto {
    private String nombre;
    private int poder;
    private String tipo;

    public Artefacto(String nombre, int poder, String tipo) {
        this.nombre = nombre;
        this.poder = poder;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPoder() {
        return poder;
    }

    public double getNivelDePoder() {
        return poder;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Artefacto artefacto = (Artefacto) o;
        return Objects.equals(nombre, artefacto.nombre);
    }
    @Override
    public int hashCode(){
        return Objects.hash(nombre);
    }

    public void aumentarPoder(int cantidad){
        poder += cantidad;
    }

    public void aumentarPoderConLimite(int cantidad, int limite){
        poder += cantidad;
        if (poder > limite){
            poder = limite;
        }
    }

    public void mejorar(EstrategiaDeMejora estrategiaDeMejora){
        estrategiaDeMejora.mejorar(this);
    }

    public void bajarPoder(int cantidad){
        poder -= cantidad;
    }

    public void duplicarPoder(){
        poder = poder * 2;
    }

    public void aumentarPoderConPorcentaje(double porcentaje){
        double resultado = poder * (1 + (porcentaje / 100));
        poder = (int) resultado;
    }
}
