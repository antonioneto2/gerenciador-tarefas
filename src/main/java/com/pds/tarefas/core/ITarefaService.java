package com.pds.tarefas.core;

import java.util.List;

public interface ITarefaService {
    void adicionarTarefa(ITarefa tarefa);
    void removerTarefa(String descricao);
    ITarefa buscarTarefa(String descricao);
    List<ITarefa> listarTodasTarefas();
    void marcarTarefaConcluida(String descricao);
}