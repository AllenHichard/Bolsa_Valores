/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.model;

import br.uefs.ecomp.treeStock.util.DadoNaoEncontradoException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class ClienteCarteiraTest {

    private final Cliente allen, lais, naruto;

    public ClienteCarteiraTest() {
        allen = new Cliente("allen", "brasília");
        lais = new Cliente("lais", "mangabeira");
        naruto = new Cliente("naruto", "konoha");
    }

    /**
     * Test of getCarteira method, of class Cliente.
     *
     * @throws br.uefs.ecomp.treeStock.util.DadoNaoEncontradoException
     */
    @Test
    public void testGetCarteira() throws DadoNaoEncontradoException {
        assertTrue(allen.getCarteira().getAcao().estaVazia());
        assertTrue(lais.getCarteira().getAcao().estaVazia());
        assertTrue(naruto.getCarteira().getAcao().estaVazia());
        allen.getCarteira().getAcao().put("a", 10);
        lais.getCarteira().getAcao().put("a", 10);
        assertFalse(allen.getCarteira().getAcao().estaVazia());
        assertFalse(lais.getCarteira().getAcao().estaVazia());
        assertTrue(naruto.getCarteira().getAcao().estaVazia());
    }

    /**
     * Test of getNome method, of class Cliente.
     */
    @Test
    public void testGetNome() {
        assertEquals("allen", allen.getNome());
        assertEquals("lais", lais.getNome());
        assertEquals("naruto", naruto.getNome());
    }

    @Test
    public void testGetEndereco() {
        assertEquals("brasília", allen.getEndereco());
        assertEquals("mangabeira", lais.getEndereco());
        assertEquals("konoha", naruto.getEndereco());
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals() {
        assertFalse(allen.getNome().equals(lais.getNome()));
    }

    /**
     * Test of compareTo method, of class Cliente.
     */
    @Test
    public void testCompareTo() {
        assertTrue(allen.compareTo(lais) < 0);
        assertTrue(allen.compareTo(naruto) < 0);
        assertTrue(naruto.compareTo(lais) > 0);
        assertTrue(naruto.compareTo(allen) > 0);
        assertTrue(allen.compareTo(allen) == 0);
    }

}
