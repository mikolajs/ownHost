package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Order extends LongKeyedMapper[Order] with CreatedUpdated with IdPK {
    def getSingleton = Order
    object user  extends MappedString(this, 45)
    object offer extends MappedString(this, 45)
    object isNew extends MappedBoolean(this)
    object idService extends MappedLongForeignKey(this, Service)
    object addMonth extends MappedInt(this)
    object addGB extends MappedInt(this)
    object beAdmin extends MappedBoolean(this)
}

object Order extends Order with LongKeyedMetaMapper[Order] {
    override def dbTableName = "order_table"
}