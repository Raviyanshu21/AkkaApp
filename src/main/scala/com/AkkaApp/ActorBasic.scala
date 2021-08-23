package com.AkkaApp

import akka.actor.{Actor, ActorSystem, Props}

object  ActorBasic extends App {
  //Every AKka app has to start with an actor system

  val actorSystem = ActorSystem("firstActorSystem")
  println(actorSystem.name)

  class WordCountActor extends Actor {
    // internal data
    var totalWords = 0

    def receive: Receive = {                          //PartialFunction[Any, Unit] == Receive
          //behaviour
      case message: String =>
        println((s"[word counter] I have received: $message"))
        totalWords += message.split(" ").length
      case msg => println(s"I cannot understand ${msg.toString}")
    }
  }

  //instantiating our actor:- by invoking the actor system
  val wordCounter = actorSystem.actorOf(Props[WordCountActor], "wordCounter")
  val anotherWordCounter = actorSystem.actorOf(Props[WordCountActor], "anotherWordCounter")

  //part-4 Communicating!
  wordCounter ! "I am learning Akka and its pretty damn cool!" //infix notation
  anotherWordCounter ! "A different message"            //from the output you can see message are send asynchronously

  class Person(name: String) extends Actor{
    override def receive: Receive = {
      case "hi" => println(s"Hi my name is $name")
      case _ =>
    }
  }
  object Person{
    def props(name: String) = Props(new Person(name))
  }
  val person = actorSystem.actorOf(Person.props("kate"))
  person ! "hi"
}
