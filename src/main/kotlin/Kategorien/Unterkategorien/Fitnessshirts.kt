package Kategorien.Unterkategorien

import Artikel

class Fitnessshirts (
    artikelname: String,
    markenName: String,
    artikelnummer: String,
    farbbezeichnung: String,
    stückspreisVerkauf: Double,
    verkaufsbestand: Int,
    kundenbewertung: MutableList<Double>,
    var größe: String = "",
    var kragenForm: String= "",
    )

    : Artikel(artikelname,
    markenName,artikelnummer,farbbezeichnung,stückspreisVerkauf,verkaufsbestand,kundenbewertung) {


}