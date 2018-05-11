## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src="https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkausrakenne.png" width="240">

Pakkaus _platformer.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _platformer.domain_ sovelluslogiikan ja _platformer.dao_ tietojen pysyväistallennuksesta vastaavan koodin. Pakkaus _platformer.domain.entity_ sisältää pelilogiikassa käytettävät 'liikkuvat palaset' ja pakkaus _platformer.domain.stage_ sisältää pelin tasot rakentavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää viisi erillistä päänäkymää
- päävalikko
- peliruutu
- tasonvalintaruutu
- parhaiden aikojen tarkasteluruutu
- asetusvalikko

jokainen näistä on toteutettu omana [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen [stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Käyttöliittymä on rakennettu ohjelmallisesti pakkauksen _platformer.ui_ luokissa.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavien olioiden metodeja.

## Sovelluslogiikka

Sovelluksen logiikasta vastaavat pääosin luokat _GameLogic_, _GameCharacter_ ja MenuLogic-rajapinnan toteuttava _RealMenuLogic_. RealMenuLogic hoitaa esimerkiksi näkymien siirtymät ja tietokantaoperaatiot. GameLogic ja GameCharacter toimivat vahvasti yhdessä ja vastaavat itse pelin logiikasta.

RealMenuLogic-luokka hallinnoi tietokantaa pakkauksessa _platformer.dao_ sijaitsevien luokkien TimeDao ja UsernameDao kautta.

### Pelinäkymään siirtymistä kuvaava sekvenssikaavio

![sekvenssikaavio](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/a-2.png "sekvenssikaavio")

## Tietojen pysyväistallennus

Sovellus käyttää käyttäjänimen ja parhaiden aikojen tallentamiseen SQL-tietokantaa nimeltä _platformerDB_, jota hallinnoivat pakkauksen _platformer.dao_ luokat _TimeDao_ ja _UsernameDao_.

Luokat noudattavat [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa.

Sovellus tallentaa kerrallaan aina vain yhden käyttäjänimen ja kymmenen parasta aikaa per taso. Kun käyttäjänimi vaihdetaan, vanha nimi poistetaan tietokannasta ja kun samaan tasoon tehdään lisää aikoja, hitain aika poistetaan tietokannasta.

## Ohjelman rakenteeseen jääneet heikkoudet

### yleinen rakenne

Tällä hetkellä ohjelman luokkien välillä on erittäin suuri määrä riippuvuuksia. Huolellisella tarkastelulla osa riippuvuuksista voitaisiin todennäköisesti karsia, jolloin sovelluksen rakenteesta tulisi selkeämpi tulkita ja sovellusta olisi helpompi testata.

### pelin tasot

Tasot ovat sovelluksessa rakennettu suoraan kovakoodatun koodin perusteella. Parempi toteutus olisi luoda tasot jonkinlaiseen tiedostoon, joka sitten tulkitaan ja käännetään pelin tasoksi.
