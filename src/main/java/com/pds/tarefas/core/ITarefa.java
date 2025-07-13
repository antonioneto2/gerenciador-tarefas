package com.pds.tarefas.core;

public interface ITarefa {
    String getDescricao();
    void setDescricao(String descricao);
    boolean isConcluida();
    void marcarComoConcluida();
    void exibirDetalhes(); // Método para exibir informações da tarefa
}
