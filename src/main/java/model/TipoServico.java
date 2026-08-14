/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author User
 */
public enum TipoServico {
    
    DIAGNOSTICO_MENSAL("Diagnóstico mensal", 0),
    PGR("PGR", 0),
    PCMSO("PCMSO", 0),
    LTCAT("LTCAT", 0),
    PPP("PPP", 0),
    AEP("AEP", 0),
    AET("AET", 0),
    ESOCIAL("eSocial", 0),

    CIPATR("Treinamento CIPATR", 20),
    DEFENSIVOS_AGRICOLAS("Treinamento de Defensivos Agrícolas", 20),
    OPERACAO_SEGURA_MAQUINAS("Operação segura de máquinas e implementos", 20),
    MAQUINAS_AUTOPROPELIDAS("Máquinas autopropelidas e implementos", 24),
    MOTOSSERRA_MOTOPODA("Motosserra, motopoda e similares", 8),
    ESPACO_CONFINADO("Espaço Confinado", 8),
    TRABALHO_EM_ALTURA("Trabalho em Altura", 8),

    NR31("NR31", 24),
    PRIMEIROS_SOCORROS("Primeiros Socorros", 4),
    INSPECAO_CALDEIRA("Inspeção de Caldeira", 0),
    LEGISLACAO("Legislação", 4),
    EPIS("EPI's", 6),
    ERGONOMIA("Ergonomia", 4),
    ELETRICA_NR10("Elétrica - NR10", 10);

    private final String descricao;
    private final int cargaHoraria;

    TipoServico(String descricao, int cargaHoraria) {
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
