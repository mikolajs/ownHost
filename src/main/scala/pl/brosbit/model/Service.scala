package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Service extends LongKeyedMapper[Service] with IdPK {
    def getSingleton = Service
    object owncloud extends MappedString(this, 45)
    object idOffer extends MappedLongForeignKey(this, Offer)
    object idPeriod extends MappedLongForeignKey(this, Period)
    object idUser extends MappedLongForeignKey(this, User)
}

object Service extends Service with LongKeyedMetaMapper[Service] {
    override def dbTableName = "service"
}