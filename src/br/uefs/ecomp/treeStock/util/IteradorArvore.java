/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

import java.util.Iterator;

/**
 * O iteradorArvore tem a finalizade de iterar sobre uma arvore, enquanto haver
 * elementos a serem iterados.
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class IteradorArvore implements Iterator {

    private Node current;
    private final ListaEncadeada pilha = new ListaEncadeada();

    /**
     * O construtor inicializa um n� da Arvore ou seja sua ra�z, em uma lista
     * encadeada, chamada pilha por ter o mesmo comportamento, e o current �
     * inicalizado para a esquerda.
     *
     * @param root a ra�z da �rvore desejada.
     */
    public IteradorArvore(Node root) {
        if (root != null) {
            pilha.insereInicio(root);
            current = root.getEsquerda();
        }
    }
    /**
     * O m�todo hasNext verifica se a lista est� vazia, enquanto a pilha n�o estiver vazia
     * vai sempre haver pr�ximo na �rvore.
     * @return true or flase
     */
    @Override
    public boolean hasNext() {
        return !pilha.estaVazia();
    }
    
    /**
     * O m�todo next retorna o primeiro elemento da pilha que no caso � o mesmo
     * do pr�ximo da �rvore.
     * @return o proximo objeto da lista at� o final.
     */
    @Override
    public Object next() {

        while (current != null) {
            pilha.insereInicio(current);
            current = current.getEsquerda();
        }
        current = (Node) pilha.removeInicio();
        Node aux = current;

        if (current.getDireita() != null) {
            pilha.insereInicio(current.getDireita());
            current = current.getDireita();
            current = current.getEsquerda();
        } else {
            current = null;
        }

        return aux.getElemento();
    }
    
    /**
     * N�o foi necess�rio a implementa��o do m�todo remover.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
