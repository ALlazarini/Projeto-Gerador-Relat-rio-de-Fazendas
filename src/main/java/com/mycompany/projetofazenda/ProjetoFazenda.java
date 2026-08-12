/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetofazenda;

import model.*;

public class ProjetoFazenda {

    public static void main(String[] args) {

        Fazenda fazenda = new Fazenda("Fazenda Teste", 2027);

        PlanejamentoAnual planejamento = fazenda.getPlanejamento();

        PlanejamentoMensal janeiro = planejamento.getMeses().get(0);

        Servico servico = new Servico(
                TipoServico.CIPATR,
                "Curral",
                "Treinamento planejado para janeiro."
        );

        janeiro.adicionarServico(servico);

        System.out.println("Fazenda: " + fazenda.getNome());
        System.out.println("Ano: " + planejamento.getAno());
        System.out.println("Mês: " + janeiro.getMes());
        System.out.println("Serviço: " + servico.getTipo());
        System.out.println("Local: " + servico.getLocal());
        System.out.println("Obs: " + servico.getObservacoes());
        System.out.println("Carga horária: " + servico.getTipo().getCargaHoraria() + " horas");
    }
}