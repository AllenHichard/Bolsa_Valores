/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.model;

/**
 * A classe Acao é responsável para que a bolsa de valores exista. Pois é dela
 * que se pode adicionar e remover ações, e é o seu valor que deixa o cliente
 * que a tém com uma carteira mas valorizada.
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class Acao implements Comparable {

    private final String siglaAcao;
    private String nomeAcao;
    private double valorInicial;

    /**
     * O contrutor de Acao, apenas instancia o Objeto apenas com sua sigla.
     *
     * @param siglaAcao cria a ação
     */
    public Acao(String siglaAcao) {
        this.siglaAcao = siglaAcao;
    }

    /**
     * O método getSigla retorna a sigla da Ação.
     *
     * @return sigla
     */
    public String getSigla() {
        return siglaAcao;
    }

    /**
     * O método getNome retorna o nome da Ação.
     *
     * @return nome
     */
    public String getNome() {
        return nomeAcao;
    }

    /**
     * O método getValor retorna o valor da Ação.
     *
     * @return valor
     */
    public double getValor() {
        return valorInicial;
    }

    /**
     * O método setNomeAcao, recebe o nome de uma determinada ação. No
     * construtor só inicia a ação com sua sigla, o nome é inicializado por esse
     * método, método esse que não muda o nome depois de inicializado, mesmo
     * tendo essa funcionalidade.
     *
     * @param nome muda o nome
     */
    public void setNomeAcao(String nome) {
        this.nomeAcao = nome;
    }

    /**
     * O método setValorInicial, recebe como parâmetro um valor e inicializa o
     * valor de uma determinada ação. Com esse método podemos aumentar e
     * diminuir o valor de uma ação.
     *
     * @param valor atualiza o valor
     */
    public void setValorInicial(double valor) {
        this.valorInicial = valor;
    }

    /**
     * O método equals Verefica se dois objetos Acao são iguals. Para que possam
     * ser iguais necessariamente precisam ter a mesma sigla,retornando true ou
     * false.
     *
     * @param o objeto a ser comparado
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Acao) {
            Acao b = (Acao) o;
            if (b.siglaAcao.equals(siglaAcao)) {
                return true;
            }
        }
        return false;
    }

    /**
     * O método compareTo compara duas Strings e verifica quals delas é a maior
     * ou se são iguais e retorna um inteiro para identificar isso. Exemplo
     * "allen" e "lais" o Método vai dizer que "lais" é maior que allen e
     * retornará um número maior que 0 se o objeto passado for allen.
     *
     * @param arg objeto a ser comparado
     * @return inteiro que indica quem é maior e 0 para igual.
     */
    @Override
    public int compareTo(Object arg) {
        return siglaAcao.compareTo(((Acao) arg).getSigla());
    }

}
