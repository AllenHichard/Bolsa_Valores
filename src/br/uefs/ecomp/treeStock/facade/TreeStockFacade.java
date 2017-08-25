package br.uefs.ecomp.treeStock.facade;

import br.uefs.ecomp.treeStock.model.Acao;
import br.uefs.ecomp.treeStock.model.Cliente;
import br.uefs.ecomp.treeStock.model.exception.AcaoNaoEncontradaException;
import br.uefs.ecomp.treeStock.model.exception.ClienteNaoEncontradoException;
import br.uefs.ecomp.treeStock.util.DadoDuplicadoException;
import br.uefs.ecomp.treeStock.util.IPares;
import java.util.Iterator;

public class TreeStockFacade {

    private final TreeStockController controller;

    public TreeStockFacade() {
        controller = new TreeStockController();
    }

    public Cliente cadastrarCliente(String nome, String endereco) throws DadoDuplicadoException {
        return controller.cadastrarCliente(nome, endereco);
    }

    public Cliente removerCliente(String nome) throws ClienteNaoEncontradoException {
        return controller.removerCliente(nome);
    }

    public boolean clienteCadastrado(String nome) {
        return controller.clienteCadastrado(nome);
    }

    public Acao cadastrarAcao(String siglaAcao, String nomeAcao, double valorInicial) throws DadoDuplicadoException {
        return controller.cadastrarAcao(siglaAcao, nomeAcao, valorInicial);
    }

    public Acao removerAcao(String siglaAcao) throws AcaoNaoEncontradaException {
        return controller.removerAcao(siglaAcao);
    }

    public boolean acaoCadastrada(String siglaAcao) {
        return controller.acaoCadastrada(siglaAcao);
    }

    public void setValorAcao(String siglaAcao, double novoValor) throws AcaoNaoEncontradaException {
        controller.setValorAcao(siglaAcao, novoValor);
    }
    
    public double getValorAcao(String siglaAcao) throws AcaoNaoEncontradaException {
        return controller.getValorAcao(siglaAcao);
    }

    /**
     * Retorna os clientes ordenados pelo nome.
     *
     * @return os clientes ordenados pelo nome.
     */
    public Iterator iterator() {
        return controller.iterator();
    }

    public void incluirAcaoCliente(String nomeCliente, String siglaAcao, int quantidade) throws ClienteNaoEncontradoException, AcaoNaoEncontradaException{
        controller.incluirAcaoCliente(nomeCliente, siglaAcao, quantidade);
    }

    public void removerAcaoCliente(String nomeCliente, String siglaAcao) throws ClienteNaoEncontradoException, AcaoNaoEncontradaException {
        controller.removerAcaoCliente(nomeCliente, siglaAcao);
    }

    public int getQuantidadeAcaoCliente(String nomeCliente, String siglaAcao) throws ClienteNaoEncontradoException, AcaoNaoEncontradaException {
        return  controller.getQuantidadeAcaoCliente(nomeCliente, siglaAcao);
    }
    
    public void setQuantidadeAcaoCliente(String nomeCliente, String siglaAcao, int quantidade) throws ClienteNaoEncontradoException, AcaoNaoEncontradaException {
        controller.setQuantidadeAcaoCliente(nomeCliente, siglaAcao, quantidade);
    }

    public IPares getCarteiraCliente(String nomeCliente) throws ClienteNaoEncontradoException {
        return controller.getCarteiraCliente(nomeCliente);
    }

    public double getValorCarteiraCliente(String nomeCliente) throws ClienteNaoEncontradoException, AcaoNaoEncontradaException {
        return controller.getValorCarteiraCliente(nomeCliente);
    }

    /**
     * Retorna os k melhores clientes em ordem descrescente de valor da
     * carteira.
     *
     * @param k os k melhores clientes
     * @return os k melhores clientes em ordem descrescente de valor da
     * carteira.
     * @throws br.uefs.ecomp.treeStock.model.exception.ClienteNaoEncontradoException "cliente não encontrado"
     * @throws br.uefs.ecomp.treeStock.model.exception.AcaoNaoEncontradaException "Ação não encontrada
     */
    public Iterator melhoresClientes(int k) throws ClienteNaoEncontradoException, AcaoNaoEncontradaException {
        return controller.melhoresClientes(k);
    }
}