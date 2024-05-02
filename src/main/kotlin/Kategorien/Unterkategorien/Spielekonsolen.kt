package Kategorien.Unterkategorien

import Artikel

class Spielekonsolen (
    artikelname: String,
    markenName: String,
    artikelnummer: String,
    farbbezeichnung:String,
    stückspreisVerkauf: Double,
    verkaufsbestand: Int,
    kundenbewertung: MutableList<Double>,
    var altersbeschränkung: Boolean = false,

)

    : Artikel(artikelname,
    markenName,artikelnummer,farbbezeichnung,stückspreisVerkauf,verkaufsbestand,kundenbewertung) {


}