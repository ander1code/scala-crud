package crud

import java.sql.Date

class PhysicalPerson extends Person {
  private
  var id: Int = 0
  private
  var name: String = null
  private
  var email: String = null
  private
  var salary: Float = 0.0f
  private
  var birthday: Date = null
  private
  var gender: String = null

  def setId(value: Int) {
    this.id = value
  }

  def getId(): Int = {
    return this.id
  }

  def setName(value: String) {
    this.name = value
  }

  def getName(): String = {
    return this.name
  }

  def setEmail(value: String) {
    this.email = value
  }

  def getEmail(): String = {
    return this.email
  }

  def setSalary(value: Float) {
    this.salary = value
  }

  def getSalary(): Float = {
    return this.salary
  }

  def setBirthday(value: Date) {
    this.birthday = value
  }

  def getBirthday(): Date = {
    return this.birthday
  }

  def setGender(value: String) {
    this.gender = value
  }

  def getGender(): String = {
    return this.gender
  }
}