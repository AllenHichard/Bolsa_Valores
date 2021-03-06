package br.uefs.ecomp.treeStock.util;

public interface IArvore extends Iterable{
    
    public Object buscar(Comparable item) throws DadoNaoEncontradoException;

    public void inserir(Comparable item) throws DadoDuplicadoException;

    public void remover(Comparable item) throws DadoNaoEncontradoException;

}