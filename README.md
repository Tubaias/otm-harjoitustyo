# OTM-harjoitustyö
Ohjelmistotekniikan menetelmät -kurssia varten toteutettava harjoitustyö. Ohjelman on tarkoitus olla yksinkertainen tasohyppelypeli, joka toteutetaan Javan ja Mavenin avulla.
## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)  

[Arkkitehtuurikuvaus](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)  

[Työaikakirjanpito](https://github.com/Tubaias/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/Tubaias/otm-harjoitustyo/releases/tag/viikko5)

## !!!
Ohjelmalle on lähes mahdotonta kirjoittaa testejä, koska suurin osa ohjelman logiikasta on välillisesti riippuvaista javafx:n Stage-luokasta, josta ei tuntemattomista syistä ole mahdollista luoda oliota testausta varten.

## Tämänhetkinen toiminnallisuus
Ohjelmassa on päävalikko, tasonvalintaruutu, asetusvalikko, ja peliruutu, jossa on 'pelaajahahmo' ja nappi pääruutuun palaamista varten. Ohjelmassa voi siirtyä taaksepäin Esc-näppäimellä ja peliruudussa hahmoa voi ohjata nuolinäppäimillä.

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

generoi hakemistoon _target_ suoritettavan jar-tiedoston _otm-platformer-Viikko 5-jar-with-dependencies.jar_

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

