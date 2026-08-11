# Projeto Gerador de Relatórios de Fazendas

Projeto desenvolvido em Java com o objetivo de servir como um simples gerador de relatórios de visitas a fazendas.

A aplicação possui uma interface gráfica desenvolvida com Java Swing, permitindo o preenchimento das informações coletadas durante uma visita e a geração de um relatório em formato PDF.

## Funcionalidades

- Cadastro do nome da fazenda visitada;
- Registro do tipo e nome do local de visita;
- Registro de observações relacionadas ao local;
- Registro de informações sobre colaboradores;
- Registro de função e atividade dos colaboradores;
- Registro de informações sobre utilização de EPI;
- Registro de treinamentos realizados;
- Campo para observações gerais;
- Geração automática do relatório em PDF.

## Tecnologias utilizadas

- Java 17
- Java Swing
- Maven
- OpenPDF 2.0.5
- NetBeans

## Estrutura principal

```text
src/main/java/com/mycompany/projetofazenda/
├── MainHUD.java
├── MainHUD.form
├── ProjetoFazenda.java
├── Relatorio.java
└── GeradorPDF.java
