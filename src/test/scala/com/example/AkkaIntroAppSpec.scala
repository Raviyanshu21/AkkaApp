package com.example

import akka.actor.{Actor, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import com.AkkaApp.AkkaIntroApp.{PersonDetails, age2, name1}
import org.scalatest.{BeforeAndAfterAll, WordSpecLike}


class AkkaIntroAppSpec extends TestKit(ActorSystem("BasicSpec"))
  with ImplicitSender
  with WordSpecLike
  with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "Akka intro app" should {
    "send back the same message" in {
      val echoActor = system.actorOf(PersonDetails.props("Ravi", 21))
      val message1 = s"Hi, you are $name1"
      val message2 =  s"And your age is: $age2"
      echoActor ! message1
      echoActor ! message2

    }
  }


}
