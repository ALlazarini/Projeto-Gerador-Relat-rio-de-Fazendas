/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author User
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.TipoServico;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class TipoServicoRepositoryJson {
    
     private static final String CAMINHO_ARQUIVO =
            "dados/servicos.json";

    private final Gson gson;

    public TipoServicoRepositoryJson() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        inicializarArquivoSeNaoExistir();
    }

    public List<TipoServico> listarTodos() {

        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {

            Type tipoLista =
                    new TypeToken<List<TipoServico>>() {
                    }.getType();

            List<TipoServico> servicos =
                    gson.fromJson(reader, tipoLista);

            if (servicos == null) {
                return new ArrayList<>();
            }

            return servicos;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<TipoServico> listarAtivos() {

        List<TipoServico> ativos =
                new ArrayList<>();

        for (TipoServico servico : listarTodos()) {
            if (servico.isAtivo()) {
                ativos.add(servico);
            }
        }

        return ativos;
    }

    public void salvarTodos(List<TipoServico> servicos) {

        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {

            gson.toJson(servicos, writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void adicionarServico(
        String descricao,
        int cargaHoraria
) {

    List<TipoServico> servicos =
            listarTodos();

    int novoId =
            gerarProximoId(servicos);

    TipoServico novoServico =
            new TipoServico(
                    novoId,
                    descricao,
                    cargaHoraria,
                    true
            );

    servicos.add(novoServico);

    salvarTodos(servicos);
}

public void atualizarServico(
        TipoServico servicoAtualizado
) {

    List<TipoServico> servicos =
            listarTodos();

    for (int i = 0; i < servicos.size(); i++) {

        TipoServico servico =
                servicos.get(i);

        if (servico.getId()
                == servicoAtualizado.getId()) {

            servicos.set(
                    i,
                    servicoAtualizado
            );

            break;
        }
    }

    salvarTodos(servicos);
}

public void alterarStatusServico(
        int id,
        boolean ativo
) {

    List<TipoServico> servicos =
            listarTodos();

    for (TipoServico servico : servicos) {

        if (servico.getId() == id) {
            servico.setAtivo(ativo);
            break;
        }
    }

    salvarTodos(servicos);
}

private int gerarProximoId(
        List<TipoServico> servicos
) {

    int maiorId = 0;

    for (TipoServico servico : servicos) {

        if (servico.getId() > maiorId) {
            maiorId = servico.getId();
        }
    }

    return maiorId + 1;
}

    private void inicializarArquivoSeNaoExistir() {

        File arquivo =
                new File(CAMINHO_ARQUIVO);

        File pastaDados =
                arquivo.getParentFile();

        if (pastaDados != null && !pastaDados.exists()) {
            pastaDados.mkdirs();
        }

        if (!arquivo.exists()) {
            salvarTodos(criarServicosPadrao());
        }
    }

    private List<TipoServico> criarServicosPadrao() {

        List<TipoServico> servicos =
                new ArrayList<>();

        servicos.add(new TipoServico(1, "Diagnóstico mensal", 0, true));
        servicos.add(new TipoServico(2, "PGR", 0, true));
        servicos.add(new TipoServico(3, "PCMSO", 0, true));
        servicos.add(new TipoServico(4, "LTCAT", 0, true));
        servicos.add(new TipoServico(5, "PPP", 0, true));
        servicos.add(new TipoServico(6, "AEP", 0, true));
        servicos.add(new TipoServico(7, "AET", 0, true));
        servicos.add(new TipoServico(8, "eSocial", 0, true));

        servicos.add(new TipoServico(9, "Treinamento CIPATR", 20, true));
        servicos.add(new TipoServico(10, "Treinamento de Defensivos Agrícolas", 20, true));
        servicos.add(new TipoServico(11, "Operação segura de máquinas e implementos", 20, true));
        servicos.add(new TipoServico(12, "Máquinas autopropelidas e implementos", 24, true));
        servicos.add(new TipoServico(13, "Motosserra, motopoda e similares", 8, true));
        servicos.add(new TipoServico(14, "Espaço Confinado", 8, true));
        servicos.add(new TipoServico(15, "Trabalho em Altura", 8, true));

        servicos.add(new TipoServico(16, "NR31", 24, true));
        servicos.add(new TipoServico(17, "Primeiros Socorros", 4, true));
        servicos.add(new TipoServico(18, "Inspeção de Caldeira", 0, true));
        servicos.add(new TipoServico(19, "Legislação", 4, true));
        servicos.add(new TipoServico(20, "EPI's", 6, true));
        servicos.add(new TipoServico(21, "Ergonomia", 4, true));
        servicos.add(new TipoServico(22, "Elétrica - NR10", 10, true));

        return servicos;
    }
    
}
