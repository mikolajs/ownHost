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

import net.liftweb.http.js.JsCmds.{ SetHtml, Alert, Run}

class MainSn {

    def userData() = {

        val user = User.currentUser.open_!
        var firstName = user.firstName.is
        var lastName = user.lastName.is
        var phone = user.phone.is
            
        def save() = {
            user.firstName(firstName.trim).lastName(lastName.trim).phone(phone.trim).save
            Run("")
        }
        
        val form = "#firstName" #> SHtml.text(firstName, firstName = _) &
        "#lastName" #> SHtml.text(lastName, lastName = _) &
        "#phone" #> SHtml.text(phone, phone = _) &
        "#saveUser" #> SHtml.ajaxSubmit("Zapisz", save) andThen SHtml.makeFormsAjax
        
        "form" #> (in => form(in))

    }
    
    def test() = {
        
        def save(n:Account):Any = {
            n.save
        }      
        
        val a = S.param("a").openOr("0")
        val account = Account.find(a).openOr(Account.create)
        val f = "input" #> account.toForm(Full("Dodaj"),save _) andThen SHtml.makeFormsAjax
        "form" #> (in => f(in))
    }

}

