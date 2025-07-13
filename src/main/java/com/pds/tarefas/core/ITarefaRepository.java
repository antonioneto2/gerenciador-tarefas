package com.pds.tarefas.core;

import java.util.List;

public interface ITarefaRepository {
    void salvar(ITarefa tarefa);
    ITarefa carregar(String descricao);
    List<ITarefa> carregarTodas();
    void remover(String descricao);
}