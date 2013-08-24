package pl.brosbit.snippet

import java.lang.Math
import java.util.Locale
import scala.xml.{ NodeSeq, Text, Unparsed }
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


class UsersSn {
    
    var userId = S.param("id").openOr("0")
     val user = User.find(userId).openOr(User.create)
     def userData() = {
        "tr" #> user.toHtml
    }
    
    def companyData() = {
            val company = user.idCompany.obj.getOrElse(Company.create)
  
             "tr" #> company.toHtml
    }

     def sendMassage() = {
        var message = ""
        
         def saveMessage() = {
            Alert("Funkcjonalność niezaimplementowana")
        }
        
        val form = "#messageContent" #> SHtml.textarea(message, message = _) &
        "#saveMessage" #> SHtml.ajaxSubmit("Wyślij", saveMessage) andThen SHtml.makeFormsAjax
        
        "#messageForm" #> (in => form(in))
    }
     
     def servicesList() = {
         val services = Service.findAll(By(Service.idUser, user.id.is))
          services.map(s => "tr" #> s.toHtml)
     }

}