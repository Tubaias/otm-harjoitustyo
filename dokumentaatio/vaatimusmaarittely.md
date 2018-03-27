# Vaatimusmäärittely

## Soveluksen tarkoitus

Sovellus on suhteellisen yksinkertainen tasohyppelypeli, joka tallentaa pelaajan nopeimpia aikoja yksittäisille tasoille ja koko pelin läpäisylle.

## Perusversion tarjoama toiminnallisuus
- Sovelluksessa on päävalikko, josta voi siirtyä pelaamaan peliä tai tarkastelemaan parhaita aikoja

- Pelissä on hahmo, jota ohjataan näppäimistöllä, ja ainakin kaksi tasoa.

- Tasoja voi pelata yksitellen, jolloin peli laskee tasoon kuluvan ajan
  - Tasossa kuoleminen tai tason uudestaan aloittaminen nollaa aikalaskurin
  
- Pelaaja voi myös aloittaa uuden läpipeluun, jossa lasketaan yhtäjaksoinen aika kaikkien tasojen läpäisyyn
  - Tällöin kuoleminen tai yksittäisen tason uudestaan aloittaminen ei nollaa aikalaskuria

- Parhaat ajat tallennetaan pysyvästi tietokantaan pelaajan valitseman nimen kanssa

## Jatkokehitysideoita

Ohjelman yleinen toimintaperiaate tulee aina seuraamaan perusversion esimerkkiä, mutta ajan salliessa pelin logiikkaa ja mekaniikkoja voi laajentaa ja tasoja voi tehdä enemmän kuin kaksi. Mahdollisia laajennuksia olisivat esimerkiksi:

- Monimutkaisemmat liikkumismahdollisuudet pelihahmolle
- Liikkuvat osat tasoissa
- Internetissä sijaitseva tietokanta parhaista ajoista
- Jonkinlainen asetusvalikko, jolla voidaan säätää sovelluksen ominaisuuksia
