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
     * Construtor que recebe um array e o tamanho, e faz uma cópia deste array
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
     * Método que verifica se o array já chegou na sua posição máxima que é 26.
     * Se o tamanho ainda não foi atingido ainda haverá lista para suas
     * iterações.
     *
     * @return true ou false
     */
    @Override
    public boolean hasNext() {
        return i < tamanho;
    }

    /**
     * Método que após verificação se temPróximo, retorna o objeto desejado.
     * Antes do retorno o auxiliar avança uma posição, e assim sucessivamente
     * até o temPróximo for falso (Caracteristica de não exixtir mais elementos
     * na lista de todos os vetores). Esse next é especial pois faz a iteração com
     * 26 listas em cada posição do vetor.
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
