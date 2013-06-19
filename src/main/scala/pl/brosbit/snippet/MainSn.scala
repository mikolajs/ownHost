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
	
    val user = Klient.currentUser.openOrThrowException("No user")
    
    def userData() = {

        val user = Klient.currentUser.openOrThrowException("No user")
        var firstName = user.firstName.is
        var lastName = user.lastName.is
        var telefon = user.telefon.is
        var ulica = user.ulica.is
        var miasto = user.miasto.is
        var kod	= user.kod.is
        var email = user.email.is
            
        def save() = {
            user.firstName(firstName.trim).lastName(lastName.trim).telefon(telefon.trim).
            ulica(ulica.trim).miasto(miasto.trim).kod(kod.trim).email(email.trim).save
            Run("")
        }
        
        val form = "#firstName" #> SHtml.text(firstName, firstName = _) &
        "#lastName" #> SHtml.text(lastName, lastName = _) &
        "#telefon" #> SHtml.text(telefon, telefon = _) &
        "#ulica" #> SHtml.text(ulica, ulica = _) &
        "#miasto" #> SHtml.text(miasto, miasto = _) &
        "#kod" #> SHtml.text(kod, kod = _) &
        "#mail" #> SHtml.text(email, email = _) &
        "#saveUser" #> SHtml.ajaxSubmit("Zapisz", save) andThen SHtml.makeFormsAjax
        
        "form" #> (in => form(in))

    }
    
    def test() = {
        
        def save(n:Klient):Any = {
            n.save
        }      
        
        val a = S.param("a").openOr("0")
        val account = Klient.find(a).openOr(Klient.create)
        val f = "input" #> account.toForm(Full("Dodaj"),save _) andThen SHtml.makeFormsAjax
        "form" #> (in => f(in))
    }
    
    def firma() = {
        val idFirma = user.id_firma.is
        if(idFirma != null && idFirma > 0) {
            
        }
    }
    
    def oferta() = {
        
    }
    
    def okres() = {
        
    }
    
    def uslugi() = {
        
    }

}

