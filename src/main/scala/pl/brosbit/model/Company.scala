package pl.brosbit.model

import scala.xml._
import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._
import net.liftweb.http.SHtml.ElemAttr

class Company extends LongKeyedMapper[Company] with IdPK {
    def getSingleton = Company

    object name extends MappedString(this, 45) {
        override def displayName = "Nazwa firmy"
        override def fieldId = Some(<span>nameComp</span>)
    }
    object street extends MappedString(this, 45) {
        override def displayName = "Ulica"
        override def fieldId = Some(<span>streetComp</span>)
    }
    object nr extends MappedString(this, 45) {
        override def displayName = "Numer domu i mieszkania"
        override def fieldId = Some(<span>nrComp</span>)
    }
    object code extends MappedString(this, 45) {
        override def displayName = "Kod pocztowy"
        override def fieldId = Some(<span>codeComp</span>)
    }
    object city extends MappedString(this, 45) {
        override def displayName = "Miasto"
        override def fieldId = Some(<span>cityComp</span>)
    }
    object nip extends MappedString(this, 45) {

        override def validations = Validations.validateNameMinLength(this) _ :: Nil
        override def displayName = "NIP"
        override def fieldId = Some(<span>NIPComp</span>)
    }
    object phone extends MappedString(this, 45) {
        override def displayName = "Telefon"
        override def fieldId = Some(<span>phoneComp</span>)
    }

}

object Company extends Company with LongKeyedMetaMapper[Company] {
    override def dbTableName = "company"
        
   //change view of form
    override def toForm(toMap: Company): NodeSeq =
        toMap.allFields.map(f => f.asInstanceOf[MappedField[AnyBound, Company]]).
            filter(f => { f.show_? }).
            flatMap(
                field => field.toForm.toList.
                    flatMap(form => <p><label>{ field.displayName }</label>{ form } </p>))

}
