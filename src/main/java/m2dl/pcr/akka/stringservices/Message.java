package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.io.Serializable;

public class Message implements Serializable {

    private ActorRef actor;
    private String message;

    public Message(ActorRef untypedActor, String message) {
        this.actor = untypedActor;
        this.message = message;
    }

    public ActorRef getActor() {
        return actor;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(java.lang.String message) {
        this.message = message;
    }

    public void setActor(ActorRef actor) {
        this.actor = actor;
    }
}
