
package pl.brosbit.model

import _root_.net.liftweb.http.S
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

object Klient extends Klient with MetaMegaProtoUser[Klient] {
    override def dbTableName = "Klients" // define the DB table name

    override def fieldOrder = List(id, firstName, lastName, email,
        locale, timezone, password, role)
    override def screenWrap = Full(<lift:surround with="login" at="content">
                                       <div class="loginForm">
                                           <lift:bind/>
                                       </div>
                                   </lift:surround>)

    override def skipEmailValidation = false

    override def createKlientMenuLoc = Empty
    override def editKlientMenuLoc = Empty

}

class Klient extends MegaProtoUser[Klient] {
    def getSingleton = Klient // what's the "meta" server

    object role extends MappedString(this, 1)

    object telefon extends MappedString(this, 45)
    object ulica extends MappedString(this, 45)
    object miasto extends MappedString(this, 45)
    object nr extends MappedString(this, 45)
    object kod extends MappedString(this, 45)
    object id_firma extends MappedLongForeignKey(this, Firma)

    def getFullName = firstName.is + " " + lastName.is

}

