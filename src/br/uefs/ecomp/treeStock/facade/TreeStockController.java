/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.facade;

import br.uefs.ecomp.treeStock.model.Acao;
import br.uefs.ecomp.treeStock.model.Cliente;
import br.uefs.ecomp.treeStock.model.exception.AcaoNaoEncontradaException;
import br.uefs.ecomp.treeStock.model.exception.ClienteNaoEncontradoException;
import br.uefs.ecomp.treeStock.util.ArvoreAVL;
import br.uefs.ecomp.treeStock.util.DadoDuplicadoException;
import br.uefs.ecomp.treeStock.util.DadoNaoEncontradoException;
import br.uefs.ecomp.treeStock.util.IArvore;
import br.uefs.ecomp.treeStock.util.IPares;
import java.util.Iterator;
import br.uefs.ecomp.treeStock.util.ListaEncadeada;

/**
 * A Classe TreeStockCntroller é responsável por garantir a completa
 * funcionalidade do programa. Ela Contém todos os métodos que serão utilizados
 * pela equipe responsável pela interface.
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
class TreeStockController {

    private final IArvore arvoreCliente;
    private final IArvore arvoreAcao;

    /**
     * O construtor desta classe inicializa todos os atributos da classe
     * TreeStockCntroller. ou seja As estruturas de dados Arvore para cliente e
     * ações.
     */
    public TreeStockController() {
        arvoreCliente = new ArvoreAVL();
        arvoreAcao = new ArvoreAVL();
    }

    /**
     * O método cadastrarCliente, efetua o cadastro individual de cada cliente.
     * Após ser cadastrados todos ficam armazenados no sistema, para sua
     * utilização.
     *
     * @param nome
     * @param endereco
     * @return O cliente cadastrado
     * @throws DadoDuplicadoException
     */
    public Cliente cadastrarCliente(String nome, String endereco) throws DadoDuplicadoException {
        Cliente cliente = new Cliente(nome, endereco);
        arvoreCliente.inserir(cliente);
        return cliente;
    }

    /**
     * O método removerCliente, busca o cliente pelo seu nome na Árvore de
     * cliente e o exclui do sistema.
     *
     * @param nome
     * @return
     * @throws ClienteNaoEncontradoException
     */
    public Cliente removerCliente(String nome) throws ClienteNaoEncontradoException {
        try {
            Cliente cliente = new Cliente(nome);
            arvoreCliente.buscar(cliente);
            arvoreCliente.remover(cliente);
            return cliente;
        } catch (DadoNaoEncontradoException e) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
    }

    /**
     * O método clienteCadastrado, verifica se existe um cliente com o nome
     * informado no sistema. Caso esse cliente exista ou não retorna um
     * verdadeiro ou falso.
     *
     * @param nome
     * @return
     */
    public boolean clienteCadastrado(String nome) {
        try {
            Cliente cliente = new Cliente(nome);
            arvoreCliente.buscar(cliente);
            return true;
        } catch (DadoNaoEncontradoException e) {
            return false;
        }
    }

    /**
     * O método cadastrarAcao, efetua o cadastro de novas ações no sistema. Cada
     * ação contendo sua sigla, nome e o seu valor inicial.
     *
     * @param siglaAcao
     * @param nomeAcao
     * @param valorInicial
     * @return
     * @throws DadoDuplicadoException
     */
    public Acao cadastrarAcao(String siglaAcao, String nomeAcao, double valorInicial) throws DadoDuplicadoException {
        Acao acao = new Acao(siglaAcao);
        acao.setNomeAcao(nomeAcao);
        acao.setValorInicial(valorInicial);
        arvoreAcao.inserir(acao);
        return acao;
    }

    /**
     * O método removerAcao, remove uma ação que está armazenado no sistema
     * apartir de sua sigla.
     *
     * @param siglaAcao
     * @return
     * @throws AcaoNaoEncontradaException
     */
    public Acao removerAcao(String siglaAcao) throws AcaoNaoEncontradaException {
        try {
            Acao acao = new Acao(siglaAcao);
            arvoreAcao.buscar(acao);
            arvoreAcao.remover(acao);
            return acao;
        } catch (DadoNaoEncontradoException e) {
            throw new AcaoNaoEncontradaException("Ação não encontrada");
        }
    }

    /**
     * O método acaoCadastrada, verifica se existe uma ação apartir de uma
     * sigla. Após a verificação retorna um true ou false.
     *
     * @param siglaAcao
     * @return
     */
    public boolean acaoCadastrada(String siglaAcao) {
        try {
            Acao acao = new Acao(siglaAcao);
            arvoreAcao.buscar(acao);
            return true;
        } catch (DadoNaoEncontradoException e) {
            return false;
        }
    }

    /**
     * O método setValorAcao, busca a ação que contém a sigla passada e altera
     * seu antigo valor para o mais atual. Esse método tem um adicional, ele
     * busca todos os cliente que já compraram essa ação e atualiza o valor da
     * sua carteira, pela diferençã sofrida entre e atual e antigo valor.
     *
     * @param siglaAcao
     * @param novoValor
     * @throws AcaoNaoEncontradaException
     */
    public void setValorAcao(String siglaAcao, double novoValor) throws AcaoNaoEncontradaException {
        double ajuste;
        try {
            Acao acao = new Acao(siglaAcao);
            Acao a = (Acao) arvoreAcao.buscar(acao);
            double valorAntigo = a.getValor();
            a.setValorInicial(novoValor);
            double valorAtual = a.getValor();
            ajuste = valorAtual - valorAntigo;
            Iterator it = arvoreCliente.iterator();
            while (it.hasNext()) {
                try {
                    Cliente cliente = (Cliente) it.next();
                    int quantidade = (int) cliente.getCarteira().getAcao().get(siglaAcao);
                    cliente.getCarteira().getAcao().setValor(ajuste * quantidade);
                } catch (DadoNaoEncontradoException e) {
                }
            }
        } catch (DadoNaoEncontradoException e) {
            throw new AcaoNaoEncontradaException();
        }
    }

    /**
     * O método getValorAcao busca a ação que tem a mesma sigla da sigla passada
     * e retorna o valor desta ação.
     *
     * @param siglaAcao
     * @return
     * @throws AcaoNaoEncontradaException
     */
    public double getValorAcao(String siglaAcao) throws AcaoNaoEncontradaException {
        try {
            Acao acao = new Acao(siglaAcao);
            Acao a = (Acao) arvoreAcao.buscar(acao);
            return a.getValor();

        } catch (DadoNaoEncontradoException e) {
            throw new AcaoNaoEncontradaException();
        }
    }

    /**
     * O método iterator, retorna o iterator da Arvore de clientes.
     *
     * @return
     */
    public Iterator iterator() {
        return arvoreCliente.iterator();
    }

    /**
     * O método incluirAcaoCliente, adiciona a um cliente desejado uma nova ação
     * a sua carteira. Após seu cadastro na carteira, é atualizado o tamanho e o
     * valor da carteira, além desses dados ficarem armazenados na memória.
     *
     * @param nomeCliente
     * @param siglaAcao
     * @param quantidade
     * @throws AcaoNaoEncontradaException
     * @throws ClienteNaoEncontradoException
     */
    public void incluirAcaoCliente(String nomeCliente, String siglaAcao, int quantidade) throws AcaoNaoEncontradaException, ClienteNaoEncontradoException {
        Acao a;
        Cliente c;
        try {
            Cliente cliente = new Cliente(nomeCliente);
            c = (Cliente) arvoreCliente.buscar(cliente);
        } catch (DadoNaoEncontradoException e) {
            throw new ClienteNaoEncontradoException("CLiente não encontrado");
        }
        try {
            Acao acao = new Acao(siglaAcao);
            a = (Acao) arvoreAcao.buscar(acao);
        } catch (DadoNaoEncontradoException e) {
            throw new AcaoNaoEncontradaException("Ação não encontrada");
        }
        c.getCarteira().getAcao().setValor(quantidade * a.getValor());
        c.getCarteira().getAcao().put(siglaAcao, quantidade);
        c.getCarteira().getAcao().setTamanho(quantidade);
    }

    /**
     * O método removerAcaoCliente, exclui uma ação de uma carteira de um
     * cliente que tenha o mesmo nome do nomeCliente que foi passado,
     * atualizando o novo valor da carteira e sua nova quantidade.
     *
     * @param nomeCliente
     * @param siglaAcao
     * @throws AcaoNaoEncontradaException
     * @throws ClienteNaoEncontradoException
     */
    public void removerAcaoCliente(String nomeCliente, String siglaAcao) throws AcaoNaoEncontradaException, ClienteNaoEncontradoException {

        Acao a;
        Cliente c;
        try {
            Cliente cliente = new Cliente(nomeCliente);
            c = (Cliente) arvoreCliente.buscar(cliente);
        } catch (DadoNaoEncontradoException e) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        try {
            Acao acao = new Acao(siglaAcao);
            a = (Acao) arvoreAcao.buscar(acao);
        } catch (DadoNaoEncontradoException e) {
            throw new AcaoNaoEncontradaException("Ação não encontrada");
        }
        try {
            int quantidade = (int) c.getCarteira().getAcao().get(siglaAcao);
            c.getCarteira().getAcao().setValor(-1 * quantidade * a.getValor());
            c.getCarteira().getAcao().setTamanho(-1 * quantidade);
            c.getCarteira().getAcao().remove(siglaAcao);
        } catch (DadoNaoEncontradoException e) {
        }
    }

    /**
     * O método getQuantidadeAcaoCliente, busca a carteira do cliente informado,
     * e analisa a quantidade e ações na carteira desse cliente contém com o
     * nome passado, e retorna o quantidade daquelas ações da carteira do
     * determinado cliente.
     *
     * @param nomeCliente
     * @param siglaAcao
     * @return
     * @throws AcaoNaoEncontradaException
     * @throws ClienteNaoEncontradoException
     */
    public int getQuantidadeAcaoCliente(String nomeCliente, String siglaAcao) throws AcaoNaoEncontradaException, ClienteNaoEncontradoException {
        Cliente c;
        int i = 0;
        Acao a;
        try {
            Cliente cliente = new Cliente(nomeCliente);
            c = (Cliente) arvoreCliente.buscar(cliente);
        } catch (DadoNaoEncontradoException e) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        try {
            i = (int) c.getCarteira().getAcao().get(siglaAcao);

        } catch (DadoNaoEncontradoException e) {
            throw new AcaoNaoEncontradaException("Ação não encotrada");
        }
        return i;
    }

    /**
     * O método setquantidadeAcaoCliente, atualiza a quantidade das ações deste
     * cliente de acordo com a quantidade passada, e ajusta o novo valor para a
     * carteira, pois se mudar a quantidade também muda o valor.
     *
     * @param nomeCliente
     * @param siglaAcao
     * @param quantidade
     * @throws AcaoNaoEncontradaException
     * @throws ClienteNaoEncontradoException
     */
    public void setQuantidadeAcaoCliente(String nomeCliente, String siglaAcao, int quantidade) throws AcaoNaoEncontradaException, ClienteNaoEncontradoException {
        Acao a;
        Cliente c;
        try {
            Cliente cliente = new Cliente(nomeCliente);
            c = (Cliente) arvoreCliente.buscar(cliente);
        } catch (DadoNaoEncontradoException e) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        try {
            Acao acao = new Acao(siglaAcao);
            a = (Acao) arvoreAcao.buscar(acao);
            c.getCarteira().getAcao().set(siglaAcao, quantidade);
            int quant = (int) c.getCarteira().getAcao().get(siglaAcao);
            int atual = quantidade - quant;
            c.getCarteira().getAcao().setValor(atual * a.getValor());
            c.getCarteira().getAcao().setTamanho(atual);
        } catch (DadoNaoEncontradoException e) {
            incluirAcaoCliente(nomeCliente, siglaAcao, quantidade);
        }
    }

    /**
     * O métodogetCarteiraCliente, retorna uma carteira de um cliente que tenha
     * o mesmo nome que o nome informado.
     *
     * @param nomeCliente
     * @return
     * @throws ClienteNaoEncontradoException
     */
    public IPares getCarteiraCliente(String nomeCliente) throws ClienteNaoEncontradoException {
        Cliente c;
        try {
            Cliente cliente = new Cliente(nomeCliente);
            c = (Cliente) arvoreCliente.buscar(cliente);
        } catch (DadoNaoEncontradoException e) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        return c.getCarteira().getAcao();
    }

    /**
     * O método getValorCarteiraCliente, busca o cliente que tenha o nome
     * passado e pega a sua carteira retornando o valor da mesma.
     *
     * @param nomeCliente
     * @return
     * @throws ClienteNaoEncontradoException
     * @throws AcaoNaoEncontradaException
     */
    public double getValorCarteiraCliente(String nomeCliente) throws ClienteNaoEncontradoException, AcaoNaoEncontradaException {

        Cliente c;
        try {
            Cliente cliente = new Cliente(nomeCliente);
            c = (Cliente) arvoreCliente.buscar(cliente);
        } catch (DadoNaoEncontradoException e) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        return c.getCarteira().getAcao().getValor();

    }

    /**
     * O método melhoresCliente, retorna os K melhores cliente de todo o
     * sistema. ou seja se passamos 5, o métodos vai pegar os 5 cliente que tem
     * as carteiras com os valores mais elevados e os retornará.
     *
     * @param k
     * @return
     * @throws ClienteNaoEncontradoException
     * @throws AcaoNaoEncontradaException
     */
    public Iterator melhoresClientes(int k) throws ClienteNaoEncontradoException, AcaoNaoEncontradaException {
        ListaEncadeada aux;
        try {
            aux = listarTopCliente(k);
            Iterator it = aux.iterator();
            return it;
        } catch (DadoNaoEncontradoException e) {
            throw new ClienteNaoEncontradoException();
        } catch (DadoDuplicadoException e) {

        }
        return null;
    }

    /**
     * O método listarTopCLiente, é o método auxiliar que organiza uma lista com
     * todos os elementos em forma decrescente de valores e depois pega os k
     * elementos desejados e os retorna para ser iterado.
     *
     * @param k
     * @return
     * @throws DadoNaoEncontradoException
     * @throws DadoDuplicadoException
     */
    private ListaEncadeada listarTopCliente(int k) throws DadoNaoEncontradoException, DadoDuplicadoException {
        ListaEncadeada ordenada = new ListaEncadeada();
        ListaEncadeada aux = new ListaEncadeada();
        Iterator it = arvoreCliente.iterator();
        while (it.hasNext()) {
            int index = 0;
            Cliente cliente = (Cliente) it.next();
            if (ordenada.tamanho() == 0) {
                ordenada.insereOrdenado(cliente, index);
            } else {
                for (int j = 0; j < ordenada.tamanho(); j++) {
                    Cliente c = (Cliente) ordenada.recupera(j);
                    if (c.getCarteira().getAcao().getValor() > cliente.getCarteira().getAcao().getValor()) {
                        index++;
                    }
                }
                ordenada.insereOrdenado(cliente, index);
            }
        }
        Iterator ip = ordenada.iterator();
        for (int i = 0; i < k; i++) {
            Cliente cliente = (Cliente) ip.next();
            aux.insereFinal(cliente);
        }
        return aux;

    }
}
