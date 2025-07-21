package com.pds.tarefas.patterns.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que simula um sistema de persistência de dados "legado" ou externo.
 * Ele armazena dados de tarefas em um formato simples de string (como se fosse CSV).
 * Sua interface é incompatível com a ITarefaRepository.
 * Formato CSV: "descricao;concluida;prioridade"
 */
public class LegadoDadosTarefasCSV {
    // Simula um armazenamento de dados onde a chave é a descrição e o valor é a linha CSV
    private Map<String, String> dadosCSV = new HashMap<>();

    /**
     * Grava os dados de uma tarefa em formato CSV.
     * @param descricao A descrição da tarefa.
     * @param concluida O status de conclusão da tarefa.
     * @param prioridade A prioridade da tarefa (pode ser uma string vazia se não houver).
     */
    public void gravarTarefa(String descricao, boolean concluida, String prioridade) {
        // Formato: "descricao;concluida;prioridade"
        String dados = descricao + ";" + concluida + ";" + prioridade;
        dadosCSV.put(descricao.trim(), dados);
        System.out.println("LegadoCSV: Dados '" + descricao + "' gravados em formato CSV.");
    }

    /**
     * Lê uma linha de dados de tarefa em formato CSV pela descrição.
     * @param descricao A descrição da tarefa a ser lida.
     * @return A linha CSV correspondente, ou null se não encontrada.
     */
    public String lerTarefa(String descricao) {
        System.out.println("LegadoCSV: Lendo dados CSV para a tarefa '" + descricao + "'.");
        return dadosCSV.get(descricao);
    }

    /**
     * Retorna todos os dados CSV armazenados.
     * @return Uma lista de strings, onde cada string é uma linha CSV de tarefa.
     */
    public List<String> lerTodasTarefas() {
        System.out.println("LegadoCSV: Lendo todas as tarefas em formato CSV.");
        return new java.util.ArrayList<>(dadosCSV.values());
    }

    /**
     * Remove uma tarefa do armazenamento CSV pela descrição.
     * @param descricao A descrição da tarefa a ser removida.
     * @return true se a tarefa foi removida, false caso contrário.
     */
    public boolean removerTarefa(String descricao) {
        System.out.println("LegadoCSV: Removendo tarefa '" + descricao + "' do armazenamento CSV.");
        return dadosCSV.remove(descricao) != null;
    }
}
