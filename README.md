# OTM-harjoitustyö
Ohjelmistotekniikan menetelmät -kurssia varten toteutettava harjoitustyö. Ohjelman on tarkoitus olla yksinkertainen tasohyppelypeli, joka toteutetaan Javan ja Mavenin avulla.
## Dokumentaatio
[Käyttöohje](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)  

[Vaatimusmäärittely](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)  

[Arkkitehtuurikuvaus](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)  

[Testausdokumentti](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/testaus.md)  

[Työaikakirjanpito](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/Tubaias/otm-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/Tubaias/otm-harjoitustyo/releases/tag/viikko6)

[1.0](https://github.com/Tubaias/otm-harjoitustyo/releases/tag/1.0)

## Tämänhetkinen toiminnallisuus
Ohjelmassa on päävalikko, tasonvalintaruutu, tulosruutu, asetusvalikko, ja peliruutu, jossa on 'pelaajahahmo' ja nappi pääruutuun palaamista varten. Ohjelmassa voi siirtyä taaksepäin Esc-näppäimellä ja peliruudussa hahmoa voi ohjata nuolinäppäimillä. Peliruudussa nykyisen tason voi aloittaa alusta R-näppäimellä ja koko pelin läpipeluun voi aloittaa alusta T-näppäimellä.

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn clean compile assembly:single
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _otm-platformer-1.0-jar-with-dependencies.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/Tubaias/otm-harjoitustyo/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

