/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author User
 */
public class Servico {
    
    private TipoServico tipo;
    private String local;
    private String observacoes;

    public Servico(TipoServico tipo, String local, String observacoes) {
        this.tipo = tipo;
        this.local = local;
        this.observacoes = observacoes;
    }

    public TipoServico getTipo() {
        return tipo;
    }

    public String getLocal() {
        return local;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
}
