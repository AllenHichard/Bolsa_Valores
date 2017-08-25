package br.uefs.ecomp.treeStock.util;

public interface ILista extends Iterable{

    public boolean estaVazia();

    public int tamanho();

    public void insereInicio(Object o);

    public void insereFinal(Object o);

    public Object removeInicio() throws DadoNaoEncontradoException;

    public Object removeFinal() throws DadoNaoEncontradoException;

    public Object recupera(int index) throws DadoNaoEncontradoException;
}
