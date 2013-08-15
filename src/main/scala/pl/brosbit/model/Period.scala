package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Period extends LongKeyedMapper[Period] with IdPK {
    def getSingleton = Period
    object poczatek extends MappedDate(this)
    object koniec extends MappedDate(this)
}

object Period extends Period with LongKeyedMetaMapper[Period] {
    override def dbTableName = "period"
}