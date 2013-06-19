package pl.brosbit.model

import net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

class Firma extends LongKeyedMapper[Firma] with IdPK {
  def getSingleton = Firma

  object nazwa extends MappedString(this, 45)
  object ulica extends MappedString(this, 45)
  object nr extends MappedString(this, 45)
  object kod extends MappedString(this, 45)
  object miasto extends MappedString(this, 45)
  object nip extends MappedString(this, 45)
  object telefon extends MappedString(this, 45)

}

object Firma extends Firma with LongKeyedMetaMapper[Firma] {
  override def dbTableName = "Firma"
}
