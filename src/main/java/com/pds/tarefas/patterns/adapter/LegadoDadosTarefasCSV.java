package com.pds.tarefas.patterns.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Classe que simula um sistema de persistência de dados "legado" ou externo.
 * Ele armazena dados de tarefas em um formato simples de string (como se fosse CSV).
 * Sua interface é incompatível com a ITarefaRepository.
 */
public class LegadoDadosTarefasCSV {
    // Simula um armazenamento de dados onde a chave é a descrição e o valor é a linha CSV
    private Map<String, String> dadosCSV = new HashMap<>();

    /**
     * Grava uma linha de dados de tarefa em formato CSV.
     * @param dados Linha de dados da tarefa no formato "descricao;concluida".
     */
    public void gravarTarefaCSV(String dados) {
        String[] partes = dados.split(";");
        if (partes.length == 2) {
            dadosCSV.put(partes[0].trim(), dados);
            System.out.println("LegadoCSV: Dados '" + partes[0].trim() + "' gravados em formato CSV.");
        } else {
            System.err.println("LegadoCSV: Formato de dados CSV inválido para gravação: " + dados);
        }
    }

    /**
     * Lê uma linha de dados de tarefa em formato CSV pela descrição.
     * @param descricao A descrição da tarefa a ser lida.
     * @return A linha CSV correspondente, ou null se não encontrada.
     */
    public String lerTarefaCSV(String descricao) {
        System.out.println("LegadoCSV: Lendo dados CSV para a tarefa '" + descricao + "'.");
        return dadosCSV.get(descricao);
    }

    /**
     * Retorna todos os dados CSV armazenados.
     * @return Uma lista de strings, onde cada string é uma linha CSV de tarefa.
     */
    public List<String> lerTodasTarefasCSV() {
        System.out.println("LegadoCSV: Lendo todas as tarefas em formato CSV.");
        return new java.util.ArrayList<>(dadosCSV.values());
    }

    /**
     * Remove uma tarefa do armazenamento CSV pela descrição.
     * @param descricao A descrição da tarefa a ser removida.
     * @return true se a tarefa foi removida, false caso contrário.
     */
    public boolean removerTarefaCSV(String descricao) {
        System.out.println("LegadoCSV: Removendo tarefa '" + descricao + "' do armazenamento CSV.");
        return dadosCSV.remove(descricao) != null;
    }
}
