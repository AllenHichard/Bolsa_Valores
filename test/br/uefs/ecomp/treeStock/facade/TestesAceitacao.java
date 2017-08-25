package br.uefs.ecomp.treeStock.facade;

import br.uefs.ecomp.treeStock.model.Acao;
import br.uefs.ecomp.treeStock.model.Cliente;
import br.uefs.ecomp.treeStock.model.exception.AcaoNaoEncontradaException;
import br.uefs.ecomp.treeStock.model.exception.ClienteNaoEncontradoException;
import br.uefs.ecomp.treeStock.util.DadoDuplicadoException;
import br.uefs.ecomp.treeStock.util.DadoNaoEncontradoException;
import br.uefs.ecomp.treeStock.util.IPares;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import org.junit.Before;

public class TestesAceitacao {

    private static final double EPSILON = 0.001;
    private TreeStockFacade facade;
    private Cliente maria, joao, pedro, marcos, suzana, tarcizio, priscila;
    private Acao petr, elet, bbas, embr;

    @Before
    public void setUp() throws Exception {
        facade = new TreeStockFacade();

        tarcizio = facade.cadastrarCliente("Tarcizio Nery", "Rua Pedro Suzart, 3456");
        maria = facade.cadastrarCliente("Maria dos Santos", "Rua Drummond, 23, Centro");
        joao = facade.cadastrarCliente("João dos Santos", "Rua Pessoa, 12, Centro");
        pedro = facade.cadastrarCliente("Pedro da Silva", "Rua Andrade, 45, Cidade Nova");
        marcos = facade.cadastrarCliente("Marcos Oliveira", "Rua Quintana, 45, Santa Monica");
        suzana = facade.cadastrarCliente("Suzana Abreu Lima", "Rua da ladeira, 23");
        priscila = facade.cadastrarCliente("Priscila Costa e Silva", "Rua E, 27");

        petr = facade.cadastrarAcao("PETR4", "Petrobrás", 21.10);
        elet = facade.cadastrarAcao("ELET3", "Eletrobrás", 9.80);
        bbas = facade.cadastrarAcao("BBAS3", "Banco do Brasil", 29.50);
        embr = facade.cadastrarAcao("EMBR3", "Embraer", 22.60);
    }

    @Test
    public void testCadastrarCliente() throws DadoDuplicadoException {
        assertFalse(facade.clienteCadastrado("Fulano de tal"));

        Cliente cliente = facade.cadastrarCliente("Fulano de tal", "Rua da hora, 23, Centro");
        assertEquals("Fulano de tal", cliente.getNome());
        assertEquals("Rua da hora, 23, Centro", cliente.getEndereco());

        assertTrue(facade.clienteCadastrado("Fulano de tal"));
    }

    @Test(expected = DadoDuplicadoException.class)
    public void testCadastrarClienteRepetido() throws DadoDuplicadoException {
        //deve lançar exceção
        facade.cadastrarCliente("Marcos Oliveira", "Independente do endereço");
    }

    @Test
    public void testRemoverCliente() throws DadoDuplicadoException, ClienteNaoEncontradoException {
        assertTrue(facade.clienteCadastrado("Tarcizio Nery"));

        Cliente cliente = facade.removerCliente("Tarcizio Nery");
        assertEquals(cliente, tarcizio);

        assertFalse(facade.clienteCadastrado("Tarcizio Nery"));
    }

    @Test(expected = ClienteNaoEncontradoException.class)
    public void testRemoverClienteInexistente() throws ClienteNaoEncontradoException {
        //deve lançar exceção
        facade.removerCliente("Cliente nao cadastrado");
    }

    @Test
    public void testCadastrarAcao() throws DadoDuplicadoException {
        assertFalse(facade.acaoCadastrada("FAKE4"));

        Acao acao = facade.cadastrarAcao("FAKE4", "Ação Fake", 1.00);

        assertEquals("FAKE4", acao.getSigla());
        assertEquals("Ação Fake", acao.getNome());
        assertEquals(1.00, acao.getValor(), EPSILON);

        assertTrue(facade.acaoCadastrada("FAKE4"));
    }

    @Test(expected = DadoDuplicadoException.class)
    public void testCadastrarAcaoRepetida() throws DadoDuplicadoException {
        //deve lançar exceção
        facade.cadastrarAcao("PETR4", "Nao importa o nome  ou o valor, apenas a sigla", 123);
    }

    @Test
    public void testRemoverAcao() throws DadoDuplicadoException, AcaoNaoEncontradaException {
        assertTrue(facade.acaoCadastrada("EMBR3"));

        Acao acao = facade.removerAcao("EMBR3");
        assertEquals(new Acao("EMBR3"), acao);

        assertFalse(facade.acaoCadastrada("EMBR3"));
    }

    @Test(expected = AcaoNaoEncontradaException.class)
    public void testRemoverAcaoInexistente() throws AcaoNaoEncontradaException {
        //deve lançar exceção
        facade.removerAcao("UEFS3");
    }
    
    @Test
    public void testValorAcao() throws AcaoNaoEncontradaException {
        assertEquals(21.10, facade.getValorAcao("PETR4"), EPSILON);

        facade.setValorAcao("PETR4", 40.80);

        assertEquals(40.80, facade.getValorAcao("PETR4"), EPSILON);
    }

    @Test
    public void testListarClientes() throws DadoDuplicadoException {
        Iterator clientes = facade.iterator();

        assertTrue(clientes.hasNext());
        assertEquals(joao, clientes.next());

        assertTrue(clientes.hasNext());
        assertEquals(marcos, clientes.next());

        assertTrue(clientes.hasNext());
        assertEquals(maria, clientes.next());

        assertTrue(clientes.hasNext());
        assertEquals(pedro, clientes.next());

        assertTrue(clientes.hasNext());
        assertEquals(priscila, clientes.next());

        assertTrue(clientes.hasNext());
        assertEquals(suzana, clientes.next());

        assertTrue(clientes.hasNext());
        assertEquals(tarcizio, clientes.next());

        assertFalse(clientes.hasNext());
    }

    @Test
    public void testIncluirAcaoCliente() throws ClienteNaoEncontradoException, AcaoNaoEncontradaException, DadoDuplicadoException {
        try {
            facade.getQuantidadeAcaoCliente("João dos Santos", "PETR4");
        } catch (AcaoNaoEncontradaException e) {
            //indica que a carteira do cliente não possui esta ação
            facade.incluirAcaoCliente("João dos Santos", "PETR4", 1000);
            assertEquals(1000, facade.getQuantidadeAcaoCliente("João dos Santos", "PETR4"));
        }
    }

    @Test(expected = ClienteNaoEncontradoException.class)
    public void testIncluirAcaoClienteNaoCadastradaNaBolsa() throws ClienteNaoEncontradoException, AcaoNaoEncontradaException, DadoDuplicadoException {
        facade.incluirAcaoCliente("Cliente não Cadastrado", "PERT2", 1000);
    }
    
    @Test(expected = AcaoNaoEncontradaException.class)
    public void testSetQuantidadeAcaoNaoCadastradaNaBolsa() throws ClienteNaoEncontradoException, AcaoNaoEncontradaException, DadoDuplicadoException {
        facade.setQuantidadeAcaoCliente("João dos Santos", "MICO2", 1000);        
    }

    @Test
    public void testExcluirAcaoCliente() throws ClienteNaoEncontradoException, AcaoNaoEncontradaException, DadoDuplicadoException {
        facade.setQuantidadeAcaoCliente("João dos Santos", "PETR4", 500);

        assertEquals(500, facade.getQuantidadeAcaoCliente("João dos Santos", "PETR4"));

        facade.removerAcaoCliente("João dos Santos", "PETR4");

        try {
            //deve lançar exceção: AcaoNaoEncontradaException
            facade.getQuantidadeAcaoCliente("João dos Santos", "PETR4");
            assertTrue(false);
        } catch (AcaoNaoEncontradaException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetCarteiraCliente() throws ClienteNaoEncontradoException, AcaoNaoEncontradaException, DadoNaoEncontradoException {
        IPares carteira = facade.getCarteiraCliente("Suzana Abreu Lima");
        assertTrue(carteira.estaVazia());

        double valor = facade.getValorCarteiraCliente("Suzana Abreu Lima");
        assertEquals(0.0, valor, EPSILON);

        facade.setQuantidadeAcaoCliente("Suzana Abreu Lima", "PETR4", 500);
        carteira = facade.getCarteiraCliente("Suzana Abreu Lima");
        assertFalse(carteira.estaVazia());
        assertEquals(500, carteira.get("PETR4"));
        assertEquals(500, facade.getQuantidadeAcaoCliente("Suzana Abreu Lima", "PETR4"));

        valor = facade.getValorCarteiraCliente("Suzana Abreu Lima");
        assertEquals(petr.getValor() * 500, valor, EPSILON);

        facade.setQuantidadeAcaoCliente("Suzana Abreu Lima", "EMBR3", 1000);
        facade.setQuantidadeAcaoCliente("Suzana Abreu Lima", "BBAS3", 100);
        carteira = facade.getCarteiraCliente("Suzana Abreu Lima");
        assertFalse(carteira.estaVazia());
        assertEquals(500, carteira.get("PETR4"));
        assertEquals(1000, carteira.get("EMBR3"));
        assertEquals(100, carteira.get("BBAS3"));

        valor = facade.getValorCarteiraCliente("Suzana Abreu Lima");
        assertEquals(petr.getValor() * 500 + embr.getValor() * 1000 + bbas.getValor() * 100, valor, EPSILON);

        facade.removerAcaoCliente("Suzana Abreu Lima", "PETR4");
        facade.removerAcaoCliente("Suzana Abreu Lima", "EMBR3");
        facade.removerAcaoCliente("Suzana Abreu Lima", "BBAS3");

        carteira = facade.getCarteiraCliente("Suzana Abreu Lima");
        assertTrue(carteira.estaVazia());
    }

    @Test
    public void testMelhoresClientes() throws ClienteNaoEncontradoException, AcaoNaoEncontradaException, DadoNaoEncontradoException {
        Iterator melhores = facade.melhoresClientes(0);
        assertFalse(melhores.hasNext());

        facade.setQuantidadeAcaoCliente(maria.getNome(), petr.getSigla(), 501);
        facade.setQuantidadeAcaoCliente(maria.getNome(), elet.getSigla(), 1150);

        facade.setQuantidadeAcaoCliente(joao.getNome(), petr.getSigla(), 1500);
        facade.setQuantidadeAcaoCliente(joao.getNome(), elet.getSigla(), 100);

        facade.setQuantidadeAcaoCliente(pedro.getNome(), petr.getSigla(), 2500);
        facade.setQuantidadeAcaoCliente(pedro.getNome(), elet.getSigla(), 50);

        facade.setQuantidadeAcaoCliente(marcos.getNome(), petr.getSigla(), 400);
        facade.setQuantidadeAcaoCliente(suzana.getNome(), petr.getSigla(), 200);
        facade.setQuantidadeAcaoCliente(tarcizio.getNome(), petr.getSigla(), 300);
        facade.setQuantidadeAcaoCliente(priscila.getNome(), petr.getSigla(), 500);

        melhores = facade.melhoresClientes(1);
        assertTrue(melhores.hasNext());
        assertEquals(pedro, melhores.next());
        assertFalse(melhores.hasNext());


        melhores = facade.melhoresClientes(3);
        assertTrue(melhores.hasNext());
        assertEquals(pedro, melhores.next());
        assertEquals(joao, melhores.next());
        assertEquals(maria, melhores.next());
        assertFalse(melhores.hasNext());

        facade.setValorAcao(elet.getSigla(), 40.00);

        melhores = facade.melhoresClientes(1);
        assertTrue(melhores.hasNext());
        assertEquals(maria, melhores.next());
        assertFalse(melhores.hasNext());

        facade.setQuantidadeAcaoCliente(suzana.getNome(), bbas.getSigla(), 2000);

        melhores = facade.melhoresClientes(1);
        assertTrue(melhores.hasNext());
        assertEquals(suzana, melhores.next());
        assertFalse(melhores.hasNext());
    }
}
