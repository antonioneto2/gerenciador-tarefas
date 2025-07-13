package com.pds.tarefas.core;

public class TarefaConcreta implements ITarefa {
    private String descricao;
    private boolean concluida;

    public TarefaConcreta(String descricao) {
        this.descricao = descricao;
        this.concluida = false;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean isConcluida() {
        return concluida;
    }

    @Override
    public void marcarComoConcluida() {
        this.concluida = true;
        System.out.println("Tarefa '" + descricao + "' marcada como concluída.");
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Tarefa: " + descricao + " | Concluída: " + (concluida ? "Sim" : "Não"));
    }
}