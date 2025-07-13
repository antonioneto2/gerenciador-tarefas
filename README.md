# Projeto Detalhado de Software: Gerenciador de Tarefas com Padrões de Projeto

## Visão Geral do Projeto

Este projeto tem como objetivo desenvolver um sistema de gerenciamento de tarefas simples, com foco na aplicação prática dos princípios da **Programação Orientada a Objetos (POO)** e na utilização dos padrões de projeto **Proxy, Decorator e Adapter**. O sistema será projetado para ser extensível, flexível e com controle de acesso adequado às funcionalidades.

## Tecnologias Utilizadas

* Java (JDK 17)
* Apache Maven (para gerenciamento de projeto e dependências)
* Visual Studio Code (ambiente de desenvolvimento)
* Git (controle de versão)

## Padrões de Projeto Aplicados

* **Proxy**: Utilizado para implementar controle de acesso às operações do serviço de tarefas.
* **Decorator**: Utilizado para adicionar responsabilidades dinamicamente a objetos de tarefas (ex: prioridade, data de vencimento).
* **Adapter**: Utilizado para integrar o sistema de tarefas com um sistema de persistência de dados "legado" (ex: formato CSV).

## Estrutura do Projeto

A estrutura de pastas segue as convenções Maven e padrões de projeto:

```
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── pds/
│   │               └── tarefas/
│   │                   ├── core/
│   │                   │   # Classes base e interfaces:
│   │                   │   # ITarefa, TarefaConcreta,
│   │                   │   # ITarefaService, TarefaServiceImpl,
│   │                   │   # ITarefaRepository, Main
│   │                   └── patterns/
│   │                       # Implementações dos padrões de projeto:
│   │                       ├── adapter/
│   │                       ├── decorator/
│   │                       └── proxy/
├── pom.xml                   # Configuração do projeto Maven
├── README.md                 # Este arquivo (documentação do projeto)
└── .gitignore                # Arquivos e pastas ignorados pelo Git
```
## Como Rodar o Projeto

1.  **Pré-requisitos**:
    * Certifique-se de ter o JDK 17 (ou superior) e o Apache Maven instalados.
    * O VS Code com o "Extension Pack for Java" é recomendado.
2.  **Clonar o Repositório**:
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

## Membros do Grupo

* Antoio Nogueira
* Samuel de Almeida
* Leandro Carlos
* Carlos Henrique


---
*Este README.md será atualizado à medida que o projeto evolui.*