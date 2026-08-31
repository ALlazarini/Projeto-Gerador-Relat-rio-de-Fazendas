<h1>APP Gerador de Relatórios Rurais</h1>

<p>
  Sistema desktop desenvolvido em Java para criação de planejamentos e relatórios de serviços em fazendas.
</p>

<p>
  O projeto permite cadastrar serviços por mês, organizar um planejamento anual e gerar relatórios em PDF,
  tanto mensais quanto anuais.
</p>

<hr>

<h2>Funcionalidades</h2>

<ul>
  <li>Cadastro do nome da fazenda</li>
  <li>Seleção de mês e ano</li>
  <li>Cadastro de serviços por mês</li>
  <li>Registro da data do serviço</li>
  <li>Registro do local de realização</li>
  <li>Registro de observações</li>
  <li>Registro de informações sobre colaboradores</li>
  <li>Adição de fotos aos serviços</li>
  <li>Geração de relatório mensal em PDF</li>
  <li>Geração de planejamento anual em PDF</li>
  <li>Relatório anual em formato de tabela, com 3 meses por linha</li>
  <li>Relatório mensal com listagem dos serviços e imagens anexadas</li>
  <li>Gerenciamento de tipos de serviço</li>
  <li>Carga horária automática de acordo com o tipo de serviço</li>
</ul>

<hr>

<h2>Tecnologias utilizadas</h2>

<ul>
  <li>Java 17</li>
  <li>Java Swing</li>
  <li>Maven</li>
  <li>OpenPDF</li>
  <li>FlatLaf</li>
  <li>Gson</li>
</ul>

<hr>

<h2>Estrutura principal do projeto</h2>

<pre><code>src/main/java/
├── com/mycompany/projetofazenda/
│   ├── MainHUD.java
│   ├── GeradorPDF.java
│   ├── GerenciarServicosDialog.java
│   └── ProjetoFazenda.java
├── model/
│   ├── Fazenda.java
│   ├── PlanejamentoAnual.java
│   ├── PlanejamentoMensal.java
│   ├── Servico.java
│   └── TipoServico.java
└── service/
    ├── PlanejamentoService.java
    └── TipoServicoRepositoryJson.java</code></pre>

<hr>

<h2>Relatórios gerados</h2>

<h3>Relatório anual</h3>

<p>
  O relatório anual apresenta o planejamento dos serviços separados por mês.
</p>

<p>
  O arquivo é gerado em PDF no formato paisagem, com os meses organizados em tabelas de 3 colunas.
</p>

<p>Exemplo de organização:</p>

<pre><code>Janeiro  | Fevereiro | Março
Abril    | Maio      | Junho
Julho    | Agosto    | Setembro
Outubro  | Novembro  | Dezembro</code></pre>

<p>
  Cada mês exibe os serviços previstos e seus respectivos locais.
</p>

<h3>Relatório mensal</h3>

<p>
  O relatório mensal apresenta os serviços de um mês específico, contendo:
</p>

<ul>
  <li>Data do serviço</li>
  <li>Tipo do serviço</li>
  <li>Carga horária</li>
  <li>Local</li>
  <li>Observações</li>
  <li>Fotos adicionadas</li>
  <li>Informações sobre colaboradores</li>
</ul>

<hr>

<h2>Como executar pelo NetBeans</h2>

<ol>
  <li>Abrir o projeto no NetBeans</li>
  <li>Garantir que o Java 17 esteja configurado</li>
  <li>Executar a classe <code>MainHUD.java</code></li>
</ol>

<hr>

<h2>Como gerar o arquivo .jar</h2>

<p>Na pasta raiz do projeto, executar:</p>

<pre><code>mvn clean package</code></pre>

<p>O arquivo <code>.jar</code> será gerado dentro da pasta:</p>

<pre><code>target/</code></pre>

<hr>

<h2>Como gerar o executável para Windows</h2>

<p>Primeiro, copiar as dependências do projeto:</p>

<pre><code>mvn dependency:copy-dependencies -DoutputDirectory=target/dependency</code></pre>

<p>Criar a pasta de empacotamento:</p>

<pre><code>New-Item -ItemType Directory -Force -Path .\target\app</code></pre>

<p>Copiar o <code>.jar</code> principal:</p>

<pre><code>Copy-Item .\target\ProjetoFazenda-1.0-SNAPSHOT.jar .\target\app\</code></pre>

<p>Copiar as dependências:</p>

<pre><code>Copy-Item .\target\dependency\*.jar .\target\app\</code></pre>

<p>Gerar o aplicativo desktop:</p>

<pre><code>jpackage --type app-image --input .\target\app --name PlanejamentoFGA --main-jar ProjetoFazenda-1.0-SNAPSHOT.jar --main-class com.mycompany.projetofazenda.MainHUD --dest .\release</code></pre>

<p>O executável será gerado em:</p>

<pre><code>release/PlanejamentoFGA/PlanejamentoFGA.exe</code></pre>

<hr>

<h2>Como compactar para envio</h2>

<p>Após gerar o executável, compactar a pasta inteira:</p>

<pre><code>Compress-Archive -Path .\release\PlanejamentoFGA -DestinationPath .\PlanejamentoFGA.zip -Force</code></pre>

<p>
  É importante enviar a pasta completa compactada. O arquivo <code>.exe</code> não deve ser enviado sozinho,
  pois depende das pastas internas geradas pelo <code>jpackage</code>.
</p>

<hr>

<h2>Compatibilidade</h2>

<p>
  O sistema é um aplicativo desktop Java/Swing, empacotado para Windows.
</p>

<h2>Observações</h2>

<ul>
  <li>Os relatórios em PDF são gerados na máquina local do usuário.</li>
  <li>As fotos adicionadas aos serviços são usadas na geração do relatório mensal.</li>
  <li>A logo do sistema é carregada a partir da pasta de recursos do projeto.</li>
  <li>O executável deve ser utilizado junto com as pastas internas geradas pelo empacotamento.</li>
</ul>

<hr>

<h2>Autor</h2>

<p>
  Desenvolvido por <strong>André Lemos Lazarini</strong>.
</p>
