package br.uefs.ecomp.treeStock.model.exception;


public class AcaoNaoEncontradaException extends Exception {
    public AcaoNaoEncontradaException() {
        super();
    }

    public AcaoNaoEncontradaException(String msg) {
        super(msg);
    }

    public AcaoNaoEncontradaException(Throwable exception) {
        super(exception);
    }
}
