/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

import java.util.Iterator;

/**
 * A classe Lista Ordenada, � respondavel por ordenar os dados de forma
 * decrescente, os m�todos s�o iguais ao de Lista encadeada, com mudan�a no
 * parametro para comparable.
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class ListaOrdenada implements IListaOrdenada {

    private Celula primeiro;
    private Celula ultimo;

    /**
     * M�todo que verifica se a lista encadeada est� vazia. Dentro do retorno �
     * feito uma pergunta, se Primeiro "refer�ncia da lista � nulo" caso
     * verdadeiro retorna que tem um elemento na lista, caso falso, a lista
     * estar� vazia.
     *
     * @return true ou false
     */
    @Override
    public boolean estaVazia() {
        return primeiro == null;
    }

    /**
     * M�todo que verifica o tamanho atual da lista, o m�todo percorre a lista
     * contando cada elemento at� que n�o seja encontrado mais nenhum elemento o
     * tamanho obtido � retornado, informando o tamanho atual da lista.
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
     * O m�todo insere, recebe o elemento do tipo comparador, e o organiza em
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
     * O m�todo remover recebe o elemento Comparable, e quando esse elemento �
     * encontrado na lista ele � excluido da mesma.
     *
     * @param o elemento a ser removido
     * @return objeto comparable
     * @throws DadoNaoEncontradoException "n�o foi encontrado"
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
     * M�todo que recupera um elemento de uma determinada posi��o da lista
     * encadeada. O m�todo recebe um n�mero que faz com que uma refer�ncia
     * aulixiar avance at� encontrar a posi��o indicada retornando o objeto
     * desejado. Caso o elemento da lista n�o exista ou seja a sua posi��o �
     * retornado um nulo "vazio".
     *
     * @param index �ndice a recuperar
     * @return object
     * @throws br.uefs.ecomp.treeStock.util.DadoNaoEncontradoException "n�o foi encontrado"
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
        throw new DadoNaoEncontradoException("N�o existe esse elemento");
    }

    /**
     * M�todo que cria um objeto do tipo MyIterador que recebe como par�metro
     * uma lista encadeada, retornando esse interador "Detalhe o Iterador cont�m
     * dois m�todos o temPr�ximo e o proximo, explicado na classe MyIterador".
     *
     * @return Iterador
     */
    @Override
    public Iterator iterator() {
        MyIterator it = new MyIterator(primeiro);
        return (Iterator) it;
    }

    /**
     * O m�todo de buscar retorna um verdadeiro ou falso, caso o elemento
     * passado estava em alguma posi��o da lista.
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
     * O m�todo busca, ele pega o elemento desejado em uma determinada lista e o
     * retorna.
     *
     * @param obj objeto para retornar
     * @return objeto
     * @throws DadoNaoEncontradoException "n�o foi encontrado"
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
