package pl.brosbit.snippet

import java.lang.Math
import java.util.Locale
import scala.xml.{ NodeSeq, Text }
import net.liftweb.util._
import net.liftweb.common._
import net.liftweb.mapper._
import net.liftweb.http._
import java.util.Date
import Helpers._
import pl.brosbit.model._
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.JE.JsFunc

import net.liftweb.http.js.JsCmds.{ SetHtml, Alert, Run }

class AdminSn {

    def offerTypes() = {
        val offers = OfferType.findAll()
        "tr" #> offers.map(offer => {
            <tr id={"rowid_"+ offer.id.is.toString } onclick="insertToForm(this)"  >
                <td>{ offer.name.is }</td><td>{ offer.description.is }</td><td>{ offer.unitPrice.is.toString }</td>
                <td>{ offer.unitMonth.is.toString }</td><td>{ offer.unitGB.is.toString }</td>
            </tr>
        })
    }

    def offerTypeEdit() = {
    	var id = ""
        var name = ""
        var description = ""
        var unitPrice = ""
        var unitM = ""
        var unitGB = ""
            
         def saveOffer() =  {
            val offer = OfferType.find(id).openOr(OfferType.create)
            offer.name(name).description(description).unitPrice(tryo(unitPrice.toInt).openOr(99)).
            unitMonth(tryo(unitM.toInt).openOr(36)).unitGB(tryo(unitGB.toInt).openOr(1)).save
            JsFunc("savedOffer",  offer.id.toString).cmd
        }
        
        def deleteOffer() = {
            OfferType.find(id) match {
                case  Full(offer) => { if(offer.delete_! )  Alert("Usunięto ofertę %s [%s]".format(offer.name.is, offer.id.is.toString))
                										else Alert("Oferta %s[%s] jest używana nie można jej usunąć".format(offer.name.is, offer.id.is.toString)) 
                										}
                case _ =>  Alert("Nie znaleziono oferty!")
            }
        }
        
       var form =  "#idOffer" #> SHtml.text(id, id = _ ) &
        "#name" #> SHtml.text(name, name = _) &
            "#description" #> SHtml.textarea(description, description = _) &
            "#unitPrice" #> SHtml.text(unitPrice, unitPrice = _) &
            "#unitM" #> SHtml.text(unitM, unitM = _) &
            "#unitGB" #> SHtml.text(unitGB, unitGB = _) &
            "#saveOffer" #> SHtml.ajaxSubmit("Zapisz", saveOffer) &
            "#deleteOffer" #> SHtml.ajaxSubmit("Skasuj", deleteOffer) andThen SHtml.makeFormsAjax
            
            "form" #> (in =>  form(in) )

    }

}