package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Offer extends LongKeyedMapper[Offer] with IdPK {
    def getSingleton = Offer
    object name extends MappedString(this, 45)
    object price extends MappedString(this, 45)
}

object Offer extends Offer with LongKeyedMetaMapper[Offer] {
    override def dbTableName = "offer"
}