package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Okres extends LongKeyedMapper[Okres] with IdPK {
    def getSingleton = Okres
    object poczatek extends MappedDate(this)
    object koniec extends MappedDate(this)
}

object Okres extends Okres with LongKeyedMetaMapper[Okres] {
    override def dbTableName = "okres"
}