Rendszerterv
=====

A rendszer célja
-------
A rendszer célja létrehozni egy olyan androidos alkalmazást, amelyen a felhasználó zenét tud hallgatni, saját lejátszási listákat létrehozni.

Egy felhasználó annyi zenét tud majd a saját lejátszási listájába beletenni, amennyit az előfizetése megenged. Abból tud majd zenéket eltávolítani illetve újabbakat hozzáadni. Hozzáadni zenéket majd csak a rendszerben bent levő zenék közül lehet megtenni.

Minden felhasználó aki elindítja a programot, először vagy belép saját fiókjával, vagy amennyiben ez még nincs neki, egy felhasználónév, egy email cím és egy jelszó megadásával regisztrálhat, majd ezekkel az adatokkal be is léphet.

Projekt terv
-----
- ### Projektszerepkörök, felelőségek ###
    - A projektszerepkörök és felelőségek a feladatok típusától, összetettségétől és az illető hozzáértésétől is függ. A feladat elosztás fő szempontjai az egyéni igények illetve a kompetenciák függvényében történik. Továbbá, ha valamelyik csapattag az egyéni fejlődése érdekében egyéb technológiát kíván használni, erre természetese lehetősége van a kompatibilitás biztosításának függvényében.
    - Valamilyen szinten ezek a szerepkörök átjárhatók inspiráció, segítség nyújtás vagy egyéni igények alapján a csapat kollektív fejlődése és csapatépítés érdekében.
    
- ### Projektmunkások és felelőségek ###
    - ### Front-end munkálatok: ### 
    
        - Résztvevői: Huszka Dániel
        - Feladatuk az alkalmazás kinézetének létrehozása.
        
	- ### Back-end munkálatok: ### 
        - Résztvevői: Lehoczki Gergő Péter és Huszka Dániel
        - Feladatuk az alkalmazás funkcionalitásának biztosítása, kommunikáció az adatbázissal, inputok fogadása és feldolgozása a felhasználói felületről.

	- ### Adatbázis munkálatok: ### 
        - Résztvevői: Lehoczki Gergő Péter
        - Feladatuk az alkalmazás számára biztosítani egy átgondolt és rendezett adatbázist az adatok biztonságos tárolásának garantálása mellett.
        
- ### Ütemterv ###
    - Meetingek igényszerint spontán egyéb időpontokban megtartva. Fejlesztés részben egyéni ütemben, a többieket ezzel nem hátrálatva.
    
- ### Mérföldkövek ###
	 - Dokumentáció elkészítése.
	 - Funkciók létrehozása és manuális, kisebb tesztelések a működés szempontjából. Egy funkcionális demó alkalmazás elkészítése.
	 - Program teljeskörű tesztelése. Ennél a mérföldkőnél rendelkezésre fog állni a program közel egésze, illetve működőképes lesz.
	 - Kényelmi funkciók kialakítása és tökéletesítése, illetve a dizájn kerül beépítésre.

Üzleti folyamatok modellje
-------

- Üzleti szereplők:
	- Felhasználók: Felhasználóvá a felületen történő regisztrációval válhat a látogató.

- Üzleti folyamatok: 
  - Felhasználó regisztrációja: A felhasználó egy regisztrációs oldal kitöltésével tud regisztrálni. A sikeres regisztrációhoz az alábbi adatok megadása szükséges.    
    - Felhasználónév
    - Jelszó (A jelszavak titkosítás után kerülnek eltárolásra az adatbázisban)
  - Felhasználó azonosítása: A felhasználó a bejelentkezés során a megfelelő mezőkbe megadja felhasználónevét és jelszavát, majd a rendszer ellenőrzi, hogy a felhasználó szerepel-e az adatbázisban. Amennyiben szerepel, a rendszer ellenrőzi a felhasználótól érkezett jelszó egyezik-e az adatbázisban tárolta. Ha az azonosítás sikeres, a felhasználó a nyitó oldalára kerül. Amennyiben az azonosítás sikertelen, a rendszer értesíti, hogy miért volt sikertelen a bejelentkezés.

Követelmények
------
- Dizájn
    - Színes dizájn
- Regisztráció / Bejelentkezés
    - A felhasználó tudjon bejelentkezni, valamint regisztrálni, ha nincsen még fiókja. Felhasználó névvel, valamint egy tetszőlegesen megadott jelszóval.
- Lejátszási listák
    - A lejátszási listák annyi zenét tartalmaznak, amennyit a felhasználó előfizetése megenged neki.
    -  A megoldott lejátszási listák zenéit minden felhasználónak külön egy adatbázisban tárolni, melyből könnyen lehet csoportosítani.
- Adatbázis
    - Relációs adatbázis az adatok tárolására a megoldott lejátszási listákra kapcsolatosan minden felhasználóra.

Funkcionális terv
-------
- Rendszerszereplők
  - Sima felhasználó.
  
- Rendszerhasználati esetek és lefutásaik
  - Sima felhasználó
    - Regisztrálhat
	- Készíthet saját lejátszási listát
	- Saját lejátszási listájából törölhet, illetve adhat is hozzá zenét
	- Saját lejátszási listájából is, illetve az összes zenéből is hallgathat zenét
- Menü hierarchiák
    - Bejelentkező/Regisztrációs oldal
	  - Sikeres bejelentkezés vagy regisztrálás után a főoldalra irányít minket át az oldal
	- Főoldal
	  - Ezt az oldalt csak a bejelentkezett felhasználók láthatják
	  - Alul kiválaszthatjuk a lejátszani kívánt zenét.
	  - Az oldal közepén láthatunk gombokat, melyekkel a zenét kezelhetjük.
	  - Alul a zenék alatt még látható egy gomb, mellyel megnyithatjuk saját lejátszási listánkat, amely ha még nincs, akkor egy olyan oldalra visz minket az alkalmazás, ahol elkészíthetjük azt.

Fizikai környezet
---------
 - Adatbázis: Oracle-MySQL
 - Programozási nyelvek
   - Back-end: Java
   - Adatbázis: SQL, PL/SQL
 - Fejlesztői eszközök
   - Android Studio
   - XAMPP (Apache és MySQL service)
   - MySQL workbench
 - Verziókezelést segítő eszközök
   - Github Desktop
 - Az alkalmazás jellege
   - Androidos alkalmazás
 - Szükséges rendszerkövetelmények
   - Androidos telefon

Adatbázis terv
------
A szükséges adatokat MySQL adatbázisban tároljuk, itt lesznek a felhasználó és lejátszási listák adatai.

Az adatbázis adattáblái:

**musics Tábla** (A zenéket tartalmazó tábla)<br>
| Mező              	| Típus  	|        Leírás                	     |
|-------------------	|----------	|-----------------------------	   |
| id          	      | int     	| azonosító, elsődleges kulcs      |
| raw_id               	| varchar  	| egyedi id a kódolás segítésére                 |
| title          	  | varchar      	| a zene címe                      |
| author            	| varchar  	| a zene szerzője      |
| length      	| time  	| a zene hossza      |
| created_at 	| timestamp 	| hozzáadás dátuma              |
| update_at 	| timestamp 	| frissítés dátuma leírása              |

**playlist Tábla** (A lejátszási listákat tartalmazó tábla)<br>
| Mező              	| Típus  	|        Leírás                	     |
|-------------------	|----------	|-----------------------------	   |
| id          	      | int     	| azonosító, elsődleges kulcs      |
| name          	  | varchar      	| a lejátszási lista címe                      |
| created_at 	| timestamp 	| hozzáadás dátuma              |
| update_at 	| timestamp 	| frissítés dátuma leírása              |
