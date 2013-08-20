package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Service extends LongKeyedMapper[Service] with IdPK {
    def getSingleton = Service
    
    object idOffer extends MappedLongForeignKey(this, OfferType) {
    	override def dbNotNull_? = true
    }
    object idUser extends MappedLongForeignKey(this, User)
    object begin extends MappedDate(this)
    object end extends MappedDate(this)
    object capacity extends MappedInt(this)
    object isActive extends MappedBoolean(this)
    object newOrder extends MappedBoolean(this)
    object addMonth extends MappedInt(this)
    object addGB extends MappedInt(this)
    def getExpiredTime = end.is.toString
    
}

object Service extends Service with LongKeyedMetaMapper[Service] {
    override def dbTableName = "service"
}