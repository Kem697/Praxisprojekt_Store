package KontoAnlegen.Unterkonten

import Artikel
import KontoAnlegen.Konto
import shop

class Kundenkonten(
    vorName: String,
    nachName: String,
    alter: Int,
    nutzerName: String,
    passwort: String,
    var zahlungsmethoden: MutableList<String>,
    var warenkorb: MutableList<Artikel>,
    var warenkorbMenge: MutableList<Int> = mutableListOf(),
    var kundenGuthaben: Double? = null ?: 0.00
) : Konto(vorName, nachName, alter, nutzerName, passwort) {


    /*
    * Diese Methode war die größte Herausforderung beim Programmieren, da viele Verrechnungen und
    * Initialisierungen in den Eigenschaften der Klassen während des Programmflusses passieren. Zunächst einmal wird als Schleife das Sortiment
    * inklusiver weiterer initialisierten Argumente der Eigenschaften der Klasse Artikel angezeigt.
    * Anschließend kann der Nutzer über eine Konsolen eingabe, den Artikel zum Warenkorb hinzufügen.
    * Dieser Warenkorb ist eine zunächst einmal als leere Liste in der Eigenschaft warenkorb von der Klasse Kundenkonten initialisiert.
    * Sobald eine korrekte Eingabe erfolgt ist, wird nach der Stückzahl des Artikels nachgefragt. Hierbei soll über
    * die Konsole ein Int Wert eingegeben werden, welcher danach den Eingabewert einer leeren veränderlichen Liste der Eigenschaft warenkorbmenge
    * aus dieser Klasse hinzugefügt. Wenn dieser Eingabewert (gewünschte Stückzahl) mindestens dem initialisierten Wert der Eigenschaft
    * lagerbestand der Klasse Artikel übereinstimmt, wird der Artikel zum Warenkorb hinzugefügt. Andernfalls werden die zuvor hinzugefügten Elemente
    * ,gewählter Artikel und Stückzahl, aus den Eigenschaften warenkorb und warenkorbMenge entfernt. Diese Schritte sind unerlässlich, damit
    * zur Berechnung des Geldbetrags und der Aktualisierung des Warenbestands nach dem Kauf keine Fehler entstehen.
    * Zur Anzeige des Warenkorbs wird die Eigenschaft warenkorb als Schleife iteriert. Abhängig vom Indexwert des warenkorbs, wird der
    * jeweilige Artikel und einige Eigenschaften angezeigt. Gleichzeit wird auch der Indexwert der Liste warenkorbMenge abhängig von
    * der Iterator Position iteriert, um genau das Anzuzeigen, was in den beiden vorherigen Eingaben geschehen ist. Anschließend
    * kann der Nutzer einen weiteren Artikel dem Warenkorb hinzufügen, oder zur Kasse gehen. Der Bezahlpreis wird innerhalb einer
    * Schleife berechnet, indem der Iterator abhängig von der Indexposition das jeweilige Element aus der Eigenschaft warenkorb und dessen initialisierter
    * Stückpreis mit dem jeweiligen Indexwert der Eigenschaft warenkorbMenge multipliziert.
    *
    * Nachtrag: In der Klasse Kundenkonten.kt wurde eine Eigenschaft kundenGuthaben erstellt und mit null und dem Rückgabewert 0.00 initialisiert.
    * Dieser Wert kann über die Konsolen eingabe verändert werden, nach dem ein eingeloggter Kunde über 12 Jahren
    * die Zahlungsmethode Geschenkkarte ausgewählt hat. Hierbei wird die leere Liste der Eigenschaft zahlungsmethoden
    * mit dem Element Geschenkkarte initialisiert. Anschließend fordert das Programm als Konsolen eingabe einen Doublewert,
    * welcher das Kundeguthaben zur Geschenkkarte darstellt. Wenn die Geschenkkarte die einzige hinterlegte Zahlungsmethode
    * im Kundenkonto ist, wird im Kasierprozess der Rechnungsbetrag erst beglicht, wenn ausreichend Kundenguthaben zur Begleichung
    * des Rechnungsbetrags vorhanden ist. Ansonsten wird die Meldung mangelnder Deckung der Geschenkkarte von der Konsole ausgegeben.
    * Die Überprüfung dieser Bedingung wird in der implizierten if-else Verzweigungen dieser Methode ab Zeile
    * 102 durchgeführt.
    *
    * Bei allen anderen hinterlegten Zahlungsmethoden läuft der Kassierprozess ohne der Verrechnung von Kundenguthaben normal weiter.
    *
    * Die Methode enthält eine große do-while Schleife, die abhängig von dem Boolean Wert der Variabel validInput ist. Dadurch kann der
    * Nutzer immer wieder neue Artikel seinem Warenkorb hinzufügen. Die Variabel ist standardmäßig auf false
    * initialisiert und verändert sich erst auf true, sobald der Warenkorb abkassiert worden ist. Dann ist die Methode passend zur
    * Bezahlung ist Einkaufs abgeschlossen.
    *
    * */

    fun einkaufswagen() {
        var validInput = false

        do {
            var index = 0
            println("-----------WAREN--------")
            for (artikel in shop.sortiment) {
                println("${index + 1}-------> Artikel: ${artikel.artikelname} | Marke: ${artikel.markenName} | Preis: ${artikel.stückspreisVerkauf} € ")
                index++
            }
            println()
            println("Bitte wähle eine Zahl, um Artikel in den Warenkorb hinzuzufügen:")
            var gewählterArtikel = readln()
            when (gewählterArtikel) {
                "1" -> this.warenkorb.add(shop.sortiment.first())
                "2" -> this.warenkorb.add(shop.sortiment.elementAt(index - 3))
                "3" -> this.warenkorb.add(shop.sortiment.elementAt(index - 2))
                "4" -> this.warenkorb.add(shop.sortiment.last())
                else -> {
                    println("Fehleingabe! Wähle zwischen 1-4.")
                    Thread.sleep(1000)
                    return einkaufswagen()
                }
            }
            println("Wie viel Stück?")
            var wunschMenge = readln().toInt()
            warenkorbMenge.add(wunschMenge)
            if (wunschMenge <= shop.sortiment.elementAt(gewählterArtikel.toInt() - 1).verkaufsbestand) {
                println("In deinem Warenkorb befinden sich folgende Artikel: ")
                for (artikelIndex in this.warenkorb.indices) {
                    println("| Artikel: ${warenkorb[artikelIndex].artikelname} | Menge: ${warenkorbMenge[artikelIndex]} | Stückpreispreis: ${warenkorb[artikelIndex].stückspreisVerkauf} €.")
                }
                shop.sortiment.elementAt(gewählterArtikel.toInt() - 1).verkaufsbestand -= warenkorbMenge.last()
                println("Möchtest du sonst noch etwas einkaufen (ja/nein)?")
                var userChoice = readln()
                if (userChoice == "nein") {
                    println("------QUITTUNG-------")
                    println("Artikelliste: ")
                    for (warenkorbIndex in this.warenkorb.indices) {
                        println("${warenkorb[warenkorbIndex].artikelname} | ${warenkorb[warenkorbIndex].stückspreisVerkauf} € | Menge: ${warenkorbMenge[warenkorbIndex]} | Gesamtpreis: ${warenkorb[warenkorbIndex].stückspreisVerkauf * warenkorbMenge[warenkorbIndex]}")
                    }
                    var gesamtPreis = 0.00
                    for (preisIndex in this.warenkorb.indices) {
                        gesamtPreis += warenkorb[preisIndex].stückspreisVerkauf * warenkorbMenge[preisIndex]
                    }
                    if (shop.aktuellerLogin!!.zahlungsmethoden.size==1 && shop.aktuellerLogin!!.zahlungsmethoden.contains("Geschenkkarte") && shop.aktuellerLogin!!.kundenGuthaben!! < gesamtPreis) {
                        println("---------------")
                        println("Du hast nicht genug Guthaben auf der Geschenkkarte um den Zahlungsbetrag zu begleichen!")
                        println(
                            "Zahlungsbetrag von: ${
                                gesamtPreis.toString().format("%.2f").toDouble()
                            } € kann nicht mit Geschenkkartenbetrag von : $kundenGuthaben € bezahlt werden."
                        )
                        warenkorb.clear()
                        warenkorbMenge.clear()
                        println()
                    } else if (shop.aktuellerLogin!!.zahlungsmethoden.size==1 && shop.aktuellerLogin!!.zahlungsmethoden.contains("Geschenkkarte") && shop.aktuellerLogin!!.kundenGuthaben!! >= gesamtPreis) {
                        kundenGuthaben = kundenGuthaben?.minus(gesamtPreis)
                        println("---------------")
                        Thread.sleep(1000)
                        println("Zahlungsbetrag: ${gesamtPreis.toString().format("%.2f").toDouble()} €")
                        println("Restguthaben auf Geschenkkarte: ${kundenGuthaben.toString().format("%.2f").toDouble()} €")
                        println("Vielen Dank für deinen Einkauf! ")
                        validInput = true
                        Thread.sleep(1400)
                    } else {
                        println("---------------")
                        Thread.sleep(1000)
                        println("Zahlungsbetrag: ${gesamtPreis.toString().format("%.2f").toDouble()} €")
                        println("Vielen Dank für deinen Einkauf!")
                        validInput = true
                        Thread.sleep(1400)
                    }
                } else if (userChoice == "ja") {
                    println("Schau dich ruhig weiter um.")
                    Thread.sleep(1000)
                } else {
                    println("Eingabefehler!")
                }
            } else {
                if (warenkorb.size >= 1 && warenkorbMenge.size >= 1) {
                    println("Gewünschte Stückzahl ist nicht auf Lager!")
                    warenkorb.removeLast()
                    warenkorbMenge.removeLast()
                    Thread.sleep(1000)
                } else {
                    println("Gewünschte Stückzahl ist nicht auf Lager!")
                    warenkorb.clear()
                    warenkorbMenge.clear()
                    Thread.sleep(1000)
                }
            }
        } while (!validInput)

    }


    /*In dieser Methode kann der Nutzer seinem Konto eine neue Zahlungsmethode hinzufügen.
    * Der Eingabewert wird in der Instanz zum Kundenkonto der Eigenschaft zahlenmethode
    * als Argument initialisiert. */


    fun zahlungsmethode() {
        println("Bitte wähle, um eine eine Zahlungsmethode deinem Kundenkonto hinzuzufügen:")
        println("1 = Paypal")
        println("2 = Lastschrift")
        println("3 = Kreditkarte")
        println("4 = Geschenkkarte")
        println("Mit Taste 0 kehrst du zum Hauptmenü zurück.")
        var zahlungsart = readln()

        when (zahlungsart) {
            "1" -> {
                this.zahlungsmethoden.add("Paypal")
                println("Zahlungsmethode: ${this.zahlungsmethoden.last()} wurde deinem Kundenkonto hinzufügt.")
                Thread.sleep(1000)
            }

            "2" -> {
                this.zahlungsmethoden.add("Lastschrift")
                println("Zahlungsmethode: ${this.zahlungsmethoden.last()} wurde deinem Kundenkonto hinzufügt.")
                Thread.sleep(1000)
            }

            "3" -> {
                this.zahlungsmethoden.add("Kreditkarte")
                println("Zahlungsmethode: ${this.zahlungsmethoden.last()} wurde deinem Kundenkonto hinzufügt.")
                Thread.sleep(1000)
            }

            "4" -> {
                this.zahlungsmethoden.add("Geschenkkarte")
                println("Bitte nenne den Aufladebetrag:")
                var aufladeBetrag = readln().toDoubleOrNull()
                if (aufladeBetrag != null) {
                    kundenGuthaben = kundenGuthaben?.plus(aufladeBetrag)
                    println("Zahlungsmethode: ${this.zahlungsmethoden.last()} wurde deinem Kundenkonto hinzufügt.")
                    println("Der Geldbetrag deiner Geschenkkarte beträgt: $kundenGuthaben €")
                    Thread.sleep(1000)
                } else {
                    println("Du hast keinen korrekt Betrag eingegeben. Du kehrst zum Hauptmenü zurück")
                    Thread.sleep(1000)
                }

            }

            "0" -> {
                println("Du kehrst zum Hauptmenü zurück.")
                Thread.sleep(800)
            }

            else -> {
                println("Deine Eingabe ist keiner Zahlungsmethode zu zuordnen! Bitte mache eine korrekte Eingabe.")
                Thread.sleep(700)
                zahlungsmethode()
            }
        }

    }


    /*In dieser Methode kann der Nutzer einen Artikel aus dem
    * Sortiment bewerten. Hierbei muss über eine Konsoleneingabe
    * der Name des Artikels eingegeben werden. Sobald Konsoleneingabe
    * einem der initialisierten Artikelnamen des Sortiments entspricht,
    * kann der Nutzer über eine weitere Konsoleneingabe den Artikel bewerten.
    * Sobald dies geschieht, verändert sich der Wert der Eigenschaft kundenbewertung
    * des Artikels, weil aus einer veränderlichen List von Double Werten der Durchschnittswert
    * wiedergegeben wird.
    *
    *  */

    fun artikelBewerten() {
        println("Bitte nenne den Artikelnamen des Artikels den du bewerten möchtest:")
        var artikelName = readln()
        var suchArtikel = shop.sortiment.find { it.artikelname == artikelName }
        if (suchArtikel != null) {
            println("Wie bewerten Sie den Artikel bei einer Skala von 1.0 - 10.0 ?: ${suchArtikel.artikelname}?")
            var kundenBewertung = readln().toDoubleOrNull()
            if (kundenBewertung !=null){
            suchArtikel!!.kundenbewertung += kundenBewertung
            println("Artikel:${suchArtikel.artikelname} wurde von Ihnen mit $kundenBewertung bewertet. Vielen Dank für Ihre Bewertung!")
            } else {
                println("Eingabe Fehler. Gib einen Wert zwischen der Skala 1.0 - 10.0 ein.")
                Thread.sleep(800)
                artikelBewerten()
            }
        } else {
            println("Keiner Ihrer Eingaben ist einem Artikel von unserm Sortiment zuzuordnen!")
        }
    }


}