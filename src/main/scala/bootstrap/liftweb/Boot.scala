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
            Class.forName("org.postgresql.Driver")
            val dm = DriverManager.getConnection("jdbc:postgresql:ownhost", "ownhost", "ownhost456")
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

        Schemifier.schemify(true, Schemifier.infoF _, Klient, Okres, Firma, Oferta, Usluga)

        LiftRules.addToPackages("pl.brosbit")
        
        if (DB.runQuery("select * from users where lastname = 'Administrator'")._2.isEmpty) {
      val u = Klient.create
      u.lastName("Administrator").superUser(true).password("123qwerty").email("mail@mail.org").validated(true).save
      u.lastName("Testowy").superUser(false).password("123qwerty").email("test@mail.org").validated(true).save
        }
        var loggedIn =  If(() => Klient.loggedIn_?, () => RedirectResponse("user_mgt/login"))
        var isSuperUser =  If(() => Klient.loggedIn_? && 
                Klient.currentUser.openOrThrowException("Not user").superUser.is
                , () => RedirectResponse("user_mgt/login"))		
        
        val entries = List(
            Menu("Główna") / "index" >> loggedIn,
            Menu("Główna") / "adm" / "admin" >> isSuperUser,
            Menu("Główna") / "adm" / "company" >> isSuperUser,
            Menu("Główna") / "adm" / "offer" >> isSuperUser,
            Menu("Główna") / "adm" / "client" >> isSuperUser,
            Menu("Główna") / "adm" / "service" >> isSuperUser
        ) :::  (Klient.sitemap)
            
        LiftRules.setSiteMap(SiteMap(entries: _*))

        LiftRules.htmlProperties.default.set((r: Req) =>
            new Html5Properties(r.userAgent))

        LiftRules.ajaxStart =
            Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

        LiftRules.ajaxEnd =
            Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

        // Force the request to be UTF-8
        LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
        
        LiftRules.loggedInTest = Full(() => Klient.loggedIn_?)

    }
}
