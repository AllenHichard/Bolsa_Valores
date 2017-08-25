/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

/**
 * Classe C�lula, respons�vel pela cria��o de cada elemento da lista encadeada,
 * recebendo um objeto para cria��o individual de casa elemento.
 *
 * @author Allen Hichard Marques dos Santos e Alisson vilas Verde
 */
public class Celula {

    private Object objeto;
    private Celula prox;

    /**
     * Construtor da Classe respons�vel pela cria��o da nova C�lula, onde recebe
     * um objeto e cria uma c�lula para aquele determinado objeto.
     *
     * @param objeto novo objeto
     */
    public Celula(Object objeto) {
        this.objeto = objeto;
        this.prox = null;
    }

     /**
     * m�todo que devido o encapsulamento, � necess�rio para utiliza��o deste
     * atributo, atributo esse privado.
     *
     * @return Object.
     */
    public Object getObjeto() {
        return objeto;
    }

    /**
     * M�todo que recebe um novo objeto fazendo altera��o diretamento no
     * atributo privado desta classe.
     *
     * @param objeto muda o objeto anterior pelo passado
     */
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    /**
     * M�todo que devido o encapsulamento, precisa retornar a refer�ncia do
     * pr�ximo da lista encadeada
     *
     * @return Celula.
     */
    public Celula getProx() {
        return prox;
    }

    /**
     * Metodo que permite a altera��o do pr�ximo elemento da lista, permitindo
     * manipular a lista de diferentes formas.
     *
     * @param Prox muda o prozimo elemento da lista
     */
    public void setProx(Celula Prox) {
        this.prox = Prox;
    }

     /**
     * M�todo que imprime apenas uma c�lula, utilizada mas para testes de
     * remo��o e inser��o. M�todo que n�o recebe par�metro e n�o retorna
     * informa��es.
     */
    public void displayLink() {
        System.out.println(objeto);
    }
}
