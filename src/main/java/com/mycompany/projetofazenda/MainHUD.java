/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projetofazenda;

import model.TipoServico;
import model.Servico;
import model.PlanejamentoMensal;
import model.PlanejamentoAnual;
import service.PlanejamentoService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//imports pra uma hud mais moderna

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import java.awt.Font;


import javax.swing.JPanel;


/**
 *
 * @author User
 */
public class MainHUD extends javax.swing.JFrame {

    /**
     * Creates new form MainHUD
     */
    
    

    private PlanejamentoService planejamentoService;
    private Servico servicoEmEdicao;
    private boolean inicializando;
    private java.util.List<String> fotosSelecionadas;
    
    
    private PlanejamentoMensal getMesSelecionado(){
    
        if (jComboBoxAno.getSelectedItem() == null || jComboBoxMes.getSelectedIndex() < 0 ) {
        return null;
    }

    int ano = Integer.parseInt(
            jComboBoxAno.getSelectedItem().toString()
    );

    int mes = jComboBoxMes.getSelectedIndex() + 1;

    return planejamentoService.obterMes(ano, mes);
    
    }
    
   
    
    private void atualizarListaServicos() {
        
        panelServicos.removeAll();

    PlanejamentoMensal mesSelecionado = getMesSelecionado();

    if (mesSelecionado == null) {
        panelServicos.revalidate();
        panelServicos.repaint();
        return;
    }

    panelServicos.setLayout(
        new javax.swing.BoxLayout(
            panelServicos,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    for (Servico servico : mesSelecionado.getServicos()) {

        JPanel card = criarCardServico(servico);

        panelServicos.add(card);

        panelServicos.add(
            javax.swing.Box.createVerticalStrut(10)
        );
    }

    panelServicos.revalidate();
    panelServicos.repaint();
}
    
    private JPanel criarCardServico(Servico servico) {

    JPanel card = new JPanel();

    DateTimeFormatter formatoData =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    card.setBackground(java.awt.Color.WHITE);

    card.setLayout(
            new javax.swing.BoxLayout(
                    card,
                    javax.swing.BoxLayout.Y_AXIS
            )
    );

    card.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(
                            new java.awt.Color(220, 225, 230)
                    ),
                    javax.swing.BorderFactory.createEmptyBorder(
                            12, 12, 12, 12
                    )
            )
    );

    String carga;

    if (servico.getTipo().getCargaHoraria() > 0) {
        carga = servico.getTipo().getCargaHoraria() + " horas";
    } else {
        carga = "Não se aplica";
    }

    javax.swing.JLabel lblData = new javax.swing.JLabel(
            "Data: " + servico.getDataServico().format(formatoData)
    );

    javax.swing.JLabel lblTipo = new javax.swing.JLabel(
            "Serviço: " + servico.getTipo().getDescricao()
    );

    javax.swing.JLabel lblLocal = new javax.swing.JLabel(
            "Local: " + servico.getLocal()
    );

    javax.swing.JLabel lblCarga = new javax.swing.JLabel(
            "Carga horária: " + carga
    );

    javax.swing.JLabel lblObservacoes = new javax.swing.JLabel(
            "Observações: " + servico.getObservacoes()
    );

    javax.swing.JLabel lblFotos = new javax.swing.JLabel(
            "Fotos: " + servico.getCaminhosFotos().size()
    );

    javax.swing.JButton btnEditar =
            new javax.swing.JButton("Editar");

    btnEditar.putClientProperty(
            "JButton.buttonType",
            "roundRect"
    );

    card.add(lblData);
    card.add(lblTipo);
    card.add(lblLocal);
    card.add(lblCarga);
    card.add(lblObservacoes);
    card.add(lblFotos);

    card.add(
            javax.swing.Box.createVerticalStrut(8)
    );

    card.add(btnEditar);

    btnEditar.addActionListener(e -> editarServico(servico));

    return card;
    
    }
    
    private void editarServico(Servico servico) {

    servicoEmEdicao = servico;

    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    jComboBox3.setSelectedItem(
            servico.getTipo().getDescricao()
    );

    txtDataServico.setText(
            servico.getDataServico().format(formatoData)
    );

    txtLocal.setText(
            servico.getLocal()
    );

    txtObservacoes.setText(
            servico.getObservacoes()
    );

    JBAdicionarServico.setText(
            "Salvar alterações"
    );
    
    fotosSelecionadas.clear();

    fotosSelecionadas.addAll( servico.getCaminhosFotos());
    lblFotosSelecionadas.setText( "Fotos selecionadas: " + fotosSelecionadas.size());

}
    
    private void atualizarInformacoesGerais() {

        if (jComboBoxAno.getSelectedItem() == null) {
            return;
        }   

        int ano = Integer.parseInt(
            jComboBoxAno.getSelectedItem().toString()
        );

        PlanejamentoAnual planejamento =
            planejamentoService.obterPlanejamento(ano);

        planejamento.setInformacoesColaboradores(
            jTextArea2.getText().trim()
        );

        
}
    
    public MainHUD() {
inicializando = true;

    initComponents();

    planejamentoService = new PlanejamentoService();
    fotosSelecionadas = new java.util.ArrayList<>();

    getContentPane().setBackground(
            new java.awt.Color(245, 247, 250)
    );

    panelServicos.setBackground(
            new java.awt.Color(245, 247, 250)
    );

    setTitle("Relato Rural - Planejamento e Relatórios");

    JBAdicionarServico.putClientProperty(
            "JButton.buttonType",
            "roundRect"
    );

    btnGerarRelatorioMensal.putClientProperty(
            "JButton.buttonType",
            "roundRect"
    );

    jButton1.putClientProperty(
            "JButton.buttonType",
            "roundRect"
    );

    txtDataServico.putClientProperty(
            "JTextField.placeholderText",
            "dd/MM/aaaa"
    );

    jComboBox3.removeAllItems();

    for (TipoServico tipo : TipoServico.values()) {
        jComboBox3.addItem(tipo.getDescricao());
    }

    int anoAtual = java.time.Year.now().getValue();

    jComboBoxAno.removeAllItems();

    for (int ano = anoAtual; ano <= anoAtual + 5; ano++) {
        jComboBoxAno.addItem(String.valueOf(ano));
    }

    String[] meses = {
        "Janeiro",
        "Fevereiro",
        "Março",
        "Abril",
        "Maio",
        "Junho",
        "Julho",
        "Agosto",
        "Setembro",
        "Outubro",
        "Novembro",
        "Dezembro"
    };

    jComboBoxMes.removeAllItems();

    for (String mes : meses) {
        jComboBoxMes.addItem(mes);
    }

    inicializando = false;

    atualizarListaServicos();
    }

    private void atualizarPlanejamentoPorAno(){
    
          if (jComboBoxAno.getSelectedItem() == null) {
        return;
    }

    int anoSelecionado = Integer.parseInt(
            jComboBoxAno.getSelectedItem().toString()
    );

    planejamentoService.obterPlanejamento(anoSelecionado);
    

    inicializando = false;
    
    atualizarListaServicos();
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxAno = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLocal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        JBAdicionarServico = new javax.swing.JToggleButton();
        jLabel10 = new javax.swing.JLabel();
        panelServicos = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtDataServico = new javax.swing.JTextField();
        btnGerarRelatorioMensal = new javax.swing.JButton();
        btnAdicionarFotos = new javax.swing.JButton();
        lblFotosSelecionadas = new javax.swing.JLabel();
        btnLimparFotos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome da fazenda:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("mês:");

        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMesActionPerformed(evt);
            }
        });

        jLabel3.setText("ano:");

        jComboBoxAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Novo serviço:");

        jLabel5.setText("Tipo do serviço: ");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Carga horaria: ");

        jLabel7.setText("horas");

        jLabel8.setText("Local de realização:");

        jLabel9.setText("Obs:");

        txtObservacoes.setColumns(20);
        txtObservacoes.setRows(5);
        jScrollPane1.setViewportView(txtObservacoes);

        JBAdicionarServico.setText("Adicionar Serviço");
        JBAdicionarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAdicionarServicoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Outras informações:");

        panelServicos.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout panelServicosLayout = new javax.swing.GroupLayout(panelServicos);
        panelServicos.setLayout(panelServicosLayout);
        panelServicosLayout.setHorizontalGroup(
            panelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );
        panelServicosLayout.setVerticalGroup(
            panelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel11.setText("Serviços do mês:");

        jLabel12.setText("Sobre colaboradores:");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setText("Gerar relatorio (anual)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Data do Serviço");

        txtDataServico.setText("dd/MM/yyyy");
        txtDataServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataServicoActionPerformed(evt);
            }
        });

        btnGerarRelatorioMensal.setText("Gerar relatorio (mensal)");
        btnGerarRelatorioMensal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarRelatorioMensalActionPerformed(evt);
            }
        });

        btnAdicionarFotos.setText("Adicionar fotos");
        btnAdicionarFotos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarFotosActionPerformed(evt);
            }
        });

        lblFotosSelecionadas.setText("Fotos selecionadas: ");

        btnLimparFotos.setText("Remover imagens (todas)");
        btnLimparFotos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparFotosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addGap(388, 388, 388))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(146, 146, 146)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDataServico))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdicionarFotos)
                                .addGap(18, 18, 18)
                                .addComponent(lblFotosSelecionadas, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimparFotos)
                                .addGap(17, 17, 17))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnGerarRelatorioMensal)
                                        .addGap(63, 63, 63)
                                        .addComponent(jButton1)
                                        .addGap(72, 72, 72))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JBAdicionarServico)
                                        .addGap(177, 177, 177)))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addComponent(panelServicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14)
                            .addComponent(txtDataServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdicionarFotos)
                            .addComponent(lblFotosSelecionadas)
                            .addComponent(btnLimparFotos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JBAdicionarServico)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(btnGerarRelatorioMensal)))
                    .addComponent(panelServicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void JBAdicionarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAdicionarServicoActionPerformed

        String descricaoSelecionada =
            (String) jComboBox3.getSelectedItem();

    if (descricaoSelecionada == null) {
        return;
    }

    TipoServico tipoSelecionado = null;

    for (TipoServico tipo : TipoServico.values()) {
        if (tipo.getDescricao().equals(descricaoSelecionada)) {
            tipoSelecionado = tipo;
            break;
        }
    }

    if (tipoSelecionado == null) {
        return;
    }

    String local = txtLocal.getText().trim();

    String observacoes = txtObservacoes.getText().trim();

    if (local.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Informe o local do serviço."
        );
        return;
    }
    
    String dataTexto = txtDataServico.getText().trim();

    if (dataTexto.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(
            this,
            "Informe a data do serviço."
    );
        return;
    }

    DateTimeFormatter formatoData =
        DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate dataServico;

    try {
    dataServico = LocalDate.parse(dataTexto, formatoData);
    } catch (DateTimeParseException e) {
    javax.swing.JOptionPane.showMessageDialog(
            this,
            "Data inválida. Use o formato dd/MM/yyyy."
    );
        return;
    }
    
    if (servicoEmEdicao != null) {

    servicoEmEdicao.setTipo(tipoSelecionado);
    servicoEmEdicao.setLocal(local);
    servicoEmEdicao.setObservacoes(observacoes);
    servicoEmEdicao.setDataServico(dataServico);
    servicoEmEdicao.setCaminhosFotos( new java.util.ArrayList<>(fotosSelecionadas));

    servicoEmEdicao = null;

    JBAdicionarServico.setText(
            "Adicionar Serviço"
    );

    atualizarListaServicos();

    txtLocal.setText("");
    txtObservacoes.setText("");

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "Serviço atualizado com sucesso!"
    );

    return;
}

    PlanejamentoMensal mesSelecionado = getMesSelecionado();
    
    
    Servico servico = new Servico( tipoSelecionado, dataServico, local, observacoes);
    
    servico.setCaminhosFotos( new java.util.ArrayList<>(fotosSelecionadas));

    System.out.println("Serviço criado:");
    System.out.println("Tipo: " + servico.getTipo());
    System.out.println("Local: " + servico.getLocal());
    System.out.println("Observações: " + servico.getObservacoes());
    
    int ano = Integer.parseInt( jComboBoxAno.getSelectedItem().toString());

    int mes = jComboBoxMes.getSelectedIndex() + 1;

    planejamentoService.adicionarServico( ano, mes, servico);
    atualizarListaServicos();
    
    javax.swing.JOptionPane.showMessageDialog(this, "Serviço adicionado com sucesso!");
    
    txtDataServico.setText("");
    txtLocal.setText("");
    txtObservacoes.setText("");
    fotosSelecionadas.clear();
    lblFotosSelecionadas.setText( "Fotos selecionadas: 0");

    }//GEN-LAST:event_JBAdicionarServicoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

         if (jComboBoxAno.getSelectedItem() == null) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Selecione um ano."
        );
        return;
    }

    atualizarInformacoesGerais();

    int ano = Integer.parseInt(
            jComboBoxAno.getSelectedItem().toString()
    );

    String nomeFazenda =
            jTextField1.getText().trim();

    if (nomeFazenda.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Informe o nome da fazenda."
        );
        return;
    }

    PlanejamentoAnual planejamento =
            planejamentoService.obterPlanejamento(ano);

    try {

        GeradorPDF.gerar(
                nomeFazenda,
                planejamento
        );

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Planejamento anual gerado com sucesso!"
        );

    } catch (Exception e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Erro ao gerar o PDF:\n"
                + e.getMessage(),
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        
        String descricaoSelecionada =
            (String) jComboBox3.getSelectedItem();

    if (descricaoSelecionada == null) {
        return;
    }

    for (TipoServico tipo : TipoServico.values()) {
        if (tipo.getDescricao().equals(descricaoSelecionada)) {

            if (tipo.getCargaHoraria() > 0) {
                jLabel7.setText(
                        "Carga horária: " + tipo.getCargaHoraria() + " horas"
                );
            } else {
                jLabel7.setText("Carga horária: não se aplica");
            }

            break;
        }
    }
        
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBoxAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnoActionPerformed

       if (inicializando) {
        return;
    }

    atualizarPlanejamentoPorAno();

    }//GEN-LAST:event_jComboBoxAnoActionPerformed

    private void jComboBoxMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesActionPerformed
       
        if (!inicializando) {
        return;
    }

    atualizarListaServicos();
        
    }//GEN-LAST:event_jComboBoxMesActionPerformed

    private void txtDataServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataServicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataServicoActionPerformed

    private void btnGerarRelatorioMensalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarRelatorioMensalActionPerformed
        
        if (jComboBoxAno.getSelectedItem() == null) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Selecione um ano."
            );
        return;
        }

        if (jComboBoxMes.getSelectedIndex() < 0) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Selecione um mês."
            );
        return;
        }

    String nomeFazenda = jTextField1.getText().trim();

        if (nomeFazenda.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Informe o nome da fazenda."
            );
        return;
    }

    int ano = Integer.parseInt( jComboBoxAno.getSelectedItem().toString());

    int indiceMes = jComboBoxMes.getSelectedIndex();

    String nomeMes = jComboBoxMes.getSelectedItem().toString();

    PlanejamentoMensal mes =planejamentoService.obterMes( ano, indiceMes + 1);

    String informacoesColaboradores = jTextArea2.getText().trim();

        try {

            GeradorPDF.gerarMensal(
                nomeFazenda,
                mes,
                ano,
                nomeMes,
                informacoesColaboradores
        );

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Relatório mensal gerado com sucesso!"
        );

    } catch (Exception e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Erro ao gerar o relatório mensal:\n"
                + e.getMessage(),
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
        
    }//GEN-LAST:event_btnGerarRelatorioMensalActionPerformed

    private void btnAdicionarFotosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarFotosActionPerformed
        
        javax.swing.JFileChooser fileChooser =
        new javax.swing.JFileChooser();

fileChooser.setDialogTitle("Selecionar fotos do serviço");

fileChooser.setMultiSelectionEnabled(true);

fileChooser.setFileFilter(
        new javax.swing.filechooser.FileNameExtensionFilter(
                "Imagens",
                "jpg",
                "jpeg",
                "png"
        )
);

int resultado = fileChooser.showOpenDialog(this);

if (resultado == javax.swing.JFileChooser.APPROVE_OPTION) {

    java.io.File[] arquivos =
            fileChooser.getSelectedFiles();

    for (java.io.File arquivo : arquivos) {
        fotosSelecionadas.add(
                arquivo.getAbsolutePath()
        );
    }

    lblFotosSelecionadas.setText(
            "Fotos selecionadas: "
            + fotosSelecionadas.size()
    );
}
        
    }//GEN-LAST:event_btnAdicionarFotosActionPerformed

    private void btnLimparFotosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparFotosActionPerformed
        
        fotosSelecionadas.clear();

    lblFotosSelecionadas.setText(
            "Fotos selecionadas: 0"
    );

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "Fotos removidas da seleção."
    );
        
    }//GEN-LAST:event_btnLimparFotosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
        FlatLightLaf.setup();

        UIManager.put(
                "defaultFont",
                new Font("Segoe UI", Font.PLAIN, 14)
        );

    } catch (Exception e) {
        System.out.println("Erro ao carregar tema.");
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new MainHUD().setVisible(true);
        }
    });   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton JBAdicionarServico;
    private javax.swing.JButton btnAdicionarFotos;
    private javax.swing.JButton btnGerarRelatorioMensal;
    private javax.swing.JButton btnLimparFotos;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBoxAno;
    private javax.swing.JComboBox<String> jComboBoxMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblFotosSelecionadas;
    private javax.swing.JPanel panelServicos;
    private javax.swing.JTextField txtDataServico;
    private javax.swing.JTextField txtLocal;
    private javax.swing.JTextArea txtObservacoes;
    // End of variables declaration//GEN-END:variables
}
