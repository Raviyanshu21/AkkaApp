package com.AkkaApp

import akka.actor.{Actor, ActorSystem, Props}

object AkkaCapabilities extends App{
  class SimpleActor extends Actor{
    override def receive: Receive = {
      case message: String => println(s"[simple actor] I have received $message")
      case number: Int => println(s"[simple actor] I have received the number $number")
      case SpecialMessage(content) => println(s"[simple actor] this is the special message: $content")
    }
  }

  val system = ActorSystem("actorCapabilitiesDemo")
  val simpleActor = system.actorOf(Props[SimpleActor], "simpleActor")

  simpleActor ! "Hello, actor"

  // 1- Messages can be of any types
  simpleActor ! 42

  case class SpecialMessage(content: String)
  simpleActor ! SpecialMessage("This is a special message")


}
