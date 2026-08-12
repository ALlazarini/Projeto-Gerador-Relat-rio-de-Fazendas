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

public class PlanejamentoAnual {
    
     private int ano;
    private List<PlanejamentoMensal> meses;

    public PlanejamentoAnual(int ano) {
        this.ano = ano;
        this.meses = new ArrayList<>();

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
    
}
