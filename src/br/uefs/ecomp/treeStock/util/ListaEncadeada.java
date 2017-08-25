
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

import java.util.Iterator;



/**
 * Classe implementada apartir dos m�todos pedidos na classe ILista.
 *
 * @author Allen Hichard Marques dos Santos e Alisson vilas Verde
 */
public class ListaEncadeada implements ILista{

    private Celula primeiro;

    public ListaEncadeada() {
        primeiro = null;
    }

    /**
     * M�todo que verifica se a lista encadeada est� vazia. Dentro do retorno �
     * feito uma pergunta, se Primeiro "refer�ncia da lista � nulo" caso
     * verdadeiro retorna que tem um elemento na lista, caso falso, a lista
     * estar� vazia.
     *
     * @return true ou false
     */
    @Override
    public boolean estaVazia() {
        return (primeiro == null);
    }

    /**
     * M�todo que verifica o tamanho atual da lista, o m�todo percorre a lista
     * contando cada elemento at� que n�o seja encontrado mais nenhum elemento o
     * tamanho obtido � retornado, informando o tamanho atual da lista.
     *
     * @return tamanho atual
     */
    
    @Override
    public int tamanho() {
        int Tamanho = 0;
        Celula aux = primeiro;
        while (aux != null) {
            Tamanho++;
            aux = aux.getProx();
        }
        return Tamanho;
    }

    /**
     * M�todo que recebe um objeto para inserir no in�cio de uma lista
     * encadeada. Ap�s receber cada objeto � criado uma nova c�lula. Nova c�lula
     * que recebe a lista atualizada at� o momento antes desse objeto ser
     * criado, atualizando a lista com todos os objetos que fica salvo na c�lula
     * e no campo pr�ximo desta c�lula.
     *
     * @param objeto novo elemento
     */
    @Override
    public void insereInicio(Object objeto) {
        Celula nova = new Celula(objeto);
        nova.setProx(primeiro);
        primeiro = nova;
    }

    /**
     * M�todo que recebe um objeto para inserir no final de uma lista encadeada.
     * Ap�s receber cada objeto � criado uma nova c�lula para este objeto e
     * criando o auxiliar que recebe a refer�ncia da lista Encadeada. Quando
     * essa refer�ncia for vazia "nulo", ser� inserido o primeiro objeto no
     * inicio da lista, que nesse primeiro caso tamb�m ser� o �ltimo. Depois que
     * a lista j� conter elementos o aulixiar que recebe o endere�o da lista,
     * percorre essa lista, deixando a refer�ncia principal intacta"no caso o
     * primeiro", quando esse auxiliar encontrar um elemento nulo ou vazio, ele
     * se posiciona naquele lugar e no seu pr�ximo � adicionado um vazio ou
     * nulo.
     *
     * @param objeto novo elemento
     */
    @Override
    public void insereFinal(Object objeto) {
        Celula nova = new Celula(objeto);
        Celula aux = primeiro;
        if (primeiro == null) {
            nova.setProx(primeiro);
            primeiro = nova;
        } else {
            while (aux.getProx() != null) {
                aux = aux.getProx();
            }
            aux.setProx(nova);

        }
    }

    /**
     * M�todo que remove o primeiro elemento da lista encadeada. � criado um
     * aulixiar que recebe a lista encadeada, ent�o o primeiro que � a
     * refer�ncia da lista, passa a ser o segundo elemento, retornando o
     * primeiro que o auxiliar guardava na sua refer�ncia. "Detalhe o Primeiro
     * quando passou a ser o segundo, esse segundo virou o primeiro, p�s ele n�o
     * passa mais a conhecer o elemento que o auxiliar guardou."
     *
     * @return Object
     */
    @Override
    public Object removeInicio() {
        Celula aux = primeiro;
        primeiro = primeiro.getProx();
        return aux.getObjeto();
    }

    /**
     * M�todo que remove o �ltimo elemento da lista encadeada. � criando dois
     * aulixiares para manipular a exclus�o de elemento do final desta lista.
     * Caso a lista encadeada esteja vazia, retorna um null "vazio". Se a lista
     * conter apenas um elemento, � retornado esse objeto e tornando a lista
     * vazia. Caso a lista tenha mais de um elemento, os auxiliares v�o
     * alternandos casas at� achar a �ltima c�lula da lista. "Detalhe os dois
     * auxiliares v�o alternando as casas sendo que um se posiciona atr�s do
     * outro, quando � encontrado o ultimo elemento e auxiliar anterior se torna
     * o �ltimo retornando o auxiliar posterior a ele".
     *
     * @return object
     */
    @Override
    public Object removeFinal() {
        Celula aux = primeiro;
        Celula aux2 = primeiro;
        if (primeiro == null) {
            return null;
        } else if (aux.getProx() == null) {
            primeiro = null;
            return aux.getObjeto();
        } else {
            while (aux.getProx() != null) {
                aux2 = aux;
                aux = aux.getProx();
            }
            aux2.setProx(null);
            return aux.getObjeto();
        }
    }

    /**
     * M�todo que recupera um elemento de uma determinada posi��o da lista
     * encadeada. O m�todo recebe um n�mero que faz com que uma refer�ncia
     * aulixiar avance at� encontrar a posi��o indicada retornando o objeto
     * desejado. Caso o elemento da lista n�o exista ou seja a sua posi��o �
     * retornado um nulo "vazio".
     *
     * @param num posi��o desejava do objeto a ser retornado
     * @return object
     */
    @Override
    public Object recupera(int num) throws DadoNaoEncontradoException {
        Celula aux = primeiro;
        if (num >= 0 && num < tamanho()) {
            for (int i = 0; i < num; i++) {
                aux = aux.getProx();
            }
            return aux.getObjeto();
        }
        throw new DadoNaoEncontradoException("N�o existe esse elemento");
    }

    /**
     * M�todo que imprime todos os elementos de uma lista encadeada. Esse m�todo
     * n�o tem par�metro e tamb�m n�o tem retorno, o funcionamento � de forma
     * direta, � criando um auxiliar da refer�ncia da lista, e enquanto a
     * posi��o que o auxiliar estiver for diferente de vazia, ele imprime sua
     * posi��o atual e avan�a para o pr�ximo elemento.
     */
    public void imprime() {
        Celula aux = primeiro;
        while (aux != null) {
            System.out.print(aux.getObjeto() + " ");
            aux = aux.getProx();
        }

    }

    /**
     * M�todo que cria um objeto do tipo MyIterador que recebe como par�metro
     * uma lista encadeada, retornando esse interador "Detalhe o Iterador cont�m
     * dois m�todos o temPr�ximo e o proximo, explicado na classe MyIterador".
     *
     * @return Iterador
     */
    
    @Override
    public Iterator iterator() {

        MyIterator it = new MyIterator(primeiro);
        return (Iterator) it;

    }

    public void insereOrdenado(Object objeto, int index) {
        Celula aux = primeiro;
        Celula aux1 = null;
        if (index == 0) {
            insereInicio(objeto);
        } else {
            for (int i = 0; i < index; i++) {
                aux1 = aux;
                aux = aux.getProx();
            }
            Celula novo = new Celula(objeto);
            aux1.setProx(novo);
            novo.setProx(aux);
        }

    }

  
 
}
