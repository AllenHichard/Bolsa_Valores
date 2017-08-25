/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.model;


import br.uefs.ecomp.treeStock.util.HashCode;


/**
 * A classe Carteira, é responsábel por ter um HashCode de listas Ordenadas de
 * Ações, sendo que cada cliente tem uma carteira, que guarda todas as ações
 * correspondentes ao cliente.
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class Carteira {

    private final HashCode carteira;
    /**
     * O construtor da Classe carteira, só inicializa o HashCode.
     */
    public Carteira() {
        carteira = new HashCode();
    }
    /**
     * O Método getAcao retorna a Carteira que contém ações.
     * @return  tabela hash.
     */
    public HashCode getAcao() {
        return carteira;
    }

}
