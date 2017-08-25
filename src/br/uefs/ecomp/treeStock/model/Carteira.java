/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.model;


import br.uefs.ecomp.treeStock.util.HashCode;


/**
 * A classe Carteira, � respons�bel por ter um HashCode de listas Ordenadas de
 * A��es, sendo que cada cliente tem uma carteira, que guarda todas as a��es
 * correspondentes ao cliente.
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class Carteira {

    private final HashCode carteira;
    /**
     * O construtor da Classe carteira, s� inicializa o HashCode.
     */
    public Carteira() {
        carteira = new HashCode();
    }
    /**
     * O M�todo getAcao retorna a Carteira que cont�m a��es.
     * @return  tabela hash.
     */
    public HashCode getAcao() {
        return carteira;
    }

}
