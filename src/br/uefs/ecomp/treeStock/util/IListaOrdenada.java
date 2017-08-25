package br.uefs.ecomp.treeStock.util;

public interface IListaOrdenada extends Iterable{

    public boolean estaVazia();

    public int tamanho();

    public void insere(Comparable o);

    public Comparable remove(Comparable o) throws DadoNaoEncontradoException;    

    public Comparable recupera(int index) throws DadoNaoEncontradoException;
}