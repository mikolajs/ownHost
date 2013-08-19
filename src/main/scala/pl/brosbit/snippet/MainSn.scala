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

import net.liftweb.http.js.JsCmds.{ SetHtml, Alert, Run }

class MainSn {

    val user = User.currentUser.openOrThrowException("No user")

    def userData() = {

        val user = User.currentUser.openOrThrowException("No user")
        var firstName = user.firstName.is
        var lastName = user.lastName.is
        var phone = user.phone.is
        var street = user.street.is
        var city = user.city.is
        var houseNr = user.houseNr.is
        var zipCode = user.zipCode.is
        var email = user.email.is

        def save() = {
            user.firstName(firstName.trim).lastName(lastName.trim).phone(phone.trim).
                street(street.trim).city(city.trim).zipCode(zipCode.trim).email(email.trim).
                houseNr(houseNr.trim).save
            Run("")
        }

        val form = "#firstName" #> SHtml.text(firstName, firstName = _) &
            "#lastName" #> SHtml.text(lastName, lastName = _) &
            "#phone" #> SHtml.text(phone, phone = _) &
            "#street" #> SHtml.text(street, street = _) &
            "#city" #> SHtml.text(city, city = _) &
            "#house" #> SHtml.text(houseNr, houseNr = _) &
            "#zipCode" #> SHtml.text(zipCode, zipCode = _) &
            "#email" #> SHtml.text(email, email = _) &
            "#saveUser" #> SHtml.ajaxSubmit("Zapisz", save) andThen SHtml.makeFormsAjax

        "form" #> (in => form(in))

    }

    def companyData() = {
        val company = Company.find(user.idCompany).openOr(Company.create)

        def save(com: Company): Any = {
            com.validate match {
                case Nil => S.notice("Person is valid")
                case errors: List[FieldError] => {
                    println("EROR VALIDATE "); S.error(errors) // S.error will handle this properly

                }
            }
            com.save
            user.idCompany(com.id.get).save
        }

        val f = "input" #> company.toForm(Full("Dodaj"), save _) andThen SHtml.makeFormsAjax
        "form" #> (in => f(in))
    }

    def servicesData() = {
    	val services = Service.findAll(By(Service.idUser, user.id.is))
    	"#services" #> services.map(service => {
    	    val offer = service.idOffer.obj.open_!
    	    "li" #> <li>{"%s  - pojemność %d GB - termin wygaśnięcia:  ".format(offer.name.is, service.capacity.is,  service.getExpiredTime) }
    	    	<span id={"serviceid_"+service.id.is.toString}>Zmiany</span></li>   	    
    	})
    }

}

