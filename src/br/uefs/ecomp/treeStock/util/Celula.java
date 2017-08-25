/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

/**
 * Classe Célula, responsável pela criação de cada elemento da lista encadeada,
 * recebendo um objeto para criação individual de casa elemento.
 *
 * @author Allen Hichard Marques dos Santos e Alisson vilas Verde
 */
public class Celula {

    private Object objeto;
    private Celula prox;

    /**
     * Construtor da Classe responsável pela criação da nova Célula, onde recebe
     * um objeto e cria uma célula para aquele determinado objeto.
     *
     * @param objeto novo objeto
     */
    public Celula(Object objeto) {
        this.objeto = objeto;
        this.prox = null;
    }

     /**
     * método que devido o encapsulamento, é necessário para utilização deste
     * atributo, atributo esse privado.
     *
     * @return Object.
     */
    public Object getObjeto() {
        return objeto;
    }

    /**
     * Método que recebe um novo objeto fazendo alteração diretamento no
     * atributo privado desta classe.
     *
     * @param objeto muda o objeto anterior pelo passado
     */
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    /**
     * Método que devido o encapsulamento, precisa retornar a referência do
     * próximo da lista encadeada
     *
     * @return Celula.
     */
    public Celula getProx() {
        return prox;
    }

    /**
     * Metodo que permite a alteração do próximo elemento da lista, permitindo
     * manipular a lista de diferentes formas.
     *
     * @param Prox muda o prozimo elemento da lista
     */
    public void setProx(Celula Prox) {
        this.prox = Prox;
    }

     /**
     * Método que imprime apenas uma célula, utilizada mas para testes de
     * remoção e inserção. Método que não recebe parâmetro e não retorna
     * informações.
     */
    public void displayLink() {
        System.out.println(objeto);
    }
}
