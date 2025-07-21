package com.pds.tarefas.patterns.proxy;

import com.pds.tarefas.core.ITarefa;
import com.pds.tarefas.core.ITarefaService;
import com.pds.tarefas.core.TarefaServiceImpl; // O serviço real que o proxy vai proteger

import java.util.List;

/**
 * Proxy para o serviço de tarefas, que controla o acesso a operações sensíveis.
 * Permite que apenas usuários com permissão de administrador possam remover tarefas.
 */
public class TarefaServiceProxy implements ITarefaService {
    private ITarefaService realService; // Referência ao serviço real (TarefaServiceImpl)
    private boolean isAdmin; // Simula a permissão do usuário

    /**
     * Construtor do Proxy.
     * @param isAdmin Booleano que indica se o usuário atual tem permissão de administrador.
     */
    public TarefaServiceProxy(boolean isAdmin) {
        this.realService = new TarefaServiceImpl(); // O Proxy cria e gerencia a instância do serviço real
        this.isAdmin = isAdmin;
        System.out.println("Proxy de Serviço de Tarefas criado. Permissão de Administrador: " + isAdmin);
    }

    @Override
    public void adicionarTarefa(ITarefa tarefa) {
        // Operação que pode ser permitida a todos, ou adicionar uma verificação se necessário
        System.out.println("Proxy: Tentando adicionar tarefa...");
        realService.adicionarTarefa(tarefa);
    }

    @Override
    public void removerTarefa(String descricao) {
        // Esta é a operação protegida pelo Proxy
        if (isAdmin) {
            System.out.println("Proxy: Permissão concedida. Removendo tarefa '" + descricao + "'.");
            realService.removerTarefa(descricao);
        } else {
            System.out.println("Proxy: Acesso negado! Apenas administradores podem remover tarefas.");
        }
    }

    @Override
    public ITarefa buscarTarefa(String descricao) {
        // Operação que pode ser acessada sem restrições
        System.out.println("Proxy: Buscando tarefa '" + descricao + "'.");
        return realService.buscarTarefa(descricao);
    }

    @Override
    public List<ITarefa> listarTodasTarefas() {
        // Operação que pode ser acessada sem restrições
        System.out.println("Proxy: Listando todas as tarefas.");
        return realService.listarTodasTarefas();
    }

    @Override
    public void marcarTarefaConcluida(String descricao) {
        // Esta operação também pode ser protegida ou não, dependendo da regra de negócio
        System.out.println("Proxy: Tentando marcar tarefa '" + descricao + "' como concluída.");
        realService.marcarTarefaConcluida(descricao);
    }
}
