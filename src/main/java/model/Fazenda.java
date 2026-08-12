/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author ALlazarini -> on GitHub 
 */
public class Fazenda {


    private String nome;
    private PlanejamentoAnual planejamento;

    public Fazenda(String nome, int ano) {
        this.nome = nome;
        this.planejamento = new PlanejamentoAnual(ano);
    }

    public String getNome() {
        return nome;
    }

    public PlanejamentoAnual getPlanejamento() {
        return planejamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
    

