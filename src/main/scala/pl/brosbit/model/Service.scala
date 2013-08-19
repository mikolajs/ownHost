package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Service extends LongKeyedMapper[Service] with IdPK {
    def getSingleton = Service
    //object owncloud extends MappedString(this, 45)????????????
    object idOffer extends MappedLongForeignKey(this, OfferType)
    object idUser extends MappedLongForeignKey(this, User)
    object begin extends MappedDate(this)
    object end extends MappedDate(this)
    object capacity extends MappedInt(this)
    def getExpiredTime = end.is.toString
    
}

object Service extends Service with LongKeyedMetaMapper[Service] {
    override def dbTableName = "service"
}