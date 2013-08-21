package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class OrderDone extends LongKeyedMapper[OrderDone] with CreatedUpdated with IdPK {
    def getSingleton = OrderDone
    object idUser  extends MappedLongForeignKey(this, User)
    object isNew extends MappedBoolean(this)
    object idService extends MappedLongForeignKey(this, Service)
    object addMonth extends MappedInt(this)
    object addGB extends MappedInt(this)
}

object OrderDone extends OrderDone with LongKeyedMetaMapper[OrderDone] {
    override def dbTableName = "orderdone"
}