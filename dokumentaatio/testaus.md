# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testien ytimen moudostavat pelilogiikkaa testaavat integraatiotestit [GameLogicTest](https://github.com/Tubaias/otm-harjoitustyo/blob/master/src/test/java/platformer/domain/GameLogicTest.java) ja [GameCharacterTest](https://github.com/Tubaias/otm-harjoitustyo/blob/master/src/test/java/platformer/domain/entity/GameCharacterTest.java) joiden määrittelevät testitapaukset simuloivat pelin yleisiä toiminnalisuuksia ja GameLogic- sekä GameCharacter-luokkien integraatiota.

Testit käyttävät hyödykseen MenuLogic-interfacen toteuttavaa FakeMenuLogic-luokkaa, koska oikean toiminnallisuuden tarjoava RealMenuLogic-luokka ei toimi JUnit-testien kanssa.

### DAO-luokat

Molempien DAO-luokkien toiminnallisuus on testattu luomalla testeissä tilapäinen testitietokanta.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 86% ja haarautumakattavuus 63%

<img src="https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/tests.png" width="800">

Testaamatta jäivät muutamat sovelluslogiikan erityistapaukset, sekä RealMenuLogic-luokka, jota ei ollut javafx:n rajoitusten takia mahdollista testata.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja kanfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla sekä Windows- että Linux-ympäristöön.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/Tubaias/otm-platformer/blob/master/dokumentaatio/vaatimusmaarittely.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä.

## Sovellukseen jääneet laatuongelmat

Pelin törmäyksentarkastuskoodissa on edelleen pieniä ongelmia.
