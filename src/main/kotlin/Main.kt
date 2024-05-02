
/*Zum Testen des Shops und den Betreiberoptionen nutze folgende Zugangsdaten:
* Nutzername = a
* Passwort = a
*
* Zum Testen der eingeschränkten Nutzung nutze folgenden folgende Zugangsdaten für die Shopanmeldung:
* Nutzername = k
* Passwort = k
*
*/


var shop = OnlineShop()
fun main() {

    try {
        loginMenu()
    } catch (e: Exception) {
        println("Unerwarteter Fehler! Du kehrst zum Startbildschirm zurück.")
        loginMenu()
    }

}

fun loginMenu() {
    var validInput = false

    println("--------WILLKOMMEN--------")
    while (!validInput) {
        println("Um Zugang zum Store zu erhalten, melde dich mit deinen Zugangsdaten an (1) oder erstelle ein Konto (2):")
        println("Für Betreiber Anmeldung drücke Taste (3).")
        val mainUserInput = readln()
        when (mainUserInput) {
            "1" -> {
                if (shop.kundenLogin() != null) {
                    println("Anmeldung erfolgreich!")
                    validInput = true
                } else {
                    Thread.sleep(1000)
                }
                mainMenu()
            }

            "2" -> {
                shop.kontoErstellen()
            }

            "3" -> {
                if (shop.betreiberLogin() != null) {
                    println("Anmeldung erfolgreich!")
                    validInput = true
                } else {
                    Thread.sleep(1000)
                }
                betreiberMenu()
            }

            else -> {
                println("Deine Eingabe kann nicht verarbeitet werden!")
                Thread.sleep(700)
            }

        }

    }

}

fun mainMenu() {
    println("--------HAUPTMENÜ-----------")
    println()
    println("------Unser Warenangebot------")
    for (artikel in shop.sortiment) {
        println("Artikelname: ${artikel.artikelname} | Kundenbewertung: ${artikel.kundenbewertung.average()} ⭐️| Preis:  ${artikel.stückspreisVerkauf} €")
    }
    println()
    println("Zum Navigieren bitte wählen:")
    Thread.sleep(500)
    println(
        """
        1 -> Sortierte Warenansichten
        2 -> Neues Kundenkonto anlegen
        3 -> Zahlungsmethode hinzufügen
        4 -> Artikel bewerten
        5 -> Artikel dem Warenkorb hinzufügen und einkaufen
       
        0 -> Ausloggen
        
    """.trimIndent()
    )
    var userNavigation = readln()

    when (userNavigation) {
        "1" -> {
            println("1 = Preis absteigend, 2 = Beste Kundenbewertung, 3 = Alphabetische Sortierung,")
            println("4 = Kategorisierte Sortierung, 5 = Sortierung nach Marke, 0 = Hauptmenü")
            println("Wähle deine Eingabe:")
            userNavigation = readln()
            when (userNavigation) {
                "1" -> shop.sortimentSortiertPreis(shop.sortiment)
                "2" -> shop.sortimentSortiertKundenbewertung(shop.sortiment)
                "3" -> shop.sortimentSortiertAlphabet(shop.sortiment)
                "4" -> shop.sortimentSortiertKategorie(shop.sortiment)
                "5" -> {
                    println("Bitte gib einen Markennamen ein:")
                    userNavigation = readln()
                    shop.sortimentSortiertMarke(shop.sortiment, userNavigation)
                }

                "0" -> mainMenu()
                else -> println("Falsche Eingabe. Du kehrst zum Hauptmenü zurück!")
            }
            mainMenu()
        }

        "2" -> {
            if (shop.aktuellerLogin!!.alter >= 12) {
                shop.kontoErstellen()
                println("Du kehrst zum Startbildschirm zurück.")
                Thread.sleep(1000)
                loginMenu()
            } else {
                println("Zugang verweigert! Mindestalter von 12 Jahren nicht erfüllt.")
                Thread.sleep(1000)
                mainMenu()
            }

        }

        "3" -> {
            if (shop.aktuellerLogin!!.alter >= 12) {
                shop.aktuellerLogin!!.zahlungsmethode()
                mainMenu()
            } else {
                println("Zugang verweigert! Mindestalter von 12 Jahren nicht erfüllt.")
                Thread.sleep(1000)
                mainMenu()
            }
        }

        "4" -> {
            if (shop.aktuellerLogin!!.alter >= 12) {
                shop.aktuellerLogin!!.artikelBewerten()
                mainMenu()
            } else {
                println("Zugang verweigert! Mindestalter von 12 Jahren nicht erfüllt.")
                Thread.sleep(1000)
                mainMenu()
            }

        }

        "5" -> {
            if (shop.aktuellerLogin!!.alter >= 12) {
                if (shop.aktuellerLogin!!.zahlungsmethoden.size>=1) {
                    shop.aktuellerLogin!!.einkaufswagen()
                    mainMenu()
                } else {
                    println("In deinem Kundenkonto ist keine Zahlungsmethode hinterlegt!")
                    Thread.sleep(700)
                    mainMenu()
                }
            } else {
                println("Zugang verweigert! Mindestalter von 12 Jahren nicht erfüllt.")
                Thread.sleep(1000)
                mainMenu()
            }
        }


        "0" -> {
            println("Vielen Dank für deinen Besuch! Du kehrst zum Startbildschirm zurück.")
            shop.aktuellerLogin!!.warenkorb.clear()
            shop.aktuellerLogin!!.warenkorbMenge.clear()
            println()
            Thread.sleep(1000)
            loginMenu()
        }

        else -> {
            println("Falsche Eingabe! Menü wird neu gestartet. ")
            Thread.sleep(700)
            mainMenu()
        }


    }

}

fun betreiberMenu() {
    println("---------HALLO CHEF------------------")
    println()
    println("Zum Navigieren bitte wählen:")
    println(
        """
        1 -> Warenwirtschaftssystem.
        2 -> Warenbestand
        9 -> Kundenmenü
        0 -> Ausloggen
        
    """.trimIndent()
    )
    var userNavigation = readln()
    when (userNavigation) {
        "1" -> {
            println("1 = Produkt hinzufügen, 2 = Produkt nachbestellen,")
            println("3 = Produkt aus dem Sortiment entfernen, 0 = Hauptmenü")
            println("Wähle deine Eingabe:")
            userNavigation = readln()
            when (userNavigation) {
                "1" -> shop.betreiberkonten.random().produktHinzufügen(shop.sortiment)
                "2" -> shop.betreiberkonten.random().produktNachbestellen(shop.sortiment)
                "3" -> shop.betreiberkonten.random().produktlöschen(shop.sortiment)
                "0" -> mainMenu()
                else -> println("Falsche Eingabe. Du kehrst zum Hauptmenü zurück!")
            }
            betreiberMenu()
        }

        "2" -> {
            shop.sortiment.forEach {
                println(
                    "Artikelname: ${it.artikelname}| Artikelnummer: ${it.artikelnummer} |" +
                            " Lagerbestand: ${it.verkaufsbestand}| Verkaufspreis: ${it.stückspreisVerkauf} €."
                )
            }
            betreiberMenu()
        }

        "9" -> {
            mainMenu()
        }

        "0" -> {
            println("Du wirst abgemeldet. Gute Umsätze!")
            loginMenu()
        }

        else -> {
            println("Deine Eingabe kann nicht verarbeitet werden!")
            betreiberMenu()
        }
    }

}





