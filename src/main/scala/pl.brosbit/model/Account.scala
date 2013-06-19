package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Account extends LongKeyedMapper[Account] with IdPK {
    def getSingleton = Account
    object name extends MappedString(this, 50)
    object latitude extends MappedDouble(this)
    object longitude extends MappedDouble(this)
}

object Account extends Account with LongKeyedMetaMapper[Account] {
    override def dbTableName = "Account"
}