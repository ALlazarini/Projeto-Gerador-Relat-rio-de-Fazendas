/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.projetofazenda;

/**
 *
 * @author ALlazarini -> on GitHub
 */

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import model.PlanejamentoAnual;
import model.PlanejamentoMensal;
import model.Servico;

import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;

public class GeradorPDF {

    public static void gerar(
            String nomeFazenda,
            PlanejamentoAnual planejamento
    ) throws Exception {

        String nomeArquivo =
                "Planejamento_" +
                nomeFazenda.replaceAll("[\\\\/:*?\"<>|]", "_") +
                "_" +
                planejamento.getAno() +
                ".pdf";

        Document documento = new Document();

        PdfWriter.getInstance(
                documento,
                new FileOutputStream(nomeArquivo)
        );

        documento.open();

        // =========================
        // CABEÇALHO
        // =========================

        documento.add(
                new Paragraph("PLANEJAMENTO ANUAL")
        );

        documento.add(
                new Paragraph(
                        "Fazenda: " + nomeFazenda
                )
        );

        documento.add(
                new Paragraph(
                        "Ano: " + planejamento.getAno()
                )
        );

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        documento.add(
                new Paragraph(
                        "Data de criação: "
                        + planejamento.getDataCriacao().format(formato)
                )
        );

        documento.add(
                new Paragraph(" ")
        );

        // =========================
        // MESES
        // =========================

        String[] nomesMeses = {
            "JANEIRO",
            "FEVEREIRO",
            "MARÇO",
            "ABRIL",
            "MAIO",
            "JUNHO",
            "JULHO",
            "AGOSTO",
            "SETEMBRO",
            "OUTUBRO",
            "NOVEMBRO",
            "DEZEMBRO"
        };

        for (int i = 0; i < planejamento.getMeses().size(); i++) {

            PlanejamentoMensal mes =
                    planejamento.getMeses().get(i);

            documento.add(
                    new Paragraph(
                            nomesMeses[i]
                    )
            );

            documento.add(
                    new Paragraph(
                            "----------------------------------------"
                    )
            );

            if (mes.getServicos().isEmpty()) {

                documento.add(
                        new Paragraph(
                                "Nenhum serviço planejado."
                        )
                );

            } else {

                for (Servico servico : mes.getServicos()) {

                    documento.add(
                            new Paragraph(
                                    "Serviço: "
                                    + servico.getTipo().getDescricao()
                            )
                    );

                    int carga =
                            servico.getTipo().getCargaHoraria();

                    if (carga > 0) {
                        documento.add(
                                new Paragraph(
                                        "Carga horária: "
                                        + carga
                                        + " horas"
                                )
                        );
                    } else {
                        documento.add(
                                new Paragraph(
                                        "Carga horária: "
                                        + "Não se aplica"
                                )
                        );
                    }

                    documento.add(
                            new Paragraph(
                                    "Local: "
                                    + servico.getLocal()
                            )
                    );

                    documento.add(
                            new Paragraph(
                                    "Observações: "
                                    + servico.getObservacoes()
                            )
                    );

                    documento.add(
                            new Paragraph(" ")
                    );
                }
            }

            documento.add(
                    new Paragraph(" ")
            );
        }

        // =========================
        // INFORMAÇÕES GERAIS
        // =========================

        documento.add(
                new Paragraph(
                        "INFORMAÇÕES GERAIS"
                )
        );

        documento.add(
                new Paragraph(
                        "Sobre colaboradores:"
                )
        );

        documento.add(
                new Paragraph(
                        planejamento.getInformacoesColaboradores()
                )
        );

        documento.add(
                new Paragraph(" ")
        );

        documento.add(
                new Paragraph(
                        "Gerais:"
                )
        );

        documento.add(
                new Paragraph(
                        planejamento.getInformacoesGerais()
                )
        );

        documento.close();
    }
}
    

