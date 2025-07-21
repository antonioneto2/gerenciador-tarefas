package com.pds.tarefas.patterns.adapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList; // Adicionado para a lista de retorno

/**
 * Classe que simula um sistema de persistência de dados "legado" ou externo.
 * Agora, armazena e lê dados de tarefas em um arquivo CSV real no disco.
 * Formato CSV: "descricao;concluida;prioridade"
 */
public class LegadoDadosTarefasCSV {
    // Simula um armazenamento de dados onde a chave é a descrição e o valor é a linha CSV
    private Map<String, String> dadosCSV = new HashMap<>();
    private static final String NOME_ARQUIVO = "tarefas_legado.csv"; // Nome do arquivo para persistência

    /**
     * Construtor do sistema legado.
     * Ao ser instanciado, tenta carregar os dados do arquivo CSV.
     */
    public LegadoDadosTarefasCSV() {
        carregarDadosDoArquivo();
    }

    /**
     * Carrega os dados das tarefas do arquivo CSV para a memória.
     */
    private void carregarDadosDoArquivo() {
        dadosCSV.clear(); // Limpa dados em memória antes de carregar
        try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 1) { // Garante que a linha não está vazia ou mal formatada
                    dadosCSV.put(partes[0].trim(), linha);
                }
            }
            System.out.println("LegadoCSV: Dados carregados do arquivo '" + NOME_ARQUIVO + "'.");
        } catch (IOException e) {
            System.out.println("LegadoCSV: Arquivo '" + NOME_ARQUIVO + "' não encontrado ou erro de leitura. Criando um novo.");
            // Se o arquivo não existe, ele será criado na primeira gravação.
        }
    }

    /**
     * Salva todos os dados das tarefas da memória para o arquivo CSV.
     */
    private void salvarDadosNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (String linha : dadosCSV.values()) {
                bw.write(linha);
                bw.newLine();
            }
            System.out.println("LegadoCSV: Dados salvos no arquivo '" + NOME_ARQUIVO + "'.");
        } catch (IOException e) {
            System.err.println("LegadoCSV: Erro ao salvar dados no arquivo '" + NOME_ARQUIVO + "': " + e.getMessage());
        }
    }

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
        System.out.println("LegadoCSV: Dados '" + descricao + "' gravados em memória.");
        salvarDadosNoArquivo(); // Salva no arquivo após cada alteração
    }

    /**
     * Lê uma linha de dados de tarefa em formato CSV pela descrição.
     * @param descricao A descrição da tarefa a ser lida.
     * @return A linha CSV correspondente, ou null se não encontrada.
     */
    public String lerTarefa(String descricao) {
        System.out.println("LegadoCSV: Lendo dados CSV para a tarefa '" + descricao + "' da memória.");
        return dadosCSV.get(descricao);
    }

    /**
     * Retorna todos os dados CSV armazenados.
     * @return Uma lista de strings, onde cada string é uma linha CSV de tarefa.
     */
    public List<String> lerTodasTarefas() {
        System.out.println("LegadoCSV: Lendo todas as tarefas em formato CSV da memória.");
        return new ArrayList<>(dadosCSV.values()); // Retorna uma cópia dos valores
    }

    /**
     * Remove uma tarefa do armazenamento CSV pela descrição.
     * @param descricao A descrição da tarefa a ser removida.
     * @return true se a tarefa foi removida, false caso contrário.
     */
    public boolean removerTarefa(String descricao) {
        System.out.println("LegadoCSV: Removendo tarefa '" + descricao + "' da memória.");
        boolean removido = dadosCSV.remove(descricao) != null;
        if (removido) {
            salvarDadosNoArquivo(); // Salva no arquivo após cada alteração
        }
        return removido;
    }
}
