
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

import java.util.Iterator;

/**
 * A classe HashCode � respons�vel por implementar a interface IPares, � um tipo
 * de estrutura de dados que permite um engavetamento de informa��es. Cada hash
 * cont�m uma vetor de 26 posi��es e em cada posi��o tem uma lista, para guardar
 * informa��es. (26 listas pois cada uma � respons�vel por amazenar apenas um
 * tipo de inicial de uma a��o)
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class HashCode implements IPares {

    private final ListaOrdenada[] hash;
    private final int arraySize = 26;
    private double valorTotal = 0;
    private int quantidadeAcao = 0;

    /**
     * O Construtor da classe HashCode inicializa o quantidadeAcao do vetor e as listas
     * em cada posi��o.
     */
    public HashCode() {
        hash = new ListaOrdenada[arraySize];
        for (int i = 0; i < 26; i++) {
            hash[i] = new ListaOrdenada();
        }
    }

    /**
     * o m�todo setValor altera um valor da carteira, quando a a��o � excluida,
     * aumentada seu valor, eu pela sua quantidade.
     *
     * @param a valor
     */
    public void setValor(double a) {
        valorTotal = valorTotal + a;
    }

    /**
     * O m�todo getValor retorna o valor da Carteira.
     *
     * @return valor
     */
    public double getValor() {
        return valorTotal;
    }

    /**
     * O m�todo setTamanho altera o quantidadeAcao da carteira, quando a a��o �
     * excluida, aumenta ou decrementa a sua quantidade.
     *
     * @param a quantidadeAcao
     */
    public void setTamanho(int a) {
        quantidadeAcao = quantidadeAcao + a;
    }

    /**
     * O m�todo getTamanho retorna o quantidadeAcao atual da carteira.
     *
     * @return quantidadeAcao
     */
    public int getTamanho() {
        return quantidadeAcao;
    }

    /**
     * O m�todo calculaIndiceDaTabela, calula a prosi��o que ficar� a a��o na
     * tabela. Por exemplo a palavra lais, o m�todo vai pegar a inicial l e
     * retornar� um n�mero correspondente a letra l, n�mero esse que indicar� o
     * posi��o do vetor deste nome ou de qualquer outro.
     *
     * @param palavra chave
     * @return �ndice do vetor
     */
    private int calculaIndiceDaTabela(Object palavra) {
        return palavra.toString().toLowerCase().charAt(0) % 26;
    }

    /**
     * O m�todo contem, Simpliesmente analisa se o objeto passado existe na
     * tabela.
     *
     * @param palavra chave
     * @return true or false
     */
    public boolean contem(Object palavra) {
        int indice = this.calculaIndiceDaTabela(palavra);
        return hash[indice].buscar(palavra);
    }

    /**
     * O m�todo put, faz o cadastro de uma Celula na tabela, ou seja � criado um
     * objeto apartir de uma chave(seu nome) e o valor(quantidade).
     *
     * @param chave sigla da a��o
     * @param valor quantidade
     */
    @Override
    public void put(Object chave, Object valor) {
        HashCelula celula = new HashCelula(chave, valor);
        if (!contem(celula)) {
            int indice = this.calculaIndiceDaTabela(chave);
            hash[indice].insere(celula);
        }
    }

    /**
     * O m�todo get, atrav�s de sua chave, retorna o valor correspondente a ela,
     * caso ela exista na tabela.
     *
     * @param chave sigla da a��o
     * @return a quantidade de a��es da chave passada.
     * @throws DadoNaoEncontradoException "dado n�o encontrado"
     */
    @Override
    public Object get(Object chave) throws DadoNaoEncontradoException {
        int indice = this.calculaIndiceDaTabela(chave);
        HashCelula a = (HashCelula) hash[indice].busca(chave);
        return a.getValor();
    }

    /**
     * O m�todo set, altera um valor de uma chave.
     *
     * @param chave sigla da a��o
     * @param valor quantidade
     * @throws DadoNaoEncontradoException "dado n�o encontrado"
     */
    public void set(Object chave, Object valor) throws DadoNaoEncontradoException {
        int indice = this.calculaIndiceDaTabela(chave);
        HashCelula a = (HashCelula) hash[indice].busca(chave);
        a.setValor(valor);

    }

    /**
     * O m�todo remove, recebe uma chave, e o objeto que conter o mesmo nome
     * informado ser� excluido da tabela hash.
     *
     * @param chave sigla da a��o
     * @throws DadoNaoEncontradoException "dado n�o encontrado"
     */
    @Override
    public void remove(Object chave) throws DadoNaoEncontradoException {
        int indice = this.calculaIndiceDaTabela(chave);
        hash[indice].remove(chave.toString());
    }

    /**
     * O m�todo est�Vazia, percorre as 26 listas do vetor pegando seus quantidadeAcaos,
     * e retornando se est� vazia ou n�o(True ou false).
     *
     * @return true or false
     */
    @Override
    public boolean estaVazia() {
        int quantidadeAcaoTotal = 0;
        for (int i = 0; i < arraySize; i++) {
            if (hash[i].tamanho()!= 0) {
                quantidadeAcaoTotal = quantidadeAcaoTotal + hash[i].tamanho();
            }
        }
        return quantidadeAcaoTotal == 0;

    }
    /**
     * O iterador de Tabela Hash foi implementado voltada para utiliza��o da interface
     * N�o foi necess�rio aplicar esse iterador no problema.
     * @return iterador
     */
    @Override
    public Iterator iterator() {
        IteradorHash it = new IteradorHash(hash, arraySize);
        return (Iterator) it;
    }

}
