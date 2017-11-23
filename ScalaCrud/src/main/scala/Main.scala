package main.scala

import java.io._
import java.sql.Date
import java.util.List
import scala.collection.JavaConverters._
import crud._

object Main {
  def PrintMessage(result: Int, method: Int) {
    if (method == 1) {
      if (result == 1) {
        println("Registered successfully.")
      } else {
        println("Error registering record.")
      }
    }

    if (method == 2) {
      if (result == 1) {
        println("Edited successfully.")
      } else if (result == 0) {
        println("No record was found with this id.")
      } else {
        println("Error editing record.")
      }
    }

    if (method == 3) {
      if (result == 1) {
        println("Deleted successfully.")
      } else {
        println("Error deleting record.")
      }
    }

    if (method == 4) {
      if (result == 0) {
        println("No record was found with this name.")
      } else {
        println("Error editing record.")
      }
    }

    if (method == 5) {
      if (result == 0) {
        println("No record was found with this id.")
      } else {
        println("Error editing record.")
      }
    }
  }

  def Insert_PhysicalPerson() {
    try {
      println("\n\nINSERT PHYSICALPERSON ")
      print("\nName: ")
      val name = scala.io.StdIn.readLine()
      print("Email: ")
      val email = scala.io.StdIn.readLine()
      print("Salary '0.0': ")
      val salary = scala.io.StdIn.readFloat()
      print("Birthday ('YYYY-MM-DD'): ")
      val birthday = scala.io.StdIn.readLine()
      print("Gender: 'M' or 'F': ")
      val gender = scala.io.StdIn.readLine()
      var result: Int = Crud.Insert_PhysicalPerson(name.toString(), email.toString(), salary, Date.valueOf(birthday.toString()), gender.toString())
      PrintMessage(result, 1)
    } catch {
      case ex:
        IOException => {
        PrintMessage(-1, 1)
      }
    }
  }

  def Edit_PhysicalPerson() {
    try {
      println("\n\nUPDATE PHYSICALPERSON ")
      print("\nID: ")
      val id = scala.io.StdIn.readInt()
      var pp: crud.PhysicalPerson = Crud.Get_PhysicalPerson_By_ID(id)

      if (pp != null) {
        println("--------------------------------")
        println("Selected record.")
        println("--------------------------------")
        println("\nID = " + pp.getId())
        println("NAME = " + pp.getName())
        println("EMAIL = " + pp.getEmail())
        println("SALARY = " + pp.getSalary())
        println("BIRTHDAY = " + pp.getBirthday())
        println("GENDER = " + pp.getGender())
        println("--------------------------------\n")

        println("\nEnter the new data for this record:")
        print("\nName: ")
        val name = scala.io.StdIn.readLine()
        print("Email: ")
        val email = scala.io.StdIn.readLine()
        print("Salary '0.0': ")
        val salary = scala.io.StdIn.readFloat()
        print("Birthday ('YYYY-MM-DD'): ")
        val birthday = scala.io.StdIn.readLine()
        print("Gender: 'M' or 'F': ")
        val gender = scala.io.StdIn.readLine()
        var result: Int = Crud.Edit_PhysicalPerson(id, name.toString(), email.toString(), salary, Date.valueOf(birthday.toString()), gender.toString())
        PrintMessage(result, 2)
      } else {
        PrintMessage(0, 2)
      }
    } catch {
      case ex:
        IOException => {
        PrintMessage(-1, 2)
      }
    }
  }


  def Delete_PhysicalPerson() {
    try {
      println("\n\nDELETE PHYSICALPERSON: ")
      print("\nID: ")
      val id = scala.io.StdIn.readInt()
      var result: Int = Crud.Delete_PhysicalPerson(id)
      PrintMessage(result, 3)
    } catch {
      case ex:
        IOException => {
        PrintMessage(-1, 3)
      }
    }
  }

  def Get_PhysicalPerson_By_Name() {
    try {
      println("\n\nGET PHYSICALPERSON BY NAME: ")
      print("\nNAME: ")
      val name = scala.io.StdIn.readLine()
      var list: List[crud.PhysicalPerson] = Crud.Get_PhysicalPerson_By_Name(name)

      if (list != null) {
        println("--------------------------------")
        println("PHYSICAL PERSON'S DATAS:        |")
        println("--------------------------------\n")
        for (pp <- list.asScala) {
          println("\nID = " + pp.getId())
          println("NAME = " + pp.getName())
          println("EMAIL = " + pp.getEmail())
          println("SALARY = " + pp.getSalary())
          println("BIRTHDAY = " + pp.getBirthday())
          println("GENDER = " + pp.getGender())
          println("--------------------------------\n")
        }
      } else {
        PrintMessage(0, 4)
      }

    } catch {
      case ex:
        IOException => {
        PrintMessage(-1, 4)
      }
    }
  }

  def Get_PhysicalPerson_By_ID() {
    try {
      println("\nGET PHYSICALPERSON BY ID: ")
      print("\nID: ")
      val id = scala.io.StdIn.readInt()
      var pp: crud.PhysicalPerson = Crud.Get_PhysicalPerson_By_ID(id)
      if (pp != null) {
        println("--------------------------------")
        println("PHYSICAL PERSON'S DATAS:        |")
        println("--------------------------------\n")
        println("\nID = " + pp.getId())
        println("NAME = " + pp.getName())
        println("EMAIL = " + pp.getEmail())
        println("SALARY = " + pp.getSalary())
        println("BIRTHDAY = " + pp.getBirthday())
        println("GENDER = " + pp.getGender())
        println("--------------------------------\n")
      } else {
        PrintMessage(0, 5)
      }
    } catch {
      case ex:
        IOException => {
        PrintMessage(-1, 5)
      }
    }
  }


  def SetOptionCrud(): Int = {
    println("--------------------------------- ")
    println("| CRUD:                          |")
    println("---------------------------------| \n")
    println("| 1 - INSERT PHYSICAL-PERSON     |")
    println("| 2 - EDIT PHYSICAL-PERSON       |")
    println("| 3 - DELETE PHYSICAL-PERSON     |")
    println("| 4 - GET PHYSICAL-PERSON BY ID  |")
    println("| 5 - GET PHYSICAL-PERSON BY NAME|")
    println("| 0 - FINISH                     |")
    println("---------------------------------|\n")
    print("\nEnter option: ")
    val opc = scala.io.StdIn.readInt()
    return opc
  }

  def main(args: Array[String]) {
    var opc: Int = SetOptionCrud()
    while (opc != 0) {
      opc match {
        case 1 => Insert_PhysicalPerson()
        case 2 => Edit_PhysicalPerson()
        case 3 => Delete_PhysicalPerson()
        case 4 => Get_PhysicalPerson_By_ID()
        case 5 => Get_PhysicalPerson_By_Name()
      }
      opc = SetOptionCrud()
    }
    println("FINISH!")
    System.exit(0)
  }
}