/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.treeStock.util;

import br.uefs.ecomp.treeStock.model.exception.AcaoNaoEncontradaException;
import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Hichard
 */
public class HashCodeTest {

    private final HashCode hash;

    public HashCodeTest() {
        hash = new HashCode();
    }

    @Test
    public void testSetValorGetVAlor() {
        assertEquals(0, hash.getValor(), 0.001);
        hash.setValor(1000);
        assertEquals(1000, hash.getValor(), 0.001);
        hash.setValor(2000);
        assertEquals(3000, hash.getValor(), 0.001);
        hash.setValor(-3000);
        assertEquals(0, hash.getValor(), 0.001);

    }

    /**
     * Test of setTamanho method, of class HashCode.
     */
    @Test
    public void testSetTamanhoGetTamanho() {
        assertEquals(0, hash.getTamanho());
        hash.setTamanho(1000);
        assertEquals(1000, hash.getTamanho());
        hash.setTamanho(2000);
        assertEquals(3000, hash.getTamanho());
        hash.setTamanho(-3000);
        assertEquals(0, hash.getTamanho());
    }

    private void inserção() {
        hash.put("apple", 100);
        hash.put("micro", 200);
        hash.put("google", 300);
        hash.put("anime", 400);
        hash.put("manga", 500);

    }

    /**
     * Test of contem method, of class HashCode.
     */
    @Test
    public void testContem() {
        inserção();
        assertTrue(hash.contem("apple"));
        assertTrue(hash.contem("google"));
        assertTrue(hash.contem("anime"));
        assertFalse(hash.contem("uefs"));
    }

    @Test(expected = DadoNaoEncontradoException.class)
    public void testGet() throws Exception {
        inserção();
        assertEquals(100, hash.get("apple"));
        assertEquals(200, hash.get("micro"));
        assertEquals(300, hash.get("google"));
        assertEquals(400, hash.get("anime"));
        assertEquals(500, hash.get("manga"));
        hash.get("kakashi");
        throw new AcaoNaoEncontradaException();

    }

    /**
     * Test of set method, of class HashCode.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSet() throws Exception {
        inserção();
        hash.set("apple", 1000);
        assertEquals(1000, hash.get("apple"));
    }

    /**
     * Test of remove method, of class HashCode.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRemove() throws Exception {
        inserção();
        assertFalse(hash.estaVazia());
        hash.remove("apple");
        hash.remove("micro");
        hash.remove("google");
        hash.remove("anime");
        hash.remove("manga");
        assertTrue(hash.estaVazia());
    }

    /**
     * Test of estaVazia method, of class HashCode.
     */
    @Test
    public void testEstaVazia() {
        assertTrue(hash.estaVazia());
        inserção();
        assertFalse(hash.estaVazia());
    }
    //caso queira verificar o que ocorre e só retirar as duplas barras
    @Test
    public void iterator() {
        inserção();
        Iterator it = hash.iterator();
        assertTrue(it.hasNext());
        while (it.hasNext()) {
            HashCelula a = (HashCelula) it.next();
            if (a != null) {
                //System.out.println(a.getChave());
            }
        }
        assertFalse(it.hasNext());
    }

}
