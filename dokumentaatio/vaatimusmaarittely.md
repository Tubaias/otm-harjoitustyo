# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on suhteellisen yksinkertainen tasohyppelypeli, joka tallentaa pelaajan nopeimpia aikoja yksittäisille tasoille ja koko pelin läpäisylle. Pelin päämekaniikkana on pelaajahahmon nopeuden säilyminen hypystä seuraavaan, jos pelaaja hyppää nopeasti laskeutumisen jälkeen.

## Sovelluksen tarjoama toiminnallisuus
- Sovelluksessa on päävalikko, josta voi siirtyä pelaamaan peliä, tarkastelemaan parhaita aikoja tai muokkaamaan asetuksia.

- Pelissä on hahmo, jota ohjataan näppäimistöllä, ja neljä tasoa.

- Tasoja voi pelata yksitellen, jolloin peli laskee tasoon kuluvan ajan
  - Tasossa kuoleminen tai tason uudestaan aloittaminen nollaa aikalaskurin
  
- Pelaaja voi myös aloittaa uuden läpipeluun, jossa lasketaan yhtäjaksoinen aika kaikkien tasojen läpäisyyn
  - Tällöin kuoleminen tai yksittäisen tason uudestaan aloittaminen ei nollaa aikalaskuria

- Parhaat ajat tallennetaan pysyvästi tietokantaan pelaajan valitseman nimen kanssa

## Jatkokehitysideoita

Ohjelman yleinen toimintaperiaate tulee aina seuraamaan perusversiota, mutta ajan salliessa pelin logiikkaa ja mekaniikkoja voisi laajentaa ja tasoja voi tehdä enemmän kuin neljä. Mahdollisia laajennuksia olisivat esimerkiksi:

- Monimutkaisemmat liikkumismahdollisuudet pelihahmolle
- Liikkuvat osat tasoissa
- Internetissä sijaitseva tietokanta parhaista ajoista
- Lisää asetuksia asetusvalikkoon, kuten pelin näppäimien vaihtaminen.
