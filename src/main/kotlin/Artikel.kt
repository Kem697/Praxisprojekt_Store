open class Artikel(
    var artikelname: String,
    var markenName: String,
    var artikelnummer: String,
    var farbbezeichnung: String,
    var stückspreisVerkauf: Double,
    var verkaufsbestand: Int,
    var kundenbewertung: MutableList<Double>,
) {
}