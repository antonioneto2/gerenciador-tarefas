package com.pds.tarefas.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarefaServiceImpl implements ITarefaService {
    private List<ITarefa> tarefas;

    public TarefaServiceImpl() {
        this.tarefas = new ArrayList<>();
    }

    @Override
    public void adicionarTarefa(ITarefa tarefa) {
        // Verifica se já existe uma tarefa com a mesma descrição
        if (buscarTarefa(tarefa.getDescricao()) == null) {
            tarefas.add(tarefa);
            System.out.println("Tarefa '" + tarefa.getDescricao() + "' adicionada.");
        } else {
            System.out.println("Erro: Tarefa '" + tarefa.getDescricao() + "' já existe.");
        }
    }

    @Override
    public void removerTarefa(String descricao) {
        boolean removido = tarefas.removeIf(t -> t.getDescricao().equalsIgnoreCase(descricao));
        if (removido) {
            System.out.println("Tarefa '" + descricao + "' removida.");
        } else {
            System.out.println("Tarefa '" + descricao + "' não encontrada.");
        }
    }

    @Override
    public ITarefa buscarTarefa(String descricao) {
        // Usa Stream API e Optional para buscar de forma segura
        Optional<ITarefa> foundTask = tarefas.stream()
                                            .filter(t -> t.getDescricao().equalsIgnoreCase(descricao))
                                            .findFirst();
        return foundTask.orElse(null); // Retorna a tarefa se encontrada, senão null
    }

    @Override
    public List<ITarefa> listarTodasTarefas() {
        return new ArrayList<>(tarefas); // Retorna uma cópia para evitar modificações externas diretas
    }

    @Override
    public void marcarTarefaConcluida(String descricao) {
        ITarefa tarefa = buscarTarefa(descricao);
        if (tarefa != null) {
            if (!tarefa.isConcluida()) {
                tarefa.marcarComoConcluida();
            } else {
                System.out.println("Tarefa '" + descricao + "' já está concluída.");
            }
        } else {
            System.out.println("Tarefa '" + descricao + "' não encontrada.");
        }
    }
}