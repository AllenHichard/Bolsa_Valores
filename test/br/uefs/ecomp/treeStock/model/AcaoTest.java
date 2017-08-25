/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uefs.ecomp.treeStock.model;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *  @author Allen Hichard Marques dos Santos, Alisson Vilas Verde
 */
public class AcaoTest {
    
    private final Acao apple, microsoft, google;
    
    public AcaoTest() {
       apple = new Acao("apple");
       apple.setNomeAcao("acao1");
       apple.setValorInicial(1000);
       microsoft = new Acao("microsoft");
       microsoft.setNomeAcao("acao2");
       microsoft.setValorInicial(2000);
       google = new Acao("google");
       google.setNomeAcao("acao3");
       google.setValorInicial(3000);
    }

    @Test
    public void testGetSigla() {
        assertEquals("apple", apple.getSigla());
        assertEquals("microsoft", microsoft.getSigla());
        assertEquals("google", google.getSigla());
    }

    /**
     * Test of getNome method, of class Acao.
     */
    @Test
    public void testGetNome() {
        assertEquals("acao1", apple.getNome());
        assertEquals("acao2", microsoft.getNome());
        assertEquals("acao3", google.getNome());
    }

    /**
     * Test of getValor method, of class Acao.
     */
    @Test
    public void testGetValor() {
        assertEquals(1000, apple.getValor(), 0.001);
        assertEquals(2000, microsoft.getValor(),0.001);
        assertEquals(3000, google.getValor(), 0.001);
    }


    /**
     * Test of equals method, of class Acao.
     */
    @Test
    public void testEquals() {
        assertFalse(apple.getSigla().equals(microsoft.getSigla()));
    }

    /**
     * Test of compareTo method, of class Acao.
     */
    @Test
    public void testCompareTo() {
        assertTrue(apple.compareTo(microsoft)<0);
        assertTrue(apple.compareTo(google)<0);
        assertTrue(microsoft.compareTo(google)>0);
        assertTrue(google.compareTo(apple)>0);
        assertTrue(google.compareTo(google)==0);
    }
    
}
