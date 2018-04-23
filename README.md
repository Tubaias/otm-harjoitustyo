# OTM-harjoitustyö
Ohjelmistotekniikan menetelmät -kurssia varten toteutettava harjoitustyö. Ohjelman on tarkoitus olla yksinkertainen tasohyppelypeli, joka toteutetaan Javan ja Mavenin avulla.
## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)  

[Arkkitehtuurikuvaus](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)  

[Työaikakirjanpito](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Tämänhetkinen toiminnallisuus
Ohjelmassa on päävalikko, asetusvalikko, ja peliruutu, jossa on 'pelaajahahmo' ja nappi pääruutuun palaamista varten. Ohjelmassa voi siirtyä taaksepäin Esc-näppäimellä ja peliruudussa hahmoa voi ohjata nuolinäppäimillä.

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
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _OtmTodoApp-1.0-SNAPSHOT.jar_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/Tubaias/otm-harjoitustyo/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

