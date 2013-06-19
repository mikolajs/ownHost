
package pl.brosbit.model

    import _root_.net.liftweb.http.S
    import _root_.net.liftweb.mapper._
    import _root_.net.liftweb.util._
    import _root_.net.liftweb.common._
    import Mailer._

   
    object User extends User with MetaMegaProtoUser[User] {
      override def dbTableName = "users" // define the DB table name

      override def fieldOrder = List(id, firstName, lastName, email,
        locale, timezone, password, role)
      override def screenWrap = Full(<lift:surround with="login" at="content">
    		  							<div class="loginForm"><lift:bind/>
    		  							</div>
                                     </lift:surround>)
     
      override def skipEmailValidation = false
      
       override def createUserMenuLoc = Empty
       override def editUserMenuLoc = Empty
       
     
     // override def afterValidation  = List( (user) => { S.redirectTo("/user_mgt/change_password")})
     
    }


    class User extends MegaProtoUser[User] {
      def getSingleton = User // what's the "meta" server

      object role extends MappedString(this, 1)
      object scratched extends MappedBoolean(this){
         override def defaultValue = false
      }
      object phone extends MappedString(this, 12)
      object address extends MappedText(this)
      object pesel extends MappedString(this, 11)

      def getFullName = firstName.is + " " + lastName.is
      def getFullNameReverse = lastName.is + " " + firstName.is
      def shortInfo = getFullName + " [" + id.is.toString + "]"

    }

