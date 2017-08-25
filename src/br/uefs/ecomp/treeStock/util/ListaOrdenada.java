/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

import java.util.Iterator;

/**
 * A classe Lista Ordenada, è respondavel por ordenar os dados de forma
 * decrescente, os métodos são iguais ao de Lista encadeada, com mudança no
 * parametro para comparable.
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class ListaOrdenada implements IListaOrdenada {

    private Celula primeiro;
    private Celula ultimo;

    /**
     * Método que verifica se a lista encadeada está vazia. Dentro do retorno é
     * feito uma pergunta, se Primeiro "referência da lista é nulo" caso
     * verdadeiro retorna que tem um elemento na lista, caso falso, a lista
     * estará vazia.
     *
     * @return true ou false
     */
    @Override
    public boolean estaVazia() {
        return primeiro == null;
    }

    /**
     * Método que verifica o tamanho atual da lista, o método percorre a lista
     * contando cada elemento até que não seja encontrado mais nenhum elemento o
     * tamanho obtido é retornado, informando o tamanho atual da lista.
     *
     * @return tamanho atual
     */
    @Override
    public int tamanho() {
        int Tamanho = 0;
        Celula aux = primeiro;
        while (aux != null) {
            Tamanho++;
            aux = aux.getProx();
        }
        return Tamanho;
    }

    /**
     * O método insere, recebe o elemento do tipo comparador, e o organiza em
     * uma lista em ordem decrescente de dados.
     *
     * @param o elemento a ser inserido
     */
    @Override
    public void insere(Comparable o) {
        Celula elemento = new Celula(o);
        if (primeiro == null) {
            primeiro = elemento;
            ultimo = elemento;
        } else {
            Celula corrente = primeiro;
            Celula anterior = null;
            while (corrente != null) {
                Comparable compare = (Comparable) corrente.getObjeto();
                if (compare.compareTo(o) < 0) {
                    if (anterior == null) {
                        elemento.setProx(primeiro);
                        primeiro = elemento;
                        return;
                    } else {
                        anterior.setProx(elemento);
                        elemento.setProx(corrente);
                        return;
                    }
                }
                anterior = corrente;
                corrente = corrente.getProx();
                if (corrente == null) {
                    anterior.setProx(elemento);
                    ultimo = elemento;
                }
            }
        }
    }

    /**
     * O método remover recebe o elemento Comparable, e quando esse elemento é
     * encontrado na lista ele é excluido da mesma.
     *
     * @param o elemento a ser removido
     * @return objeto comparable
     * @throws DadoNaoEncontradoException "não foi encontrado"
     */
    @Override
    public Comparable remove(Comparable o) throws DadoNaoEncontradoException {
        Celula corrente = primeiro, anterior = null;
        Comparable compare;
        while (corrente != null) {
            compare = (Comparable) corrente.getObjeto();
            if (compare.compareTo(o) == 0) {
                if (corrente == primeiro) {
                    primeiro = primeiro.getProx();
                    if (primeiro == null) {
                        ultimo = null;
                    }
                    return (Comparable) corrente.getObjeto();
                } else {
                    anterior.setProx(corrente.getProx());
                    if (corrente == ultimo) {
                        ultimo = anterior;
                    }
                    return (Comparable) corrente.getObjeto();
                }
            }
            anterior = corrente;
            corrente = corrente.getProx();
        }
        return null;
    }

    /**
     * Método que recupera um elemento de uma determinada posição da lista
     * encadeada. O método recebe um número que faz com que uma referência
     * aulixiar avance até encontrar a posição indicada retornando o objeto
     * desejado. Caso o elemento da lista não exista ou seja a sua posição é
     * retornado um nulo "vazio".
     *
     * @param index índice a recuperar
     * @return object
     * @throws br.uefs.ecomp.treeStock.util.DadoNaoEncontradoException "não foi encontrado"
     */
    @Override
    public Comparable recupera(int index) throws DadoNaoEncontradoException {
        Celula aux = primeiro;
        if (index >= 0 && index < tamanho()) {
            for (int i = 0; i < index; i++) {
                aux = aux.getProx();
            }
            return (Comparable) aux.getObjeto();
        }
        throw new DadoNaoEncontradoException("Não existe esse elemento");
    }

    /**
     * Método que cria um objeto do tipo MyIterador que recebe como parâmetro
     * uma lista encadeada, retornando esse interador "Detalhe o Iterador contém
     * dois métodos o temPróximo e o proximo, explicado na classe MyIterador".
     *
     * @return Iterador
     */
    @Override
    public Iterator iterator() {
        MyIterator it = new MyIterator(primeiro);
        return (Iterator) it;
    }

    /**
     * O método de buscar retorna um verdadeiro ou falso, caso o elemento
     * passado estava em alguma posição da lista.
     *
     * @param obj objeto para buscar
     * @return true or false
     */
    public boolean buscar(Object obj) {
        Celula corrente = primeiro;

        while (corrente != null) {
            HashCelula a = (HashCelula) corrente.getObjeto();
            if (a.compareTo(obj) == 0) {
                return true;
            }
            corrente = corrente.getProx();
        }
        return false;

    }

    /**
     * O método busca, ele pega o elemento desejado em uma determinada lista e o
     * retorna.
     *
     * @param obj objeto para retornar
     * @return objeto
     * @throws DadoNaoEncontradoException "não foi encontrado"
     */
    public Object busca(Object obj) throws DadoNaoEncontradoException {
        Celula corrente = primeiro;
        while (corrente != null) {
            HashCelula a = (HashCelula) corrente.getObjeto();
            if ((a.compareTo(obj) == 0)) {

                return corrente.getObjeto();
            }
            corrente = corrente.getProx();

        }
        throw new DadoNaoEncontradoException("nao encontrado");
    }

}
