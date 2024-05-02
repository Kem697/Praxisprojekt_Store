package Kategorien.Unterkategorien

import Artikel

class Sportsocken (
    artikelname: String,
    markenName: String,
    artikelnummer: String,
    farbbezeichnung:String,
    stückspreisVerkauf: Double,
    verkaufsbestand: Int,
    kundenbewertung: MutableList<Double>,
    var größe: Double = 0.0,
    var hygieneEttikett: Boolean = true,
    )

    : Artikel(artikelname,
    markenName,artikelnummer,farbbezeichnung, stückspreisVerkauf,verkaufsbestand,kundenbewertung) {


}