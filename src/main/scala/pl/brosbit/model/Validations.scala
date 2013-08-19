package pl.brosbit.model

import net.liftweb.mapper._
import net.liftweb.util._

object Validations {
def onlyInActionBooks(field: FieldIdentifier) (string : String) =
if(!string.toLowerCase.endsWith("in action")) List(FieldError(field, "Not an in action book?"))
else List[FieldError]()


def validateNameMinLength(field: FieldIdentifier)(nip : String) = {
      if (nip.length < 10) {
    	  List(FieldError(field, "Nazwa musi mieć co najmniej 10 liter!"))
      } else {
        List[FieldError]()
      }
}

}

