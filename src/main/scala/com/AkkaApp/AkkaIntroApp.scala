package com.AkkaApp

import akka.actor.{Actor, ActorSystem, Props}


object AkkaIntroApp extends App {

  class PersonDetails(name: String, age: Int) extends Actor{

    override def receive: Receive = {
      case "name" => println(s"Hi, you are $name")
      case "age" => println(s"And your age is: $age")
    }
  }
  val system = ActorSystem("personIntroApp")
  object PersonDetails{
    def props(name: String, age: Int) = Props(new PersonDetails(name, age))
  }
  println("Enter your name: ")
  val name1 = scala.io.StdIn.readLine()
  println("Enter your age: ")
  val age2  = scala.io.StdIn.readInt()
  val person = system.actorOf(PersonDetails.props(name1, age2))
  person ! "name"
  person ! "age"

}
