

package crud

import java.sql.Connection
import java.io._
import java.sql.DriverManager

object ConnectObject {

  def Connect(): Connection = {
    val driver = "org.sqlite.JDBC"
    var connection: Connection = null

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection("jdbc:sqlite:dbCrud.sqlite3")
      return connection
    } catch {
      case ex: IOException => {
        return null
      }
    }
  }
}