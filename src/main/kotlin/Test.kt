import Kategorien.Elektronik
import Kategorien.Sport
import Kategorien.Unterkategorien.Fitnessshirts
import Kategorien.Unterkategorien.Smartphones
import Kategorien.Unterkategorien.Spielekonsolen
import Kategorien.Unterkategorien.Sportsocken
import KontoAnlegen.Unterkonten.Betreiberkonten
import KontoAnlegen.Unterkonten.Kundenkonten


/*
* Diese Datei dient dazu die einzelnen Methoden und Eigenschaften aus den erstellten Klassen
* zu Testzwecken aufzurufen. Weiterhin wurden die Objektinstanzen der Klassen
* erstellt.
* */
fun main() {
    var umbroShirt = Fitnessshirts(
        "T-Shirt",
        "Umbro",
        "01NKS",
        "grün",
        19.99,
        20,
        kundenbewertung = mutableListOf(2.5),
        "Xl",
        "RundSchnitt",
    )

    var carmanoSocken = Sportsocken(
        "Läufer",
        "Carmano",
        "03LSS",
        "beig",
        9.99,
        50,
        kundenbewertung = mutableListOf(6.5),
        45.5,
        true,
    )

    var sportwaren = Sport(sportsocken = mutableListOf(), fitnessProdukte = mutableListOf())

    sportwaren.sportsocken.add(carmanoSocken)
    sportwaren.fitnessProdukte.add(umbroShirt)

    /*So rufst du die initialisierten Argumente deiner Waren
    innerhalb der Listeneigenschaften der(Oberkategorie) Klassen auf:*/
    println("In der Kategorien Sport sind: ${sportwaren.sportsocken.elementAt(0).artikelname}")
    println("In der Kategorien Sport sind: ${sportwaren.fitnessProdukte.elementAt(0).artikelname}")


    println("Das Shirt ${umbroShirt.artikelname} von ${umbroShirt.markenName} ist ${umbroShirt.farbbezeichnung}.")
    println("Die Socken ${carmanoSocken.artikelname} von ${carmanoSocken.markenName} ist ${carmanoSocken.farbbezeichnung}.")

    var xbox = Spielekonsolen(
        "Xbox One Series S",
        "Microsoft Xbox",
        "02XBS",
        "schwarz",
        459.99,
        10,
        kundenbewertung = mutableListOf(8.8),
        true
    )


    var iphone = Smartphones(
        "Apple Iphone 15 Pro",
        "Apple",
        "04AIP",
        "blau",
        959.99,
        2,
        kundenbewertung = mutableListOf(7.8),
        128
    )

    var elektronik = Elektronik(spielekonsolenProdukte = mutableListOf(), smartphoneProdukte = mutableListOf(), true)

    println("Die  ${xbox.artikelname} von ${xbox.markenName} ist ${xbox.farbbezeichnung}.")
    println("Das  ${iphone.artikelname} von ${iphone.markenName} ist ${iphone.farbbezeichnung}.")

    elektronik.spielekonsolenProdukte.add(xbox)
    elektronik.smartphoneProdukte.add(iphone)

    println("In der Kategorie Elektronik sind: ${elektronik.spielekonsolenProdukte.elementAt(0).artikelname}")
    println("In der Kategorien Elektronik sind: ${elektronik.smartphoneProdukte.elementAt(0).artikelname}")


    var hansMueller = Kundenkonten(
        "Hans", "Müller", 44, "muellerhans", "hmueller44",
        zahlungsmethoden = mutableListOf(), warenkorb = mutableListOf()
    )
    var tilMeier = Kundenkonten(
        "Til", "Meier", 22, "meiertil", "tmeier22",
        zahlungsmethoden = mutableListOf(), warenkorb =  mutableListOf()
    )
    var mirkoMeier = Kundenkonten(
        "Mirko", "Meier", 10, "meiermirko", "mmeier10",
        zahlungsmethoden = mutableListOf(), warenkorb =  mutableListOf()
    )

    /*Test der Zahlungshinzufügungsmethoden:*/
   /* hansMueller.zahlungsmethode(hansMueller)
    println(hansMueller.zahlungsmethoden)
    hansMueller.zahlungsmethode(hansMueller)*/
    println(hansMueller.zahlungsmethoden)
    println()
    /*Test der Funktion, um den Warengesamtwert aufzurufen.*/
/*
    hansMueller.warenkorbPreis()
*/
    println()

    /*Test der Funktion, zu Bewertung eines Artikels (Methode hat readln().*/
/*
    hansMueller.artikelBewerten(xbox)
*/


    var ulfRintel = Betreiberkonten("Ulf", "Rinte", 59, "rintelUlf", "urintel59")

    /*Mit der Variabel shop initialisiere die Eigenschaften mit Waren, Kunden- und Betreiberkonten.*/
    var shop = OnlineShop(
        sortiment = mutableListOf(xbox, iphone, umbroShirt, carmanoSocken),
        kundenkonten = mutableListOf(hansMueller, tilMeier),
        betreiberkonten = mutableListOf(ulfRintel)
    )

    /*Mit dieser Schleife kann ich eine bestimmte Eigenschaft meines Sortiments
    * ausdrucken.*/
    println()
    for (artikel in shop.sortiment) {
        println("Artikel: ${artikel.artikelname}, ${artikel.stückspreisVerkauf} €.")
    }
    println()
    for (kunde in shop.kundenkonten) {
        println("Kundenkonto: ${kunde.vorName} ${kunde.nachName}, ${kunde.alter} Jahre alt.")
    }

    /*Methodentest: Einen Artikel aus dem Sortiment entfernt.*/
    println(shop.sortiment.elementAt(0).artikelname)
    ulfRintel.produktlöschen(shop.sortiment)

    println(shop.sortiment.elementAt(0).artikelname)

    /*Methodentest: Verkaufsbestand eines Artikels erhöhen.*/
    println(iphone.verkaufsbestand)
    ulfRintel.produktNachbestellen(shop.sortiment)
    println(iphone.verkaufsbestand)
    println()

    /*Methodentest: Sortiment, um einen neuen Artikel ergänzen.
    * Um eine nachträgliche Aktulisierung der Produktkategorien zu
    * ermöglichen, sollte das neueProdukt mit dem Datentyp zur
    * jeweiligen Produktunterkategorie initialisiert werden (hier
    * Spielekonsolen):*/

    /*ulfRintel.produktHinzufügen(
        shop.sortiment, neuesProdukt = Spielekonsolen(
            "Nintendo Switch", "Nintendo",
            "05NSS", "rot", 309.99, 2,
            2.5, false
        )
    )*/

    for (artikel in shop.sortiment) {
        println("Artikel: ${artikel.artikelname}, ${artikel.stückspreisVerkauf} €.")
    }
    /*Problem: Wie kann ich neue Artikel ohne vorheriger Instanzierung meiner Kategorien hinzufügen?*/

    /*Schritt 1: Ich filter in meinem Sortiment alle Elemente vom Typ/Klasse Spielekonsole.
    * Schritt 2: Dann suche ich das Element anand einer Eigenschaft der Klasse (hier: markenname).
    * Sobald mein zugewiesenes Element mit dem zugewiesenem Argument im Markenamen übereinstimmt,
    * kriege ich die Identifikationsnummer der Klasse raus. Anschließend speicher ich dies als Varibal ab. ()*/
    var nintendoSwitch = shop.sortiment.filterIsInstance<Spielekonsolen>().find { it.markenName == "Nintendo" }
    /*
        Schritt 3: Ich füge die Variabel meiner Liste an Elektronikartikeln zu.
    */
    elektronik.spielekonsolenProdukte.add(nintendoSwitch!!)

    /*Hier rufe ich das neue Element auf:*/
/*
    println(elektronik.spielekonsolenProdukte.find { it.artikelname == "Nintendo Switch"}?.artikelname)
*/

    println()

    /*Test der Funktion zum Aufrufen des Sortiments nach dem Alphabet beginnend mit A.*/
    shop.sortimentSortiertAlphabet(shop.sortiment)
    println()
    /*Test der Funktion zum Aufrufen des Sortiments sortiert nach absteigendem Verkaufspreis.*/
    shop.sortimentSortiertPreis(shop.sortiment)
    println()
    /*Test der Funktion zum Aufrufen des Sortiments sortiert nach Bestbewertung.*/
    shop.sortimentSortiertKundenbewertung(shop.sortiment)
    println()
    /*Test der Funktion zum Aufrufen des Sortiments nach Marke.*/
    shop.sortiment.add(xbox)
    println()
    shop.sortimentSortiertMarke(shop.sortiment, "Apple")
    println()
    /*Test der Funktion zum Aufrufen des Sortiments nach Unterkategorien.*/
    shop.sortimentSortiertKategorie(shop.sortiment)

    /*Test der Funktion zur eingeschränkten Nutzung des Shops. Diese Funktion
    * wurde in der Main.kt nicht verwendet, da sich im Verlauf des Programmierens
    * alternative und effizientere Lösungsansätze zur Durchführung der Altersprüfung
    * im Shop programmiert habe. Hierbei habe ich nämlich eine if-else Verzweigungen in der Funktion
    * mainMenu() verwendet. Mit dem aktuellen Login kann man alle Funktionalitäten
    * des Shops ausschöpfen, sofern die initialisierte Eigenschaft Alter des aktuellen Logins über
    * 12 liegt (siehe große when Verzweigung Zeile 103-137 der Main.kt).  */
    shop.eingeschränkteNutzung(mirkoMeier)
    shop.eingeschränkteNutzung(hansMueller)





}