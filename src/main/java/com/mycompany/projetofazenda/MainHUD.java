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
    
    
    private PlanejamentoMensal getMesSelecionado(){
    
        if (jComboBoxAno.getSelectedItem() == null) {
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

        card.setLayout(
                new javax.swing.BoxLayout(
                    card,
                    javax.swing.BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createEtchedBorder(),
                        javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)
                    )
        );

        String carga;

        if (servico.getTipo().getCargaHoraria() > 0) {
            carga = servico.getTipo().getCargaHoraria() + " horas";
        } else {
            carga = "Não se aplica";
        }

        javax.swing.JLabel lblTipo = new javax.swing.JLabel("Serviço: " + servico.getTipo().getDescricao());

        javax.swing.JLabel lblLocal = new javax.swing.JLabel("Local: " + servico.getLocal());

        javax.swing.JLabel lblCarga = new javax.swing.JLabel("Carga horária: " + carga);

        javax.swing.JLabel lblObservacoes = new javax.swing.JLabel("Observações: " + servico.getObservacoes());

        javax.swing.JButton btnEditar = new javax.swing.JButton("Editar");

        card.add(lblTipo);
        card.add(lblLocal);
        card.add(lblCarga);
        card.add(lblObservacoes);

        card.add(javax.swing.Box.createVerticalStrut(8));

        card.add(btnEditar);

        btnEditar.addActionListener(e -> editarServico(servico));  
        
        return card;
    }
    
    private void editarServico(Servico servico) {

        servicoEmEdicao = servico;

        jComboBox3.setSelectedItem(servico.getTipo().getDescricao());

        txtLocal.setText(servico.getLocal());

        txtObservacoes.setText(servico.getObservacoes());

        JBAdicionarServico.setText("Salvar alterações");
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

        planejamento.setInformacoesGerais(
            jTextArea3.getText().trim()
        );
}
    
    public MainHUD() {
        initComponents();
        
        
        planejamentoService = new PlanejamentoService();
        inicializando = true;
        
        
        
    //adiciona os serviços da classe TipoServico    
        
        jComboBox3.removeAllItems();

    for (TipoServico tipo : TipoServico.values()) {
        jComboBox3.addItem(tipo.getDescricao());
    }
    
    //coloca na comboBox de anos os proximos 5 anos
    
        int anoAtual = java.time.Year.now().getValue();

        jComboBoxAno.removeAllItems();

        for (int ano = anoAtual; ano <= anoAtual + 5; ano++) {
            jComboBoxAno.addItem(String.valueOf(ano));
        }
    
    //coloca os meses na CB de meses
    
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
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

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
        jLabel10.setText("Informações gerais:");

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

        jLabel13.setText("Gerais:");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jButton1.setText("Gerar relatorio (anual)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(JBAdicionarServico))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(388, 388, 388))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
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
                        .addComponent(jLabel5)
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
                        .addGap(18, 18, 18)
                        .addComponent(JBAdicionarServico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelServicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
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
    
    if (servicoEmEdicao != null) {

    servicoEmEdicao.setTipo(tipoSelecionado);
    servicoEmEdicao.setLocal(local);
    servicoEmEdicao.setObservacoes(observacoes);

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
    
    Servico servico = new Servico( tipoSelecionado, local, observacoes);

    System.out.println("Serviço criado:");
    System.out.println("Tipo: " + servico.getTipo());
    System.out.println("Local: " + servico.getLocal());
    System.out.println("Observações: " + servico.getObservacoes());
    
    int ano = Integer.parseInt( jComboBoxAno.getSelectedItem().toString());

    int mes = jComboBoxMes.getSelectedIndex() + 1;

    planejamentoService.adicionarServico( ano, mes, servico);
    atualizarListaServicos();
    
    javax.swing.JOptionPane.showMessageDialog(this, "Serviço adicionado com sucesso!");
    
    txtLocal.setText("");
    txtObservacoes.setText("");

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

      if (!inicializando) {
        atualizarPlanejamentoPorAno();
    }

    }//GEN-LAST:event_jComboBoxAnoActionPerformed

    private void jComboBoxMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesActionPerformed
       
        if (!inicializando) {
        atualizarListaServicos();
    }
        
        
    }//GEN-LAST:event_jComboBoxMesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainHUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainHUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainHUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainHUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainHUD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton JBAdicionarServico;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBoxAno;
    private javax.swing.JComboBox<String> jComboBoxMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelServicos;
    private javax.swing.JTextField txtLocal;
    private javax.swing.JTextArea txtObservacoes;
    // End of variables declaration//GEN-END:variables
}
