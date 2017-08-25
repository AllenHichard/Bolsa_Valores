
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

import java.util.Iterator;

/**
 * A classe HashCode é responsável por implementar a interface IPares, é um tipo
 * de estrutura de dados que permite um engavetamento de informações. Cada hash
 * contém uma vetor de 26 posições e em cada posição tem uma lista, para guardar
 * informações. (26 listas pois cada uma é responsável por amazenar apenas um
 * tipo de inicial de uma ação)
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
     * em cada posição.
     */
    public HashCode() {
        hash = new ListaOrdenada[arraySize];
        for (int i = 0; i < 26; i++) {
            hash[i] = new ListaOrdenada();
        }
    }

    /**
     * o método setValor altera um valor da carteira, quando a ação é excluida,
     * aumentada seu valor, eu pela sua quantidade.
     *
     * @param a valor
     */
    public void setValor(double a) {
        valorTotal = valorTotal + a;
    }

    /**
     * O método getValor retorna o valor da Carteira.
     *
     * @return valor
     */
    public double getValor() {
        return valorTotal;
    }

    /**
     * O método setTamanho altera o quantidadeAcao da carteira, quando a ação é
     * excluida, aumenta ou decrementa a sua quantidade.
     *
     * @param a quantidadeAcao
     */
    public void setTamanho(int a) {
        quantidadeAcao = quantidadeAcao + a;
    }

    /**
     * O método getTamanho retorna o quantidadeAcao atual da carteira.
     *
     * @return quantidadeAcao
     */
    public int getTamanho() {
        return quantidadeAcao;
    }

    /**
     * O método calculaIndiceDaTabela, calula a prosição que ficará a ação na
     * tabela. Por exemplo a palavra lais, o método vai pegar a inicial l e
     * retornará um número correspondente a letra l, número esse que indicará o
     * posição do vetor deste nome ou de qualquer outro.
     *
     * @param palavra chave
     * @return índice do vetor
     */
    private int calculaIndiceDaTabela(Object palavra) {
        return palavra.toString().toLowerCase().charAt(0) % 26;
    }

    /**
     * O método contem, Simpliesmente analisa se o objeto passado existe na
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
     * O método put, faz o cadastro de uma Celula na tabela, ou seja é criado um
     * objeto apartir de uma chave(seu nome) e o valor(quantidade).
     *
     * @param chave sigla da ação
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
     * O método get, através de sua chave, retorna o valor correspondente a ela,
     * caso ela exista na tabela.
     *
     * @param chave sigla da ação
     * @return a quantidade de ações da chave passada.
     * @throws DadoNaoEncontradoException "dado não encontrado"
     */
    @Override
    public Object get(Object chave) throws DadoNaoEncontradoException {
        int indice = this.calculaIndiceDaTabela(chave);
        HashCelula a = (HashCelula) hash[indice].busca(chave);
        return a.getValor();
    }

    /**
     * O método set, altera um valor de uma chave.
     *
     * @param chave sigla da ação
     * @param valor quantidade
     * @throws DadoNaoEncontradoException "dado não encontrado"
     */
    public void set(Object chave, Object valor) throws DadoNaoEncontradoException {
        int indice = this.calculaIndiceDaTabela(chave);
        HashCelula a = (HashCelula) hash[indice].busca(chave);
        a.setValor(valor);

    }

    /**
     * O método remove, recebe uma chave, e o objeto que conter o mesmo nome
     * informado será excluido da tabela hash.
     *
     * @param chave sigla da ação
     * @throws DadoNaoEncontradoException "dado não encontrado"
     */
    @Override
    public void remove(Object chave) throws DadoNaoEncontradoException {
        int indice = this.calculaIndiceDaTabela(chave);
        hash[indice].remove(chave.toString());
    }

    /**
     * O método estáVazia, percorre as 26 listas do vetor pegando seus quantidadeAcaos,
     * e retornando se está vazia ou não(True ou false).
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
     * O iterador de Tabela Hash foi implementado voltada para utilização da interface
     * Não foi necessário aplicar esse iterador no problema.
     * @return iterador
     */
    @Override
    public Iterator iterator() {
        IteradorHash it = new IteradorHash(hash, arraySize);
        return (Iterator) it;
    }

}
