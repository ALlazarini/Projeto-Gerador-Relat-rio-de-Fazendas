# Relato Rural

Sistema desktop desenvolvido em Java para auxiliar na organização de planejamentos e relatórios de serviços realizados em propriedades rurais.

O sistema permite cadastrar serviços por fazenda, mês e ano, informar data de realização, local, observações, informações sobre colaboradores e gerar relatórios em PDF nos formatos mensal e anual.

---

## Funcionalidades

- Cadastro do nome da fazenda
- Seleção de mês e ano
- Cadastro de serviços por mês
- Data individual para cada serviço
- Local de realização do serviço
- Observações por serviço
- Informações sobre colaboradores
- Edição de serviços cadastrados
- Inserção de fotos vinculadas ao serviço
- Remoção/limpeza de fotos selecionadas
- Geração de relatório mensal em PDF
- Geração de planejamento/relatório anual em PDF
- Organização dos serviços por data nos relatórios
- Suporte a logo nos relatórios, quando disponível localmente

###Observação sobre logo

O sistema possui suporte para adicionar uma logo nos relatórios em PDF.

A logo deve estar localmente em:

src/main/resources/imagens/LOGO.png

Por motivos de privacidade e controle de uso da identidade visual do cliente, a pasta de imagens pode ser mantida fora do versionamento do Git.

---

## Serviços disponíveis

O sistema conta com uma lista pré-definida de serviços, incluindo:

- Diagnóstico mensal
- PGR
- PCMSO
- LTCAT
- PPP
- AEP
- AET
- eSocial
- Treinamento CIPATR
- Treinamento de Defensivos Agrícolas
- Operação segura de máquinas e implementos
- Máquinas autopropelidas e implementos
- Motosserra, motopoda e similares
- Espaço Confinado
- Trabalho em Altura
- NR31
- Primeiros Socorros
- Inspeção de Caldeira
- Legislação
- EPI's
- Ergonomia
- Elétrica - NR10

Alguns serviços possuem carga horária associada automaticamente.

---

## Relatórios

### Relatório mensal

O relatório mensal é gerado com base no mês e ano selecionados na interface.

Ele apresenta:

- Nome da fazenda
- Mês e ano
- Data de geração
- Serviços organizados por dia
- Tipo do serviço
- Carga horária
- Local
- Observações
- Fotos vinculadas ao serviço
- Informações sobre colaboradores

Exemplo de organização:

```text
RELATÓRIO MENSAL - JUNHO

DIA 10/06/2026
Serviço: NR31
Carga horária: 24 horas
Local: Sala de treinamento
Observações: ...

Fotos:
[imagens do serviço]

DIA 25/06/2026
Serviço: Máquinas autopropelidas e implementos
Carga horária: 24 horas
Local: Pátio
Observações: ...

### Relatório anual

O relatório anual apresenta uma visão geral do planejamento/serviços do ano inteiro.

Ele apresenta:

- Nome da fazenda
- Ano do planejamento
- Data de criação do relatório
- Serviços separados por mês
- Serviços ordenados pela data de realização
- Tipo do serviço
- Carga horária
- Local de realização
- Observações do serviço
- Informações sobre colaboradores

O relatório anual **não inclui fotos**, para manter o arquivo mais leve e servir como uma visão geral do ano.

Exemplo de organização:

```text
PLANEJAMENTO ANUAL

Fazenda: Fazenda Exemplo
Ano: 2026
Data de criação: 17/08/2026

JANEIRO
----------------------------------------
Data: 10/01/2026
Serviço: NR31
Carga horária: 24 horas
Local: Sala de treinamento
Observações: Treinamento realizado com os colaboradores.

Data: 25/01/2026
Serviço: Máquinas autopropelidas e implementos
Carga horária: 24 horas
Local: Pátio principal
Observações: Capacitação prática realizada no local.

FEVEREIRO
----------------------------------------
Nenhum serviço planejado.

INFORMAÇÕES SOBRE COLABORADORES
----------------------------------------
Colaboradores participaram dos treinamentos conforme planejamento mensal.

##Tecnologias utilizadas

Java 17
Java Swing
Maven
OpenPDF
FlatLaf

##Estrutura geral do projeto

	src/main/java
	├── com/mycompany/projetofazenda
	│   ├── MainHUD.java
	│   └── GeradorPDF.java
	│
	├── model
	│   ├── TipoServico.java
	│   ├── Servico.java
	│   ├── PlanejamentoMensal.java
	│   ├── PlanejamentoAnual.java
	│   └── Fazenda.java
	│
	└── service
	    └── PlanejamentoService.java

##Geração do aplicativo para distribuição

O projeto pode ser empacotado usando jpackage.

Exemplo:

jpackage --type app-image --name "Relato Rural" --input release --main-jar ProjetoFazenda-1.0-SNAPSHOT.jar --main-class com.mycompany.projetofazenda.MainHUD --dest dist

A pasta gerada em dist/Relato Rural deve ser enviada inteira ao cliente, não apenas o arquivo .exe.

