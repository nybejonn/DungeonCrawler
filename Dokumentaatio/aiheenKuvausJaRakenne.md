**Aihe:** 

Yksinkertainen luolastoroolipeli, jossa liikutaan (äärellisessä) ruudukossa. Jokainen ruutu on joko tyhjä jolloin mitään ei tapahdu tai sitten törmätään pelitapahtumaan, jossa vaihtoehtoisia toiminnallisuuksia. Tavoite on tutkia kaikki ruudut, joissa on toiminnallisuutta kuolematta.

**Käyttäjät:** 

Pelaaja.

**Toiminnot:** 

Liikkuminen ruudukossa lähinnä oleviin ruutuihin
* Jos ruudussa, johon liikuttiin on esimerkiksi vihollinen tulee esiin valikko josta pelaaja valitsee mitä tehdään

Mahdollista korottaa pelaajan attribuutteja esineiden avulla.

**Sekvenssikaavioita:**

Metodille enmyActn luokassa Logic
![](https://github.com/nybejonn/DungeonCrawler/blob/master/Dokumentaatio/enmyActn_sequence.png)

Metodille plrAtt luokassa Logic
![](https://github.com/nybejonn/DungeonCrawler/blob/master/Dokumentaatio/plrAttack_sequence.png)

**Rakennekuvaus:**

Main luo logiikka ja käyttöliittymä luokat, jotka Runner luokka saa attribuuteikseen. Runner luokan start metodi aloittaa käymään läpi logiikan metodeja tarkistamalla ensin onko nykyisessä lokaatiossa toiminnallisuutta. Tämän jälkeen ohjelma odottaa, että käyttäjä valitsee jonkin toiminnon hiirellä. Jos toiminto on liikkuminen, niin Runner luokka pyytää logiikkaa tarkastamaan voiko siihen suuntaan liikkua. Tämän logiikka tarkistaa GameWorld luokalta. Tämän jälkeen liikutaan jos se on mahdollista ja logiikka luokka tarkistaa GameWorld luokalta onko koordinaateissa toiminnallisuutta.
Jos ruudussa on Enemy luokan olio, niin Runner siirtyy battle metodiin joka kysyy vuorotellen käyttäjältä ja viholliselta toiminnallisuutta. Jos ruudussa on Loot olio se lisätään pelaajan inventorioon. Tämän jälkeen on mahdollista avata inventorio valikko ja valita Loot olio pelaajan käyttöön.
