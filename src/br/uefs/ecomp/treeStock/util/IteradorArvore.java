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
     * O construtor inicializa um nó da Arvore ou seja sua raíz, em uma lista
     * encadeada, chamada pilha por ter o mesmo comportamento, e o current é
     * inicalizado para a esquerda.
     *
     * @param root a raíz da árvore desejada.
     */
    public IteradorArvore(Node root) {
        if (root != null) {
            pilha.insereInicio(root);
            current = root.getEsquerda();
        }
    }
    /**
     * O método hasNext verifica se a lista está vazia, enquanto a pilha não estiver vazia
     * vai sempre haver próximo na árvore.
     * @return true or flase
     */
    @Override
    public boolean hasNext() {
        return !pilha.estaVazia();
    }
    
    /**
     * O método next retorna o primeiro elemento da pilha que no caso é o mesmo
     * do próximo da Àrvore.
     * @return o proximo objeto da lista até o final.
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
     * Não foi necessário a implementação do método remover.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
