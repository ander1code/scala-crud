

package crud

trait Person {
  def setId(value: Int)

  def getId(): Int

  def setName(value: String)

  def getName(): String

  def setEmail(value: String)

  def getEmail(): String
}