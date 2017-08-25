/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

import java.util.Iterator;

/**
 * O iteradorHash foi implementado para iterar sobre uma tabela hash, mas pouco
 * utilizado no problema
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class IteradorHash implements Iterator {

    private final ListaOrdenada[] hash;
    private final int tamanho;
    private int i=0;
    private Iterator it;

    /**
     * Construtor que recebe um array e o tamanho, e faz uma c�pia deste array
     * para um no do mesmo tipo, o mesmo para seu tamanho
     *
     * @param hashCode array de listas da tabela hash.
     * @param tamanho tamanho do vetor
     */
    public IteradorHash(ListaOrdenada[] hashCode, int tamanho) {
        hash = hashCode;
        this.tamanho = tamanho;
        it = hash[0].iterator();
    }

    /**
     * M�todo que verifica se o array j� chegou na sua posi��o m�xima que � 26.
     * Se o tamanho ainda n�o foi atingido ainda haver� lista para suas
     * itera��es.
     *
     * @return true ou false
     */
    @Override
    public boolean hasNext() {
        return i < tamanho;
    }

    /**
     * M�todo que ap�s verifica��o se temPr�ximo, retorna o objeto desejado.
     * Antes do retorno o auxiliar avan�a uma posi��o, e assim sucessivamente
     * at� o temPr�ximo for falso (Caracteristica de n�o exixtir mais elementos
     * na lista de todos os vetores). Esse next � especial pois faz a itera��o com
     * 26 listas em cada posi��o do vetor.
     *
     * @return objecto
     */
    @Override
    public Object next() {
        while(hasNext()){
            while (it.hasNext()) {
                Object o = it.next();
                return o;
            }
            i++;
            if(hasNext())
            it = hash[i].iterator();
        }
        return null;
    }

}
