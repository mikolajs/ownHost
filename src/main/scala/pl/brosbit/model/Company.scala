package pl.brosbit.model

import net.liftweb.mapper._

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Company extends LongKeyedMapper[Company] with IdPK {
  def getSingleton = Company

  object name extends MappedString(this, 45) {
    override def displayName = "Nazwa firmy"
  }
  object street extends MappedString(this, 45) {
    override def displayName = "Ulica"
  }
  object nr extends MappedString(this, 45){
    override def displayName = "Numer domu i mieszkania"
  }
  object code extends MappedString(this, 45) {
    override def displayName = "Kod pocztowy"
  }
  object city extends MappedString(this, 45){
    override def displayName = "Miasto"
  }
  object nip extends MappedString(this, 45) {
     def validateNameMinLength(nip : String) = {
      if (nip.length < 10) {
    	  List(FieldError(this, "Name too short, dude!"))
      } else {
        List[FieldError]()
      }
    }
    override def displayName = "NIP"
  }
  object phone extends MappedString(this, 45){
    override def displayName = "Telofon"
  }

}

object Company extends Company with LongKeyedMetaMapper[Company] {
  override def dbTableName = "company"
}
