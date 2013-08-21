package pl.brosbit.model

import java.util.Date
import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Service extends LongKeyedMapper[Service] with IdPK {
    def getSingleton = Service
    
    object idOffer extends MappedLongForeignKey(this, OfferType) {
    	override def dbNotNull_? = true
    }
    object idUser extends MappedLongForeignKey(this, User)
    object begin extends MappedDate(this) {
        override def defaultValue = new Date()
    }
    object end extends MappedDate(this){
        override def defaultValue = new Date()
    }
    object capacity extends MappedInt(this)  
    object newOrder extends MappedBoolean(this)
    object addMonth extends MappedInt(this)
    object addGB extends MappedInt(this)
    def getExpiredTime = end.is.toString
    
}

object Service extends Service with LongKeyedMetaMapper[Service] {
    override def dbTableName = "service"
}