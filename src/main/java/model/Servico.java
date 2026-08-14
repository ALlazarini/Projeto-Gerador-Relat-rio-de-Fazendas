/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author User
 */
public class Servico {
    
        private TipoServico tipo;
    private LocalDate dataServico;
    private String local;
    private String observacoes;
    private List<String> caminhosFotos;

    public Servico(
            TipoServico tipo,
            LocalDate dataServico,
            String local,
            String observacoes
    ) {
        this.tipo = tipo;
        this.dataServico = dataServico;
        this.local = local;
        this.observacoes = observacoes;
        this.caminhosFotos = new ArrayList<>();
    }

    public TipoServico getTipo() {
        return tipo;
    }

    public LocalDate getDataServico() {
        return dataServico;
    }

    public String getLocal() {
        return local;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public List<String> getCaminhosFotos() {
        return caminhosFotos;
    }

    public void setTipo(TipoServico tipo) {
        this.tipo = tipo;
    }

    public void setDataServico(LocalDate dataServico) {
        this.dataServico = dataServico;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setCaminhosFotos(List<String> caminhosFotos) {
        this.caminhosFotos = caminhosFotos;
    }

    public void adicionarFoto(String caminhoFoto) {
        this.caminhosFotos.add(caminhoFoto);
    }
}
