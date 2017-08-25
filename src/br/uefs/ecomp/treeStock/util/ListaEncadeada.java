
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

import java.util.Iterator;



/**
 * Classe implementada apartir dos métodos pedidos na classe ILista.
 *
 * @author Allen Hichard Marques dos Santos e Alisson vilas Verde
 */
public class ListaEncadeada implements ILista{

    private Celula primeiro;

    public ListaEncadeada() {
        primeiro = null;
    }

    /**
     * Método que verifica se a lista encadeada está vazia. Dentro do retorno é
     * feito uma pergunta, se Primeiro "referência da lista é nulo" caso
     * verdadeiro retorna que tem um elemento na lista, caso falso, a lista
     * estará vazia.
     *
     * @return true ou false
     */
    @Override
    public boolean estaVazia() {
        return (primeiro == null);
    }

    /**
     * Método que verifica o tamanho atual da lista, o método percorre a lista
     * contando cada elemento até que não seja encontrado mais nenhum elemento o
     * tamanho obtido é retornado, informando o tamanho atual da lista.
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
     * Método que recebe um objeto para inserir no início de uma lista
     * encadeada. Após receber cada objeto é criado uma nova célula. Nova célula
     * que recebe a lista atualizada até o momento antes desse objeto ser
     * criado, atualizando a lista com todos os objetos que fica salvo na célula
     * e no campo próximo desta célula.
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
     * Método que recebe um objeto para inserir no final de uma lista encadeada.
     * Após receber cada objeto é criado uma nova célula para este objeto e
     * criando o auxiliar que recebe a referência da lista Encadeada. Quando
     * essa referência for vazia "nulo", será inserido o primeiro objeto no
     * inicio da lista, que nesse primeiro caso também será o último. Depois que
     * a lista já conter elementos o aulixiar que recebe o endereço da lista,
     * percorre essa lista, deixando a referência principal intacta"no caso o
     * primeiro", quando esse auxiliar encontrar um elemento nulo ou vazio, ele
     * se posiciona naquele lugar e no seu próximo é adicionado um vazio ou
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
     * Método que remove o primeiro elemento da lista encadeada. É criado um
     * aulixiar que recebe a lista encadeada, então o primeiro que é a
     * referência da lista, passa a ser o segundo elemento, retornando o
     * primeiro que o auxiliar guardava na sua referência. "Detalhe o Primeiro
     * quando passou a ser o segundo, esse segundo virou o primeiro, pós ele não
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
     * Método que remove o último elemento da lista encadeada. É criando dois
     * aulixiares para manipular a exclusão de elemento do final desta lista.
     * Caso a lista encadeada esteja vazia, retorna um null "vazio". Se a lista
     * conter apenas um elemento, é retornado esse objeto e tornando a lista
     * vazia. Caso a lista tenha mais de um elemento, os auxiliares vão
     * alternandos casas até achar a última célula da lista. "Detalhe os dois
     * auxiliares vão alternando as casas sendo que um se posiciona atrás do
     * outro, quando é encontrado o ultimo elemento e auxiliar anterior se torna
     * o último retornando o auxiliar posterior a ele".
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
     * Método que recupera um elemento de uma determinada posição da lista
     * encadeada. O método recebe um número que faz com que uma referência
     * aulixiar avance até encontrar a posição indicada retornando o objeto
     * desejado. Caso o elemento da lista não exista ou seja a sua posição é
     * retornado um nulo "vazio".
     *
     * @param num posição desejava do objeto a ser retornado
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
        throw new DadoNaoEncontradoException("Não existe esse elemento");
    }

    /**
     * Método que imprime todos os elementos de uma lista encadeada. Esse método
     * não tem parâmetro e também não tem retorno, o funcionamento é de forma
     * direta, é criando um auxiliar da referência da lista, e enquanto a
     * posição que o auxiliar estiver for diferente de vazia, ele imprime sua
     * posição atual e avança para o próximo elemento.
     */
    public void imprime() {
        Celula aux = primeiro;
        while (aux != null) {
            System.out.print(aux.getObjeto() + " ");
            aux = aux.getProx();
        }

    }

    /**
     * Método que cria um objeto do tipo MyIterador que recebe como parâmetro
     * uma lista encadeada, retornando esse interador "Detalhe o Iterador contém
     * dois métodos o temPróximo e o proximo, explicado na classe MyIterador".
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
