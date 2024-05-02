package KontoAnlegen.Unterkonten

import Artikel
import Kategorien.Unterkategorien.Fitnessshirts
import Kategorien.Unterkategorien.Smartphones
import Kategorien.Unterkategorien.Spielekonsolen
import Kategorien.Unterkategorien.Sportsocken
import KontoAnlegen.Konto
import betreiberMenu
import loginMenu

class Betreiberkonten(
    vorName: String,
    nachName: String,
    alter: Int,
    nutzerName: String,
    passwort: String,
) : Konto(vorName, nachName, alter, nutzerName, passwort) {


    /*Diese Methoden bilden das Warenwirtschaftssystem des Stores:*/


    /*Mit dieser Methode kann der Nutzer einem Artikel zum Sortiment hinzufügen.
    * Hierzu müssen einige Konsolen eingaben zu Initialisierung von Argumenten der Eigenschaft einer
    * Objektinstanz gemacht werden. Dabei entscheidet die letzte Eingabe zur Kategoriebezeichnung,
    * nach welcher Klasse das Objekt instanziert wird. Sobald die Eingabe zu Kategoriebezeichnung mit
    * der Unterkategoriebezeichnung im Sortiment übereinstimmt, wird eine Instanz abhängig der Klasse
    * zu Unterkategorie erzeugt und dessen Eigenschaften mit den vorherigen Konsolen eingaben initialisiert.
    * Dadurch wird der neue Artikel direkt der passenden Unterkategorie des Sortiments zugeordnet, um bei der
    * gefilterten Warenansicht nach Unterkategorie auch den neuen Artikel anzuzeigen.
    * Falls jedoch die Kategoriebezeichnung keiner Unterkategorie aus dem Sortiment entspricht, wird zwar
    * der Artikel dem Sortiment hinzugefügt. Jedoch keiner Unterkategorie zugeordnet. Diese geschieht dadurch,
    * weil das neue Produkt nach der Klasse Artikel instanziert wird und die Argumente aus den Konsolen eingaben
    * die Eigenschaft dieser Klasse in der Instanz initialisieren.
    *
    *
    * */

    fun produktHinzufügen(sortiment: MutableList<Artikel>) {
        println("Bitte mache einige Eingaben zum neuen Produkt:")
        println("Artikelname:")
        var artikelName = readln()
        println("Markenname: ")
        var markenName = readln()
        println("Artikelnummer:")
        var artikelNummer = readln()
        println("Farbbezeichnung:")
        var farbbezeichnung = readln()
        println("Verkaufspreis je Stück:")
        var stückpreisVerkauf = readln().toDouble()
        println("Verkaufsbestand:")
        var verkaufsbestand = readln().toInt()
        var kundenBewertung: MutableList<Double> = mutableListOf()
        println("Kategoriebezeichnung:")
        var kategorieBezeichnung = readln()
        var neuesProdukt: Artikel
        when (kategorieBezeichnung) {
            "Smartphones" -> {
                neuesProdukt = Smartphones(
                    artikelName,
                    markenName,
                    artikelNummer,
                    farbbezeichnung,
                    stückpreisVerkauf,
                    verkaufsbestand,
                    kundenBewertung
                )
                sortiment.add(neuesProdukt)

            }

            "Sportsocken" -> {
                neuesProdukt = Sportsocken(
                    artikelName,
                    markenName,
                    artikelNummer,
                    farbbezeichnung,
                    stückpreisVerkauf,
                    verkaufsbestand,
                    kundenBewertung
                )
                sortiment.add(neuesProdukt)

            }

            "Spielekonsolen" -> {
                neuesProdukt = Spielekonsolen(
                    artikelName,
                    markenName,
                    artikelNummer,
                    farbbezeichnung,
                    stückpreisVerkauf,
                    verkaufsbestand,
                    kundenBewertung
                )
                sortiment.add(neuesProdukt)

            }

            "Fitnesshirts" -> {
                neuesProdukt = Fitnessshirts(
                    artikelName,
                    markenName,
                    artikelNummer,
                    farbbezeichnung,
                    stückpreisVerkauf,
                    verkaufsbestand,
                    kundenBewertung
                )
                sortiment.add(neuesProdukt)
            }

             else -> {
                 println("Hinweis: Kategoriebezeichnung: $kategorieBezeichnung ist keiner bestehenden Kategorie zuzuordnen!")
                neuesProdukt = Artikel(
                    artikelName,
                    markenName,
                    artikelNummer,
                    farbbezeichnung,
                    stückpreisVerkauf,
                    verkaufsbestand,
                    kundenBewertung
                )
                sortiment.add(neuesProdukt)
            }

        }
        println("Artikel: $artikelName steht für $stückpreisVerkauf € im Verkauf.")
    }


    /*Mit dieser Methode wird ein Produkt aus dem Warenbestand gelöscht. Sobald
    * die Konsolen Eingaben zum Artikelnamen und zur Artikelnummer mit den initialisierten Eigenschaften
    * meiner instanzierten Argumenten der Eigenschaft Artikelnummer und Artikelname meiner
    * Waren übereinstimmen, wird die jeweilige Instanz und somit der Artikel aus dem
    * Sortiment entfernt (Klasse: Online-Shop).*/

    fun produktlöschen(sortiment: MutableList<Artikel>) {
        println("Bitte mache einige Eingaben zum Auslauf Produkt:")
        println("Artikelname:")
        var artikelName = readln()
        println("Artikelnummer:")
        var artikelNummer = readln()
        var auslaufArtikel = sortiment.find { it.artikelname == artikelName && it.artikelnummer == artikelNummer }
        if (auslaufArtikel != null) {
            sortiment.remove(auslaufArtikel)
            println(
                "Der Artikel: ${auslaufArtikel.artikelname} mit der Artikelnummer: ${auslaufArtikel.artikelnummer} " +
                        "wurde erfolgreich aus dem Sortiment entfernt!"
            )
        } else {
            println("Deine Eingaben konnten keinem Artikel im Sortiment zugeordnet werden!")
            println("Bitte wähle: 0 = Eingabe wiederholen/ 1 = Zum Betreibermenu")
            var userDecision = readln()
            when (userDecision) {
                "0" -> {
                    println("Eingabe wird wiederholt.")
                    Thread.sleep(700)
                    produktlöschen(sortiment)
                }

                "1" -> {
                    println("Du kehrst zum Betreibermenu zurück.")
                    Thread.sleep(700)
                    betreiberMenu()
                }

                else -> {
                    println("Eingabe nicht erkannt! Du kehrst automatisch zum Startbildschirm zurück!")
                    Thread.sleep(700)
                    loginMenu()
                }
            }

        }


    }


    /*Mit dieser Methode wird die Bestandsmenge eins Produkt aus dem Warenbestand erhöht. Sobald
* die Konsolen Eingabe zur Artikelnummer mit den initialisierten Eigenschaften
* meiner instanzierten Argumenten der Eigenschaft Artikelnummer meiner Waren übereinstimmen,
* wird der Verkaufsbestand des Artikels aktualisiert.*/

    fun produktNachbestellen(sortiment: MutableList<Artikel>) {
        println("Bitte mache einige Eingaben, um den Verkaufsbestand des Produkts zu erhöhen:")
        println("Artikelnummer:")
        var artikelNummer = readln()
        println("Einkaufsmenge:")
        var einkaufsmenge = readln().toInt()
        var artikel = sortiment.find { it.artikelnummer == artikelNummer }
        if (artikel != null) {
            println(
                "Verkaufsbestand von ${artikel.verkaufsbestand} des Artikels: ${artikel.artikelname}, " +
                        "Marke: ${artikel.markenName} mit der Artikelnummer: " +
                        "${artikel.artikelnummer} wurde erhöht."
            )
            artikel!!.verkaufsbestand += einkaufsmenge
            println("Neuer Verkaufsbestand: ${artikel!!.verkaufsbestand}.")
        } else {
            println("Deine Eingaben konnten keinem Artikel im Sortiment zugeordnet werden!")
            println("Bitte wähle: 0 = Eingabe wiederholen/ 1 = Zum Betreibermenu")
            var userDecision = readln()
            when (userDecision) {
                "0" -> {
                    println("Eingabe wird wiederholt.")
                    Thread.sleep(700)
                    produktNachbestellen(sortiment)
                }

                "1" -> {
                    println("Du kehrst zum Betreibermenu zurück.")
                    Thread.sleep(700)
                    betreiberMenu()
                }

                else -> {
                    println("Eingabe nicht erkannt! Du kehrst automatisch zum Startbildschirm zurück!")
                    Thread.sleep(700)
                    loginMenu()
                }
            }

        }


    }
}

