package Kategorien.Unterkategorien

import Artikel

class Smartphones (
    artikelname: String,
    markenName: String,
    artikelnummer: String,
    farbbezeichnung:String,
    stückspreisVerkauf: Double,
    verkaufsbestand: Int,
    kundenbewertung: MutableList<Double>,
    var speicherKapazität: Int =0,
   )

    : Artikel(artikelname,
    markenName,artikelnummer,farbbezeichnung,stückspreisVerkauf,verkaufsbestand,kundenbewertung) {


}