package com.pds.tarefas.patterns.adapter;

import com.pds.tarefas.core.ITarefa;
import com.pds.tarefas.core.ITarefaRepository;
import com.pds.tarefas.core.TarefaConcreta; // Necessário para converter de CSV para ITarefa

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do padrão Adapter para ITarefaRepository.
 * Adapta a interface do sistema legado LegadoDadosTarefasCSV
 * para a interface ITarefaRepository que o sistema espera.
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
        // Converte a ITarefa para o formato CSV que o sistema legado entende
        String dadosCSV = tarefa.getDescricao() + ";" + tarefa.isConcluida();
        legado.gravarTarefaCSV(dadosCSV);
        System.out.println("Adapter: Tarefa '" + tarefa.getDescricao() + "' salva via CSV.");
    }

    @Override
    public ITarefa carregar(String descricao) {
        // Lê os dados CSV do sistema legado
        String dadosCSV = legado.lerTarefaCSV(descricao);
        if (dadosCSV != null) {
            // Converte o formato CSV de volta para um objeto ITarefa
            String[] partes = dadosCSV.split(";");
            if (partes.length == 2) {
                String desc = partes[0].trim();
                boolean concluida = Boolean.parseBoolean(partes[1].trim());
                ITarefa tarefa = new TarefaConcreta(desc); // Cria uma TarefaConcreta
                if (concluida) {
                    tarefa.marcarComoConcluida(); // Marca como concluída se necessário
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
        List<String> todasLinhasCSV = legado.lerTodasTarefasCSV();
        for (String dadosCSV : todasLinhasCSV) {
            String[] partes = dadosCSV.split(";");
            if (partes.length == 2) {
                String desc = partes[0].trim();
                boolean concluida = Boolean.parseBoolean(partes[1].trim());
                ITarefa tarefa = new TarefaConcreta(desc);
                if (concluida) {
                    tarefa.marcarComoConcluida();
                }
                tarefas.add(tarefa);
            }
        }
        System.out.println("Adapter: Todas as tarefas carregadas via CSV.");
        return tarefas;
    }

    @Override
    public void remover(String descricao) {
        boolean removido = legado.removerTarefaCSV(descricao);
        if (removido) {
            System.out.println("Adapter: Tarefa '" + descricao + "' removida via CSV.");
        } else {
            System.out.println("Adapter: Tarefa '" + descricao + "' não encontrada para remoção via CSV.");
        }
    }
}
