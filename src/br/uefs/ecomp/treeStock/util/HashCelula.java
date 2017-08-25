/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uefs.ecomp.treeStock.util;



/**
 * A classe HashCelula é o nó da Tabela Hash, Classe explicada na sua
 * implements IPar.
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class HashCelula implements IPar, Comparable {
    
    private final Object chave;
    private Object valor;
    
    public HashCelula(Object chave, Object valor){
        this.chave = chave;
        this.valor = valor;
    }
    
    @Override
    public Object getChave() {
        return chave;
    }

    @Override
    public Object getValor() {
        return valor;
    }
    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    @Override
    public int compareTo(Object arg0) {
            return chave.toString().compareTo(arg0.toString());
    }
    
    
}
