# Projeto Detalhado de Software: Gerenciador de Tarefas com Padrões de Projeto

## Visão Geral do Projeto 🎯

Este projeto consiste em um sistema básico de gerenciamento de tarefas desenvolvido em **Java**, com foco na aplicação prática dos princípios da **Programação Orientada a Objetos (POO)** e na implementação estratégica dos padrões de projeto **Proxy, Decorator e Adapter**.

O sistema agora conta com um **menu interativo** que permite ao usuário:

* Adicionar novas tarefas (com a opção de definir prioridade, demonstrando o **Decorator**).

* Listar tarefas (carregadas de um arquivo CSV, demonstrando o **Adapter**).

* Marcar tarefas como concluídas.

* Remover tarefas (com controle de acesso via **Proxy**).

* Trocar o perfil do usuário entre "ADMINISTRADOR" e "COMUM" para demonstrar o comportamento do Proxy.

* Os dados das tarefas são persistidos em um arquivo CSV, garantindo que não se percam ao reiniciar o sistema.

## 2. Conceitos Fundamentais 💡

Nosso projeto é fundamentado em pilares essenciais do desenvolvimento de software, garantindo um código robusto, manutenível e extensível.

### 2.1 Programação Orientada a Objetos (POO)

A POO é o paradigma de programação central que utilizamos. Ela nos permite modelar o mundo real através de **objetos**, que são instâncias de **classes**, promovendo modularidade e reuso. Os quatro pilares da POO que aplicamos são:

* **Encapsulamento**: Oculta os detalhes internos de uma classe e expõe apenas uma interface pública para interagir com ela. Isso protege a integridade dos dados e simplifica o uso dos objetos.

* **Herança**: Permite que uma nova classe (subclasse) herde atributos e métodos de uma classe existente (superclasse), facilitando o reuso de código e a criação de hierarquias de tipos.

* **Polimorfismo**: Significa "muitas formas". Permite que objetos de diferentes classes sejam tratados como objetos de um tipo comum (geralmente uma interface ou superclasse), o que aumenta a flexibilidade e a extensibilidade do código.

* **Abstração**: Foca no que um objeto faz, em vez de como ele faz. Esconde a complexidade de implementação, apresentando apenas as funcionalidades essenciais através de interfaces e classes abstratas.

### 2.2 Padrões de Projeto (Design Patterns)

Padrões de Projeto são soluções elegantes e comprovadas para problemas recorrentes de design de software. Eles nos fornecem um vocabulário comum e uma estrutura para resolver desafios de arquitetura de forma eficaz. Estamos aplicando três padrões estruturais específicos:

* **Padrão Proxy (Padrão Estrutural)**:

  * **Conceito**: Fornece um substituto ou "procurador" para outro objeto. O proxy controla o acesso ao objeto real, permitindo adicionar lógica antes ou depois de uma operação ser executada no objeto real.

  * **Uso no Projeto**: Implementado em `TarefaServiceProxy`. Ele controla o acesso a operações sensíveis (como `removerTarefa`) no serviço de gerenciamento de tarefas (`TarefaServiceImpl`). O sistema permite trocar o perfil do usuário (ADMIN/COMUM) para demonstrar como o Proxy permite ou nega a remoção de tarefas com base na permissão.

* **Padrão Decorator (Padrão Estrutural)**:

  * **Conceito**: Permite adicionar novas funcionalidades (responsabilidades) a um objeto dinamicamente, sem modificar sua estrutura original através de herança. Ele "embrulha" o objeto original com um ou mais objetos decoradores.

  * **Uso no Projeto**: Implementado com `TarefaDecorator` e `TarefaComPrioridadeDecorator`. Ao adicionar uma nova tarefa no menu, o usuário tem a opção de decorá-la com uma prioridade (ALTA, MEDIA, BAIXA). Essa prioridade é exibida com um ícone específico quando a tarefa é listada.

* **Padrão Adapter (Padrão Estrutural)**:

  * **Conceito**: Permite que interfaces incompatíveis colaborem. Ele "adapta" a interface de uma classe existente (o "adaptee") para outra interface que o cliente espera (o "target").

  * **Uso no Projeto**: Implementado com `LegadoDadosTarefasCSV` (o sistema legado simulado) e `LegadoDadosTarefasCSVAdapter`. O Adapter permite que nosso sistema utilize a `ITarefaRepository` para salvar e carregar tarefas de um arquivo CSV real (`tarefas_legado.csv`), traduzindo os objetos `ITarefa` para o formato CSV e vice-versa, incluindo a prioridade.

## 3. Estrutura do Projeto e Organização do Código 📁

Nosso projeto segue a estrutura padrão de um projeto **Maven**, que é amplamente utilizada em projetos Java, combinada com uma organização lógica de pacotes para clareza e manutenção.

```
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── pds/                  # Pacote base do grupo/disciplina
│                   └── tarefas/          # Pacote principal do projeto
│                       ├── core/         # <-- Classes e interfaces fundamentais
│                       └── patterns/     # <-- Implementações dos Padrões de Projeto
│                           ├── adapter/
│                           ├── decorator/
│                           └── proxy/
├── pom.xml                               # Configuração do projeto Maven (dependências, build)
├── README.md                             # Este arquivo (documentação do projeto)
└── .gitignore                            # Arquivos e pastas ignorados pelo Git (inclui *.csv e src/test/)
```
### Detalhamento dos Pacotes e Classes/Interfaces Atuais:

Aqui estão as classes e interfaces atualmente implementadas no projeto, com seus respectivos atributos e métodos.

#### 3.1 Pacote `com.pds.tarefas.core`

Contém as classes e interfaces fundamentais do sistema de gerenciamento de tarefas, que formam a lógica central e são independentes dos padrões de projeto.

* **`ITarefa.java` (Interface)**

  * **Propósito**: Define o contrato básico para qualquer tarefa.

  * **Métodos**: `getDescricao()`, `setDescricao(String descricao)`, `isConcluida()`, `marcarComoConcluida()`, `exibirDetalhes()`.

* **`TarefaConcreta.java` (Classe Concreta)**

  * **Propósito**: É a implementação **básica e concreta** de uma `ITarefa`.

  * **Atributos**: `private String descricao`, `private boolean concluida`.

  * **Métodos**: Implementa todos os métodos de `ITarefa`.

* **`ITarefaService.java` (Interface)**

  * **Propósito**: Define as operações que o serviço de gerenciamento de tarefas deve oferecer.

  * **Métodos**: `adicionarTarefa(ITarefa tarefa)`, `removerTarefa(String descricao)`, `buscarTarefa(String descricao)`, `listarTodasTarefas()`, `marcarTarefaConcluida(String descricao)`.

* **`TarefaServiceImpl.java` (Implementação do Serviço)**

  * **Propósito**: É a implementação **básica e concreta** do `ITarefaService`, gerenciando as tarefas em uma lista na memória.

  * **Atributos**: `private List<ITarefa> tarefas`.

  * **Métodos**: Implementa todos os métodos de `ITarefaService`.

* **`ITarefaRepository.java` (Interface para Persistência)**

  * **Propósito**: Define uma interface genérica para a persistência de dados de tarefas. Será a interface "alvo" que o padrão Adapter implementará.

  * **Métodos**: `salvar(ITarefa tarefa)`, `carregar(String descricao)`, `carregarTodas()`, `remover(String descricao)`.

* **`Main.java` (Classe Principal)**

  * **Propósito**: Ponto de entrada da aplicação. Contém o **menu interativo** e a lógica para demonstrar a integração de todos os padrões de projeto.

  * **Atributos**: `private static ITarefaService currentTarefaService`, `private static ITarefaRepository currentTarefaRepository`, `private static Scanner scanner`, `private static boolean isAdminUser`.

  * **Métodos**: `main(String[] args)`, `exibirMenuPrincipal()`, `lerOpcao()`, `adicionarTarefa()`, `listarTarefas()`, `marcarTarefaConcluida()`, `removerTarefa()`, `trocarUsuario()`.

#### 3.2 Pacote `com.pds.tarefas.patterns`

Esta pasta contém as implementações dos padrões de projeto que estendem e modificam o comportamento das classes `core`.

* **`com.pds.tarefas.patterns.proxy`**

  * **`TarefaServiceProxy.java`**

    * **Propósito**: Implementa o padrão Proxy para `ITarefaService`. Controla o acesso a operações sensíveis (como `removerTarefa`) com base na permissão do usuário.

    * **Atributos**: `private ITarefaService realService`, `private boolean isAdmin`.

    * **Métodos**: Implementa `ITarefaService`, com lógica de verificação de permissão em `removerTarefa()`.

* **`com.pds.tarefas.patterns.decorator`**

  * **`TarefaDecorator.java` (Classe Abstrata)**

    * **Propósito**: Base abstrata para todos os decoradores de `ITarefa`. Delega a maioria dos métodos para a tarefa decorada.

    * **Atributos**: `protected ITarefa tarefaDecorada`.

    * **Métodos**: Implementa `ITarefa`, delegando chamadas para `tarefaDecorada`.

  * **`TarefaComPrioridadeDecorator.java`**

    * **Propósito**: Decorador concreto que adiciona a funcionalidade de prioridade a uma `ITarefa`.

    * **Atributos**: `private String prioridade`.

    * **Métodos**: Sobrescreve `exibirDetalhes()` para incluir a prioridade. Possui `getPrioridade()` e `setPrioridade()`.

* **`com.pds.tarefas.patterns.adapter`**

  * **`LegadoDadosTarefasCSV.java` (O Adaptee - Sistema Legado)**

    * **Propósito**: Simula um sistema de persistência de dados "legado" que armazena e lê tarefas em um arquivo CSV real (`tarefas_legado.csv`).

    * **Atributos**: `private Map<String, String> dadosCSV`, `private static final String NOME_ARQUIVO`.

    * **Métodos**: `gravarTarefa(String descricao, boolean concluida, String prioridade)`, `lerTarefa(String descricao)`, `lerTodasTarefas()`, `removerTarefa(String descricao)`. Inclui métodos internos para `carregarDadosDoArquivo()` e `salvarDadosNoArquivo()`.

  * **`LegadoDadosTarefasCSVAdapter.java` (O Adapter)**

    * **Propósito**: Implementa `ITarefaRepository` e adapta a interface do sistema legado `LegadoDadosTarefasCSV` para o nosso sistema. Converte `ITarefa` para formato CSV e vice-versa, incluindo a prioridade.

    * **Atributos**: `private LegadoDadosTarefasCSV legado`.

    * **Métodos**: Implementa `ITarefaRepository`, traduzindo as chamadas para os métodos de `LegadoDadosTarefasCSV`.

## 4. Como Rodar o Projeto ⚙️

Para que todos possam compilar, testar e executar o projeto:

1. **Pré-requisitos**:

   * Certifique-se de ter o **JDK 17** (ou superior) e o **Apache Maven** instalados.

   * O **Visual Studio Code** com o "Extension Pack for Java" é altamente recomendado para uma experiência de desenvolvimento integrada.

2. **Clonar o Repositório**:
   Se você ainda não tem o projeto localmente, use o comando:

    ```bash
    git clone https://github.com/antonioneto2/gerenciador-tarefas.git
    cd gerenciador-tarefas
    ```
3.  **Compilar e Executar**:
    Abra o projeto no VS Code. Você pode rodar a classe `Main.java` diretamente do VS Code (clique no botão "Run" acima do método `main`) ou via terminal:
    ```bash
    mvn clean install # Compila o projeto e roda os testes
    mvn exec:java -Dexec.mainClass="com.pds.tarefas.core.Main" # Executa a classe Main
    ```

O arquivo de dados `tarefas_legado.csv` será criado na raiz do projeto ao adicionar tarefas.

## Membros do Grupo

* Antoio Nogueira
* Samuel de Almeida
* Leandro Carlos
* Carlos Henrique


---
*Agradecemos a colaboração de todos no desenvolvimento deste projeto. Em caso de dúvidas ou sugestões, a comunicação no grupo é fundamental para o sucesso!*