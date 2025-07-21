package com.pds.tarefas.core;

import com.pds.tarefas.patterns.decorator.TarefaComPrioridadeDecorator;
import com.pds.tarefas.patterns.proxy.TarefaServiceProxy;
import com.pds.tarefas.patterns.adapter.LegadoDadosTarefasCSV;
import com.pds.tarefas.patterns.adapter.LegadoDadosTarefasCSVAdapter;

import java.util.List;
import java.util.Scanner;

public class Main {
    // O serviço de tarefas atual que será usado. Pode ser o TarefaServiceImpl ou o TarefaServiceProxy.
    private static ITarefaService currentTarefaService;
    // O repositório de tarefas atual. Já está usando o Adapter para o CSV legado.
    private static ITarefaRepository currentTarefaRepository;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isAdminUser = false; // Estado inicial: usuário comum

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Sistema de Gerenciamento de Tarefas!");

        // Inicializa o repositório padrão (usando o adapter para o CSV legado)
        currentTarefaRepository = new LegadoDadosTarefasCSVAdapter(new LegadoDadosTarefasCSV());
        // Inicializa o serviço de tarefas com base no estado inicial do usuário (comum)
        // Isso demonstra o Proxy desde o início para operações de remoção.
        currentTarefaService = new TarefaServiceProxy(isAdminUser);

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    adicionarTarefa();
                    break;
                case 2:
                    listarTarefas();
                    break;
                case 3:
                    marcarTarefaConcluida();
                    break;
                case 4:
                    removerTarefa();
                    break;
                case 5:
                    trocarUsuario(); // Nova opção para demonstrar o Proxy
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine(); // Consome a nova linha pendente
        } while (opcao != 0);

        scanner.close(); // Fecha o scanner ao sair
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("Status do Usuário: " + (isAdminUser ? "ADMINISTRADOR" : "COMUM"));
        System.out.println("1. Adicionar Tarefa (Decorator: Prioridade)");
        System.out.println("2. Listar Tarefas (Adapter: Carrega do CSV)");
        System.out.println("3. Marcar Tarefa como Concluída");
        System.out.println("4. Remover Tarefa (Proxy: Acesso Restrito)");
        System.out.println("5. Trocar Usuário (ADMIN/COMUM)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next(); // Consome a entrada inválida
            System.out.print("Escolha uma opção: ");
        }
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha pendente após nextInt()
        return opcao;
    }

    private static void adicionarTarefa() {
        System.out.print("Digite a descrição da nova tarefa: ");
        String descricao = scanner.nextLine();
        ITarefa novaTarefa = new TarefaConcreta(descricao);

        // Integração do Decorator: Adicionar prioridade
        System.out.print("Deseja adicionar uma prioridade a esta tarefa? (sim/não): ");
        String respostaPrioridade = scanner.nextLine().toLowerCase();
        if (respostaPrioridade.equals("sim")) {
            System.out.print("Digite a prioridade (ALTA, MEDIA, BAIXA): ");
            String prioridade = scanner.nextLine();
            novaTarefa = new TarefaComPrioridadeDecorator(novaTarefa, prioridade);
        }

        currentTarefaService.adicionarTarefa(novaTarefa);
        // O Adapter já está em uso aqui para salvar a tarefa no repositório CSV
        currentTarefaRepository.salvar(novaTarefa);
    }

    private static void listarTarefas() {
        System.out.println("\n--- LISTA DE TAREFAS ---");
        // O Adapter já está em uso aqui para carregar as tarefas do repositório CSV
        List<ITarefa> tarefas = currentTarefaRepository.carregarTodas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            for (int i = 0; i < tarefas.size(); i++) {
                System.out.print((i + 1) + ". ");
                tarefas.get(i).exibirDetalhes();
            }
        }
    }

    private static void marcarTarefaConcluida() {
        System.out.print("Digite a descrição da tarefa a ser marcada como concluída: ");
        String descricao = scanner.nextLine();
        currentTarefaService.marcarTarefaConcluida(descricao);
        // Atualiza o status da tarefa no repositório via Adapter
        ITarefa tarefaAtualizada = currentTarefaService.buscarTarefa(descricao);
        if (tarefaAtualizada != null) {
            currentTarefaRepository.salvar(tarefaAtualizada); // Sobrescreve a tarefa no repositório
        }
    }

    private static void removerTarefa() {
        System.out.print("Digite a descrição da tarefa a ser removida: ");
        String descricao = scanner.nextLine();
        // O Proxy (currentTarefaService) controlará o acesso a esta operação
        currentTarefaService.removerTarefa(descricao);
        // Se a remoção for permitida pelo Proxy, remove também do repositório via Adapter
        // Nota: A lógica de remoção do repositório aqui é chamada independentemente do Proxy.
        // Se o Proxy negar a remoção no serviço, a remoção do repositório não encontrará a tarefa.
        currentTarefaRepository.remover(descricao);
    }

    private static void trocarUsuario() {
        isAdminUser = !isAdminUser; // Inverte o status do usuário
        currentTarefaService = new TarefaServiceProxy(isAdminUser); // Reinstancia o Proxy com a nova permissão
        System.out.println("Status do usuário alterado para: " + (isAdminUser ? "ADMINISTRADOR" : "COMUM"));
        System.out.println("Tente remover uma tarefa para ver o Proxy em ação!");
    }
}