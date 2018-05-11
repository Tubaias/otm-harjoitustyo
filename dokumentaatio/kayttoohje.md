# Käyttöohje

Lataa tiedosto [otm-platformer-1.0.jar](https://github.com/Tubaias/otm-harjoitustyo/releases/tag/Loppupalautus)

Mitään ylimääräistä konfiguraatiota ei vaadita. Sovellus luo tallennuskansioonsa tietokannan nimellä platformerDB.db.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar otm-platformer-1.0.jar
```

## Päävalikko

<img src="https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/mainmenu.png" width="300">

Päävalikossa painikkeet:
- Start game: Aloittaa uuden täyden läpipeluun.
- Level select: Avaa uuden valikon jonka kautta voidaan valita yksittäinen taso pelattavaksi.
- Best times: Avaa uuden valikon, jossa voidaan tarkastella parhaita aikoja.
- Options: Avaa asetusvalikon, jossa voidaan vaihtaa pelaajanimeä ja pyyhkiä tietokanta.

## Peliruutu

<img src="https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/gamescreen.png" width="800">

Kuvassa nimettynä pelin eri elementit. Taso voitetaan keräämällä kaikki kolikot ja pääsemällä sen jälkeen maaliin. Ajastin mittaa tasoon tai läpipeluuseen kulunutta aikaa.

## Kontrollit

Valikoissa taaksepäin voi siirtyä ESC-näppäimellä ja eteenpäin välilyönnillä. 
Peliruudussa hahmoa voi ohjata nuolinäppäimillä, nykyisen tason voi aloittaa alusta R-näppäimellä ja koko läpipeluun voi aloittaa alusta T-näppäimellä.
