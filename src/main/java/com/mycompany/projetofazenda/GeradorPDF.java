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
import com.lowagie.text.Image;

import model.PlanejamentoAnual;
import model.PlanejamentoMensal;
import model.Servico;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.InputStream;

public class GeradorPDF {

    public static void gerar(
            
            String nomeFazenda,
            PlanejamentoAnual planejamento
            
    ) throws Exception {

        String nomeArquivo =
                "Planejamento_" +
                limparNomeArquivo(nomeFazenda) +
                "_" +
                planejamento.getAno() +
                ".pdf";

        Document documento = new Document();

        PdfWriter.getInstance(
                documento,
                new FileOutputStream(nomeArquivo)
        );

        documento.open();
        
        adicionarLogo(documento);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        documento.add(
                new Paragraph("PLANEJAMENTO ANUAL")
        );

        documento.add(
                new Paragraph("Fazenda: " + nomeFazenda)
        );

        documento.add(
                new Paragraph("Ano: " + planejamento.getAno())
        );

        documento.add(
                new Paragraph(
                        "Data de criação: "
                        + planejamento.getDataCriacao().format(formato)
                )
        );

        documento.add(
                new Paragraph(" ")
        );

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
                    new Paragraph(nomesMeses[i])
            );

            documento.add(
                    new Paragraph("----------------------------------------")
            );

            List<Servico> servicosOrdenados =
                    ordenarServicosPorData(mes);

            if (servicosOrdenados.isEmpty()) {

                documento.add(
                        new Paragraph("Nenhum serviço planejado.")
                );

            } else {

                for (Servico servico : servicosOrdenados) {

                    documento.add(
                            new Paragraph(
                                    "Data: "
                                    + servico.getDataServico().format(formato)
                            )
                    );

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
                                        "Carga horária: Não se aplica"
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

        documento.add(
                new Paragraph("INFORMAÇÕES SOBRE COLABORADORES")
        );

        documento.add(
                new Paragraph("----------------------------------------")
        );

        if (planejamento.getInformacoesColaboradores() == null
                || planejamento.getInformacoesColaboradores().trim().isEmpty()) {

            documento.add(
                    new Paragraph("Nenhuma informação registrada.")
            );

        } else {

            documento.add(
                    new Paragraph(
                            planejamento.getInformacoesColaboradores()
                    )
            );
        }

        documento.close();
    }

    public static void gerarMensal(
            
            String nomeFazenda,
            PlanejamentoMensal mes,
            int ano,
            String nomeMes,
            String informacoesColaboradores
    ) throws Exception {

        String nomeArquivo =
                "Relatorio_Mensal_" +
                limparNomeArquivo(nomeFazenda) +
                "_" +
                nomeMes +
                "_" +
                ano +
                ".pdf";

        Document documento = new Document();

        PdfWriter.getInstance(
                documento,
                new FileOutputStream(nomeArquivo)
        );

        documento.open();
        
        adicionarLogo(documento);

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        documento.add(
                new Paragraph("RELATÓRIO MENSAL - " + nomeMes.toUpperCase())
                );

        documento.add(
                new Paragraph("Fazenda: " + nomeFazenda)
        );


        documento.add(
                new Paragraph("Ano: " + ano)
        );

        documento.add(
                new Paragraph(
                        "Data de geração: "
                        + LocalDate.now().format(formato)
                )
        );

        documento.add(
                new Paragraph(" ")
        );

        documento.add(
                new Paragraph("SERVIÇOS REALIZADOS / PLANEJADOS")
        );

        documento.add(
                new Paragraph("----------------------------------------")
        );

        documento.add(
                new Paragraph(" ")
        );

        List<Servico> servicosOrdenados =
                ordenarServicosPorData(mes);

        if (servicosOrdenados.isEmpty()) {

            documento.add(
                    new Paragraph(
                            "Nenhum serviço registrado para este mês."
                    )
            );

        } else {

            LocalDate dataAtual = null;

            for (Servico servico : servicosOrdenados) {

                if (!servico.getDataServico().equals(dataAtual)) {

                    dataAtual = servico.getDataServico();

                    documento.add(
                            new Paragraph(" ")
                    );

                    documento.add(
                            new Paragraph(
                                    "DIA " + dataAtual.format(formato)
                            )
                    );

                    documento.add(
                            new Paragraph("----------------------------------------")
                    );
                }

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
                                    "Carga horária: Não se aplica"
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

                adicionarFotosDoServico(
                        documento,
                        servico
                );

                documento.add(
                        new Paragraph(" ")
                );
            }
        }

        documento.add(
                new Paragraph(" ")
        );

        documento.add(
                new Paragraph("INFORMAÇÕES SOBRE COLABORADORES")
        );

        documento.add(
                new Paragraph("----------------------------------------")
        );

        if (informacoesColaboradores == null
                || informacoesColaboradores.trim().isEmpty()) {

            documento.add(
                    new Paragraph(
                            "Nenhuma informação registrada."
                    )
            );

        } else {

            documento.add(
                    new Paragraph(
                            informacoesColaboradores
                    )
            );
        }

        documento.close();
    }
    
    private static void adicionarLogo(Document documento) {

    try {
        InputStream logoStream =
                GeradorPDF.class.getResourceAsStream(
                        "/imagens/LOGO.png"
                );

        if (logoStream == null) {
    System.out.println("Logo não encontrada em /imagens/LOGO.png");
    return;
}

        byte[] bytesLogo =
                logoStream.readAllBytes();

        Image logo =
                Image.getInstance(bytesLogo);

        logo.scaleToFit(180, 90);

        logo.setAlignment(Image.ALIGN_CENTER);

        documento.add(logo);

        documento.add(
                new Paragraph(" ")
        );

    } catch (Exception e) {
        System.out.println(
                "Não foi possível carregar a logo: "
                + e.getMessage()
        );
    }
}

    private static List<Servico> ordenarServicosPorData(
            PlanejamentoMensal mes
    ) {

        List<Servico> servicosOrdenados =
                new ArrayList<>(mes.getServicos());

        servicosOrdenados.sort(
                Comparator.comparing(Servico::getDataServico)
        );

        return servicosOrdenados;
    }

    private static void adicionarFotosDoServico(
            Document documento,
            Servico servico
    ) throws Exception {

        if (servico.getCaminhosFotos() == null
                || servico.getCaminhosFotos().isEmpty()) {

            documento.add(
                    new Paragraph("Fotos: nenhuma foto adicionada.")
            );

            return;
        }

        documento.add(
                new Paragraph("Fotos:")
        );

        for (String caminhoFoto : servico.getCaminhosFotos()) {

            File arquivoFoto =
                    new File(caminhoFoto);

            if (!arquivoFoto.exists()) {

                documento.add(
                        new Paragraph(
                                "Foto não encontrada: "
                                + caminhoFoto
                        )
                );

                continue;
            }

            Image imagem =
                    Image.getInstance(caminhoFoto);

            imagem.scaleToFit(
                    250,
                    180
            );

            documento.add(imagem);

            documento.add(
                    new Paragraph(" ")
            );
        }
    }

    private static String limparNomeArquivo(
            String nome
    ) {

        return nome.replaceAll(
                "[\\\\/:*?\"<>|]",
                "_"
        );
    }
}