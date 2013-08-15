
package pl.brosbit.model

import _root_.net.liftweb.http.S
import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

object User extends User  with MetaMegaProtoUser[User] {
    override def dbTableName = "klients" // define the DB table name

    override def fieldOrder = List(id, firstName, lastName, email,
        locale, timezone, password, role)
    override def screenWrap = Full(<lift:surround with="login" at="content">
                                       <div class="loginForm">
                                           <lift:bind/>
                                       </div>
                                   </lift:surround>)

    override def skipEmailValidation = false

    override def createUserMenuLoc = Empty
    override def editUserMenuLoc = Empty

}

class User extends MegaProtoUser[User] {
    def getSingleton = User

    object role extends MappedString(this, 1)

    object phone extends MappedString(this, 45)
    object street extends MappedString(this, 45)
    object city extends MappedString(this, 45)
    object houseNr extends MappedString(this, 45)
    object  zipCode extends MappedString(this, 45)
    object idCompany extends MappedLongForeignKey(this, Company)

    def getFullName = firstName.is + " " + lastName.is

}

