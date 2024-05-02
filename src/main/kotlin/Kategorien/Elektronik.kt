package Kategorien

import Kategorien.Unterkategorien.Smartphones
import Kategorien.Unterkategorien.Spielekonsolen




class Elektronik
    (var spielekonsolenProdukte: MutableList<Spielekonsolen> = mutableListOf(),
    var smartphoneProdukte: MutableList<Smartphones> = mutableListOf(),
    var gesetzlicheGewährleistung: Boolean)
{
}
