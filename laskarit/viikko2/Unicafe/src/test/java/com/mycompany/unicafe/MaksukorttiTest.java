package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void alkuSaldoOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(5);
        assertEquals(15, kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOttaessa() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }
    
    @Test
    public void saldoEiVaheneJosEiVaraa() {
        kortti.otaRahaa(20);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void ottaminenPalauttaaTrueJosRiitti() {
        assertEquals(true, kortti.otaRahaa(10));
    }
    
    @Test
    public void ottaminenPalauttaaFalseJosEiRiittanyt() {
        assertEquals(false, kortti.otaRahaa(11));
    }
    
    @Test
    public void toStringPalauttaaSaldon() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
