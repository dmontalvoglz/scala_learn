package com.hello.crud

import java.sql.{Connection, DriverManager, Statement, ResultSet}
import scala.io.StdIn.{readLine, readInt}

object Main extends App {
  Class.forName("org.sqlite.JDBC")

  val connection: Connection = DriverManager.getConnection("jdbc:sqlite:sample.db")
  val statement: Statement = connection.createStatement(
    ResultSet.TYPE_FORWARD_ONLY,
    ResultSet.CONCUR_READ_ONLY)

  // Create a new table named "people"
  statement.executeUpdate("CREATE TABLE IF NOT EXISTS animes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, genre TEXT NOT NULL)")

  def showList(): Unit = {
    println("Here is your favorite anime list:")
    val resultSet: ResultSet = statement.executeQuery("SELECT * FROM animes")

    println("ID || Title || Genre")
    while (resultSet.next()) {
      val id = resultSet.getInt("id")
      val title = resultSet.getString("title")
      val genre = resultSet.getString("genre")

      println(s"$id || $title || $genre")
    }
  }

  def insertList(): Unit = {
    println("Please enter the title of the anime: ")
    val title = readLine()
    println("Please enter the genre of the anime: ")
    val genre = readLine()
    statement.executeUpdate(s"INSERT INTO animes (title, genre) VALUES ('$title', '$genre')")
    println("Your database has been updated succesfully.")
  }

  def deleteFromList(): Unit = {
    println("Which anime would you like to delete? (ID)")
    val id = readInt()
    val resultSet: ResultSet = statement.executeQuery(s"SELECT * FROM animes WHERE id = '$id'")
    val okMessage = s"You have succesfully deleted ${resultSet.getString("title")} from the DB"
    statement.executeUpdate(s"DELETE FROM animes WHERE id = $id")
    println(okMessage)
  }

  var quit: Boolean = false
  while (quit != true) {
    println("""Favorite anime list DB
      
                Option1: See the list.
                Option2: Add entry to the list.
                Option3: Remove entry from the list.
                Option4: Quit""")

    var option = readInt()

    option match {
      case 1 => showList()
      case 2 => insertList()
      case 3 => deleteFromList()
      case _ => quit = true
    }
  }

  connection.close()
}