/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uefs.ecomp.treeStock.util;


import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Hichard
 */
public class ListaOrdenadaTest {
    
    private ListaOrdenada lista;
    public ListaOrdenadaTest() {
        lista = new ListaOrdenada();
    }
    
    private void testInserir(){
        lista.insere("kakashi");
        lista.insere("allen");
        lista.insere("naruto");
        lista.insere("lais");
        lista.insere("sasuke");
    }
    
    @Test
    public void testEstaVazia() {
        assertTrue(lista.estaVazia());
        testInserir();
        assertFalse(lista.estaVazia());
    }

    
    @Test
    public void testTamanho() {
        assertEquals(0, lista.tamanho());
        testInserir();
        assertEquals(5, lista.tamanho());
    }


    /**
     * Test of remove method, of class ListaOrdenada.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemove() throws Exception {
        testInserir();
        assertFalse(lista.estaVazia());
        lista.remove("kakashi");
        lista.remove("allen");
        lista.remove("naruto");
        lista.remove("lais");
        lista.remove("sasuke");
        assertTrue(lista.estaVazia());
    }

    /**
     * Test of recupera method, of class ListaOrdenada.
     * @throws java.lang.Exception
     */
    @Test
    public void testRecupera() throws Exception {
        testInserir();
        assertEquals("sasuke", lista.recupera(0));
        assertEquals("naruto", lista.recupera(1));
        assertEquals("lais", lista.recupera(2));
        assertEquals("kakashi", lista.recupera(3));
        assertEquals("allen", lista.recupera(4));
    }

    /**
     * Test of iterator method, of class ListaOrdenada.
     */
    @Test
    public void testIterator() {
       testInserir();
       Iterator it = lista.iterator();
       assertTrue(it.hasNext());
       assertEquals("sasuke", it.next());
       assertEquals("naruto", it.next());
       assertEquals("lais", it.next());
       assertEquals("kakashi", it.next());
       assertEquals("allen", it.next());
       assertFalse(it.hasNext());
       
        
    }

    
    
}
