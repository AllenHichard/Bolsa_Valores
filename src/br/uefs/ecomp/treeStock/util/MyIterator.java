/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

import java.util.Iterator;






/**
 * O Iterador � uma classe que permite ser utilizada para manipular uma lista
 * encadeada sem fazer nenhum altera��o tanto elementar, tanto em sua estrutura.
 *
 * @author Allen Hichard Marques dos Santos e Alisson vilas Verde
 */
public class MyIterator implements Iterator {

    private Celula no;

    /**
     * Construtor que recebe a lista encadeada, e faz uma c�pia desta lista para
     * um no do mesmo tipo
     *
     * @param primeiro primeiro elemento de uma lista.
     */
    public MyIterator(Celula primeiro) {
        no = primeiro;
    }

    /**
     * M�todo que verifica se a lista est� vazia no momento, retornando um
     * verdadeiro caso ainda tenha elementos nessa lista, e falso para n�o
     * existir mais elementos na lista.
     *
     * @return true ou false
     */
    @Override
    public boolean hasNext() {
        return no != null;
    }

    /**
     * M�todo que ap�s verifica��o se temPr�ximo, retorna o objeto desejado.
     * Antes do retorno o auxiliar avan�a uma posi��o, e assim sucessivamente
     * at� o temPr�ximo for falso (Caracteristica de n�o exixtir mais elementos
     * na lista.)
     *
     * @return objecto
     */
    @Override
    public Object next() {
        Object corrente = no.getObjeto();
        no = no.getProx();
        return corrente;

    }

   
}
