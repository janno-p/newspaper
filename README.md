# newspaper #

IDU00200 (2014) Vaheülesanne: Veebirakenduse osade vaheline tööjaotus ja DAO-muster


## Ülesande sisu ##

1. Rakenduse põhifunktsionaalsus
    
    * 4 pöördumist
        
        | URL path | HTTP request type | Mida teeb? |
        | --- | --- | --- |
        | /newspaper/s | GET | Näitab kõiki andmebaasi tabelist võetud objekte. |
        | /newspaper/s?id=1 | GET | Näitab sisendparameetri "id" alusel andmebaasist võetud ühe andmeobjekti andmeid andmete muutmist võimaldaval HTML vormil |
        | /newspaper/s?action=save | POST | Salvestab HTML-vormist saadetud andmed andmebaasis (olemasoleva kirje muutmine). Kui HTML vormile sisestatud andmed on ebakorrektsed, siis näitab vormi uuesti koos vigaste andmetega, aga andmebaasi midagi ei salvesta. Näitab kasutajale vormi välja täpsusega, kus tehti viga. |
        | /newspaperservice?id=1&amp;tm=&lt;timestamp&gt;; | GET | Näitab sisendparameetri "id" alusel andmebaasist võetud ühe andmeobjekti "kirjelduse"-nimelise välja sisu andmebaasist võetud objektide tabeli all. Veebiteenus, mille poole pöördub AJAX-i päring. |
        
    * 2 servletti
    * 1 "Data Access Object" ehk DAO-klass
    * 1 Java klass andmeobjektide jaoks
    * 1 validaator
    * 3 veebilehte (JSP)
    
2. Rakenduse siseehitus (klassid, veebilehed) peab järgima etteantud disaini ja komponentide-vahelist
   tööjaotust, nii nagu on kirjeldatud punktis 2.

3. DAO klassile, mida rakenduses kasutate, tuleb kirjutada käsurealt (Apache ant-i või Maven-iga)
   käivitatavad testid.

4. Tuleb joonistada jada-diagramm (sequence diagram), mis näitab, kuidas teie rakenduse komponendid
   suhtlevad omavahel kui kasutaja sisestas andmete muutmise vormile ebakorrektsed andmed ja proovis
   neid salvestada.

5. Tuleb sättida rakenduses tööle logimine nii, et tekiks logifail. Vaata lähemalt punktist 2.
