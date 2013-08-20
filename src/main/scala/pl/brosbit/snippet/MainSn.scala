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
    	
    	"#serviceCont" #> services.map(service => {
    	    val offer = service.idOffer.obj.openOrThrowException("No offert in service!")
    	    "h4 *" #> offer.name.is & 
    	    "h4[id]" #> ("serviceId_" + service.id.is.toString) &
    	    ".serviceInfo *" #> (if (service.isActive.is) {<p> Pojemność: <span class="amountGB">{service.capacity.is.toString}</span> GB  <br/> 
    	    			Aktywna do: <span class="amountTime">{service.end.is.toString}</span></p>}
    	    									else <p>Usługa nieaktywna</p>) &
    	    ".serviceOrder *" #> ( if(service.newOrder.is) <p><span class="amountGBtoAdd">{service.addGB.is.toString}</span>
    	    																								<span class="amountTimetoAdd">{service.addMonth.is.toString}</span></p>
    	    										else <p><button onclick="changeService(this)">Przedłuż/Zmień usługę</button></p>)
    	})
    }
    
    def addService() = {
        var serviceId = ""
        var orderId = ""
        var serviceKind = ""
        var amountGB = ""
        var amountTime = ""
        val offers = OfferType.findAll.map(o => (o.id.is.toString, o.name.is))
        
         def saveOrder() = {
            // order 0  nie ma jeszcze zamówienia
            // service 0 nie istnieje jeszcze usługa
            //jak mam service to nie potrzebuje serviceKind
              Order.find(orderId) match {
        	        case  Full(order) => {
        	            
        	        } 
        	        case _ => {
        	            
        	        }
        	    }
            
  
        	Alert("Zapisano")
        }
        val form =  "#serviceId" #> SHtml.text(serviceId, serviceId = _) &
         "#orderId" #> SHtml.text(orderId,  orderId = _) &
        "#serviceKind" #> SHtml.select(offers, Full(serviceKind), serviceKind = _) & 
        "#amountGB" #> SHtml.text(amountGB, amountGB = _) &
        "#amountTime" #> SHtml.text(amountTime, amountTime = _) &
        "#saveOrder" #> SHtml.ajaxSubmit("Zapisz", saveOrder) andThen SHtml.makeFormsAjax
        
        "form" #> (in => form(in))
    }
    
    def offerTypesJSON() = {
        val offers = OfferType.findAll.map(  o => {
            "{'id':"  + o.id.is.toString + " , 'gb':" + o.unitGB.is.toString + ", 'time':" + o.unitMonth.is.toString + "}"   })
        
        "script" #> <script>var offerTypes = [ {offers.mkString(",")} ]</script>
    }
    
}

