
package br.uefs.ecomp.treeStock.util;

public class DadoNaoEncontradoException extends Exception{
    public DadoNaoEncontradoException(){
        super();
    }
    
    public DadoNaoEncontradoException(String msg){
        super(msg);
    }
    
    public DadoNaoEncontradoException(Throwable t){
        super(t);
    }
}
