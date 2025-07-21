package com.pds.tarefas.patterns.decorator;

import com.pds.tarefas.core.ITarefa; // Importando a interface ITarefa

public abstract class TarefaDecorator implements ITarefa {
    protected ITarefa tarefaDecorada; // A tarefa que está sendo decorada

    /**
     * Construtor para TarefaDecorator.
     * @param tarefaDecorada A instância de ITarefa a ser decorada.
     */
    public TarefaDecorator(ITarefa tarefaDecorada) {
        this.tarefaDecorada = tarefaDecorada;
    }

    // Métodos da interface ITarefa, delegados para a tarefaDecorada

    @Override
    public String getDescricao() {
        return tarefaDecorada.getDescricao();
    }

    @Override
    public void setDescricao(String descricao) {
        tarefaDecorada.setDescricao(descricao);
    }

    @Override
    public boolean isConcluida() {
        return tarefaDecorada.isConcluida();
    }

    @Override
    public void marcarComoConcluida() {
        tarefaDecorada.marcarComoConcluida();
    }

    @Override
    public void exibirDetalhes() {
        tarefaDecorada.exibirDetalhes();
    }
}
