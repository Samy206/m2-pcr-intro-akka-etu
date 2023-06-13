package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import m2dl.pcr.akka.helloworld3.System;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemString {

    public static final Logger log = LoggerFactory.getLogger(SystemString.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef actorRefReceiverA = actorSystem.actorOf(Props.create(Receiver.class), "receiver-actor-A");
        final ActorRef actorRefReceiverB = actorSystem.actorOf(Props.create(Receiver.class), "receiver-actor-B");
        final ActorRef actorRefReceiverC = actorSystem.actorOf(Props.create(Receiver.class), "receiver-actor-C");
        final ActorRef actorRefCryptageProvider = actorSystem.actorOf(Props.create(CryptageProvider.class), "Cryptage-actor");
        final ActorRef actorRefErreurProvider = actorSystem.actorOf(Props.create(ErreurControleProvider.class), "Erreur-Provider-actor");
        final ActorRef actorRefIntermediaire = actorSystem.actorOf(Props.create(IntermedaireActor.class, actorRefErreurProvider, actorRefReceiverC), "intermediaire-actor");
        /*Message messageA = new Message(actorRefReceiverA, "Nul-A");
        actorRefCryptageProvider.tell(messageA, null);

        Message messageB = new Message(actorRefReceiverB, "Nul-B");
        actorRefErreurProvider.tell(messageB, null);*/

        Message messageC = new Message(actorRefIntermediaire, "Nul-C");
        actorRefCryptageProvider.tell(messageC, null);


        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}
