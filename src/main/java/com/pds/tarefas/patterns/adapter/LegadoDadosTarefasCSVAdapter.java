package com.pds.tarefas.patterns.adapter;

import com.pds.tarefas.core.ITarefa;
import com.pds.tarefas.core.ITarefaRepository;
import com.pds.tarefas.core.TarefaConcreta;
import com.pds.tarefas.patterns.decorator.TarefaComPrioridadeDecorator; // Importe o Decorator

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do padrão Adapter para ITarefaRepository.
 * Adapta a interface do sistema legado LegadoDadosTarefasCSV
 * para a interface ITarefaRepository que o sistema espera.
 * Agora suporta persistência de prioridade para tarefas decoradas.
 */
public class LegadoDadosTarefasCSVAdapter implements ITarefaRepository {
    private LegadoDadosTarefasCSV legado; // A instância do sistema legado (Adaptee)

    /**
     * Construtor do Adapter.
     * @param legado A instância do sistema LegadoDadosTarefasCSV a ser adaptada.
     */
    public LegadoDadosTarefasCSVAdapter(LegadoDadosTarefasCSV legado) {
        this.legado = legado;
        System.out.println("Adapter de Repositório CSV criado.");
    }

    @Override
    public void salvar(ITarefa tarefa) {
        String prioridade = "";
        // Verifica se a tarefa é uma instância do Decorator de Prioridade
        if (tarefa instanceof TarefaComPrioridadeDecorator) {
            prioridade = ((TarefaComPrioridadeDecorator) tarefa).getPrioridade();
        }
        // Salva no sistema legado, incluindo a prioridade
        legado.gravarTarefa(tarefa.getDescricao(), tarefa.isConcluida(), prioridade);
        System.out.println("Adapter: Tarefa '" + tarefa.getDescricao() + "' salva via CSV.");
    }

    @Override
    public ITarefa carregar(String descricao) {
        String dadosCSV = legado.lerTarefa(descricao); // Chama o método atualizado do legado
        if (dadosCSV != null) {
            // Divide a string CSV: descricao;concluida;prioridade
            String[] partes = dadosCSV.split(";");
            if (partes.length >= 2) { // Pode ter 2 (sem prioridade) ou 3 (com prioridade) partes
                String desc = partes[0].trim();
                boolean concluida = Boolean.parseBoolean(partes[1].trim());
                String prioridade = (partes.length == 3) ? partes[2].trim() : ""; // Pega a prioridade se existir

                ITarefa tarefa = new TarefaConcreta(desc);
                if (concluida) {
                    tarefa.marcarComoConcluida();
                }

                // Se houver prioridade, decora a tarefa antes de retorná-la
                if (!prioridade.isEmpty()) {
                    tarefa = new TarefaComPrioridadeDecorator(tarefa, prioridade);
                }
                System.out.println("Adapter: Tarefa '" + descricao + "' carregada via CSV.");
                return tarefa;
            }
        }
        System.out.println("Adapter: Tarefa '" + descricao + "' não encontrada via CSV.");
        return null;
    }

    @Override
    public List<ITarefa> carregarTodas() {
        List<ITarefa> tarefas = new ArrayList<>();
        List<String> todasLinhasCSV = legado.lerTodasTarefas(); // Chama o método atualizado do legado
        for (String dadosCSV : todasLinhasCSV) {
            String[] partes = dadosCSV.split(";");
            if (partes.length >= 2) { // Pode ter 2 (sem prioridade) ou 3 (com prioridade) partes
                String desc = partes[0].trim();
                boolean concluida = Boolean.parseBoolean(partes[1].trim());
                String prioridade = (partes.length == 3) ? partes[2].trim() : "";

                ITarefa tarefa = new TarefaConcreta(desc);
                if (concluida) {
                    tarefa.marcarComoConcluida();
                }

                // Se houver prioridade, decora a tarefa antes de adicioná-la à lista
                if (!prioridade.isEmpty()) {
                    tarefa = new TarefaComPrioridadeDecorator(tarefa, prioridade);
                }
                tarefas.add(tarefa);
            }
        }
        System.out.println("Adapter: Todas as tarefas carregadas via CSV.");
        return tarefas;
    }

    @Override
    public void remover(String descricao) {
        boolean removido = legado.removerTarefa(descricao); // Chama o método atualizado do legado
        if (removido) {
            System.out.println("Adapter: Tarefa '" + descricao + "' removida via CSV.");
        } else {
            System.out.println("Adapter: Tarefa '" + descricao + "' não encontrada para remoção via CSV.");
        }
    }
}
