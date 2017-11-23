
package crud

import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.sql.PreparedStatement
import java.util.ArrayList
import java.util.List
import java.io._
import java.util._

class Controller() {
  private var pp: PhysicalPerson = null
  private var conn: Connection = null

  def setPhysicalPerson(_pp: PhysicalPerson) {
    this.pp = _pp
  }

  def getPhysicalPerson(): PhysicalPerson = {
    return this.pp
  }

  private def Connect() {
    this.conn = ConnectObject.Connect()
  }

  private def GenerateID(): Int = {
    try {
      var id: Int = 0
      var stmt: Statement = this.conn.createStatement()
      var sql: String = "SELECT MAX(id) + 1 FROM PERSON"
      var result: ResultSet = stmt.executeQuery(sql)
      if (result.getInt(1) > 0) {
        id = result.getInt(1)
      } else {
        id = 1
      }

      stmt.close()
      return id
    } catch {

      case ex: IOException => {
        return -1
      }

    }
  }

  private def InsertPerson(id: Int, name: String, email: String): Int = {
    try {
      var sql: String = "INSERT INTO PERSON VALUES (?, ?, ?)"
      var stmt: PreparedStatement = this.conn.prepareStatement(sql)
      stmt.setInt(1, id)
      stmt.setString(2, name)
      stmt.setString(3, email)
      stmt.execute()
      stmt.close()
      return 1
    } catch {

      case ex: IOException => {
        return -1
      }
    }
  }

  private def EditPerson(id: Int, name: String, email: String): Int = {
    try {
      var sql: String = "UPDATE PERSON SET NAME = ?, EMAIL = ? WHERE ID = ?"
      var stmt: PreparedStatement = this.conn.prepareStatement(sql)
      stmt.setInt(3, id)
      stmt.setString(1, name)
      stmt.setString(2, email)
      stmt.execute()
      stmt.close()
      return 1
    } catch {

      case ex: IOException => {
        return -1
      }
    }
  }

  private def DeletePerson(id: Int): Int = {
    try {
      var sql: String = "DELETE FROM PERSON WHERE ID = ?"
      var stmt: PreparedStatement = this.conn.prepareStatement(sql)
      stmt.setInt(1, id)
      stmt.execute()
      stmt.close()
      return 1
    } catch {

      case ex: IOException => {
        return -1
      }
    }
  }

  private def InsertPhysicalPerson(): Int = {
    try {
      var sql: String = "INSERT INTO PHYSICALPERSON VALUES (?,?,?,?,?)"
      var stmt: PreparedStatement = this.conn.prepareStatement(sql)
      stmt.setInt(1, this.pp.getId())
      stmt.setInt(2, this.pp.getId())
      stmt.setFloat(3, this.pp.getSalary())
      stmt.setDate(4, this.pp.getBirthday())
      stmt.setString(5, this.pp.getGender().toString())
      stmt.execute()
      stmt.close()
      return 1
    } catch {
      case ex: IOException => {
        return -1
      }
    }
  }

  private def EditPhysicalPerson(): Int = {
    try {
      var sql: String = "UPDATE PHYSICALPERSON SET SALARY  = ?, BIRTHDAY = ?, GENDER = ? WHERE ID = ?"
      var stmt: PreparedStatement = this.conn.prepareStatement(sql)
      stmt.setInt(4, this.pp.getId())
      stmt.setFloat(1, this.pp.getSalary())
      stmt.setDate(2, this.pp.getBirthday())
      stmt.setString(3, this.pp.getGender().toString())
      stmt.execute()
      stmt.close()
      return 1
    } catch {

      case ex: IOException => {
        return -1
      }
    }
  }

  private def DeletePhysicalPerson(): Int = {
    try {

      var sql: String = "DELETE FROM PHYSICALPERSON WHERE ID  = ?"
      var stmt: PreparedStatement = this.conn.prepareStatement(sql)
      stmt.setInt(1, this.pp.getId())
      stmt.execute()
      stmt.close()
      return 1
    } catch {

      case ex: IOException => {
        return -1
      }
    }
  }

  def Insert_PhysicalPerson(): Int = {
    try {
      this.Connect()
      this.conn.setAutoCommit(false)
      var id: Int = this.GenerateID()
      if (id > 0) {
        this.pp.setId(id)
        if (this.InsertPerson(this.pp.getId(), this.pp.getName(), this.pp.getEmail()) == 1) {
          if (this.InsertPhysicalPerson() == 1) {
            this.conn.commit()
            this.conn.close()
            return 1
          } else {
            this.conn.rollback()
            this.conn.close()
            return -1
          }
        } else {
          this.conn.rollback()
          this.conn.close()
          return -1
        }
      } else {
        this.conn.close()
        return -1
      }
    } catch {

      case ex: IOException => {
        return -1
      }
    }
  }

  def Edit_PhysicalPerson(): Int = {
    try {
      this.Connect()
      this.conn.setAutoCommit(false)
      if (this.EditPerson(this.pp.getId(), this.pp.getName(), this.pp.getEmail()) == 1) {
        if (this.EditPhysicalPerson() == 1) {
          this.conn.commit()
          this.conn.close()
          return 1
        } else {
          this.conn.rollback()
          this.conn.close()
          return -1
        }
      } else {
        this.conn.rollback()
        this.conn.close()
        return -1
      }
    } catch {

      case ex: IOException => {
        return -1
      }
    }
  }

  def Delete_PhysicalPerson(): Int = {
    try {
      this.Connect()
      this.conn.setAutoCommit(false)
      if (this.DeletePhysicalPerson() == 1) {
        if (this.DeletePerson(this.pp.getId()) == 1) {
          this.conn.commit()
          this.conn.close()
          return 1
        } else {
          this.conn.rollback()
          this.conn.close()
          return -1
        }
      } else {
        this.conn.rollback()
        this.conn.close()
        return -1
      }

    } catch {

      case ex: IOException => {
        return -1
      }
    }
  }

  def Get_PhysicalPerson_By_ID(): PhysicalPerson = {
    try {
      this.Connect()
      var sql: String = "SELECT * FROM PERSON INNER JOIN PHYSICALPERSON ON PERSON.id = PHYSICALPERSON.PERSON_ID WHERE PERSON.ID = ?"
      var stmt: PreparedStatement = this.conn.prepareStatement(sql)
      stmt.setInt(1, this.pp.getId())
      var res: ResultSet = stmt.executeQuery()
      if (res.next()) {
        this.pp.setName(res.getString(2))
        this.pp.setEmail(res.getString(3))
        this.pp.setSalary(res.getFloat(6))
        this.pp.setBirthday(res.getDate(7))
        this.pp.setGender(res.getString(8))
        stmt.close()
        this.conn.close()
        res.close()
        return this.pp
      }
      stmt.close()
      this.conn.close()
      res.close()
      return null
    } catch {

      case ex: IOException => {
        return null
      }
    }
  }


  def Get_PhysicalPerson_By_Name(): List[PhysicalPerson] = {
    try {
      this.Connect()
      var sql: String = "SELECT * FROM PERSON INNER JOIN PHYSICALPERSON ON PERSON.id = PHYSICALPERSON.PERSON_ID WHERE PERSON.NAME LIKE ?"
      var stmt: PreparedStatement = this.conn.prepareStatement(sql)
      stmt.setString(1, this.pp.getName() + '%')
      var res: ResultSet = stmt.executeQuery()
      var list: List[PhysicalPerson] = new ArrayList[PhysicalPerson]()

      while (res.next()) {
        var _pp: PhysicalPerson = new PhysicalPerson()
        _pp.setId(res.getInt(1))
        _pp.setName(res.getString(2))
        _pp.setEmail(res.getString(3))
        _pp.setSalary(res.getFloat(6))
        _pp.setBirthday(res.getDate(7))
        _pp.setGender(res.getString(8))
        list.add(_pp)
        _pp = null
      }
      stmt.close()
      this.conn.close()
      return list
    } catch {
      case ex: IOException => {
        return null
      }
    }
  }
}
