
package br.uefs.ecomp.treeStock.util;



/**
 * Classe Node, responsável pela criação de cada elemento da Árvore,
 * recebendo um objeto para criação individual de casa elemento.
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class Node {
    
    private Comparable elemento;
    private Node direita = null;
    private Node esquerda = null;
    private int hE , hD;
    
   /**
     * Construtor da Classe responsável pela criação do novo node, onde recebe
     * um comparable e cria uma node para aquele determinado objeto.
     *
     * @param elemento elemento a ser criado.
     */
    public Node(Comparable elemento){
        this.elemento = elemento;
    } 
    
    /**
     * @return o elemento
     */
    public Comparable getElemento() {
        return elemento;
    }

    /**
     * @return o elemento direita
     */
    public Node getDireita() {
        return direita;
    }

    /**
     * muda o elemento da direita.
     * @param direita novo elemento a substituir o direito
     */
    public void setDireita(Node direita) {
        this.direita = direita;
    }

    /**
     * @return o elemento esquerdo
     */
    public Node getEsquerda() {
        return esquerda;
    }

    /**
     * muda o elemento da esquerda.
     * @param esquerda novo elemento a substituir o esquerdo
     */
    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * @return altura esquerda
     */
    public int gethE() {
        return hE;
    }

    /** 
     * muda a altura esquerda
     * @param hE altura esquerda
     */
    public void sethE(int hE) {
        this.hE = hE;
    }

    /**
     * @return altura direita
     */
    public int gethD() {
        return hD;
    }

    /**
     * muda a altura direita
     * @param hD altura direita
     */
    public void sethD(int hD) {
        this.hD = hD;
    }
    
}
