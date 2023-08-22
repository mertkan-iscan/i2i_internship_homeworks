package org.example;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Configuring remote access and port
        Config configActor1 = ConfigFactory.load("actor1.conf");
        Config configActor2 = ConfigFactory.load("actor2.conf");

        // Create the actor system
        ActorSystem systemActor1 = ActorSystem.create("SenderSystem", configActor1);
        ActorSystem systemActor2 = ActorSystem.create("ReceiverSystem", configActor2);

        // Create the receiver actor first
        ActorRef receiver = systemActor2.actorOf(Props.create(Actor2.class), "receiver");

        // Create the sender actor
        ActorRef sender = systemActor1.actorOf(Props.create(Actor1.class), "sender");

        sender.tell("Hi from Actor1", ActorRef.noSender());

    }
}