package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._
import common._
import http._
import sitemap._
import Loc._
import mapper.{
    DB,
    By,
    ConnectionManager,
    ConnectionIdentifier,
    Schemifier,
    DefaultConnectionIdentifier
}
import java.sql.{ Connection, DriverManager }

import pl.brosbit.model._


object DBVendor extends ConnectionManager {
  def newConnection(name: ConnectionIdentifier): Box[Connection] = {
    try {
       Class.forName("org.h2.Driver")
      val dm = DriverManager.getConnection("jdbc:h2:ownhost")
      Full(dm)
    } catch {
      case e: Exception => e.printStackTrace; Empty
    
  }
  }
  def releaseConnection(conn: Connection) { conn.close }
}

class Boot {
    def boot {

        DB.defineConnectionManager(DefaultConnectionIdentifier, DBVendor)

        Schemifier.schemify(true, Schemifier.infoF _, User, Company, OfferType, Service, OrderDone)

        LiftRules.addToPackages("pl.brosbit")
        
        if (DB.runQuery("select * from klients where lastname = 'Administrator'")._2.isEmpty) {
      val u = User.create
      u.lastName("Administrator").superUser(true).password("123qwerty").email("mail@mail.org").validated(true).save
      u.lastName("Testowy").superUser(false).password("123qwerty").email("test@mail.org").validated(true).save
        }
        var loggedIn =  If(() => User.loggedIn_?, () => RedirectResponse("user_mgt/login"))
        var isSuperUser =  If(() => User.loggedIn_? && 
                User.currentUser.openOrThrowException("Not user").superUser.is
                , () => RedirectResponse("user_mgt/login"))		
        
        val entries = List(
            Menu("Główna") / "index" >> loggedIn,
            Menu("Administracja") / "admin",
            Menu("Użytkownik") / "user"
        ) :::  (User.sitemap)
            
        LiftRules.setSiteMap(SiteMap(entries: _*))
        
         LiftRules.statelessRewrite.prepend(NamedPF("ClassRewrite") {
      case RewriteRequest(
        ParsePath( "user" :: idUser :: Nil, _, _, _), _, _) =>
        RewriteResponse(
         "user" :: Nil, Map("id" -> idUser))
    })

        LiftRules.htmlProperties.default.set((r: Req) =>
            new Html5Properties(r.userAgent))

        LiftRules.ajaxStart =
            Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

        LiftRules.ajaxEnd =
            Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

        // Force the request to be UTF-8
        LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
        
        LiftRules.loggedInTest = Full(() => User.loggedIn_?)

    }
}
