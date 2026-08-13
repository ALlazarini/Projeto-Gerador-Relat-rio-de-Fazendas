/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author User
 */

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PlanejamentoAnual {
    
    private int ano;
    private List<PlanejamentoMensal> meses;
    private String informacoesColaboradores;
    private String informacoesGerais;
    private LocalDate dataCriacao;

    public PlanejamentoAnual(int ano) {
        this.ano = ano;
        this.meses = new ArrayList<>();
        this.informacoesColaboradores = "";
        this.informacoesGerais = "";
        this.dataCriacao = LocalDate.now();

        for (int i = 1; i <= 12; i++) {
            meses.add(new PlanejamentoMensal(i));
        }
    }

    public int getAno() {
        return ano;
    }

    public List<PlanejamentoMensal> getMeses() {
        return meses;
    }
    
    public String getInformacoesColaboradores() {
    return informacoesColaboradores;
}

    public void setInformacoesColaboradores(String informacoesColaboradores) {
        this.informacoesColaboradores = informacoesColaboradores;
    }

    public String getInformacoesGerais() {
        return informacoesGerais;
    }

    public void setInformacoesGerais(String informacoesGerais) {
        this.informacoesGerais = informacoesGerais;
    }
    
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    
}
