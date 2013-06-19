package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Oferta extends LongKeyedMapper[Oferta] with IdPK {
    def getSingleton = Oferta
    object nazwa extends MappedString(this, 45)
    object cena extends MappedString(this, 45)
}

object Oferta extends Oferta with LongKeyedMetaMapper[Oferta] {
    override def dbTableName = "Oferta"
}