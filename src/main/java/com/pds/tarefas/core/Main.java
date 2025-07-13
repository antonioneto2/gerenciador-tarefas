package com.pds.tarefas.core;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o sistema de gerenciamento de tarefas...");

        // Usando o serviço de tarefas básico
        ITarefaService tarefaService = new TarefaServiceImpl();

        // Adicionar tarefas
        tarefaService.adicionarTarefa(new TarefaConcreta("Estudar POO"));
        tarefaService.adicionarTarefa(new TarefaConcreta("Fazer compras"));
        tarefaService.adicionarTarefa(new TarefaConcreta("Preparar apresentação do projeto"));
        tarefaService.adicionarTarefa(new TarefaConcreta("Estudar POO")); // Tentando adicionar duplicata

        System.out.println("\n--- Tarefas Atuais ---");
        List<ITarefa> todasTarefas = tarefaService.listarTodasTarefas();
        for (ITarefa tarefa : todasTarefas) {
            tarefa.exibirDetalhes();
        }

        // Marcar uma tarefa como concluída
        tarefaService.marcarTarefaConcluida("Fazer compras");
        tarefaService.marcarTarefaConcluida("Fazer compras"); // Tentando marcar de novo

        System.out.println("\n--- Tarefas Após Conclusão ---");
        for (ITarefa tarefa : tarefaService.listarTodasTarefas()) {
            tarefa.exibirDetalhes();
        }

        // Buscar uma tarefa
        ITarefa tarefaBuscada = tarefaService.buscarTarefa("Estudar POO");
        if (tarefaBuscada != null) {
            System.out.println("\nTarefa buscada: ");
            tarefaBuscada.exibirDetalhes();
        } else {
            System.out.println("\nTarefa buscada não encontrada.");
        }

        // Remover uma tarefa
        tarefaService.removerTarefa("Preparar apresentação do projeto");
        tarefaService.removerTarefa("Tarefa Inexistente");

        System.out.println("\n--- Tarefas Finais ---");
        for (ITarefa tarefa : tarefaService.listarTodasTarefas()) {
            tarefa.exibirDetalhes();
        }

        System.out.println("\nSistema de gerenciamento de tarefas finalizado.");
    }
}