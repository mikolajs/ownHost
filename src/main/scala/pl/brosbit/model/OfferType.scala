package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class OfferType extends LongKeyedMapper[OfferType] with IdPK {
    def getSingleton = OfferType
    object name extends MappedString(this, 45)
    object description extends MappedText(this)
    object unitPrice extends MappedInt(this)
    object unitMonth extends MappedInt(this)
    object unitGB extends MappedInt(this)
  }

object OfferType extends OfferType with LongKeyedMetaMapper[OfferType] {
    override def dbTableName = "offertype"
}