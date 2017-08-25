package br.uefs.ecomp.treeStock.util;

import java.util.Iterator;

/**
 * A classe ArvoreAVL � uma classe criada para atender ao requesito pedido no
 * problema, que seria uma velocidade de log de n na base dois, ela garante essa
 * velocidade tanto na busca, remo��o e inser��o.
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class ArvoreAVL implements IArvore {

    private Node raiz;

    /**
     * O construtor da arvore inicializa a raiz vazia.
     */
    public ArvoreAVL() {
        raiz = null;
    }

    /**
     * O m�todo buscar, � respons�vel por busca o elemento em qualquer posi��o
     * da �rvore e retorn�-lo se encontrado.
     *
     * @param c elemento a buscar
     * @return objeto
     * @throws DadoNaoEncontradoException "dado n�o encontado"
     */
    @Override
    public Object buscar(Comparable c) throws DadoNaoEncontradoException {
        Node corrente = raiz;
        while (corrente != null) {
            if (corrente.getElemento().compareTo(c) == 0) {
                return corrente.getElemento();
            }
            if (corrente.getElemento().compareTo(c) < 0) {
                corrente = corrente.getDireita();
            } else {
                corrente = corrente.getEsquerda();
            }
        }
        throw new DadoNaoEncontradoException("n�o foi encontrado nenhum dado");
    }

    /**
     * O m�todo inserir � o m�todo que insere o elemento na �rvore, para isso
     * ela chama um m�todo auxiliar add, que insere recursivamente na posi��o
     * correta na �rvore. Quando a �rvore ficar desbalanceada ele
     * altomaticamente balancea.
     *
     * @param c elemento a ser inserido
     * @throws DadoDuplicadoException "dado n�o encontado"
     */
    @Override
    public void inserir(Comparable c) throws DadoDuplicadoException {
        add(raiz, c, null);
    }

    /**
     * O m�todo remover, remove o elemento da arvore, para isso usa um m�todo
     * auxiliar removerRecursivo.
     *
     * @param c elemento a ser removido
     * @throws DadoNaoEncontradoException "dado n�o encontado"
     */
    @Override
    public void remover(Comparable c) throws DadoNaoEncontradoException {
        if (buscar(c) != null) {
            removerRecurssao(c, raiz, null);
            calcularAltura(raiz, null);
        }
    }

    /**
     * M�todo auxiliar que faz a inser��o de forma balanceada no sistema.
     *
     * @param corrente inicialmente � raiz
     * @param c inicialmente � o novo elemento
     * @param pai inicialmente � nulo
     * @throws DadoDuplicadoException "dado n�o encontado"
     */
    private void add(Node corrente, Comparable c, Node pai) throws DadoDuplicadoException {
        if (raiz == null) {
            raiz = new Node(c);
        } else if (corrente.getElemento().compareTo(c) == 0 && corrente.getDireita() == null) {
            throw new DadoDuplicadoException("Esse elemento j� existe");
        } else if (corrente.getElemento().compareTo(c) == 0 && corrente.getEsquerda() == null) {
            throw new DadoDuplicadoException("Esse elemento j� existe");
        } else if (corrente.getElemento().compareTo(c) < 0 && corrente.getDireita() == null) {
            corrente.setDireita(new Node(c));
            calculaAlturaLadoDireito(corrente);
        } else if (corrente.getElemento().compareTo(c) > 0 && corrente.getEsquerda() == null) {
            corrente.setEsquerda(new Node(c));
            calcularAlturaLadoEsquerdo(corrente);
        } else if (corrente.getElemento().compareTo(c) < 0) {
            add(corrente.getDireita(), c, corrente);
            calculaAlturaLadoDireito(corrente);
            balanceamento(corrente, pai);
        } else {
            add(corrente.getEsquerda(), c, corrente);
            calcularAlturaLadoEsquerdo(corrente);
            balanceamento(corrente, pai);
        }
    }

    /**
     * O m�todo calculaAlturaLado Esquerdo, cacula a altura da �rvore tendendo
     * para o lado esquerdo.
     *
     * @param aux elemento que auxiliar a contar o lado esquerdo
     */
    private void calcularAlturaLadoEsquerdo(Node aux) {
        if (aux.getEsquerda() == null) {
            aux.sethE(0);
        } else {
            if (aux.getEsquerda().gethD() > aux.getEsquerda().gethE()) {
                aux.sethE(aux.getEsquerda().gethD() + 1);
            } else {
                aux.sethE(aux.getEsquerda().gethE() + 1);
            }
        }
    }

    /**
     * O m�todo calculaAlturaLado Direito, cacula a altura da �rvore tendendo
     * para o lado direito.
     *
     * @param aux elemento que auxiliar a contar o lado direito
     */
    private void calculaAlturaLadoDireito(Node aux) {
        if (aux.getDireita() == null) {
            aux.sethD(0);
        } else {
            if (aux.getDireita().gethD() > aux.getDireita().gethE()) {
                aux.sethD(aux.getDireita().gethD() + 1);
            } else {
                aux.sethD(aux.getDireita().gethE() + 1);
            }
        }
    }

    /**
     * O m�todo de balanceamento � respons�bel por identificar o fator de
     * balanceamento apartir do c�lculo de sua altura, para fazer a rota��o
     * simples ou dupla, para isso o m�todo tem dois m�todos auxiliares que
     * fazem a rota��o tanto simples quanto dupla.
     *
     * @param corrente recursivo
     * @param pai recursivo
     */
    private void balanceamento(Node corrente, Node pai) {
        int fatorBalanceamento, fatorProximo;
        fatorBalanceamento = corrente.gethD() - corrente.gethE();
        if (fatorBalanceamento == 2) {
            fatorProximo = corrente.getDireita().gethD() - corrente.getDireita().gethE();
            if (fatorProximo >= 0) {
                rotacaoEsquerda(corrente, pai);
            } else {
                rotacaoDireita(corrente.getDireita(), corrente);
                rotacaoEsquerda(corrente, pai);
            }
        } else if (fatorBalanceamento == -2) {
            fatorProximo = corrente.getEsquerda().gethD() - corrente.getEsquerda().gethE();
            if (fatorProximo <= 0) {
                rotacaoDireita(corrente, pai);
            } else {
                rotacaoEsquerda(corrente.getEsquerda(), corrente);
                rotacaoDireita(corrente, pai);
            }
        }
    }

    /**
     * O m�todo rotacaoEsquerda, balancea a arvore rotacionando ela para a
     * esquerda, deixando-a balanceada
     *
     * @param corrente recursivo
     * @param father recursivo
     */
    private void rotacaoEsquerda(Node corrente, Node father) {

        Node a1 = corrente.getDireita();
        Node a2 = a1.getEsquerda();
        corrente.setDireita(a2);
        a1.setEsquerda(corrente);
        if (corrente == raiz) {
            raiz = a1;
        } else {
            if (father.getEsquerda() == corrente) {
                father.setEsquerda(a1);
            } else {
                father.setDireita(a1);
            }
        }
        calculaAlturaLadoDireito(corrente);
        calcularAlturaLadoEsquerdo(corrente);
        calculaAlturaLadoDireito(a1);
        calcularAlturaLadoEsquerdo(a1);
    }

    /**
     * O m�todo rotacaoDireita, balancea a arvore rotacionando ela para a
     * direita, deixando-a balanceada
     *
     * @param corrente recursivo
     * @param father recursivo
     */
    private void rotacaoDireita(Node corrente, Node father) {

        Node a1 = corrente.getEsquerda();
        Node a2 = a1.getDireita();
        corrente.setEsquerda(a2);
        a1.setDireita(corrente);
        if (corrente == raiz) {
            raiz = a1;
        } else {
            if (father.getDireita() == corrente) {
                father.setDireita(a1);
            } else {
                father.setEsquerda(a1);
            }
        }
        calculaAlturaLadoDireito(corrente);
        calcularAlturaLadoEsquerdo(corrente);
        calculaAlturaLadoDireito(a1);
        calcularAlturaLadoEsquerdo(a1);
    }

    /**
     * O m�todo removerRecurssao � respons�vel por auxiliar o metodo de remover
     * a retirar um elemento da lista.
     *
     * @param c novo elemento
     * @param aux ra�z
     * @param pai nulo
     */
    private void removerRecurssao(Comparable c, Node aux, Node pai) {

        if (raiz.getElemento().compareTo(c) == 0) {
            deletarRoot();
        } else if (aux.getElemento().compareTo(c) == 0 && aux.getDireita() != null && aux.getEsquerda() != null) {
            Node folha = buscarFolha(aux);
            Node paiFolha = buscarPaiFolha(aux);
            if (pai.getDireita() == aux) {
                pai.setDireita(folha);
            } else {
                pai.setEsquerda(folha);
            }
            folha.setEsquerda(aux.getEsquerda());
            if (aux != paiFolha) {
                folha.setDireita(aux.getDireita());
                if (paiFolha.getDireita() == folha) {
                    paiFolha.setDireita(null);
                } else {
                    paiFolha.setEsquerda(null);
                }
            }
        } else if (aux.getElemento().compareTo(c) == 0 && aux.getDireita() == null && aux.getEsquerda() == null) {
            if (pai.getDireita() == aux) {
                pai.setDireita(null);
            } else {
                pai.setEsquerda(null);
            }
        } else if (aux.getElemento().compareTo(c) == 0 && (aux.getDireita() != null || aux.getEsquerda() != null)) {
            if (aux.getDireita() != null) {
                if (pai.getDireita() == aux) {
                    pai.setDireita(aux.getDireita());
                } else {
                    pai.setEsquerda(aux.getDireita());
                }
            } else {
                if (pai.getDireita() == aux) {
                    pai.setDireita(aux.getEsquerda());
                } else {
                    pai.setEsquerda(aux.getEsquerda());
                }
            }
        } else if (aux.getElemento().compareTo(c) < 0) {
            if (aux.getDireita() != null) {
                pai = aux;
                removerRecurssao(c, aux.getDireita(), pai);
            }
        } else {
            if (aux.getEsquerda() != null) {
                pai = aux;
                removerRecurssao(c, aux.getEsquerda(), pai);
            }
        }
    }

    /**
     * O m�todo deletarRoot deleta a raiz da arvore.
     */
    public void deletarRoot() {

        if (raiz.getDireita() == null && raiz.getEsquerda() == null) {
            raiz = null;
        } else if (raiz.getDireita() != null && raiz.getEsquerda() != null) {
            Node folha = buscarFolha(raiz);
            folha.setEsquerda(raiz.getEsquerda());
            raiz = raiz.getDireita();
        } else if (raiz.getDireita() != null) {
            raiz = raiz.getDireita();
        } else {
            raiz = raiz.getEsquerda();
        }
    }

    /**
     * O m�todo buscarFolha, auxilia o deletarRoot, buscando o final da �rvore
     * para deletar a �rvore completamente.
     *
     * @param atual elemento inicial para buscar a sua folha
     * @return a folha do atual.
     */
    private Node buscarFolha(Node atual) {

        Node folha;
        Node corrente = atual;
        corrente = corrente.getDireita();
        folha = corrente;
        while (corrente != null) {
            folha = corrente;
            corrente = corrente.getEsquerda();
        }
        return folha;
    }

    /**
     * O m�todo buscarPaiFolha, � respons�vel por buscar o pai do �ltimo
     * elemento da �rvore e retorn�-lo.
     *
     * @param atual elemento inicial para buscar o pai da folha
     * @return o pai da folha.
     */
    private Node buscarPaiFolha(Node atual) {

        Node corrente = atual;
        Node pai = corrente;
        corrente = corrente.getDireita();
        while (corrente != null) {
            if (corrente.getEsquerda() != null) {
                pai = corrente;
            }
            corrente = corrente.getEsquerda();
        }
        return pai;
    }

    /**
     * Calcula a altura referenciando cada pai, para lado esquerdo ou direito
     * para verificar que tipo de rota��o ser� feita, para isso tem a ajuda dos
     * m�todos que calcula altura esquerda e direita, exemplo como a
     * balanleamento � feito de baixo para cima, ele acha os ultimos elementos
     * de cada lado, calculando sua altura se der 2 ou -2 o m�todo de
     * balanceamento ir� balance�-lo.
     *
     * @param aux recursivo
     * @param pai recursivo
     */
    private void calcularAltura(Node aux, Node pai) {

        if (aux != null) {
            calcularAltura(aux.getEsquerda(), aux);
            calcularAltura(aux.getDireita(), aux);
            calculaAlturaLadoDireito(aux);
            calcularAlturaLadoEsquerdo(aux);
            balanceamento(aux, pai);
        }
    }

    /**
     * O m�todo getRoot retorna o in�cio da �rvore.
     *
     * @return a ra�z
     */
    public Node getRoot() {
        return raiz;
    }

    /**
     * o m�todo get altura retorna a altura da �rvore.
     *
     * @return altura
     */
    public int Altura() {
        return auxAltura(raiz);
    }

    /**
     * O m�todo height auxilia a altura a encontra a altura at� as folhas
     * maiores esquerdas e direitas.
     *
     * @param aux recursivo
     * @return a altura
     */
    private int auxAltura(Node aux) {

        if (aux == null) {
            return -1;
        } else {
            int e = auxAltura(aux.getEsquerda());
            int d = auxAltura(aux.getDireita());
            if (d > e) {
                return d + 1;
            } else {
                return e + 1;
            }
        }
    }

    /**
     * O m�todo precura auxProcuraErro � bem peculiar, ele varre a arvore olhando se tem
     * algum fator desbalanceado.
     */
    public void ProcurarErro() {
        auxProcuraErro(raiz);
    }

    private void auxProcuraErro(Node aux) {

        if (aux != null) {
            auxProcuraErro(aux.getEsquerda());
            if (aux.gethD() - aux.gethE() >= 2) {
                System.out.println("Esse ponto n�o ta balanceado");
            }
            auxProcuraErro(aux.getEsquerda());
        }
    }

    /**
     * O m�todo imprimirOrdemRSV, impreme os dados em ordem crescente.
     */
    public void imprimirOrdem() {
        auxImprimirOrdem(raiz);
    }

    /**
     * auxiliar do m�todo imprimirOrdem.
     *
     * @param aux recursivo
     */
    private void auxImprimirOrdem(Node aux) {
        if (aux != null) {
            auxImprimirOrdem(aux.getEsquerda());
            System.out.println(aux.getElemento());
            auxImprimirOrdem(aux.getDireita());
        }
    }

    /**
     * O m�todo imprimirPreOrdemRSV, impreme os dados tendendo para a esquerda
     * come�ando pela sua raiz.
     */
    public void imprimirPreOrdem() {
        auxImprimirPreOrdem(raiz);
    }

    /**
     * auxiliar do m�todo imprimirPreOrdem.
     *
     * @param aux recursivo
     */
    private void auxImprimirPreOrdem(Node aux) {

        if (aux != null) {

            System.out.println(aux.getElemento());
            auxImprimirPreOrdem(aux.getEsquerda());
            auxImprimirPreOrdem(aux.getDireita());
        }
    }

    /**
     * O m�todo imprimirPosOrdem, impreme os dados de forma que vai para
     * esquerda e printa da folha crescentemente, depois vai para esquerda acha
     * a folha e retorna printando o �ltimo � o Raiz.
     */
    public void imprimirPosOrdem() {
        auxImprimirPosOrdem(raiz);
    }

    /**
     * auxiliar do m�todo imprimirPosOrdem.
     *
     * @param aux recursivo
     */
    private void auxImprimirPosOrdem(Node aux) {

        if (aux != null) {

            auxImprimirPosOrdem(aux.getEsquerda());
            auxImprimirPosOrdem(aux.getDireita());
            System.out.println(aux.getElemento());

        }

    }

    @Override
    public Iterator iterator() {
        Iterator it = new IteradorArvore(raiz);
        return it;
    }
}
