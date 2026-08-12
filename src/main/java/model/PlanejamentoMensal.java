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

public class PlanejamentoMensal {

    private int mes;
    private List<Servico> servicos;

    public PlanejamentoMensal(int mes) {
        this.mes = mes;
        this.servicos = new ArrayList<>();
    }

    public int getMes() {
        return mes;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void adicionarServico(Servico servico) {
        servicos.add(servico);
    }

}