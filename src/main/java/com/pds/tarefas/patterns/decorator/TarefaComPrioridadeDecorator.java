package com.pds.tarefas.patterns.decorator;

import com.pds.tarefas.core.ITarefa; // Importa a interface ITarefa do pacote core

public class TarefaComPrioridadeDecorator extends TarefaDecorator {
    private String prioridade;

    /**
     * Construtor para TarefaComPrioridadeDecorator.
     * @param tarefaDecorada A instância de ITarefa a ser decorada.
     * @param prioridade A string que representa a prioridade (ex: "ALTA", "MEDIA", "BAIXA").
     */
    public TarefaComPrioridadeDecorator(ITarefa tarefaDecorada, String prioridade) {
        super(tarefaDecorada); // Chama o construtor da superclasse (TarefaDecorator)
        this.prioridade = prioridade.toUpperCase(); // Armazena a prioridade em maiúsculas
    }
    /**
     * Sobrescreve o método exibirDetalhes para incluir a informação de prioridade.
     * Adiciona um ícone e a prioridade antes de exibir os detalhes da tarefa original.
     */
    @Override
    public void exibirDetalhes() {
        String iconePrioridade;
        switch (prioridade) {
            case "ALTA":
                iconePrioridade = "🔥"; // Fogo para alta prioridade
                break;
            case "MEDIA":
                iconePrioridade = "🚨"; // Alerta para média prioridade
                break;
            case "BAIXA":
                iconePrioridade = "😴"; // Dormindo para baixa prioridade
                break;
            default:
                iconePrioridade = "📍"; // Marcador padrão para prioridade desconhecida
                break;
        }
        System.out.print(iconePrioridade + " "); // Imprime o ícone da prioridade
        tarefaDecorada.exibirDetalhes(); // Delega a exibição dos detalhes básicos à tarefa decorada
    }

    /**
     * Retorna a prioridade da tarefa.
     * @return A prioridade da tarefa (ALTA, MEDIA, BAIXA).
     */
    public String getPrioridade() {
        return prioridade;
    }

    /**
     * Define a prioridade da tarefa.
     * @param prioridade A nova prioridade da tarefa.
     */
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade.toUpperCase();
    }
}
