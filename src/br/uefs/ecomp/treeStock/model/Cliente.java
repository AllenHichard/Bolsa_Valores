/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.model;



/**
 * A classe Cliente, é responsábel pela criação de cliente através dos atributos
 * pedido pelo sistema. 
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class Cliente implements Comparable {

    private final String nome;
    private String endereco;
    private Carteira carteira;

    /**
     * O primeiro construtor da classe cliente é referenciado apenas com o nome,
     * utilizado para busca de cliente na ´Árvore de cliente.
     *
     * @param nome para criar apenas essa objeto com apenas seu nome
     */
    public Cliente(String nome) {
        this.nome = nome;
    }

    /**
     * O segundo Construtor, cria o cliente e inicializa sua carteira,
     * armazenando-o na árvore de clientes.
     *
     * @param nome nome do cliente
     * @param endereco endereço do cliente
     */
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.carteira = new Carteira();
    }

    /**
     * O método Carteira retorna a referência da carteira do cliente, que contém
     * ações
     *
     * @return carteira
     */
    public Carteira getCarteira() {
        return carteira;
    }

    /**
     * O método getNome, retorna o nome do cliente.
     *
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * O método getEndereço, retorna o endereço do cliente.
     *
     * @return  endereço
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * O método equals verifica se dois cliente são iguais. Eles são iguais se
     * tiverem o mesmo nome um do outro retornando true ou false.
     *
     * @param o objeto a ser comparado
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Cliente) {
            Cliente b = (Cliente) o;
            if (b.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * O método compareTo verifica se dois objetos cliente são iguais ou um
     * maior que o outro, retornando um inteiro para indicar como resposta.
     * Explicação clara na classe Acao.
     *
     * @param arg0 objeto a ser comparado.
     * @return inteiro que indica quem é maior e 0 para igual.
     */
    @Override
    public int compareTo(Object arg0) {
        return nome.compareTo(((Cliente) arg0).getNome());
    }

}
