import Kategorien.Unterkategorien.Fitnessshirts
import Kategorien.Unterkategorien.Smartphones
import Kategorien.Unterkategorien.Spielekonsolen
import Kategorien.Unterkategorien.Sportsocken
import KontoAnlegen.Unterkonten.Betreiberkonten
import KontoAnlegen.Unterkonten.Kundenkonten

class OnlineShop(
    var sortiment: MutableList<Artikel> = mutableListOf(
        Fitnessshirts(
            "T-Shirt", "Umbro", "01NKS", "grün",
            19.99, 20, kundenbewertung = mutableListOf(2.5), "Xl", "RundSchnitt",
        ),
        Sportsocken(
            "Läufer", "Carmano", "03LSS", "beige",
            9.99, 50, kundenbewertung = mutableListOf(6.5), 45.5, true,
        ), Spielekonsolen(
            "Xbox One Series S", "Microsoft Xbox", "02XBS",
            "schwarz", 459.99, 10, kundenbewertung = mutableListOf(8.8), true
        ), Smartphones(
            "Apple Iphone 15 Pro", "Apple", "04AIP",
            "blau", 959.99, 2, kundenbewertung = mutableListOf(7.8), 128
        )
    ),

    var kundenkonten: MutableList<Kundenkonten> = mutableListOf(
        Kundenkonten(
            "a", "a", 22, "a", "a",
            zahlungsmethoden = mutableListOf(), warenkorb = mutableListOf()
        ),
        Kundenkonten(
            "k", "k", 11, "k", "k",
            zahlungsmethoden = mutableListOf(), warenkorb = mutableListOf()
        ),
        Kundenkonten(
            "Kemal", "Bicakci", 26, "kbicakci", "kbicakci26",
            zahlungsmethoden = mutableListOf(), warenkorb = mutableListOf()
        ),
        Kundenkonten(
            "Hans", "Müller", 44, "muellerhans", "hmueller44",
            zahlungsmethoden = mutableListOf(), warenkorb = mutableListOf()
        ),
        Kundenkonten(
            "Til", "Meier", 22, "meiertil", "tmeier22",
            zahlungsmethoden = mutableListOf(), warenkorb = mutableListOf()
        )


    ),
    var betreiberkonten: MutableList<Betreiberkonten> = mutableListOf(
        Betreiberkonten("a", "a", 22, "a", "a"),
        Betreiberkonten("Ulf", "Rinte", 59, "rinteUlf", "urinte59")
    )
) {
    var aktuellerLogin: Kundenkonten? = null

    /*Hier habe ich chatgpt zur Hilfe genommen, um die Bedingung zu prüfen!
    * Hierzu habe ich zwei readln() Variabeln für den nutzernamen und das Passwort
    * erstellt. Anschließen habe ich noch eine Variabel erstellt, die überprüft, ob
    * das Passwort und der Nutzernamme in Kundenaccountliste mit den Nutzereingaben
    * der Konsole übereinstimmt.
    * Nachtrag: Tutor hat mir den Funktion find() gezeigt, um die Variabel validUser
    * für die spätere Altersprüfung zu nutzen.*/
    fun kundenLogin(): Kundenkonten? {
        println("Bitte nenne uns dein Nutzernamen: ")
        var userInputUserName = readln()
        println("Nenne uns jetzt dein Passwort:")
        var userInputUserPasswort = readln()
        var validUser = kundenkonten.find { it.nutzerName == userInputUserName && it.passwort == userInputUserPasswort }
        if (validUser != null) {
            this.aktuellerLogin = validUser
            return validUser
        } else {
            println("Fehler in deiner Eingabe!")
            println("Bitte wähle: 0 = Eingabe wiederholen/ 1 = Zum Startbildschirm")
            var userDecision = readln()
            when (userDecision) {
                "0" -> {
                    println("Eingabe wird wiederholt.")
                    Thread.sleep(700)
                }

                "1" -> {
                    println("Du kehrst zum Startbildschirm zurück.")
                    Thread.sleep(700)
                    loginMenu()
                }

                else -> {
                    println("Eingabe nicht erkannt! Du kehrst automatisch zum Startbildschirm zurück!")
                    Thread.sleep(700)
                    loginMenu()
                }
            }

        }
        return kundenLogin()
    }


    /*Diese Methode funktioniert nahezu identisch wie die Methode kundenlogin().*/
    fun betreiberLogin(): Betreiberkonten? {
        println("Bitte nenne uns dein Nutzernamen: ")
        var userInputUserName = readln()
        println("Nenne uns jetzt dein Passwort:")
        var userInputUserPasswort = readln()
        var validUser =
            betreiberkonten.find { it.nutzerName == userInputUserName && it.passwort == userInputUserPasswort }
        if (validUser != null) {
            return validUser
        } else {
            println("Fehler in deiner Eingabe!")
            println("Bitte wähle: 0 = Eingabe wiederholen/ 1 = Zum Startbildschirm")
            var userDecision = readln()
            when (userDecision) {
                "0" -> {
                    println("Eingabe wird wiederholt.")
                    Thread.sleep(700)
                }

                "1" -> {
                    println("Du kehrst zum Startbildschirm zurück.")
                    Thread.sleep(700)
                    loginMenu()
                }

                else -> {
                    println("Eingabe nicht erkannt! Du kehrst automatisch zum Startbildschirm zurück!")
                    Thread.sleep(700)
                    loginMenu()
                }
            }

        }
        return betreiberLogin()
    }


    /*Ursprüngliche sollte diese Methode, Nutzung der Store Funktionalitäten abhängig vom
    * Mindestalter von 12 Jahren des Kunden überprüfen. Diese Methode wurde jedoch nur
    * in der Test.kt verwendet!*/
    fun eingeschränkteNutzung(nutzer: Kundenkonten): Boolean {
        println("Für die Nutzung des Shops wird zusätzlich eine Altersprüfung durchgeführt.")
        Thread.sleep(1000)
        if (nutzer.alter < 12) {
            println("Nutzung verweigert, da Mindestalter von 12 Jahren nicht gegeben ist! ")
            return true
        } else
            println("Zugang gewährt. Viel Spaß beim Schoppen!")
        return false
    }


    /*Mit dieser Methode wird das Kundenkonto erstellt. Abhängig von den Konsolen Eingaben wird
    * eine Instanz zur Klasse Kundenkonten erstellt. Dabei werden die Argumente der Eigenschaften mit
    * Konsolen Eingaben initialisiert. Zuletzt wird die Instanz der Eigenschaft kundenkonten
    * aus dieser Klasse initialisiert. Diese Eigenschaft speichert eine Liste von Elementen
    * des Datentyps Kundenkonten.*/

    fun kontoErstellen() {
        println("Willkommen zur Kontoerstellung. Bitte mach deine Eingaben.")
        println("Vorname:")
        var vorName = readln()
        println("Nachname:")
        var nachName = readln()
        println("Nutzername:")
        var nutzerName = readln()
        println("Passwort:")
        var passwort = readln()
        println("Alter:")
        var alter = readln().toInt()
        println("Hallo $vorName $nachName.")
        println("Dein Nutzername lautet: $nutzerName und dein Passwort ist: $passwort")
        println("Im Startbildschirm kannst du dich mit deinen Nutzernamen und Passwort einloggen.")

        var neuesKundenkonto = Kundenkonten(
            vorName,
            nachName, alter, nutzerName, passwort,
            warenkorb = mutableListOf(),
            zahlungsmethoden = mutableListOf()
        )

        shop.kundenkonten.add(neuesKundenkonto)
    }


    /*Methode sortiert das Sortiment absteigend nach dem Verkaufspreis.
    * Anschließend wir in einer Schleife jeder Artikel mit dem Verkaufspreis
    * wiedergegeben.*/
    fun sortimentSortiertPreis(sortiment: MutableList<Artikel>) {
        println("Artikel nach Preis absteigend sortiert:")
        println()
        var sortimentPreis = sortiment.sortedByDescending { it.stückspreisVerkauf }
        for (artikel in sortimentPreis) {
            println("Artikelname: ${artikel.artikelname} | Preis: ${artikel.stückspreisVerkauf} €.")
        }
        Thread.sleep(1500)
    }

    /*Methode sortiert das Sortiment nach alphabetisch nach dem Artikelnamen.
   * Die Sortierung der Waren nach Alphabet wird als Schleife wiedergegeben.*/

    fun sortimentSortiertAlphabet(sortiment: MutableList<Artikel>) {
        println("Artikel nach Alphabet sortiert:")
        var sortimentAlphabetical = sortiment.sortedBy { it.artikelname }
        for (artikel in sortimentAlphabetical) {
            println("Artikelname: ${artikel.artikelname} | Preis: ${artikel.stückspreisVerkauf} €.")
        }
        Thread.sleep(1500)
    }


    /*Methode sortiert das Sortiment nach höchter durchschnittlicher Kundenbewertung.
     * Die Sortierung der Waren wird absteigend nach höhe der Kundenbewertung wiedergegeben.*/


    fun sortimentSortiertKundenbewertung(sortiment: MutableList<Artikel>) {
        println("Artikel nach Kundenbewertung sortiert:")
        var sortimentKundenbewertung = sortiment.sortedByDescending { it.kundenbewertung.average() }
        for (artikel in sortimentKundenbewertung) {
            println("Artikelname: ${artikel.artikelname} | Kundenbewertung: ${artikel.kundenbewertung} ⭐️ | Preis: ${artikel.stückspreisVerkauf} €.")
        }
        Thread.sleep(1500)
    }


    /*Methode filter das Sortiment nach dem in der Main initialisierten Markennamen.
    Dann habe ich eine if-else Verzweigung eingebaut. Die if-Anweisung wird ausgeführt,
    sobald es mind. 1 Element mit dem jeweiligen Markennamen gibt. Dort wird in einer Schleife
    jeder Artikel wiedergegeben, welcher in seiner Eigenschaft den jeweiligen Markennamen initialisiert
    bekommen hat. Falls meine Markeneingabe mit keiner Eigenschaft der Elemente aus der Liste
    übereinstimmt, erfolgt eine Fehlermeldung.*/

    fun sortimentSortiertMarke(sortiment: MutableList<Artikel>, markenName: String) {
        var sortimentMarke = sortiment.filter { it.markenName == markenName }
        if (sortimentMarke.isNotEmpty()) {
            println("Im Sortiment sind folgende Artikel unter der Marke: $markenName aufgeführt.")
            for (artikel in sortimentMarke) {
                println("Artikelname: ${artikel.artikelname} | Preis: ${artikel.stückspreisVerkauf} €.")
            }
        } else {
            println("Eingabefehler. Marke konnte nicht gefunden werden!")
        }
        Thread.sleep(1500)
    }

    /*Die Methode zum Sortieren nach Unterkategorien. Über eine Nutzereingabe in der Konsole
      wird das Sortiment gefiltert angezeigt. Die sortiment.filterIsInstance<>() Funktion
      filtert die Elemente nach initialisierten Datentyp. Diese Elemente werden anschließend
      wiedergegeben.*/

    fun sortimentSortiertKategorie(sortiment: MutableList<Artikel>) {
        println("Bitte wähle deine Eingabe zur Kategorie sortierten Ansicht:")
        println("1 = Elektronik")
        println("2 = Sport")
        println("0 = Hauptmenü")
        var userNavigation = readln()

        when (userNavigation) {
            "1" -> {
                println("------ELEKTRONIK--------")
                println("SMARTPHONES:")
                var sortimentSmartphone = sortiment.filterIsInstance<Smartphones>()

                for (artikel in sortimentSmartphone) {
                    println("Artikelname: ${artikel.artikelname} | Preis: ${artikel.stückspreisVerkauf} €.")
                }
                println()
                println("SPIELEKONSOLEN:")
                var sortimentSpielekonsolen = sortiment.filterIsInstance<Spielekonsolen>()

                for (artikel in sortimentSpielekonsolen) {
                    println("Artikelname: ${artikel.artikelname} | Preis: ${artikel.stückspreisVerkauf} €.")
                }
                Thread.sleep(1000)
            }

            "2" -> {
                println("------SPORT--------")
                println("SPORTSOCKEN:")
                var sortimentSportsocken = sortiment.filterIsInstance<Sportsocken>()

                for (artikel in sortimentSportsocken) {
                    println("Artikelname: ${artikel.artikelname} | Preis: ${artikel.stückspreisVerkauf} €.")
                }
                println()
                println("FITNESSOBERTEILE:")
                var sortimentFitnessshirts = sortiment.filterIsInstance<Fitnessshirts>()

                for (artikel in sortimentFitnessshirts) {
                    println("Artikelname: ${artikel.artikelname} | Preis: ${artikel.stückspreisVerkauf} €.")
                }
                Thread.sleep(1000)
            }

            "0" -> {
                println("Du kehrst zum Hauptmenü zurück.")
                mainMenu()
            }

            else ->{
                println("Falsche Eingabe. Du kehrst zum Hauptmenü zurück!")
                mainMenu()
            }
        }


    }


}




