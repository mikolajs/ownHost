package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Usluga extends LongKeyedMapper[Usluga] with IdPK {
    def getSingleton = Usluga
    object owncloud extends MappedString(this, 45)
    object id_oferta extends MappedLongForeignKey(this, Oferta)
    object id_okres extends MappedLongForeignKey(this, Okres)
    object id_klient extends MappedLongForeignKey(this, Klient)
}

object Usluga extends Usluga with LongKeyedMetaMapper[Usluga] {
    override def dbTableName = "usluga"
}