
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
    }

    @Test
    public void alkurahamaaraOikea() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void alkuEdullisiaNolla() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void alkuMaukkaitaNolla() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKateisellaKasvattaaKassaaOikein() {
        kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullinenKateisellaOikeaVaihtoraha() {
        assertEquals(260, kassa.syoEdullisesti(500));
    }
    
    @Test
    public void edullinenKateisellaKasvattaaMyytyja() {
        kassa.syoEdullisesti(500);
        kassa.syoEdullisesti(500);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKateinenEiRiitaEdulliseenRahamaaraEiKasva() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void josKateinenEiRiitaEdulliseenRahatPalautetaan() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }
    
    @Test
    public void josKateinenEiRiitaEdulliseenLounastaEiMyyty() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenVoiOstaaTasarahalla() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukasKateisellaKasvattaaKassaaOikein() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukasKateisellaOikeaVaihtoraha() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    @Test
    public void maukasKateisellaKasvattaaMyytyja() {
        kassa.syoMaukkaasti(500);
        kassa.syoMaukkaasti(500);
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKateinenEiRiitaMaukkaaseenRahamaaraEiKasva() {
        kassa.syoMaukkaasti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void josKateinenEiRiitaMaukkaaseenRahatPalautetaan() {
        assertEquals(200, kassa.syoMaukkaasti(200));
    }
    
    @Test
    public void josKateinenEiRiitaMaukkaaseenLounastaEiMyyty() {
        kassa.syoMaukkaasti(200);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanVoiOstaaTasarahalla() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    //maksukortti
    
    @Test
    public void edullinenVahentaaKortinSaldoa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(260, kortti.saldo());
    }
    
    @Test
    public void edullinenKorttiostoPalauttaaTrue() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullinenKorttiostoLisaaMyydyn() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEdullisenOstaessaKassanRahamaaraEiMuutu() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void josKortillaEiVaraaEdulliseenRahamaaraEiMuutu() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void josKortillaEiVaraaEdulliseenLounastaEiMyyda() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiVaraaEdulliseenPalautetaanFalse() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(false, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukasVahentaaKortinSaldoa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void maukasKorttiostoPalauttaaTrue() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukasKorttiostoLisaaMyydyn() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaMaukkaanOstaessaKassanRahamaaraEiMuutu() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void josKortillaEiVaraaMaukkaaseenRahamaaraEiMuutu() {
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(260, kortti.saldo());
    }
    
    @Test
    public void josKortillaEiVaraaMaukkaaseenLounastaEiMyyda() {
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiVaraaMaukkaaseenPalautetaanFalse() {
        kassa.syoEdullisesti(kortti);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kortilleLatausKasvattaaSaldoa() {
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void kortilleLatausKasvattaaKassaa() {
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassa.kassassaRahaa());
    }
    
    @Test
    public void negatiivisenRahanLatausEiMuutaSaldoa() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(500, kortti.saldo());
    }
    
    @Test
    public void negatiivisenRahanLatausEiMuutaKassaa() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
