package crud

import java.util._
import java.sql.Date
import java.util.List

object Crud {

  def Insert_PhysicalPerson(name: String, email: String, salary: Float, birthday: Date, gender: String): Int = {

    var pp: PhysicalPerson = new PhysicalPerson()
    pp.setName(name)
    pp.setEmail(email)
    pp.setSalary(salary)
    pp.setBirthday(birthday)
    pp.setGender(gender)
    var ctr: Controller = new Controller()
    ctr.setPhysicalPerson(pp)
    return ctr.Insert_PhysicalPerson()
  }

  def Edit_PhysicalPerson(id: Int, name: String, email: String, salary: Float, birthday: Date, gender: String): Int = {
    var pp: PhysicalPerson = new PhysicalPerson()
    pp.setId(id)
    pp.setName(name)
    pp.setEmail(email)
    pp.setSalary(salary)
    pp.setBirthday(birthday)
    pp.setGender(gender)
    var ctr: Controller = new Controller()
    ctr.setPhysicalPerson(pp)
    return ctr.Edit_PhysicalPerson()
  }

  def Delete_PhysicalPerson(id: Int): Int = {
    var pp: PhysicalPerson = new PhysicalPerson()
    pp.setId(id)
    var ctr: Controller = new Controller()
    ctr.setPhysicalPerson(pp)
    return ctr.Delete_PhysicalPerson()
  }

  def Get_PhysicalPerson_By_ID(id: Int): PhysicalPerson = {
    var pp: PhysicalPerson = new PhysicalPerson()
    pp.setId(id)
    var ctr: Controller = new Controller()
    ctr.setPhysicalPerson(pp)
    var pp_r: PhysicalPerson = ctr.Get_PhysicalPerson_By_ID()
    pp = null
    ctr = null
    return pp_r
  }

  def Get_PhysicalPerson_By_Name(name: String): List[PhysicalPerson] = {
    var pp: PhysicalPerson = new PhysicalPerson()
    pp.setName(name)
    var ctr: Controller = new Controller()
    ctr.setPhysicalPerson(pp)
    var list: List[PhysicalPerson] = ctr.Get_PhysicalPerson_By_Name()
    pp = null
    ctr = null
    return list
  }
}