/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.projetofazenda;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
/**
 *
 * @author ALlazarini -> on GitHub
 */
public class GeradorPDF {
    
    

     public static void gerar(Relatorio r) throws Exception {


        String nomeArquivo = "Relatorio_" + r.getFazenda() + ".pdf";

        Document documento = new Document();

        PdfWriter.getInstance(
                documento,
                new FileOutputStream(nomeArquivo)
        );

        documento.open();

        // TÍTULO
        documento.add(
                new Paragraph("RELATÓRIO DE VISITA")
        );

        documento.add(
                new Paragraph(" ")
        );

        // FAZENDA
        documento.add(
                new Paragraph("FAZENDA")
        );

        documento.add(
                new Paragraph(
                        "Nome da fazenda: " + r.getFazenda()
                )
        );

        documento.add(
                new Paragraph(" ")
        );

        // TREINAMENTOS
        documento.add(
                new Paragraph("TREINAMENTOS")
        );

        if (r.isNr31()) {
            documento.add(new Paragraph("NR31"));
        }

        if (r.isNr35()) {
            documento.add(new Paragraph("NR35"));
        }

        if (r.isNr06()) {
            documento.add(new Paragraph("NR06"));
        }

        if (r.isNr09()) {
            documento.add(new Paragraph("NR09"));
        }

        documento.add(
                new Paragraph("OBS: " + r.getObsTreinamento())
        );

        documento.add(
                new Paragraph(" ")
        );

        // LOCAL DE VISITA
        documento.add(
                new Paragraph("LOCAL DE VISITA")
        );

        documento.add(
                new Paragraph(
                        "Tipo: " + r.getTipoLocal()
                )
        );

        documento.add(
                new Paragraph(
                        "Nome: " + r.getNomeLocal()
                )
        );

        documento.add(
                new Paragraph(
                        "OBS: " + r.getObsLocal()
                )
        );

        documento.add(
                new Paragraph(" ")
        );

        // COLABORADOR
        documento.add(
                new Paragraph("COLABORADORES")
        );

        documento.add(
                new Paragraph(
                        "Com EPI: " + (r.isComEpi() ? "Sim" : "Não")
                )
        );

        documento.add(
                new Paragraph(
                        "Colaborador: " + r.getColaborador()
                )
        );

        documento.add(
                new Paragraph(
                        "Função: " + r.getFuncao()
                )
        );

        documento.add(
                new Paragraph(
                        "Atividade: " + r.getAtividade()
                )
        );

        documento.add(
                new Paragraph(
                        "OBS: " + r.getObsColaborador()
                )
        );

        documento.add(
                new Paragraph(" ")
        );

        // OUTROS
        documento.add(
                new Paragraph("OUTRAS OBSERVAÇÕES")
        );

        documento.add(
                new Paragraph(
                        r.getOutros()
                )
        );

        documento.close();
    }
}
    

