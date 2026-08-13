/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.HashMap;
import java.util.Map;
import model.PlanejamentoAnual;
import model.PlanejamentoMensal;
import model.Servico;

/**
 *
 * @author ALlazarini -> on GitHub
 */
public class PlanejamentoService {
    
    private final Map<Integer, PlanejamentoAnual> planejamentos;

    public PlanejamentoService() {
        planejamentos = new HashMap<>();
    }

    public PlanejamentoAnual obterPlanejamento(int ano) {

        return planejamentos.computeIfAbsent(
                ano,
                PlanejamentoAnual::new
        );
    }

    public PlanejamentoMensal obterMes(int ano, int mes) {

        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException(
                    "O mês deve estar entre 1 e 12."
            );
        }

        PlanejamentoAnual planejamento =
                obterPlanejamento(ano);

        return planejamento.getMeses().get(mes - 1);
    }

    public void adicionarServico(
            int ano,
            int mes,
            Servico servico) {

        if (servico == null) {
            throw new IllegalArgumentException(
                    "O serviço não pode ser nulo."
            );
        }

        obterMes(ano, mes)
                .adicionarServico(servico);
    }
    
}
