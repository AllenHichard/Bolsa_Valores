/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.model;

/**
 * A classe Acao � respons�vel para que a bolsa de valores exista. Pois � dela
 * que se pode adicionar e remover a��es, e � o seu valor que deixa o cliente
 * que a t�m com uma carteira mas valorizada.
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
     * @param siglaAcao cria a a��o
     */
    public Acao(String siglaAcao) {
        this.siglaAcao = siglaAcao;
    }

    /**
     * O m�todo getSigla retorna a sigla da A��o.
     *
     * @return sigla
     */
    public String getSigla() {
        return siglaAcao;
    }

    /**
     * O m�todo getNome retorna o nome da A��o.
     *
     * @return nome
     */
    public String getNome() {
        return nomeAcao;
    }

    /**
     * O m�todo getValor retorna o valor da A��o.
     *
     * @return valor
     */
    public double getValor() {
        return valorInicial;
    }

    /**
     * O m�todo setNomeAcao, recebe o nome de uma determinada a��o. No
     * construtor s� inicia a a��o com sua sigla, o nome � inicializado por esse
     * m�todo, m�todo esse que n�o muda o nome depois de inicializado, mesmo
     * tendo essa funcionalidade.
     *
     * @param nome muda o nome
     */
    public void setNomeAcao(String nome) {
        this.nomeAcao = nome;
    }

    /**
     * O m�todo setValorInicial, recebe como par�metro um valor e inicializa o
     * valor de uma determinada a��o. Com esse m�todo podemos aumentar e
     * diminuir o valor de uma a��o.
     *
     * @param valor atualiza o valor
     */
    public void setValorInicial(double valor) {
        this.valorInicial = valor;
    }

    /**
     * O m�todo equals Verefica se dois objetos Acao s�o iguals. Para que possam
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
     * O m�todo compareTo compara duas Strings e verifica quals delas � a maior
     * ou se s�o iguais e retorna um inteiro para identificar isso. Exemplo
     * "allen" e "lais" o M�todo vai dizer que "lais" � maior que allen e
     * retornar� um n�mero maior que 0 se o objeto passado for allen.
     *
     * @param arg objeto a ser comparado
     * @return inteiro que indica quem � maior e 0 para igual.
     */
    @Override
    public int compareTo(Object arg) {
        return siglaAcao.compareTo(((Acao) arg).getSigla());
    }

}
